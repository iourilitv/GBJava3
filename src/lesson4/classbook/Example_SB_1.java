package lesson4.classbook;

/**
 * GBJava3
 * Java Core. Professional level. 19.08.2019 Webinar. Teacher: Anton Batiaev
 * Урок 4. Многопоточность. Часть I.
 * Методичка.
 * Использование синхронизированных методов.
 * - Синхронизированный метод создается путем указания ключевого слова synchronized в его
 * объявлении. Как только синхронизированный метод любого объекта получает управление, объект
 * блокируется, и ни один синхронизированный метод этого объекта не может быть вызван другим
 * потоком.
 * - Потоки, которым требуется синхронизированный метод, используемый другим потоком, ожидают до
 * тех пор, пока не будет разблокирован объект, для которого он вызывается. Когда
 * синхронизированный метод завершается, объект, для которого он вызывался, разблокировался.
 * Пример 1.
 * При указании ключевого слова synchronized в объявлении метода, в роли монитора выступает объект,
 * у которого был вызван синхронизированный метод. То есть в приведенном выше примере два потока
 * не смогут параллельно выполнять method1() и method2().
 */
public class Example_SB_1 {
    public static void main(String[] args) {
        Example_SB_1 e1 = new Example_SB_1();
        new Thread(() -> e1 .method1()).start();
        new Thread(() -> e1 .method2()).start();
    }

    public synchronized void method1() {
        System. out .println( "M1" );
        for ( int i = 0 ; i < 10 ; i++) {
            System. out .println(i);
            try {
                Thread. sleep ( 100 );
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System. out .println( "M2" );
    }

    public synchronized void method2() {
        System. out .println( "M1" );
        for ( int i = 0 ; i < 10 ; i++) {
            System. out .println(i);
            try {
                Thread. sleep ( 100 );
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System. out .println( "M2" );
    }
}
