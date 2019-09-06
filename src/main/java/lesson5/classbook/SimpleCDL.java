package lesson5.classbook;

import java.util.concurrent.CountDownLatch;

/**
 * GBJava3
 * Java Core. Professional level. 19.08.2019 Webinar. Teacher: Anton Batiaev
 * Урок 5. Многопоточность. Часть II.
 * Классы для работы с многопоточностью. Concurrent collections.
 * Разбор практических примеров.
 * Классы синхронизации. CountDownLatch. Задача: закрытие пула потоков после выполнения задач всеми потоками.
 * CountDownLatch позволяет потоку ожидать завершения операций, выполняющихся в других потоках.
 * Режим ожидания запускается методом await(). При создании объекта определяется количество
 * требуемых операций, после чего уменьшается при вызове метода countDown(). Как только счетчик
 * доходит до нуля, с ожидающего потока снимается блокировка.
 * Основной поток создает 6 потоков и ждет, пока каждый из них не закончит приготовление к работе.
 */
public class SimpleCDL {
    public static void main (String[] args) {
        // задаем количество потоков
        final int THREADS_COUNT = 6 ;
        // задаем значение счетчика
        final CountDownLatch cdl = new CountDownLatch(THREADS_COUNT);
        System.out.println( "Начинаем" );
        for ( int i = 0 ; i < THREADS_COUNT; i++) {
            final int w = i;
            new Thread(() -> {
                try {
                    // считаем, что выполнение задачи занимает ~1 сек
                    Thread.sleep( 500 + ( int )( 500 * Math.random()));
                    // как только задача выполнена, уменьшаем счетчик на 1
                    cdl.countDown();
                    System.out.println( "Поток #" + w + " - готов" );
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        try {
            // пока счетчик не приравняется нулю, будем стоять на этой строке
            cdl.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // как только все потоки выполнили свои задачи - пишем сообщение
        System.out.println( "Работа завершена" );
    }
}
//Result.
//Начинаем
//Поток #0 - готов
//Поток #5 - готов
//Поток #2 - готов
//Поток #1 - готов
//Поток #3 - готов
//Поток #4 - готов
//Работа завершена