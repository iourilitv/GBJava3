package lesson4.hwTeacher;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * GBJava3
 * Java Core. Professional level. 19.08.2019 Webinar. Teacher: Anton Batiaev
 * Урок 4. Многопоточность. Часть I.
 * Домашняя работа.
 * @author Anton Batiaev
 * 1. Создать три потока, каждый из которых выводит определенную букву (A, B и C)
 * 5 раз (порядок – ABСABСABС).
 * Используйте wait/notify/notifyAll.
 * @since 02/09/19
 * Решение шире, чем в задании - универсальный вариант для разных тесктов и разного количества повторений.
 */
public class Controller {
    //входные параметры, которые можно менять в других вариантах задачи
    private int pos = 0;//позиция каретки, 0 - начальная позиция
    private static int COUNT = 5;//количество повторений текста(одно для всех)
    private final static List<String> list = Arrays.asList("A", "B", "C");//массив текстов
    private final static ExecutorService executorService =
            Executors.newFixedThreadPool(list.size());//пул потоков (по одному на каждый текст)

    public static List<String> getValues() {
        return list;
    }

    //геттер, возвращающий текущий элемент списка текстов
    String getCurrent() {
        return list.get(pos);
    }

    //метод перехода к следующему элементу списка в цикле(если достигли конца, то переходим к началу)
    void move() {
        pos = pos == list.size() - 1 ? 0 : pos + 1;
        //тернарный оператор. Если (pos == list.size() - 1): true, то pos = 0, нет - +1
        //TODO TO KNOW. Представить в виде if - Alt+Enter выбрать из списка Replace with 'if' statement.
        /*if (pos == list.size() - 1) pos = 0;
        else pos = pos + 1;*/
        //TODO TO KNOW. Представить обратно - Alt+Enter выбрать из списка Replace 'if else' with '?:' statement.
    }

    public static void main(String[] args) {
        //создаем экземпляр контроллера
        Controller controller = new Controller();
        //в цикле запускаем потоки для каждого элемента коллекции текстов
        //и одновременно создаем объекты текстового принтера для каждого элемента
        for (String symbol : list) {
            executorService.execute(
                    new TextPrinter(symbol, COUNT, controller,
                            System.out::print));
        }
        //вместо execute можно использовать submit, если нужен возврат результата
        //объект controller передаем для будущего функционала
        //System.out::print - лямбда выражение (строка?) для создания объекта класса Consumer,
        // что заменяет System.out.println();
        //Можно заменить на лямбду(Replace with forEach)
            /*list.stream().map(symbol -> new TextPrinter(symbol, COUNT, controller,
                    System.out::print)).forEach(executorService::execute);*/
        //На уроке изначально было
            /*list.forEach(symbol -> executorService.submit(
                    new TextPrinter(symbol, COUNT, controller)));*/

        //FIXME Не закрывается программа после печати. Добавить закрытие пула потоков
        //executorService.shutdown();
    }

}
