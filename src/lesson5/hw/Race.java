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
    private ExecutorService executorService;

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
        initRace();
    }

    //TODO HW.Added
    //инициируем параметры гонки
    public void initRace(){
        //инициализируем пул потоков по одному на каждого участника гонки(машину)
        executorService = Executors.newFixedThreadPool(carCount);
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

    //TODO HW.Added
    public ExecutorService getExecutorService() {
        return executorService;
    }

    //TODO HW.Added
    public CountDownLatch getCountDownLatch() {
        return countDownLatch;
    }

}
