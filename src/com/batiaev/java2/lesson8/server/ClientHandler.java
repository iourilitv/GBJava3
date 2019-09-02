package com.batiaev.java2.lesson8.server;

import com.batiaev.java2.lesson8.Command;

import java.io.Closeable;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * ClientHandler
 * Net Chat App of Java2
 * @author Anton Batiaev
 * @since 06/11/17
 */
//TODO use ExecutorService.Deleted
//public class ClientHandler extends Thread implements Closeable {
//TODO use ExecutorService.Added
public class ClientHandler implements Closeable {
    private MyServer server;
    private Socket socket;
    private DataOutputStream out;
    private DataInputStream in;
    private String name = "unknown";
    private boolean isAuth = false;
    private LocalDateTime connectTime = LocalDateTime.now();

    int c = 1;//TODO временно

    //TODO use ExecutorService.Added
    private boolean isBusy = true;

    public ClientHandler(MyServer server, Socket socket) {
        this.server = server;
        try {
            this.socket = socket;
            out = new DataOutputStream(socket.getOutputStream());
            in = new DataInputStream(socket.getInputStream());
        } catch (IOException e) {
            System.out.println("Client handler initialization failed: " + e.getLocalizedMessage());
        }
    }

    //TODO use ExecutorService.Deleted
    /*//@Override
    public void run() {
        try {
            while (socket.isConnected() && !socket.isClosed()) {
                String msg = in.readUTF();
                if (msg.startsWith(Command.AUTH_COMMAND.getText())) {
                    userAuth(msg);
                } else if (isAuth) {
                    if (msg.startsWith("/")) {
                        if (msg.startsWith(Command.PRIVATE_MESSAGE.getText() + " ")) {
                            sendPrivateMessage(msg);
                        } else if (msg.startsWith(Command.CHAT_MESSAGE.getText() + " ")) {
                            sendChatMessage(msg);
                        }
                    } else {
                        sendBroadcastMessage(name + " написал: " + msg);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Client disconnected");
    }*/
    //TODO use ExecutorService.Added
    //цикл авторизации клиента
    public synchronized void runAuth() {
        try {

                //TODO временно
                //System.out.println("1.ClientHandler.runAuth.Client " + getHandlerName() + " Thread: " + Thread.currentThread().getName());

                while (socket.isConnected() && !socket.isClosed()) {
                    String msg = in.readUTF();
                    if (msg.startsWith(Command.AUTH_COMMAND.getText())) {
                        userAuth(msg);
                        break;
                    }
                }
            } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //TODO use ExecutorService.Added
    public synchronized void runRead() {//TODO CHECK synchronized
        try {

            //TODO временно
            //System.out.println("1.ClientHandler.runRead().Client " + getHandlerName() + " Thread: " + Thread.currentThread().getName() + " isBusy: " + isBusy);

            //отправляем своему клиенту запрос на считываение буфера сообщений
            sendMessage(Command.READ_BUFFER_CALL.getText() + c++);// + c++ TODO временно

            while (socket.isConnected() && !socket.isClosed()) {
                if (isAuth) {
                    String msg = in.readUTF();

                    //TODO временно
                    //System.out.println("2.ClientHandler.runRead().Client " + getHandlerName() + " Thread: " + Thread.currentThread().getName());

                    if (msg.startsWith("/")) {

                        //TODO Not corrected Client closing.Added.ERROR java.net.SocketException: Socket closed
                        //закрываем соединение, если от своего пришел запрос на закрытие сеанса(окна клиента в т.ч.)
                        if (msg.startsWith(Command.END_CONNECTION_CALL.getText())){
                            close();
                            break;
                        }

                        //TODO use ExecutorService.Added
                        //выходим из цикла после завершения сеанса получения сообщений из буфера своего клиента
                        if (msg.startsWith(Command.READ_BUFFER_END.getText())) {

                            //TODO временно
                            //System.out.println("3.ClientHandler.runRead().Client " + getHandlerName() + " Thread: " + Thread.currentThread().getName() + " msg: " + msg);

                            break;
                        }

                        if (msg.startsWith(Command.PRIVATE_MESSAGE.getText() + " ")) {
                            sendPrivateMessage(msg);
                        } else if (msg.startsWith(Command.CHAT_MESSAGE.getText() + " ")) {
                            sendChatMessage(msg);
                        }
                    } else {
                        sendBroadcastMessage(name + " написал: " + msg);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //TODO временно.Hided
        //System.out.println("Client disconnected");
        //TODO временно.Added
        //System.out.println("9.ClientHandler.runRead().Client " + getHandlerName() + " Thread: " + Thread.currentThread().getName() + " isBusy: " + isBusy);
    }

    private boolean isUserExist(String userName) {
        return server.getAuthService().contains(userName);
    }

    private void sendPersonalMessage(String user, String message) {
        server.sendPrivateMessage(name, user, message);
    }

    private void sendBroadcastMessage(String msg) {
        server.sendBroadcastMessage(msg);
    }

    //отправка сообщения своему клиенту
    void sendMessage(String msg) {

        //TODO временно
        //System.out.println("1.ClientHandler.sendMessage.name: " + name + " message: " + msg);

        try {
            out.writeUTF(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    boolean isActive() {
        return isAuth;
    }

    Socket getSocket() {
        return socket;
    }

    String getHandlerName() {
        return name;
    }

    private void sendChatMessage(String msg) {
        // /clients nick1     nick2   nick3 -m hello nick1
        String[] data = msg.substring(Command.CHAT_MESSAGE.getText().length() + 1).split(" -m ");//TODO Useful to use
        if (data.length == 2) {
            String message = data[1];
            String[] nicknames = data[0].split(" ");
            List<String> validUsers = new ArrayList<>();
            for (String nickname : nicknames) {
                if (!nickname.trim().isEmpty()) {//TODO Useful to use
                    if (isUserExist(nickname)) {
                        validUsers.add(nickname);
                    } else {
                        sendMessage("Invalid username " + nickname);
                        System.out.println("Invalid username " + nickname);
                    }
                }
            }

            validUsers.forEach(username -> sendPersonalMessage(username, message + " " + validUsers.toString()));

            sendMessage(message + " for users: " + validUsers.toString());
        } else {
            sendMessage("Invalid chat message command!");
            System.out.println("Invalid chat message command!");
        }
    }

    private void userAuth(String msg) {
        String[] data = msg.split(" ");

        if (data.length == 3) {
            name = server.getAuthService().getNick(data[1], data[2]);
            if (name != null) {
                sendMessage(Command.AUTHOK_COMMAND.getText() + " " + name);
                isAuth = true;
                sendBroadcastMessage(name + " зашел в чат!");
            } else {
                sendMessage("Неверные логин/пароль");
            }
        }
    }

    private void sendPrivateMessage(String msg) {
        String[] data = msg.substring(3).split(" ", 2);

        String userName = data[0];
        if (isUserExist(userName)) {
            sendMessage("я написал лично " + userName + ": " + data[1]);
            sendPersonalMessage(userName, data[1]);
        } else {
            sendMessage("Попытка написать несуществующему пользователю "
                    + userName);
        }
    }

    LocalDateTime getConnectTime() {
        return connectTime;
    }

    @Override
    public void close() throws IOException {

        //TODO Not corrected Client closing.Added.ERROR java.net.SocketException: Socket closed
        //отправляем своему клиенту сообщение, закрыть коммуникацию
        sendMessage(Command.DISCONNECTED.getText());

        socket.close();
    }

    //TODO use ExecutorService.Added
    public boolean getBusy() {
        return isBusy;
    }

    //TODO use ExecutorService.Added
    public void setBusy(boolean busy) {
        isBusy = busy;
    }

}