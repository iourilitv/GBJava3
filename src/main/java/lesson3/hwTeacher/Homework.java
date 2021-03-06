package lesson3.hwTeacher;

import java.io.*;
import java.util.*;

/**
 * GBJava3
 * Java Core. Professional level. 19.08.2019 Webinar. Teacher: Anton Batiaev
 * Урок 3. Средства ввода-вывода.
 * Домашняя работа.
 * Additional tasks.
 * 1. Прочитать файл (около 50 байт) в байтовый массив и вывести этот массив в консоль;
 * 2. Последовательно сшить 5 файлов в один (файлы примерно 100 байт).
 *      Может пригодиться следующая конструкция:
 *      List<InputStream> al = new ArrayList<>();
 *      ...
 *      Enumeration<InputStream> e = Collections.enumeration(al);
 * 3. Написать консольное приложение, которое умеет постранично читать текстовые файлы (размером > 10 mb).
 *    Вводим страницу (за страницу можно принять 1800 символов), программа выводит ее в консоль.
 *    Контролируем время выполнения: программа не должна загружаться дольше 10 секунд,
 *    а чтение – занимать свыше 5 секунд.
 * @author Anton Batiaev
 * @since 29/08/19
 */
public class Homework {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(Homework.class);
    private static File file = new File("src/lesson3/hwTeacher/resources/task3.txt");

    public static void main(String[] args) {
        //создаем случайно заполненные файлы
        //createFiles();

        //task1();
        task2();//FIXME записывает данные только из одного файла или перезаписывает одинаковые данные.Done
        //task3();
    }

