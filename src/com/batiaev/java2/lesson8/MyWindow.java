package com.batiaev.java2.lesson8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * MyWindow
 * Net Chat App of Java2
 * @author Anton Batiaev
 * @since 06/11/17
 */
public class MyWindow extends JFrame {

    private final String SERVER_ADDR = "localhost";
    private final int SERVER_PORT = 8189;

    //TODO Adding a message storage.Added
    private static final int FILE_STORAGE_CAPACITY = 5;//емкость хранилища сообщений из чата пользователя
    private String userMessageStorageFileName = "src/com/batiaev/java2/lesson8/resources/";//"D:\\GeekBrains\\20190819_GB-Java Core. Профессиональный уровень\\GBJava3\\src\\com\\batiaev\\java2\\lesson8";//"../com/batiaev/java2/lesson8/resources/";
    private File userMessageStorageFile;
    List<String> userMessageList;//временная коллекция для хранения истории сообщений пользователя

    private JTextField login = new JTextField("0");//TODO Временно удалил "Login"
    private JPasswordField password = new JPasswordField("0");//TODO Временно удалил "Password"
    private JButton authBtn = new JButton("Auth");
    private JTextField jtf;
    private JTextArea jta;

    private Socket sock;
    private DataInputStream in;
    private DataOutputStream out;
    private boolean authorized = false;

    //TODO Adding a message storage.Added
    private String nick = null;

    public static void main(String[] args) {
        new MyWindow().setVisible(true);
    }

    private MyWindow() {
        initUI();
    }

