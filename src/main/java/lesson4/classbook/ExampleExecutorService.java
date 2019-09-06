package lesson4.classbook;

/**
 * GBJava3
 * Java Core. Professional level. 19.08.2019 Webinar. Teacher: Anton Batiaev
 * Урок 4. Многопоточность. Часть I.
 * Методичка.
 * ExecutorService.
 * Создание ExecutorService:
 * ExecutorService executorService1 = Executors.newSingleThreadExecutor(); - создает пул,
 * в котором только один рабочий поток(на одну задачу). Просто поток не создается каждый раз заново.
 * ...newFixedThreadPool(10); - создает пул с фиксированным количеством потоков, в примере ниже
 * можно запустить одновременно выполнение не более 10 задач.
 * ...newCachedThreadPool(); - создает пул, который может автоматически расширяться, если ему
 * дать задачу и у него будут свободные потоки, пул отдаст задачу одному из таких потоков,
 * если же в пуле свободных потоков нет, он создаст и запустит новый. НЕТ ГРАНИЧЕНИЙ - может занять все ресурсы!
 * Использование.
 * Способы делегирования задач для выполнения в ExecutorService:
 * Интерфейсы:
 * Runnable - не возвращает результат(кроме submit);
 * Callable -
 * ● execute(Runnable) - Выполняет задачи асинхронно.
 * ● submit(Runnable) - Метод submit(Runnable) также принимает реализацию Runnable, но возвращает
 *    объект типа Future, который можно использовать для проверки завершенности выполнения задачи.
 * ● submit(Callable) - позволяет дать потоку задачу, но в отличие от Runnable, его метод call()
 *   может возвращать результат. Результат Callable может быть получен через объект Future,
 *   возвращенный методом submit.
 * ● invokeAny(...)
 * ● invokeAll(...)
 * ● shutdown() - перестанет принимать новые задачи, и как только все потоки завершат текущие задачи,
 *    ExecutorService отключится
 * ● shutdownNow() - попытается немедленно остановить все выполняемые задачи и пропустить
 *    все представленные, но не обработанные задачи.
 */
public class ExampleExecutorService {

    //***простой пример использования ExecutorService:***
//    С помощью фабричного метода newFixedThreadPool() создается объект типа ExecutorService.
//    Это пул потоков с 10 рабочими потоками, выполняющими задачи. В метод execute() передается
//    реализация интерфейса Runnable, в которой прописана задача, передаваемая на выполнение
//    одному из потоков ExecutorService.
//    public static void main(String[] args) {
//        ExecutorService executorService = Executors. newFixedThreadPool ( 10 );
//        executorService.execute( new Runnable() {
//            public void run() {
//                System. out .println( "Асинхронная задача" );
//            }
//        });
//        executorService.shutdown();
//    }

    //***простой пример использования метода execute(Runnable)***
//    public static void main(String[] args) {
//        ExecutorService executorService = Executors. newFixedThreadPool ( 10 );
//        executorService.execute( new Runnable() {
//            public void run() {
//                System. out .println( "Асинхронная задача" );
//            }
//        });
//        executorService.shutdown();
//    }

    //***простой пример использования метода submit (Runnable)***
//    public static void main(String[] args) throws Exception {
//        ExecutorService executorService = Executors.newFixedThreadPool ( 2 );
//        Future future = executorService.submit ( new Runnable() {
//            public void run () {
//                System . out . println ( "Асинхронная задача" );
//            }
//        });
//        future.get(); // вернет null если задача завершилась корректно
//        executorService.shutdown();
//    }

    //***простой пример использования метода submit (Callable)***
//    public static void main(String[] args) throws Exception {
//        ExecutorService executorService = Executors. newFixedThreadPool ( 2 );
//        Future future = executorService.submit( new Callable(){
//            public Object call() throws Exception {
//                System. out .println( "Асинхронный вызов" );
//                return "Результат из потока" ;
//            }
//        });
//        System. out .println( "future.get() = " + future.get());
//        executorService.shutdown();
//        //Результат:
//        //Асинхронный вызов
//        //future.get() = Результат из потока
//    }

}
