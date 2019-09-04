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
    //объявляем переменную для хранения времени финиша участника(относительно старта)
    private long participantFinishTime;
    //номер участника в гонке//TODO на будущее
    private int numberInRace;

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
        //ожидаем на старте и начинаем гонку по сигналу старта
        goRacing();
        //фиксируем время финиша участника(относительно старта)
        fixParticipantFinishTime();
        //добавляем свой результат в коллекцию результатов
        addResultIntoList();
        //проверяем победил или нет
        if(isWinner(this)){
            System.out.println("*** " + name + " has won the race!***");
        }
        //декрементируем счетчик по окончании гонки
        race.getCountDownLatch().countDown();
    }

    //TODO HW.Added
    //метод подготовки участников к гонке
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
        //ждем на линии старта всех участников и сигнала к старту
        try {
            race.getRaceControl().getStartLine().await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
        //начинаем гонку
        //листаем коллекцию с этапами гонки в качестве элементов
        for (Stage stage: race.getRoute().getStages()) {
            stage.go(this);
        }
    }

    //TODO HW.Added
    //метод возвращаем время прохождения участником всей гонки
    private long fixParticipantFinishTime() {
        return participantFinishTime = Math.round(System.currentTimeMillis() - race.getStartRaceTime());
    }

    //TODO HW.Added
    //добавляем свой результат в коллекцию результатов
    private void addResultIntoList() {
        race.getScoreboard().getRaceResults().add(this);
    }

    //TODO HW.Added
    //метод возвращает true, если участник первым записался в коллекцию результатов
    public boolean isWinner(Car car){
        return (race.getScoreboard().getRaceResults().get(0).equals(car));
    }

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    //TODO HW.Added
    public long getParticipantFinishTime() {
        return participantFinishTime;
    }
}
