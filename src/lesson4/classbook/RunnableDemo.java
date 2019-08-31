package lesson4.classbook;

/**
 * GBJava3
 * Java Core. Professional level. 19.08.2019 Webinar. Teacher: Anton Batiaev
 * Урок 4. Многопоточность. Часть I.
 * Методичка.
 * Изменение состояний потоков исполнения.
 * Методы в класса Thread:
 * - УСТАРЕЛ suspend(), подвесить, способен порождать серьезные системные
 * сбои - в момент приостановки блокировки не будут сняты, что может привести к взаимной блокировке;
 * - УСТАРЕЛ resume(), продолжить, им нельзя пользоваться без метода suspend(), который его дополняет;
 * - УСТАРЕЛ stop(), снимает все блокировки и если данные записаны не полностью, то другой поток может считать их не верно.
 * - wait()
 * - notify()
 * - notifyAll()
 * - getState() вернет значение типа Thread.State со значениями:
 * Возможные состояния:
 * ● BLOCКED – поток приостановил выполнение, поскольку ожидает получения блокировки;
 * ● NEW – поток еще не начал выполнение;
 * ● RUNNAВLE – поток выполняется или начнет выполняться, когда получит доступ к ЦП;
 * ● TERМINATED – поток завершил выполнение;
 * ● TIМED WAITING – поток приостановил выполнение на определенное время (например, после
 *    вызова метода sleep() , wait() или join() );
 * ● WAITING – поток приостановил выполнение, ожидая определенного действия (например,
 *    вызова версии метода wait() или join() без заданного времени ожидания).
 * Пример.
 * Как исполнение потока приостанавливается и возобновляется с применением методов wait() и notify(),
 * для управления выполнением потока.
 * Класс RunnableClass содержит переменную suspended, используемую для управления выполнением потока.
 * Метод run() содержит блок оператора synchronized, где проверяется состояние переменной suspended.
 * Если она принимает логическое значение true, то вызывается метод wait() для приостановки
 * выполнения потока. В методе mySuspend() устанавливается true переменной suspendFlag, а
 * в методе myResume() - false и вызывается метод notify(), чтобы активизировать поток исполнения.
 */
public class RunnableDemo {
    static class RunnableClass implements Runnable {
        boolean suspended = false ;
        public void run() {
            System. out .println( "Запуск потока" );
            try {
                for ( int i = 10 ; i > 0 ; i--) {
                    System. out .println(i);
                    Thread. sleep ( 300 );
                    synchronized ( this ) {
                        while ( suspended ) {
                            wait();
                        }
                    }
                }
            } catch (InterruptedException e) {
                System. out .println( "Поток прерван" );
            }
            System. out .println( "Завершение потока" );
        }

        public void mySuspend() {
            suspended = true ;
        }

        public synchronized void myResume() {
            suspended = false ;
            notify();
        }
    }

    public static void main(String[] args) {
        RunnableClass rc = new RunnableClass();
        new Thread(rc).start();
        try {
            Thread. sleep ( 800 );
            rc.mySuspend();
            Thread. sleep ( 3000 );
            rc.myResume();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
