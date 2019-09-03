package lesson5.classbook;

import java.util.concurrent.CyclicBarrier;

/**
 * GBJava3
 * Java Core. Professional level. 19.08.2019 Webinar. Teacher: Anton Batiaev
 * Урок 5. Многопоточность. Часть II.
 * Классы для работы с многопоточностью. Concurrent collections.
 * Разбор практических примеров.
 * Классы синхронизации. CyclicBarrier. Задача: одновременный старт гонки по готовности всех участников.
 * CyclicBarrier выполняет синхронизацию заданного количества потоков в одной точке. Как только
 * заданное количество потоков заблокировалось (вызовами метода await()) , с них одновременно
 * снимается блокировка.
 * Потоки закончили подготовку в разное время, но стартовали вместе, так как блокировка снимается
 * одновременно.
 */
public class BarrierExample {
    public static void main (String[] args) {
        CyclicBarrier cb = new CyclicBarrier( 3 );
        for ( int i = 0 ; i < 3 ; i++) {
            final int w = i;
            new Thread(() -> {
                try {
                    System.out.println( "Поток " + w + " готовится" );
                    Thread.sleep( 100 + ( int ) ( 3000 * Math.random()));
                    System.out.println( "Поток " + w + " готов" );
                    //на этом барьере все потоки ждут пока все не будут готовы
                    cb.await();
                    // все проходят одновременно, когда барьер снят
                    System.out.println( "Поток " + w + " запустился" );
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
//Result.
//Поток 0 готовится
//Поток 2 готовится
//Поток 1 готовится
//Поток 0 готов
//Поток 1 готов
//Поток 2 готов
//Поток 0 запустился
//Поток 1 запустился
//Поток 2 запустился