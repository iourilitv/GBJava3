package lesson3.hw;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * GBJava3
 * Java Core. Professional level. 19.08.2019 Webinar. Teacher: Anton Batiaev
 * Урок 3. Средства ввода-вывода.
 * Домашняя работа.
 * @author Litvinenko Yuriy
 *
 * Дополнительно задание.
 * 3. Прочитать файл (около 50 байт) в байтовый массив и вывести этот массив в консоль;
 */
public class Task3 {

    public static void main(String[] args) throws FileNotFoundException {
        byte[] fileData;
        File file = new File("src/lesson3/hw/task3.txt");
        FileInputStream in = new FileInputStream(file);
        //ByteInputStream byteInputStream = new ByteInputStream(in);//ERROR
        Scanner scanner = new Scanner(in);
        String text = scanner.next();
        fileData = text.getBytes();

        byte[] data;
        try {
            data = "Давайте посмотрим на код, который позволит нам записать в файл слово Java. Вначале нам нужно".getBytes("UTF-8");
            System.out.println("byte array to string: " + Arrays.toString(data));
            //byte array to string: [-48, -108, -48, -80, -48, -78, -48, -80, -48, -71, -47, -126, -48, -75, 32, -48, -65, -48, -66, -47, -127, -48, -68, -48, -66, -47, -126, -47, -128, -48, -72, -48, -68, 32, -48, -67, -48, -80, 32, -48, -70, -48, -66, -48, -76, 44, 32, -48, -70, -48, -66, -47, -126, -48, -66, -47, -128, -47, -117, -48, -71, 32, -48, -65, -48, -66, -48, -73, -48, -78, -48, -66, -48, -69, -48, -72, -47, -126, 32, -48, -67, -48, -80, -48, -68, 32, -48, -73, -48, -80, -48, -65, -48, -72, -47, -127, -48, -80, -47, -126, -47, -116, 32, -48, -78, 32, -47, -124, -48, -80, -48, -71, -48, -69, 32, -47, -127, -48, -69, -48, -66, -48, -78, -48, -66, 32, 74, 97, 118, 97, 46, 32, -48, -110, -48, -67, -48, -80, -47, -121, -48, -80, -48, -69, -48, -75, 32, -48, -67, -48, -80, -48, -68, 32, -48, -67, -47, -125, -48, -74, -48, -67, -48, -66]
            String value = new String(data, "UTF-8");
            System.out.println("value: " + value);
            //value: Давайте посмотрим на код, который позволит нам записать в файл слово Java. Вначале нам нужно
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


        fileData = "ABCABCABC".getBytes();
        //byte array to string: [65, 66, 67, 65, 66, 67, 65, 66, 67]

//      System.out.println("byte array to string: " + Arrays.toString(fileData));
//
//
//        char i = 'A';
//        System.out.println("byte : " + (byte)i);//byte : 65
//        byte b = 65;
//        System.out.println("char : " + (char)b);//char : A

//        char i = 'Д';
//        System.out.println("byte : " + (byte)i);//byte : 20
//        byte b = 20;
//        System.out.println("char : " + (char)b);//char : 

//        OK!!!
//        String myString = "Давайте посмотрим на код, который позволит нам записать в файл слово Java.";
//        byte[] bytes;
//        String value = "";
//        try {
//            bytes = myString.getBytes("UTF-8");
//            value = new String(bytes, "UTF-8");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        System.out.println("value: " + value);
        //value: Давайте посмотрим на код, который позволит нам записать в файл слово Java.

    }

}
