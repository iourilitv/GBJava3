package lesson3.hwTeacher;

import java.util.Collection;
import java.util.List;

/**
 * GBJava3
 * Java Core. Professional level. 19.08.2019 Webinar. Teacher: Anton Batiaev
 * Урок 3. Средства ввода-вывода.
 * Домашняя работа.
 * HistoryManager
 * Done 1. Добавить в сетевой чат запись локальной истории в текстовый файл на клиенте.
 * Done 2. После загрузки клиента показывать ему последние 100 строк чата.
 * @author anton
 * @since 29/08/19
 */
public interface HistoryManager {

    //метод для сохранения одного сообщения
    void storeMessage(String msg);

    //метод для сохранения нескольких сообщений. Принимаем аргумент переменной длины(через запятую)
    //default - вроде, чтобы его не overwrite
    default void storeMessage(String... messages) {
        for (String message : messages) {
            storeMessage(message);
        }
    }

    //метод для сохранения коллекции сообщений
    default void storeMessage(Collection<String> messages) {
        for (String message : messages) {
            storeMessage(message);
        }
    }

    //метод для загрузки требуемого количества сообщений(limit)
    List<String> loadHistory(int limit);

    //метод для загрузки 100 сообщений(нужен только для задачи ДЗ)
    default List<String> loadHistory() {
        return loadHistory(100);
    }
}
