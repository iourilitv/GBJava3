package lesson4.hw;

/**
 * GBJava3
 * Java Core. Professional level. 19.08.2019 Webinar. Teacher: Anton Batiaev
 * Урок 4. Многопоточность. Часть I.
 * Домашняя работа.
 * @author Litvinenko Yuriy
 * DONE 1. Создать три потока, каждый из которых выводит определенную букву (A, B и C)
 * 5 раз (порядок – ABСABСABС).
 * Используйте wait/notify/notifyAll.
 */
public class Task1 {
    public static void main(String[] args) {
        new ABC();
    }
}

class ABC {
    //создаем объект монитора
    private final Object monitor = new Object();
    //инициализируем массив символов
    private char[] lettersArray = {'A', 'B', 'C'};
    //объявляем переменную для хранения текущего символа
    private volatile char currentLetter;
    //инициализируем массив потоков
    private Thread[] threadsArray = new Thread[lettersArray.length];

    public ABC() {
        //запускаем процесс начиная со стартового индекса элемента в массиве символов
        startABC(0);//351588519 = 0
    }

    //создаем и стартуем потоки, начиная с текущего символа
    private void startABC(int startIndex){
        //инициализируем стартовый символ, дополнительно приводим значение к длине массива
        currentLetter = lettersArray[startIndex % lettersArray.length];
        //инициализируем и стартуем потоки в количестве равной длине массива символов
        for (int i = 0; i < threadsArray.length; i++) {
            int finalI = i;//требование синтаксиса Thread
            threadsArray[i] = new Thread(() -> {
                //запускаем метод печати
                printChar(lettersArray, finalI);
            });
            threadsArray[i].start();
        }
    }

    //печатаем последовательно символы в гарантированной очередности
    private void printChar(char[] lettersArray, int num) {
        synchronized (monitor) {
            try {
                //запускаем цикл внутри потока
                for (int i = 0 ; i < 5 ; i++) {
                    //крутим цикл пока не встретим свой символ
                    while (currentLetter != lettersArray[num]) {
                        //захватываем монитор
                        monitor.wait();
                    }
                    //выводим в консоль свой символ
                    System.out.print(lettersArray[num]);
                    //сдвигаем индекс циклично на следующий символ в массиве символов
                    num = ++num % lettersArray.length;//++num = (num + 1) //num++ и += - инкрементирует только в конце строки
                    //присваем найденный символ текущему
                    currentLetter = lettersArray[num];
                    //освобождаем монитор
                    monitor.notify();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
//Result.
//ABCABCABCABCABC
