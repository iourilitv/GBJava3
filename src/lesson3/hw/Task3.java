package lesson3.hw;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * GBJava3
 * Java Core. Professional level. 19.08.2019 Webinar. Teacher: Anton Batiaev
 * Урок 3. Средства ввода-вывода.
 * Домашняя работа.
 * @author Litvinenko Yuriy
 * Дополнительно задание.
 * DONE 3. Прочитать файл (около 50 байт) в байтовый массив и вывести этот массив в консоль;
 * Ready
 */
public class Task3 {

    public static void main(String[] args){
        //инициализируем байтовый буфер
        byte[] buf = new byte[50];
        //создаем объект файла
        File file = new File("src/lesson3/hw/task3.txt");
        //создаем объект потока чтения из файла и используем его в try с ресурсом(закроется автоматически)
        try(FileInputStream in = new FileInputStream(file)){
            //ДОП.инициализируем строку для преобразования кирилицы используя UTF-8
            String value = "";
            //читаем данные из файла в байтовый буфер
            // .read(buf) возвращает количество считанных байтов и -1 в конце
            int count;
            while((count = in.read(buf)) > 0){
                //выводим байтовый массив в консоль по байтно
                for (int i = 0; i < count; i++) {
                    //без преобразования
                    //System.out.print(buf[i] + " ");
                    //с приведением к символу
                    //System.out.print((char)buf[i]);
                }
                //ДОП.для преобразования кирилицы используя UTF-8
                value += new String(buf, "UTF-8");
            }
            //ДОП.выводим в консоль приведенную к utf-8 строку
            //Внимание! Размер массива-буфера должен быть больше текста, иначе - артефакты
            System.out.println("value: \n" + value);
        } catch (IOException e){
            e.printStackTrace();
        }

        //***Result of System.out.print(buf[i] + " ");
        // 65 66 67 65 66 67 65 66 67 13 10 -48 -108 -48 -80 -48 -78 -48 -80 -48 -71 -47 -126 -48 -75 32 -48 -65 -48 -66 -47 -127 -48 -68 -48 -66 -47 -126 -47 -128 -48 -72 -48 -68 32 -48 -67 -48 -80 32 -48 -70 -48 -66 -48 -76 44 32 -48 -70 -48 -66 -47 -126 -48 -66 -47 -128 -47 -117 -48 -71 32 -48 -65 -48 -66 -48 -73 -48 -78 -48 -66 -48 -69 -48 -72 -47 -126 32 -48 -67 -48 -80 -48 -68 32 -48 -73 -48 -80 -48 -65 -48 -72 -47 -127 -48 -80 -47 -126 -47 -116 32 -48 -78 32 -47 -124 -48 -80 -48 -71 -48 -69 32 -47 -127 -48 -69 -48 -66 -48 -78 -48 -66 32 74 97 118 97 46 32 -48 -110 -48 -67 -48 -80 -47 -121 -48 -80 -48 -69 -48 -75 32 -48 -67 -48 -80 -48 -68 32 -48 -67 -47 -125 -48 -74 -48 -67 -48 -66 13 10 -48 -65 -48 -66 -48 -69 -47 -125 -47 -121 -48 -72 -47 -126 -47 -116 32 -48 -79 -48 -80 -48 -71 -47 -126 -48 -66 -48 -78 -48 -66 -48 -75 32 -48 -65 -47 -128 -48 -75 -48 -76 -47 -127 -47 -126 -48 -80 -48 -78 -48 -69 -48 -75 -48 -67 -48 -72 -48 -75 32 -47 -127 -48 -69 -48 -66 -48 -78 -48 -80 32 74 97 118 97 44 32 -47 -127 -48 -66 -48 -73 -48 -76 -48 -80 -48 -76 -48 -72 -48 -68 32 -48 -79 -48 -80 -48 -71 -47 -126 -48 -66 -48 -78 -47 -117 -48 -71 32 -48 -68 -48 -80 -47 -127 -47 -127 -48 -72 -48 -78 32 111 117 116 68 97 116 97 91 93 32 -48 -72 32 -48 -73 -48 -80 -48 -65 -48 -66 -48 -69 -48 -67 -48 -72 -48 -68 32 -48 -75 -48 -77 -48 -66 32 -47 -127 13 10 -48 -65 -48 -66 -48 -68 -48 -66 -47 -119 -47 -116 -47 -114 32 -48 -68 -48 -75 -47 -126 -48 -66 -48 -76 -48 -80 32 -48 -70 -48 -69 -48 -80 -47 -127 -47 -127 -48 -80 32 83 116 114 105 110 103 32 103 101 116 66 121 116 101 115 40 41 46 32 -48 -97 -48 -66 -47 -127 -48 -69 -48 -75 32 -47 -126 -48 -66 -48 -77 -48 -66 32 -48 -70 -48 -80 -48 -70 32 -48 -76 -48 -80 -48 -67 -48 -67 -47 -117 -48 -75 32 -48 -77 -48 -66 -47 -126 -48 -66 -48 -78 -47 -117 44 32 -48 -66 -47 -126 -48 -70 -47 -128 -47 -117 -48 -78 -48 -80 -48 -75 -48 -68 32 -48 -65 -48 -66 -47 -126 -48 -66 -48 -70 32 -48 -73 -48 -80 -48 -65 -48 -72 -47 -127 -48 -72 13 10 70 105 108 101 79 117 116 112 117 116 83 116 114 101 97 109 32 -48 -78 32 -47 -124 -48 -80 -48 -71 -48 -69 32 100 101 109 111 46 116 120 116 44 32 -48 -72 -47 -127 -48 -65 -48 -66 -48 -69 -47 -116 -48 -73 -47 -125 -47 -113 32 116 114 121 45 -47 -127 45 -47 -128 -48 -75 -47 -127 -47 -125 -47 -128 -47 -127 -48 -80 -48 -68 -48 -72 44 32 -48 -72 32 -47 -127 32 -48 -65 -48 -66 -48 -68 -48 -66 -47 -119 -47 -116 -47 -114 32 -48 -68 -48 -75 -47 -126 -48 -66 -48 -76 -48 -80 32 119 114 105 116 101 40 41 32 -48 -73 -48 -80 -48 -65 -48 -72 -47 -127 -47 -117 -48 -78 -48 -80 -48 -75 -48 -68 13 10 -47 -127 -48 -66 -48 -76 -48 -75 -47 -128 -48 -74 -48 -72 -48 -68 -48 -66 -48 -75 32 -48 -68 -48 -80 -47 -127 -47 -127 -48 -72 -48 -78 -48 -80 32 -48 -78 32 -47 -124 -48 -80 -48 -71 -48 -69 46 32 116 114 121 45 -47 -127 45 -47 -128 -48 -75 -47 -127 -47 -125 -47 -128 -47 -127 -48 -80 -48 -68 -48 -72 32 -48 -80 -48 -78 -47 -126 -48 -66 -48 -68 -48 -80 -47 -126 -48 -72 -47 -121 -48 -75 -47 -127 -48 -70 -48 -72 32 -48 -73 -48 -80 -48 -70 -47 -128 -48 -66 -48 -75 -47 -126 32 -48 -67 -48 -80 -47 -120 32 -48 -65 -48 -66 -47 -126 -48 -66 -48 -70 32 -48 -73 -48 -80 -48 -65 -48 -72 -47 -127 -48 -72 32 -48 -72 32 -48 -65 -47 -128 -48 -66 -48 -77 -47 -128 -48 -80 -48 -68 -48 -68 -48 -80 13 10 -48 -73 -48 -80 -48 -78 -48 -75 -47 -128 -47 -120 -48 -72 -47 -126 -47 -127 -47 -113 46
        //
        // ***Result of System.out.print((char)buf[i]);
        //ABCABCABC
        //￐ﾔ￐ﾰ￐ﾲ￐ﾰ￐ﾹ￑ﾂ￐ﾵ ￐﾿￐ﾾ￑ﾁ￐ﾼ￐ﾾ￑ﾂ￑ﾀ￐ﾸ￐ﾼ ￐ﾽ￐ﾰ ￐ﾺ￐ﾾ￐ﾴ, ￐ﾺ￐ﾾ￑ﾂ￐ﾾ￑ﾀ￑ﾋ￐ﾹ ￐﾿￐ﾾ￐ﾷ￐ﾲ￐ﾾ￐ﾻ￐ﾸ￑ﾂ ￐ﾽ￐ﾰ￐ﾼ ￐ﾷ￐ﾰ￐﾿￐ﾸ￑ﾁ￐ﾰ￑ﾂ￑ﾌ ￐ﾲ ￑ﾄ￐ﾰ￐ﾹ￐ﾻ ￑ﾁ￐ﾻ￐ﾾ￐ﾲ￐ﾾ Java. ￐ﾒ￐ﾽ￐ﾰ￑ﾇ￐ﾰ￐ﾻ￐ﾵ ￐ﾽ￐ﾰ￐ﾼ ￐ﾽ￑ﾃ￐ﾶ￐ﾽ￐ﾾ
        //￐﾿￐ﾾ￐ﾻ￑ﾃ￑ﾇ￐ﾸ￑ﾂ￑ﾌ ￐ﾱ￐ﾰ￐ﾹ￑ﾂ￐ﾾ￐ﾲ￐ﾾ￐ﾵ ￐﾿￑ﾀ￐ﾵ￐ﾴ￑ﾁ￑ﾂ￐ﾰ￐ﾲ￐ﾻ￐ﾵ￐ﾽ￐ﾸ￐ﾵ ￑ﾁ￐ﾻ￐ﾾ￐ﾲ￐ﾰ Java, ￑ﾁ￐ﾾ￐ﾷ￐ﾴ￐ﾰ￐ﾴ￐ﾸ￐ﾼ ￐ﾱ￐ﾰ￐ﾹ￑ﾂ￐ﾾ￐ﾲ￑ﾋ￐ﾹ ￐ﾼ￐ﾰ￑ﾁ￑ﾁ￐ﾸ￐ﾲ outData[] ￐ﾸ ￐ﾷ￐ﾰ￐﾿￐ﾾ￐ﾻ￐ﾽ￐ﾸ￐ﾼ ￐ﾵ￐ﾳ￐ﾾ ￑ﾁ
        //￐﾿￐ﾾ￐ﾼ￐ﾾ￑ﾉ￑ﾌ￑ﾎ ￐ﾼ￐ﾵ￑ﾂ￐ﾾ￐ﾴ￐ﾰ ￐ﾺ￐ﾻ￐ﾰ￑ﾁ￑ﾁ￐ﾰ String getBytes(). ￐ﾟ￐ﾾ￑ﾁ￐ﾻ￐ﾵ ￑ﾂ￐ﾾ￐ﾳ￐ﾾ ￐ﾺ￐ﾰ￐ﾺ ￐ﾴ￐ﾰ￐ﾽ￐ﾽ￑ﾋ￐ﾵ ￐ﾳ￐ﾾ￑ﾂ￐ﾾ￐ﾲ￑ﾋ, ￐ﾾ￑ﾂ￐ﾺ￑ﾀ￑ﾋ￐ﾲ￐ﾰ￐ﾵ￐ﾼ ￐﾿￐ﾾ￑ﾂ￐ﾾ￐ﾺ ￐ﾷ￐ﾰ￐﾿￐ﾸ￑ﾁ￐ﾸ
        //FileOutputStream ￐ﾲ ￑ﾄ￐ﾰ￐ﾹ￐ﾻ demo.txt, ￐ﾸ￑ﾁ￐﾿￐ﾾ￐ﾻ￑ﾌ￐ﾷ￑ﾃ￑ﾏ try-￑ﾁ-￑ﾀ￐ﾵ￑ﾁ￑ﾃ￑ﾀ￑ﾁ￐ﾰ￐ﾼ￐ﾸ, ￐ﾸ ￑ﾁ ￐﾿￐ﾾ￐ﾼ￐ﾾ￑ﾉ￑ﾌ￑ﾎ ￐ﾼ￐ﾵ￑ﾂ￐ﾾ￐ﾴ￐ﾰ write() ￐ﾷ￐ﾰ￐﾿￐ﾸ￑ﾁ￑ﾋ￐ﾲ￐ﾰ￐ﾵ￐ﾼ
        //￑ﾁ￐ﾾ￐ﾴ￐ﾵ￑ﾀ￐ﾶ￐ﾸ￐ﾼ￐ﾾ￐ﾵ ￐ﾼ￐ﾰ￑ﾁ￑ﾁ￐ﾸ￐ﾲ￐ﾰ ￐ﾲ ￑ﾄ￐ﾰ￐ﾹ￐ﾻ. try-￑ﾁ-￑ﾀ￐ﾵ￑ﾁ￑ﾃ￑ﾀ￑ﾁ￐ﾰ￐ﾼ￐ﾸ ￐ﾰ￐ﾲ￑ﾂ￐ﾾ￐ﾼ￐ﾰ￑ﾂ￐ﾸ￑ﾇ￐ﾵ￑ﾁ￐ﾺ￐ﾸ ￐ﾷ￐ﾰ￐ﾺ￑ﾀ￐ﾾ￐ﾵ￑ﾂ ￐ﾽ￐ﾰ￑ﾈ ￐﾿￐ﾾ￑ﾂ￐ﾾ￐ﾺ ￐ﾷ￐ﾰ￐﾿￐ﾸ￑ﾁ￐ﾸ ￐ﾸ ￐﾿￑ﾀ￐ﾾ￐ﾳ￑ﾀ￐ﾰ￐ﾼ￐ﾼ￐ﾰ
        //￐ﾷ￐ﾰ￐ﾲ￐ﾵ￑ﾀ￑ﾈ￐ﾸ￑ﾂ￑ﾁ￑ﾏ.
        //
        // ***Result of value += new String(buf, "UTF-8");System.out.println("\nvalue: " + value);
        //*byte[5000]
        //value:
        //ABCABCABC
        //Давайте посмотрим на код, который позволит нам записать в файл слово Java. Вначале нам нужно
        //получить байтовое представление слова Java, создадим байтовый массив outData[] и заполним его с
        //помощью метода класса String getBytes(). После того как данные готовы, открываем поток записи
        //FileOutputStream в файл demo.txt, используя try-с-ресурсами, и с помощью метода write() записываем
        //содержимое массива в файл. try-с-ресурсами автоматически закроет наш поток записи и программа
        //завершится.
        //
        //*byte[500]
        // value:
        //ABCABCABC
        //Давайте посмотрим на код, который позволит нам записать в файл слово Java. Вначале нам нужно
        //получить байтовое представление слова Java, создадим байтовый массив outData[] и заполним его с
        //помощью метода класса String getBytes(). После того как данные готовы, открываем поток записи
        //FileOutputStream в файл demo.txt, используя try-с-ресурсами, и с помощью метода write() записываем
        //содержимое массива в файл. try-с-ресурсами автоматически закроет наш поток записи и программа
        //завершится.
        //помощью метода класса String getBytes(). После того как данные готовы, открываем поток записи
        //
        // *byte[50]
        //value:
        //ABCABCABC
        //Давайте посмотрим на код, который позволит нам з��писать в файл слово Java. Вна��але нам нужно
        //получить байтовое представление слова Java, создадим байтовый масси�� outData[] и заполним его с
        //пом��щью метода класса String getBytes(). После того как данные гот��вы, открываем поток записи
        //FileOutputStream в файл demo.txt, используя try-с-ресурсами, и с помощью метода write() записываем
        //содержимое массива в файл. try-с-ресурсами автоматическ�� закроет наш поток записи и программа
        //завершится.�си и





        /*Scanner scanner = new Scanner(in);
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
        }*/


        //byte[] fileData = "ABCABCABC".getBytes();
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
