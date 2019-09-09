package com.batiaev.java2.lesson8.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * MyServer
 * Net Chat App of Java2
 * @author Anton Batiaev
 * @since 06/11/17
 */ //READY
public class MyServer {

    //TODO lesson6-hw-Task3.AddLogging.Added
    //инициализируем логгера
    private static final Logger log = LoggerFactory.getLogger(MyServer.class);

    private static final long MAX_DELAY_TIME = 120;

    //TODO use ExecutorService.Added
    private static final int THREADS_PULL_SIZE = 3;

    private final List<ClientHandler> clients = Collections.synchronizedList(new ArrayList<>());
    private AuthService authService;

    //TODO use ExecutorService.Added
    private ExecutorService executorService = Executors.newFixedThreadPool(THREADS_PULL_SIZE);;

    public static void main(String[] args) {
        new MyServer(new BaseAuthService());
    }

    private MyServer(AuthService authService) {
        this.authService = authService;
        Socket s = null;
        ServerSocket server = null;
        try {
            server = new ServerSocket(8189);
            System.out.println("Server created. Waiting for client...");

            //TODO временно.Hided
            //бесконечный цикл опроса клиентов и удаление, тех кто молчит долго
            //startKiller();//TODO Useful!

            //TODO use ExecutorService.Added
            //бесконечный цикл опроса подключеных клиентов для предоставления свободного потока из пула
            rollCallClientsHandlers();

            while (true) {
                s = server.accept();
                ClientHandler client = new ClientHandler(this, s);

                //TODO use ExecutorService.Deleted
                //client.start();

                clients.add(client);

                //TODO use ExecutorService.Added
                executorService.execute(new Runnable() {
                    @Override
                    public void run() {
                        //занимаем ClientHandler
                        client.setBusy(true);
                        //запускаем поток авторизации
                        client.runAuth();
                        //запускаем поток чтения
                        client.runRead();
                        //освобождаем ClientHandler
                        client.setBusy(false);
                    }
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (server != null) server.close();
                System.out.println("Server closed");
                if (s != null) s.close();

                //TODO use ExecutorService.Added
                if (!executorService.isShutdown()) executorService.shutdown();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //TODO use ExecutorService.Added
    //метод опроса подключеных клиентов для предоставления свободного потока из пула
    private void rollCallClientsHandlers() {
        new Thread(() -> {
            while (true) {
                try {
                    Iterator<ClientHandler> i = clients.iterator();

                    //TODO use ExecutorService.Solving java.util.ConcurrentModificationException.Added
                    //задержка нужна, начиная со второго не клиенты не могут авторизоваться (пробовал макс.1000)
                    Thread.sleep(100);

                    while (i.hasNext()) {
                        ClientHandler client = i.next();

                        //TODO временно
                        //System.out.println("1.MyServer.rollCallClientsHandlers.client.nick: " + client.getHandlerName() + " isActive(): " + client.isActive());

                        if (client.isActive() && !client.getBusy()) {

                            //TODO use ExecutorService.Solving java.util.ConcurrentModificationException.Deleted
                            //Thread.sleep(1000);

                            //TODO временно
                            //System.out.println("2.MyServer.rollCallClientsHandlers.client.nick: " + client.getHandlerName());

                            //предоставляем ClientHandler поток из пула и запускаем его задачу
                            executorService.execute(new Runnable() {
                                @Override
                                public void run() {

                                    //TODO временно
                                    //System.out.println("3.MyServer.rollCallClientsHandlers.client.nick: " + client.getHandlerName());

                                    //занимаем ClientHandler
                                    client.setBusy(true);
                                    //запускаем поток чтения буфера клиента
                                    client.runRead();
                                    //освобождаем ClientHandler
                                    client.setBusy(false);
                                }
                            });
                        } else {
                            //TODO временно
                            //System.out.println("9.MyServer.rollCallClientsHandlers.client.nick: " + client.getHandlerName());

                        }
                    }

                //TODO use ExecutorService.Solving java.util.ConcurrentModificationException.Added
                //Перехватываем исключение возникающее из-за изменения коллекции во время листинга(подключение/отключение пользователя)
                } catch (ConcurrentModificationException сe) {

                    //TODO lesson6-hw-Task3.AddLogging.Deleted
                    //System.out.println("99.MyServer.rollCallClientsHandlers: ConcurrentModificationException");
                    //TODO lesson6-hw-Task3.AddLogging.Added
                    //логируем сообщение об исключении
                    log.error("99.MyServer.rollCallClientsHandlers: ConcurrentModificationException");

                } catch (Exception e) {
                e.printStackTrace();
                }
            }
        }).start();
    }

    //TODO Useful!
    //метод контроля подключеного клиента и закрытия клиента, если превышено время ожидания
    private void startKiller() {
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                    LocalDateTime now = LocalDateTime.now();

                    Iterator<ClientHandler> i = clients.iterator();
                    while (i.hasNext()) {
                        ClientHandler client = i.next();

                        //TODO временно
                        System.out.println("startKiller.client.nick: " + client.getHandlerName());

                        if (!client.isActive()
                                && Duration.between(client.getConnectTime(), now).getSeconds() > MAX_DELAY_TIME) {
                            System.out.println("close unauthorized user");
                            client.close();
                            i.remove();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    AuthService getAuthService() {
        return authService;
    }

    void sendBroadcastMessage(String msg) {
        for (ClientHandler c : clients) {
            if (c.isActive()) c.sendMessage(msg);
        }
    }

    void sendPrivateMessage(String from, String userName, String message) {
        for (ClientHandler c : clients) {
            String name = c.getHandlerName();
            if (name.equals(userName) && c.isActive())
                c.sendMessage(from + " написал лично " + userName + ": " + message);
        }
    }

    //TODO Not corrected Client closing.Added
    public List<ClientHandler> getClients() {
        return clients;
    }
}