package lesson5.hw;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

//TODO HW.Added
public class RaceControl extends Thread {
    //количество участников в этой гонке
    private int carCount;
    //массив маршрута гонки с этапами (может выбираться в UI)
    private Route route;
    //объявляем объект гонка с этапами в параметрах
    private Race race;

    //объявляем объект барьера на стартовой линии
    private CyclicBarrier startLine;

    public RaceControl(int carCount, Route route) {
        this.carCount = carCount;
        this.route = route;
        //TODO ERR NotWorksBarrier.Added
        //инициируем объект барьера на стартовой линии
        startLine = new CyclicBarrier(carCount);
    }

    @Override
    public void run() {
        //приглашаем участников на старт(инициируем объект гонки)
        inviteParticipantsToStartLine();

        //собираем всех участников на линии старта.
        onStartLineWaiting();

        //запускаем процесс гонки
        goRacing();

        //завершаем гонку
        finishRace();
    }

    //приглашаем участников на старт
    private void inviteParticipantsToStartLine() {
        //Объявление выйти на старт
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        //создаем объект гонка с этапами в параметрах
        race = new Race(this, carCount, route);
    }

    //ждем остальных на линии старта
    private void onStartLineWaiting(){
        try {
            startLine.await();
            Thread.sleep(100);
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    //запускаем процесс гонки
    private void goRacing() {
        //сигнал о начале гонки
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
    }

    //завершаем гонку
    private void finishRace() {
        try {
            //как только счетчик обнулится
            race.getCountDownLatch().await();
            //закрываем пул потоков
            race.getExecutorService().shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //объявляем об окончании гонки
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
    }

    public CyclicBarrier getStartLine() {
        return startLine;
    }
}
