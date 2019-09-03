package lesson5.hw;

public class Car implements Runnable {
    //TODO HW.Deleted
    private static int CARS_COUNT;

    //TODO What is that?
    static {
        CARS_COUNT = 0;
    }

    private Race race;
    private int speed;
    private String name;

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    //TODO HW.Added
    public static int getCarsCount() {
        return CARS_COUNT;
    }

    public Car(Race race, int speed) {
        this.race = race;
        this.speed = speed;

        //TODO What is that?
        //Для каждого следующего объекта счетчик будет на 1 больше
        CARS_COUNT++;

        this.name = "Участник #" + CARS_COUNT;

        //TODO HW.Added

    }

    @Override
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
    }
}
