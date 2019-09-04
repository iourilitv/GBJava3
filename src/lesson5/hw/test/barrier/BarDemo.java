package lesson5.hw.test.barrier;

// Продемонстрировать применение класса CyclicBarrier
//Источник https://pro-java.ru/parallelizm-v-java/klass-cyclicbarrier-primery-realizacii-koda-v-java/
import java.util.concurrent.*;

class BarDemo {
    public static void main(String args[]) {
        CyclicBarrier cb = new CyclicBarrier(3, new BarAction());

        System.out.println("Запуск потоков");

        new MyThread(cb, "A");
        new MyThread(cb, "B");
        new MyThread(cb, "C");
    }
}
//Result.
//Запуск потоков
//A
//B
//C
//Барьер достигнут
