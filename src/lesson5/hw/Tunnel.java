package lesson5.hw;

public class Tunnel extends Stage {
    //FIXME поставить Semaphore tunnel.
    public Tunnel() {
        this.length = 80;
        this.description = "Тоннель " + length + " метров";
    }

    @Override
    public void go(Car c) {
        try {
            try {
                System.out.println(c.getName() + " готовится к этапу(ждет): " + description);
                //FIXME поставить Semaphore tunnel. Count + 1
                System.out.println(c.getName() + " начал этап: " + description);
                Thread.sleep(length / c.getSpeed() * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(c.getName() + " закончил этап: " + description);
                //FIXME поставить Semaphore tunnel. Count - 1
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}