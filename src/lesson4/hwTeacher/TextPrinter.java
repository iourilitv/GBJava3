package lesson4.hwTeacher;

import java.util.function.Consumer;

/**
 * TextPrinter
 *
 * @author anton
 * @since 02/09/19
 */
public class TextPrinter implements Runnable {
    //объявляем
    private final Controller monitor;//экземпляр объекта монитора
    private final int count;//количество повторений
    private final String text;//наша строка, которую он будет печатать
    private final Consumer<String> action;//объект сервиса вывода текста

    TextPrinter(String text, int count, Controller monitor,
                Consumer<String> action) {
        this.text = text;
        this.monitor = monitor;
        this.count = count;
        this.action = action;
    }

    @Override
    public void run() {
        synchronized (monitor) {//синхронизируемся на мониторе
            //проходим в цикле на количество повторений
            for (int i = 0; i < count; i++) {
                try {
                    //ждем пока текущий элемент в списке текстов еще не наш
                    while (!monitor.getCurrent().equals(text)) {
                        monitor.wait();
                    }
                    //выводим в сервис вывода наш текст
                    action.accept(text);//здесь = System.out.println(text);
                    //чтобы визуализировать, добавим паузы
                    Thread.sleep(100);
                    //сдвигаемся на следующий элемент списка текстов
                    monitor.move();
                    //будим все потоки
                    monitor.notifyAll();

                    //TODO ExecutorService shutdown adding.Added
                    //декрементируем многопоточный счетчик
                    monitor.getCountDownLatch().countDown();
                } catch (Exception e) {//ловим исключения
                    System.out.println("Step "+ i
                            + " failed with exception: " + e.getMessage());
                }
            }
        }
    }
}