    private static void task1() {
        File file = new File("src/lesson3/hwTeacher/resources/task1.txt");
        try (FileInputStream fromFile = new FileInputStream(file)) {
            byte[] arr = new byte[50];
            //читаем весь файл во временный массив
            // размер массива и файла должны совпадать! иначе прочитаем только первые байты.
            fromFile.read(arr);
            System.out.println(Arrays.toString(arr));//также можно записывать в log.info(Arrays.toString(arr));
            //Result. Выводит значение byte в десятичном представлении
            //[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49]
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void task2() {
        try (FileInputStream in1 = new FileInputStream("src/lesson3/hwTeacher/resources/task2_in1.txt");
             FileInputStream in2 = new FileInputStream("src/lesson3/hwTeacher/resources/task2_in2.txt");
             FileInputStream in3 = new FileInputStream("src/lesson3/hwTeacher/resources/task2_in3.txt");
             FileInputStream in4 = new FileInputStream("src/lesson3/hwTeacher/resources/task2_in4.txt");
             FileInputStream in5 = new FileInputStream("src/lesson3/hwTeacher/resources/task2_in5.txt");
             FileOutputStream all = new FileOutputStream("src/lesson3/hwTeacher/resources/task2_all.txt")) {

            Enumeration<InputStream> e = Collections.enumeration(
                    Arrays.asList(in1, in2, in3, in4, in5));
            SequenceInputStream seq = new SequenceInputStream(e);

            int c;

            //FIXME записывает данные только из одного файла или перезаписывает одинаковые данные.Deleted
            /*int available = seq.available();
            while (seq.available() > 0) {
                c = seq.read();
                all.write(c);
            }*/
            //FIXME записывает данные только из одного файла или перезаписывает одинаковые данные.Added
            while ((c = seq.read()) != -1) {//> 0 - does not work!
                System.out.println(c);
                all.write(c);
            }
            seq.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*private static void task2() {
        try (FileInputStream in1 = new FileInputStream("src/lesson3/hwTeacher/resources/task2_in1.txt");
             FileInputStream in2 = new FileInputStream("src/lesson3/hwTeacher/resources/task2_in2.txt");
             FileInputStream in3 = new FileInputStream("src/lesson3/hwTeacher/resources/task2_in3.txt");
             FileInputStream in4 = new FileInputStream("src/lesson3/hwTeacher/resources/task2_in4.txt");
             FileInputStream in5 = new FileInputStream("src/lesson3/hwTeacher/resources/task2_in5.txt");
             FileOutputStream all = new FileOutputStream("src/lesson3/hwTeacher/resources/task2_all.txt")) {

            //принимаем считанные файлы в виде коллекции и создаем объект перечисления входных стримов
            //Похоже кроме этого случая Enumeration больше нигде не используется, более новый - Iterator
            Enumeration<InputStream> e = Collections.enumeration(
                    Arrays.asList(in1, in2, in3, in4, in5));
            //принимает объект перечисления и сливает в один поток чтения данных
            SequenceInputStream seq = new SequenceInputStream(e);

            int c;
            int available; //= seq.available();

            int count = 0;
            System.out.println("available:");

            //while (seq.available() > 0) {//было
            //while (available > 0) {
            //while ((available = seq.available()) > 0) {
            //while (seq.available() == 0) {//empty file
            while (seq.available() != 0) {
                //System.out.print(available + " ");
                System.out.print(++count + " ");
                //читаем по байтно их потока перечисления
                c = seq.read();
                //записываем в файл
                all.write(c);
            }
            //закрываем стрим перечисления, т.к. он не входит в try-with-resource
            seq.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    //Result. infinity continuous circle if use while (available > 0)
    //available:
    //100 100 100 100 100 100 100 100 100 100 100 100 100 100 100 100 100 100 100 100 100 100 100 100 100 100 100 100 100 100 100 100 100 100 100 100 100 100 100 100 100 100 100 100 100 100 100100 100 100 100 100 100 100 100 100 100 100 100 100 100 100 100 100 100 100 100 100 100 100 100 100 100 100 100 100 100 100 100 100 100 100 100 100 100 100 100 100 100 100 100 100 100 100...
}*/

    private static void task3() {
        System.out.println("Введите номер страницы, чтобы узнать содержимое. Или /exit для выхода.");
        final int pageSize = 1800;//длина страницы в символах или байтах?
        final long pageCount = file.length() / pageSize;//длина файла в страницах
        System.out.println("Количество страниц: " + pageCount);
        while (true) {
            try {
                Scanner scanner = new Scanner(System.in);
                String s = scanner.next();//принимаем в консоль требуемый номер страницы
                if (s.equals("/exit")) return;//выходим из программы

                try {
                    int num = Integer.parseInt(s) - 1;
                    if (num < 0 || num > pageCount - 1) {
                        log.warn("Такой страницы не существует");
                        continue;
                    }

                    //создаем объект класса RandomAccessFile в режиме только чтение (mode: r)
                    RandomAccessFile accessToFile = new RandomAccessFile(file, "r");
                    //ищем первый символ на требуемой странице
                    accessToFile.seek(num * pageSize);
                    //читаем посимвольно страницу и выводим ее в консоль
                    for (int i = 0; i < pageSize; i++) {
                        System.out.print((char) accessToFile.read());
                    }
                    System.out.println();
                    //закрываем поток чтения файла
                    accessToFile.close();
                } catch (NumberFormatException e) {
                    log.warn("Вы ввели некорректные данные");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //метод создает случайно заполненные файлы
    private static void createFiles() {
        //запускаем потоки записи
        try (FileWriter outTask1 = new FileWriter("src/lesson3/hwTeacher/resources/task1.txt");
             FileWriter outTask2_1 = new FileWriter("src/lesson3/hwTeacher/resources/task2_in1.txt");
             FileWriter outTask2_2 = new FileWriter("src/lesson3/hwTeacher/resources/task2_in2.txt");
             FileWriter outTask2_3 = new FileWriter("src/lesson3/hwTeacher/resources/task2_in3.txt");
             FileWriter outTask2_4 = new FileWriter("src/lesson3/hwTeacher/resources/task2_in4.txt");
             FileWriter outTask2_5 = new FileWriter("src/lesson3/hwTeacher/resources/task2_in5.txt")) {
            //***Task1***
            // записываем значение переменной char символ со значением i в файл
            for (int i = 0; i < 50; i++) {
                outTask1.write(i);//!write() принимает char!
                outTask1.flush();//принудительно "сливаем", возможно нет autoflush в методе write.
            }
            //***Task2***
            //записываем значение переменной i в файлы
            for (int i = 0; i < 100; i++) {
                outTask2_1.write(i);
                outTask2_1.flush();
                outTask2_2.write(i);
                outTask2_2.flush();
                outTask2_3.write(i);
                outTask2_3.flush();
                outTask2_4.write(i);
                outTask2_4.flush();
                outTask2_5.write(i);
                outTask2_5.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //***Task3***
        // создаем файл из страниц, заполненных буквами английского алфавита
        try (BufferedWriter in = new BufferedWriter(new FileWriter(file))) {
            char c = 'a';
            //формируем структуру из 7000 страниц (//TODO 7_000 - что за синтаксис?)
            for (int j = 0; j < 7_000; j++) {
                //заполняем каждую страницу размером 1800 символов(одинаковые символы на всей странице)
                for (int i = 0; i < 1800; i++) {
                    //записываем символ, начиная с англ. a
                    in.write(c);
                }
                in.flush();
                //если достигли конца англ.алфавита, присваиваем значение a - начинаем с начала
                if (c == 'z') {
                    c = 'a';
                } else c++;//инкремент меняет значение на следующий по очереди элемент в алфавите (a на b, ..)
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
