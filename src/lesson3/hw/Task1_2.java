package lesson3.hw;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * GBJava3
 * Java Core. Professional level. 19.08.2019 Webinar. Teacher: Anton Batiaev
 * Урок 3. Средства ввода-вывода.
 * Домашняя работа.
 * @author Litvinenko Yuriy
 * Done 1. Добавить в сетевой чат запись локальной истории в текстовый файл на клиенте.
 * Done 2. После загрузки клиента показывать ему последние 100 строк чата.
 * Реализовано в пакете com.batiaev.java2.lesson8/MyWindow. Копия кода - ниже
 */
public class Task1_2 {
    private JTextArea jta;


    private static final int FILE_STORAGE_CAPACITY = 100;//емкость хранилища сообщений из чата пользователя
    private String userMessageStorageFileName = "src/com/batiaev/java2/lesson8/resources/";//"D:\\GeekBrains\\20190819_GB-Java Core. Профессиональный уровень\\GBJava3\\src\\com\\batiaev\\java2\\lesson8";//"../com/batiaev/java2/lesson8/resources/";
    private File userMessageStorageFile;
    List<String> userMessageList;//временная коллекция для хранения истории сообщений пользователя
    private String nick = null;

    //Метод загрузки истории сообщений
    private void restoreMessageHistory() throws FileNotFoundException {
        //создаем временную коллекцию
        userMessageList = new ArrayList<>(FILE_STORAGE_CAPACITY);
        //создаем путь к файлу для хранения истории собщений
        userMessageStorageFileName = userMessageStorageFileName.concat(nick + "_messageStorage.txt");
        userMessageStorageFile = new File(userMessageStorageFileName);

        //если файл уже создан
        if(isCreatedUserMessageStorageFile()){
            //читаем его в коллекцию
            getMessagesFromStorage();
        } else {
            System.out.println("The file to save a history of messages does not exist!");
        }
    }

    //TODO Adding a message storage.Added
    //Метод проверяющий есть ли файл для хранения сообщений в чате пользователя
    //и создает новый файл, если его еще нет
    private boolean isCreatedUserMessageStorageFile() throws FileNotFoundException {
        //проверяем есть ли файл
        if(!userMessageStorageFile.exists()){
            //если файл еще не создан
            try {
                userMessageStorageFile.createNewFile();
                System.out.println("The file to save a history of messages has been created!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //проверяем еще раз существует ли файл
        if(userMessageStorageFile.exists()){
            return true;
        } else {
            return false;
        }
    }

    //TODO Adding a message storage.Added
    //Метод возвращающий историю последние сообщения в чате пользователя из файла
    private void getMessagesFromStorage() throws FileNotFoundException {
        //запускаем поток чтения из файла
        DataInputStream readFile = new DataInputStream(new FileInputStream(userMessageStorageFile));
        Scanner scanner = new Scanner(readFile);
        //читаем пока не дойдем до пустой строки
        while(scanner.hasNext()) {
            //принимаем первую строку файла
            String line = scanner.nextLine();
            //очищаем от первых двух служебных символов в каждой строке файла
            line = line.substring(2);
            //добавляем в коллекцию строки из файла
            userMessageList.add(line);
            //выводим строку пользователю
            showMessage(line);
        }

        //TODO временно
        //System.out.println("***AFTER READING. printArrayList***");
        //printArrayList(userMessageList);

    }

    //Метод сохранения последних n сообщений в чате пользователя в файл
    void saveMessageIntoStorage(String msg) throws FileNotFoundException {
        //если в коллекции все ячейки заполнены, то
        if(userMessageList.size() == FILE_STORAGE_CAPACITY){
            // удаляем первую(елементы сдвинутся в начало)
            userMessageList.remove(0);
        }
        //записываем строку в пустую ячейку(последнюю)
        userMessageList.add(msg);
        //***перезаписываем коллекцией файл***
        DataOutputStream writeMsg = new DataOutputStream(new FileOutputStream(userMessageStorageFile));
        for (String a: userMessageList) {
            try {
                writeMsg.writeUTF(a + System.lineSeparator());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //выводим строку пользователю
    private void showMessage(String string){
        jta.append(string + System.lineSeparator());
    }

}
