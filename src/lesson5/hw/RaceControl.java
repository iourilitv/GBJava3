package lesson5.hw;

import java.util.concurrent.CyclicBarrier;

//TODO HW.Added
public class RaceControl implements Runnable{
    //передаем сюда количество участников в этой гонке
    private int carCount;
    //передаем сюда объект маршрута гонки с этапами (может выбираться в UI)
    private Route route;
    //объявляем объект гонка с этапами в параметрах
    private Race race;
    //объявляем объект барьера на стартовой линии
    private CyclicBarrier startLine;

    public RaceControl(int carCount, Route route) {
        this.carCount = carCount;
        this.route = route;
        //инициируем объект барьера на стартовой линии. Параметры: количество прав и действие
        startLine = new CyclicBarrier(carCount, this::goRacing);
        //стартуем новый поток
        new Thread(this).start();
    }

    @Override
    public void run() {
        //приглашаем участников на старт(инициируем объект гонки)
        inviteParticipantsToStartLine();
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
