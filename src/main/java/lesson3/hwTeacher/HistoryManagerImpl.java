package lesson3.hwTeacher;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * GBJava3
 * Java Core. Professional level. 19.08.2019 Webinar. Teacher: Anton Batiaev
 * Урок 3. Средства ввода-вывода.
 * Домашняя работа.
 * HistoryManagerImpl
 * Done 1. Добавить в сетевой чат запись локальной истории в текстовый файл на клиенте.
 * Done 2. После загрузки клиента показывать ему последние 100 строк чата.
 * @author anton
 * @since 29/08/19
 */
public class HistoryManagerImpl implements HistoryManager {
    private final static String HISTORY_FILE = "history.log";//константа для указания названия файла

    public static void main(String[] args) {
        HistoryManager historyManager = new HistoryManagerImpl();
        for (int i = 0; i < 120; i++) {
            String message = UUID.randomUUID().toString();
            historyManager.storeMessage(message);
        }
        List<String> strings = historyManager.loadHistory(100);
        for (String line : strings) {
            System.out.println(line);
        }
    }

    //метод для сохранения одного сообщения
    @Override
    public void storeMessage(String msg) {
        //создаем объект коллекции для временного хранения сообщений
        List<String> history1 = loadHistory(100);//FIXME move 100 to constant
        //сохраняем сообщение в начало списка
        history1.add(0, msg);
        //если количество сообщений превысило лимит
        if (history1.size() >= 100)//FIXME move 100 to constant
            //удаляем последнее сообщение из коллекции
            history1.remove(history1.size() - 1);

        //записываем в файл в конструкции try-with-resources
        try (PrintWriter pw = new PrintWriter(new FileWriter(HISTORY_FILE))) {
            //непосредственно пишем в файл, использую лямбда выражение
            //фактически мы дописываем файл всей коллекцией//TODO Странная логика? С начала или в конец?
            history1.forEach(pw::println);
            //эта строка заменяет конструкцию
            //for (String s : history1) {pw.println();}
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //метод для загрузки требуемого количества сообщений(limit)
    @Override
    public List<String> loadHistory(int limit) {
        //получаем файл истории или создаем, если нет
        File history = getOrCreateHistoryFile();

        //загружаем данные из файла
        //начиная с Java8 есть буферезированный поток чтения из файла по строчно
        //он еще и в utf-8 преобразует, т.к. InputStreamReader внутри
        try (Stream<String> stream = Files.lines(history.toPath())) {//принимает путь к файлу history.toPath()
            //возвращаем коллекцию размером с limit, используя метода limit() в классе Stream
            //!limit() читает с начала файла!
            //и завернули все, что есть в файле в коллекцию, методом collect класса Stream
            return stream
                    .limit(limit)
                    .collect(Collectors.toList());
            //это сокращенная форма - без создания промежуточных переменных, например:
            //из исходного стрима получаем стрим с ограниченным числом элементов
            // Stream<String> limit1 = stream.limit(limit);
            //и из этого стрима возвращаем коллекцию
            //limit1.collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            //если выскочило исключение, вернем пустую коллекцию
            return new ArrayList<>();
        }
    }

    //возвращает объект файла истории или создает, если нет
    private File getOrCreateHistoryFile() {
        //создаем объект класса File
        File history = new File(HISTORY_FILE);
        //если файл не существует
        if (!history.exists()) {
            try {
                //создаем новый файл
                history.createNewFile();
            } catch (IOException e) {
                System.out.println("Cannot create history file: "
                        + e.getLocalizedMessage());
            }
        }
        return history;
    }
}