    private void initUI() {
        setBounds(600, 300, 500, 500);
        setTitle("Client");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        createAuthPanel();

        jta = new JTextArea();
        jta.setEditable(false);
        jta.setLineWrap(true);
        JScrollPane jsp = new JScrollPane(jta);
        add(jsp, BorderLayout.CENTER);
        JPanel bottomPanel = new JPanel(new BorderLayout());
        add(bottomPanel, BorderLayout.SOUTH);
        JButton jbSend = new JButton("SEND");
        bottomPanel.add(jbSend, BorderLayout.EAST);
        jtf = new JTextField();
        jtf.setEnabled(false);
        bottomPanel.add(jtf, BorderLayout.CENTER);

        jbSend.addActionListener(e -> sendMsgFromUI());
        jtf.addActionListener(e -> sendMsgFromUI());

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                super.windowClosing(event);
                try {
                    out.writeUTF("end");
                    out.close();
                    in.close();
                    sock.close();
                } catch (IOException e) {
                    System.out.println("something happened on closing");
                }
            }
        });
    }

    private void createAuthPanel() {
        JPanel authPanel = new JPanel(new GridLayout());
        authPanel.add(login);
        login.setToolTipText("enter your login");
        password.setToolTipText("enter your password");
        authPanel.add(password);
        authPanel.add(authBtn);
        add(authPanel, BorderLayout.NORTH);
        authBtn.addActionListener(e -> connect(login.getText(), String.valueOf(password.getPassword())));
    }

    private void connect(String login, String password) {

        if (login.trim().isEmpty() || password.trim().isEmpty()) {//TODO Useful to use
            System.out.println("login or password is empty!");
            return;
        }

        try {
            sock = new Socket(SERVER_ADDR, SERVER_PORT);
            in = new DataInputStream(sock.getInputStream());
            out = new DataOutputStream(sock.getOutputStream());
            out.writeUTF("/auth " + login + " " + password);
            out.flush();

            new Thread(() -> {
                try {
                    while (sock.isConnected() && !sock.isClosed()) {
                        Thread.sleep(100);//TODO Useful to use
                        String msg = in.readUTF();
                        if (msg.startsWith(Command.AUTHOK_COMMAND.getText())) {//TODO Useful to use

                            //TODO Adding a message storage.Deleted
                            //String nick = msg.substring(Command.AUTHOK_COMMAND.getText().length() + 1);
                            //TODO Adding a message storage.Added
                            //запоминаем собственное имя в чате
                            nick = msg.substring(Command.AUTHOK_COMMAND.getText().length() + 1);
                            //загружаем историю переписки
                            getMessageHistory();

                            setTitle(nick + "'s client");
                            setAuthorized(true);
                        } else if (msg.startsWith(Command.DISCONNECTED.getText())) {
                            jta.append("Connection closed..=(");
                            setAuthorized(false);
                        } else if (isAuthorized()) {
                            if (msg.equalsIgnoreCase("end session")) break;

                            //TODO Adding a message storage.Deleted
                            jta.append(msg + System.lineSeparator());//TODO Useful to use
                            //TODO Adding a message storage.Added
                            saveMessageIntoStorage(msg);
                            //gettingMessageFromStorage();
                        }
                    }
                    setAuthorized(false);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //TODO Adding a message storage.Added
    //Метод загрузки истории сообщений
    private void getMessageHistory() throws FileNotFoundException {
        //создаем временную коллекцию
        userMessageList = new ArrayList<>(FILE_STORAGE_CAPACITY);
        //создаем путь к файлу для хранения истории собщений
        userMessageStorageFileName = userMessageStorageFileName.concat(nick + "_message.storage");
        userMessageStorageFile = new File(userMessageStorageFileName);

        //TODO временно
        //System.out.println("userMessageStorageFileName: " + userMessageStorageFileName);
        //System.out.println("userMessageStorageFile.getName(): " + userMessageStorageFile.getName());

        //если файл уже создан
        if(isCreatedUserMessageStorageFile()){
            //читаем его в коллекцию
            getMessagesFromStorage();
        }

        printArrayList(userMessageList);
    }

    //TODO Adding a message storage.Added
    //Метод создающий файл для храния сообщений в чате пользователя
    //создает новый файл, если его еще нет
    private boolean isCreatedUserMessageStorageFile() throws FileNotFoundException {
        //проверяем есть ли файл
        if(!userMessageStorageFile.exists()){
            //если файл еще не создан
            try {
                userMessageStorageFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("userMessageStorageFile.exists(): " + userMessageStorageFile.exists());
        return true;
    }

    //TODO Adding a message storage.Added
    //Метод сохранения последних n сообщений в чате пользователя в файл
    void saveMessageIntoStorage(String msg){
        //создаем временную коллекцию
        //List<String> list = new ArrayList<>(FILE_STORAGE_CAPACITY);
        /*//если список пустой
        if(list.isEmpty()) {
            //записываем строку в первую пустую ячейку и выходим из метода
            list.add(msg);
            return;
        }*/

        //TODO временно
        System.out.println("***printArrayList***");
        System.out.println("new msg: " + msg + ". list.size(): " + userMessageList.size());

        //если в коллекции все ячейки заполнены, то
        if(userMessageList.size() == FILE_STORAGE_CAPACITY){
            // удаляем первую(елементы сдвинутся в начало)
            userMessageList.remove(0);
        }
        //записываем строку в пустую ячейку(последнюю)
        userMessageList.add(msg);

        //TODO временно
        printArrayList(userMessageList);

        //***перезаписываем коллекцией файл***


    }

    //TODO Adding a message storage.Added
    //Метод возвращающий последние 100 сообщений в чате пользователя из файла
    private void getMessagesFromStorage(){
        //TODO временно
        System.out.println("gettingMessagesFromStorage....");

        //разделяем строку на
        //String[] temp = msg.split(System.lineSeparator());
        //list.addAll(Arrays.asList(temp));

    }

    private void sendMsgFromUI() {//TODO Useful to use
        String msg = jtf.getText();
        sendMsg(msg);
        jtf.setText("");
        jtf.grabFocus();
    }

    private void sendMsg(String msg) {
        if (!msg.trim().isEmpty()) {
            try {
                out.writeUTF(msg);
                out.flush();
            } catch (IOException e) {
                System.out.println("Fail to send message: " + e.getLocalizedMessage());
            }
        }
    }

    private void setAuthorized(boolean authorized) {
        this.authorized = authorized;
        jtf.setEnabled(authorized);
    }

    private boolean isAuthorized() {
        return authorized;
    }

    //TODO временно
    public void printArrayList(List<String> arrayList){
        System.out.println("arrayList.size(): " + arrayList.size());
        for (Object a: arrayList) {
            System.out.println("element: " + a);
        }
    }

}