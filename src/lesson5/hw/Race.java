package lesson5.hw;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Race {
    //объявляем коллекцию с этапами гонки в качестве элементов
    private ArrayList<Stage> stages;

    //TODO HW.Added
    //количество участников в этой гонке
    private int carCount;
    //объявляем объект счетчика, чтобы закрыть пул потоков после завершения задач во всех потоках
    private CountDownLatch countDownLatch;

    //геттер возвращаем коллекцию с этапами гонки в качестве элементов
    public ArrayList<Stage> getStages() { return stages; }

    //TODO HW.Added
    //инициализируем пул потоков по одному на каждого участника гонки(машину)
    private final ExecutorService executorService =
            Executors.newFixedThreadPool(carCount);

    //TODO HW.Added
    public ExecutorService getExecutorService() {
        return executorService;
    }

    //TODO HW.Added
    public CountDownLatch getCountDownLatch() {
        return countDownLatch;
    }

    //TODO HW.Deleted
    /*//в конструкторе гонки инициируем коллекцию с этапами гонки в качестве элементов
    //из переданных ему массива этапов гонки
    public Race(int carCount, Stage... stages) {
        this.stages = new ArrayList<>(Arrays.asList(stages));
    }*/
    //TODO HW.Added
    //в конструкторе гонки инициируем коллекцию с этапами гонки в качестве элементов
    //из переданных ему массива этапов гонки
    public Race(int carCount, Stage... stages) {
        this.stages = new ArrayList<>(Arrays.asList(stages));
        this.carCount = carCount;
    }

    //TODO HW.Added
    //инициируем параметры гонки
    public void initRace(){
        //создаем объект счетчика, чтобы закрыть пул потоков после завершения задач во всех потоках
        countDownLatch = new CountDownLatch(carCount);
        //создаем массив участников гонки
        Car[] cars = new Car[carCount];
        //наполняем массив объектами участников(машинами)
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(this, 20 + (int) (Math.random() * 10));
            //и запускаем потоки участников гонки
            executorService.execute(cars[i]);
        }
    }
}
