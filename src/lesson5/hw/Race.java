package lesson5.hw;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Race {
    //TODO HW.Deleted
    /*//объявляем коллекцию с этапами гонки в качестве элементов
    private ArrayList<Stage> stages;*/

    //TODO HW.Added
    //передаем сюда количество участников в этой гонке
    private int carCount;
    //передаем коллекцию маршрута гонки с этапами (может выбираться в UI)
    private Route route;
    //передаем объект контроля гонки
    private RaceControl raceControl;

    //объявляем переменную для фиксации времени начала гонки
    private long startRaceTime;
    //объявляем пул потоков по одному на каждого участника гонки(машину)
    private ExecutorService executorService;
    //объявляем объект счетчика, чтобы закрыть пул потоков после завершения задач во всех потоках
    private CountDownLatch countDownLatch;
    //объявляем массив участников гонки
    private Car[] cars;
    //объявляем объект табло результатов гонки
    private Scoreboard scoreboard;

    //TODO HW.Deleted
    /*//в конструкторе гонки инициируем коллекцию с этапами гонки в качестве элементов
    //из переданных ему массива этапов гонки
    public Race(int carCount, Stage... stages) {
        this.stages = new ArrayList<>(Arrays.asList(stages));
    }*/
    //TODO HW.Added
    //в конструкторе гонки запускаем иницииализацию самой гонки
    Race(RaceControl raceControl, int carCount, Route route) {
        this.raceControl = raceControl;
        this.carCount = carCount;
        this.route = route;
        initRace();
    }

    //TODO HW.Added
    //инициируем параметры гонки
    private void initRace(){
        //инициализируем объект табло результатов гонки
        scoreboard = new Scoreboard(this);
        //инициализируем пул потоков по одному на каждого участника гонки(машину)
        executorService = Executors.newFixedThreadPool(carCount);
        //создаем объект счетчика, чтобы закрыть пул потоков после завершения задач во всех потоках
        countDownLatch = new CountDownLatch(carCount);
        //создаем массив участников гонки
        initParticipantsArray();
    }

    //TODO HW.Added
    //создаем массив участников гонки
    private void initParticipantsArray() {
        cars = new Car[carCount];
        //наполняем массив объектами участников(машинами)
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(this, i, 20 + (int) (Math.random() * 10));
            //и запускаем потоки участников гонки
            executorService.execute(cars[i]);
        }
    }

    //TODO HW.Added
    //метод возвращае локальное время начала гонки
    void fixStartRaceTime() {
        startRaceTime = System.currentTimeMillis();
    }

    //TODO HW.Added
    //Вывести результаты гонки
    void showRaceResults() {
        System.out.println("Result of the race: ");
        //на всякий случай сортируем коллекцию перед выводом
        scoreboard.sortRaceResults();
        //пробегаем циклом по коллекции результатов
        int count = 1;
        for (Car car: scoreboard.getRaceResults()) {
            System.out.println(count++ + " place: " + car.getName() +
                    " with time: " + ((double)car.getParticipantFinishTime() / 1000) + " sec.");
        }
    }

    //TODO HW.Deleted
    /*//геттер возвращаем коллекцию с этапами гонки в качестве элементов
    public ArrayList<Stage> getStages() { return stages; }*/

    //TODO HW.Added
    public Car[] getCars() {
        return cars;
    }

    //TODO HW.Added
    ExecutorService getExecutorService() {
        return executorService;
    }

    //TODO HW.Added
    CountDownLatch getCountDownLatch() {
        return countDownLatch;
    }

    //TODO HW.Added
    Route getRoute() {
        return route;
    }

    //TODO HW.Added
    RaceControl getRaceControl() {
        return raceControl;
    }

    //TODO HW.Added
    long getStartRaceTime() {
        return startRaceTime;
    }

    //TODO HW.Added
    Scoreboard getScoreboard() {
        return scoreboard;
    }
}
