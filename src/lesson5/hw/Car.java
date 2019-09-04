package lesson5.hw;

import java.util.concurrent.BrokenBarrierException;

public class Car implements Runnable {
    //TODO HW.Deleted?
    /*private static int CARS_COUNT;

    //TODO What is that?
    static {
        CARS_COUNT = 0;
    }*/

    private Race race;
    private int speed;
    private String name;

    //TODO HW.Added
    private int numberInRace;//номер участника в гонке

    //TODO HW.Deleted
    /*public Car(Race race, int speed) {
        this.race = race;
        this.speed = speed;

        //TODO What is that?
        //Для каждого следующего объекта счетчик будет на 1 больше
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
    }*/
    //TODO HW.Added
    public Car(Race race, int numberInRace, int speed) {
        this.race = race;
        this.speed = speed;
        this.numberInRace = numberInRace;
        this.name = "Участник #" + numberInRace;
    }

    //TODO HW.Deleted
    /*@Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int)(Math.random() * 800));
            System.out.println(this.name + " готов");
        } catch (Exception e) {
            e.printStackTrace();
        }
        //листаем коллекцию с этапами гонки в качестве элементов
        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
        }
    }*/
    //TODO HW.Added
    @Override
    public void run() {
        //готовимся к гонке
        getReadyForRace();

        //начинаем гонку
        goRacing();

        //декрементируем счетчик по окончании гонки
        race.getCountDownLatch().countDown();

        //TODO временно
        //System.out.println("***Car.race.getCountDownLatch().getCount(): " + race.getCountDownLatch().getCount());
    }

    private void getReadyForRace() {
        try {
            System.out.println(this.name + " идет к линии старта и готовится.");
            Thread.sleep(500 + (int)(Math.random() * 800));
            System.out.println(this.name + " готов к старту.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //TODO HW.Added
    //начинаем гонку
    public void goRacing (){

        //TODO ERR NotWorksBarrier.Added
        //ждем всех на линии старта
        try {
            race.getRaceControl().getStartLine().await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }

        //листаем коллекцию с этапами гонки в качестве элементов
        for (Stage stage: race.getRoute().getStages()) {
            stage.go(this);
        }
    }

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

}
