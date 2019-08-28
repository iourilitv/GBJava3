package lesson3.hw;

/**
 * GBJava3
 * Java Core. Professional level. 19.08.2019 Webinar. Teacher: Anton Batiaev
 * Урок 3. Средства ввода-вывода.
 * Домашняя работа.
 * @author Litvinenko Yuriy
 *
 * Дополнительно задание.
 * 4. Последовательно сшить 5 файлов в один (файлы примерно 100 байт).
 *      Может пригодиться следующая конструкция:
 *      List<InputStream> al = new ArrayList<>();
 *      ...
 *      Enumeration<InputStream> e = Collections.enumeration(al);
 */
public class Task4 {
    String string = "Давайте посмотрим на код, который позволит нам записать в файл слово Java. Вначале нам нужно\n" +
            "получить байтовое представление слова Java, создадим байтовый массив outData[] и заполним его с\n" +
            "помощью метода класса String getBytes(). После того как данные готовы, открываем поток записи\n" +
            "FileOutputStream в файл demo.txt, используя try-с-ресурсами, и с помощью метода write() записываем\n" +
            "содержимое массива в файл. try-с-ресурсами автоматически закроет наш поток записи и программа\n" +
            "завершится.";

    public static void main(String[] args) {

    }

}
