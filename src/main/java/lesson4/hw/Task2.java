package lesson4.hw;

/**
 * GBJava3
 * Java Core. Professional level. 19.08.2019 Webinar. Teacher: Anton Batiaev
 * Урок 4. Многопоточность. Часть I.
 * Домашняя работа.
 * @author Litvinenko Yuriy
 * DONE 2. На серверной стороне сетевого чата реализовать управление потоками через ExecutorService.
 */
public class Task2 {

}
/*
Task2 сделал.
Доработал com.batiaev.java2.lesson8.
class MyServer.
- Добавил метод rollCallClientsHandlers(); - бесконечный цикл опроса подключеных клиентов
 для предоставления свободного потока из пула. Перехватил ConcurrentModificationException,
 возникающее при каждом изменении коллекции подключеных клиентов.
- Добавил ExecutorService executorService - пул потоков для работы с коллекцией из ClientHandler.
Проверено на пуле из 3-х потоков и 4-х одновременно активных клиента - работает.
- Деактивировал метод startKiller(), иначе будет два цикла опрашивающий скписок клиентов.
Не хватило времени. Нужно добавить проверку и удаление неактивных ClientHandler в метод rollCallClientsHandlers().
Нужно весь механизм отключения клиентов переписывать - все изначально не работает(например, при закрытии окна клиента).

class ClientHandler.
 - Удалил extends Thread;
 - Разделил метод run на два синхронизированных метода:
  runAuth(для процесса авторизации) и runRead(для чтения сообщений).
 - добавил переменную состояния isBusy, чтобы управлять потоками.

 class MyWindow.
 - Добавил еще коллекцию-буфер buffer для временного хранения введенных в UI сообщений.
   Да, это может быть и дублирование с коллекцией userMessageList, но у них разный функционал и лучше их разделить;
 - Добавил отправку пакета сообщений из буфера по запросу сервера.
 */
