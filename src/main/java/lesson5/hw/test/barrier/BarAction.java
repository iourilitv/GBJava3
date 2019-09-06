package lesson5.hw.test.barrier;

// Объект этого класса вызывается по достижении барьера типа CyclicBarrier
class BarAction implements Runnable {
    public void run() {
        System.out.println("Барьер достигнут");
    }
}