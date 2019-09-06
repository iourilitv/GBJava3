package lesson1.classbook;

/**
 * GBJava3
 * Java Core. Profesional level. 19.08.2019 Webinar. Teacher: Anton Batiaev
 * Урок 1. Обобщения - Generics.
 * Понятие обобщения. Обобщенные классы, методы и интерфейсы.
 * Наследование обобщенных классов. Ограничения при работе с обобщениями.
 * Пример использования обобщений.
 * Использование метасимвольных аргументов < ? >
 * Как неверно
 */
public class StatsDemoAppMetaErr {
    public static void main(String args[]) {
        //Ошибка.
        /*StatsMetaErr<Integer> intStats = new StatsMetaErr<>( 1 , 2 , 3 , 4 , 5 );
        StatsMetaErr<Double> doubleStats = new StatsMetaErr<>( 1.0 , 2.0 , 3.0 , 4.0 , 5.0 );

        if (intStats.sameAvg(doubleStats)) {
            System.out.println( "Средние значения равны" );
        } else {
            System.out.println( "Средние значения не равны" );
        }*/

        //Такой код будет работать только с объектом класса Stats, тип которого совпадает с вызывающим
        //объектом
        StatsMetaErr<Integer> intStatsME1 = new StatsMetaErr<>( 1 , 2 , 3 , 4 , 5 );
        StatsMetaErr<Integer> intStatsME2 = new StatsMetaErr<>( 2 , 1 , 3 , 4 , 5 );
        StatsMetaErr<Double> doubleStatsME = new StatsMetaErr<>( 1.0 , 2.0 , 3.0 , 4.0 , 5.0 );

        System.out.println(intStatsME1.sameAvg(intStatsME2)); // Так работает

        // System.out.println(intStats1.sameAvg(doubleStats)); // Ошибка
        // (T = Integer) != (T = Double)
    }
}
//Результат.

