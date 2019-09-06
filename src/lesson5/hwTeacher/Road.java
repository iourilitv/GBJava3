package lesson5.hwTeacher;

import static lesson5.hwTeacher.LogUtil.log;

public class Road extends Stage {
    Road(int length) {
        this.length = length;
        this.description = "Дорога " + length + " метров";
    }

    @Override
    public void go(Car c) {
        try {
            log(c.getName() + " начал этап: " + description);
            Thread.sleep(length / c.getSpeed() * 1000);
            log(c.getName() + " закончил этап: " + description);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
