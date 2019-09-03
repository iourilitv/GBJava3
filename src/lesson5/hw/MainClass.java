package lesson5.hw;

/**
 * GBJava3
 * Java Core. Professional level. 19.08.2019 Webinar. Teacher: Anton Batiaev
 * Урок 5. Многопоточность. Часть II.
 * Классы для работы с многопоточностью. Concurrent collections.
 * Разбор практических примеров.
 * Домашняя работа.
 * @author Litvinenko Yuriy
 * Организуем гонки:
 * Все участники должны стартовать одновременно, несмотря на то, что на подготовку у каждого
 * из них уходит разное время.
 * В тоннель не может заехать одновременно больше половины участников (условность).
 * Попробуйте все это синхронизировать.
 * Только после того, как все завершат гонку, нужно выдать объявление об окончании.
 * Можете корректировать классы (в т.ч. конструктор машин) и добавлять объекты классов
 * из пакета util.concurrent.
 * Пример выполнения исходного кода до корректировки - ниже.
 * Что примерно должно получиться - ниже.
 */
public class MainClass {
    //константа количество участников гонки
    public static final int CARS_COUNT = 4;

    public static void main(String[] args) {
        //Объявление выйти на старт
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        //создаем объект гонка с этапами в параметрах
        Race race = new Race(new Road(60), new Tunnel(), new Road(40));
        //создаем массив участников гонки
        Car[] cars = new Car[CARS_COUNT];
        //наполняем массив объектами участников(машинами)
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10));
        }
        //запускаем процесс гонки//FIXME поставить barrierStart
        //запускаем потоки участников гонки
        for (int i = 0; i < cars.length; i++) {
            new Thread(cars[i]).start();
        }
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");

        //окончание гонки //FIXME поставить countDownLatch finish
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
    }
}
//***Пример выполнения кода до корректировки:***
//ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!
//Участник #2 готовится
//ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!
//ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!
//Участник #1 готовится
//Участник #4 готовится
//Участник #3 готовится
//Участник #3 готов
//Участник #3 начал этап: Дорога 60 метров
//Участник #2 готов
//Участник #2 начал этап: Дорога 60 метров
//Участник #1 готов
//Участник #1 начал этап: Дорога 60 метров
//Участник #4 готов
//Участник #4 начал этап: Дорога 60 метров
//Участник #3 закончил этап: Дорога 60 метров
//Участник #3 готовится к этапу (ждет): Тоннель 80 метров
//Участник #3 начал этап: Тоннель 80 метров
//Участник #2 закончил этап: Дорога 60 метров
//Участник #2 готовится к этапу (ждет): Тоннель 80 метров
//Участник #2 начал этап: Тоннель 80 метров
//Участник #1 закончил этап: Дорога 60 метров
//Участник #1 готовится к этапу (ждет): Тоннель 80 метров
//Участник #1 начал этап: Тоннель 80 метров
//Участник #4 закончил этап: Дорога 60 метров
//Участник #4 готовится к этапу (ждет): Тоннель 80 метров
//Участник #4 начал этап: Тоннель 80 метров
//Участник #2 закончил этап: Тоннель 80 метров
//Участник #2 начал этап: Дорога 40 метров
//Участник #3 закончил этап: Тоннель 80 метров
//Участник #3 начал этап: Дорога 40 метров
//Участник #2 закончил этап: Дорога 40 метров
//Участник #1 закончил этап: Тоннель 80 метров
//Участник #1 начал этап: Дорога 40 метров
//Участник #4 закончил этап: Тоннель 80 метров
//Участник #4 начал этап: Дорога 40 метров
//Участник #3 закончил этап: Дорога 40 метров
//Участник #1 закончил этап: Дорога 40 метров
//Участник #4 закончил этап: Дорога 40 метров

//***Что примерно должно получиться***
//ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!
//Участник #2 готовится
//Участник #1 готовится
//Участник #4 готовится
//Участник #3 готовится
//Участник #2 готов
//Участник #4 готов
//Участник #1 готов
//Участник #3 готов
//ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!
//Участник #2 начал этап: Дорога 60 метров                  //barrierStart
//Участник #4 начал этап: Дорога 60 метров
//Участник #3 начал этап: Дорога 60 метров
//Участник #1 начал этап: Дорога 60 метров
//Участник #1 закончил этап: Дорога 60 метров
//Участник #3 закончил этап: Дорога 60 метров
//Участник #3 готовится к этапу (ждет): Тоннель 80 метров  //Semaphore tunnel
//Участник #1 готовится к этапу (ждет): Тоннель 80 метров
//Участник #1 начал этап: Тоннель 80 метров
//Участник #3 начал этап: Тоннель 80 метров
//Участник #4 закончил этап: Дорога 60 метров
//Участник #4 готовится к этапу (ждет): Тоннель 80 метров
//Участник #2 закончил этап: Дорога 60 метров
//Участник #2 готовится к этапу (ждет): Тоннель 80 метров
//Участник #3 закончил этап: Тоннель 80 метров
//Участник #1 закончил этап: Тоннель 80 метров
//Участник #2 начал этап: Тоннель 80 метров
//Участник #4 начал этап: Тоннель 80 метров
//Участник #3 начал этап: Дорога 40 метров
//Участник #1 начал этап: Дорога 40 метров
//Участник #3 закончил этап: Дорога 40 метров
//Участник #3 - WIN
//Участник #1 закончил этап: Дорога 40 метров
//Участник #4 закончил этап: Тоннель 80 метров
//Участник #4 начал этап: Дорога 40 метров
//Участник #2 закончил этап: Тоннель 80 метров
//Участник #2 начал этап: Дорога 40 метров
//Участник #2 закончил этап: Дорога 40 метров
//Участник #4 закончил этап: Дорога 40 метров
//ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!   //countDownLatch finish
