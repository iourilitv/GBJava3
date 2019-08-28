package lesson3.hw;

import java.io.*;
import java.util.Scanner;

/**
 * GBJava3
 * Java Core. Professional level. 19.08.2019 Webinar. Teacher: Anton Batiaev
 * Урок 3. Средства ввода-вывода.
 * Домашняя работа.
 * @author Litvinenko Yuriy
 * 1. Добавить в сетевой чат запись локальной истории в текстовый файл на клиенте.
 * 2. После загрузки клиента показывать ему последние 100 строк чата.
 *
 * Дополнительно задание.
 * Прочитать файл (около 50 байт) в байтовый массив и вывести этот массив в консоль;
 * Последовательно сшить 5 файлов в один (файлы примерно 100 байт).
 * Может пригодиться следующая конструкция:
 * List<InputStream> al = new ArrayList<>(); ... Enumeration<InputStream> e = Collections.enumeration(al);
 * Написать консольное приложение, которое умеет постранично читать текстовые файлы (размером > 10 mb).
 * Вводим страницу (за страницу можно принять 1800 символов), программа выводит ее в консоль.
 * Контролируем время выполнения: программа не должна загружаться дольше 10 секунд,
 * а чтение – занимать свыше 5 секунд.
 */
public class Task1 {

    public static void main(String[] args) throws FileNotFoundException {
        String str = "nick1 some text...\nsome more strings.\nsome more strings.\nsome more strings.\n";
        File file = new File("messageStorage");
        DataOutputStream writeMsg = new DataOutputStream(new FileOutputStream(file));
        try {
            writeMsg.writeUTF(str);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("file.toString(): " + file.toString());//file.toString(): messageStore

        DataInputStream inputMsg = new DataInputStream(new FileInputStream(file));
        Scanner scanner = new Scanner(inputMsg);
        String line = scanner.nextLine();

        try {
            System.out.println("inputMsg.readUTF(): " + inputMsg.readUTF());
        } catch (IOException e) {
            e.printStackTrace();
        }

        String temp = null;
        /*try {
            //temp = inputMsg.readUTF().split("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        System.out.println("temp: " + temp);
    }
}
