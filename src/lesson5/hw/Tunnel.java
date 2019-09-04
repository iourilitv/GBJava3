package lesson5.hw;

import java.util.concurrent.Semaphore;

public class Tunnel extends Stage {
    //TODO HW.Adding Semaphore in tunnel.Added
    //создаем объект семафор с заданным лимитом доступа в параметре
    Semaphore semaphore = new Semaphore( 2 );

    public Tunnel() {
        this.length = 80;
        this.description = "Тоннель " + length + " метров";
    }

    @Override
    public void go(Car c) {
        try {
            try {
                System.out.println(c.getName() + " готовится к этапу(ждет): " + description);

                //TODO HW.Adding Semaphore in tunnel.Added
                //поток участника(машины) запрашивает у семафора право на ресурс и
                // ждем пока не дано право прохода(значение счетчика больше 0)
                semaphore.acquire();

                System.out.println(c.getName() + " начал этап: " + description);

                //TODO HW.Adding Semaphore in tunnel.Added
                System.out.println("***Свободно мест в тоннеле: " + semaphore.availablePermits());;

                Thread.sleep(length / c.getSpeed() * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(c.getName() + " закончил этап: " + description);

                //TODO HW.Adding Semaphore in tunnel.Added
                //освобождаем тоннель для одного следующего участника
                // (счетчик семафора +1, но не больше разрешенных permits)
                semaphore.release();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}