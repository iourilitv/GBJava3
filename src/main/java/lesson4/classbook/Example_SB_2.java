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
 * Пример 2.
 * Способ синхронизации основан на использовании отдельных объектов типа java.lang.Object в
 * качестве мониторов.
 * В этом случае в роли монитора выступает объект lock1, соответственно два потока смогут
 * параллельно выполнять первую часть метода method1(), однако в блок синхронизации в единицу
 * времени может зайти только один поток, так как происходит захват монитора lock1.
 */
public class Example_SB_2 {
    private Object lock1 = new Object();
    public static void main(String[] args) {
        Example_SB_2 e2 = new Example_SB_2();
        System. out .println( "Старт main потока" );
        new Thread(() -> e2 .method1()).start();
        new Thread(() -> e2 .method1()).start();
    }
    public void method1() {
        System. out .println( "Метод запущен" );
        System. out .println( "Блок 1 начало" );
        for ( int i = 0 ; i < 3 ; i++) {
            System. out .println(i);
            try {
                Thread. sleep ( 100 );
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System. out .println( "Блок 1 конец" );
        synchronized ( lock1 ) {
            System. out .println( "Начало синхронизированного блока" );
            for ( int i = 0 ; i < 10 ; i++) {
                System. out .println(i);
                try {
                    Thread. sleep ( 100 );
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System. out .println( "Конец синхронизированного блока" );
        }
        System. out .println( "Метод завершил свою работу" );
    }
}
