package lesson4.classfiles;

/**
 * GBJava3
 * Java Core. Professional level. 19.08.2019 Webinar. Teacher: Anton Batiaev
 * Урок 4. Многопоточность. Часть I
 * CalculationThread
 * @author anton
 * @since 29/08/19
 */
public class CalculationThread  extends Thread {

    @Override
    public void run() {
        System.out.println("Hello from calculation thread "+ Thread.currentThread().getName());
    }
}
