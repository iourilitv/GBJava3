package lesson1.classbook;

/**
 * GBJava3
 * Java Core. Profesional level. 19.08.2019 Webinar. Teacher: Anton Batiaev
 * Урок 1. Обобщения - Generics.
 * Понятие обобщения. Обобщенные классы, методы и интерфейсы.
 * Наследование обобщенных классов. Ограничения при работе с обобщениями.
 * Пример использования обобщений.
 * Использование метасимвольных аргументов < ? >
 * Как правильно - с < ? > в параметрах метода
 */
public class StatsDemoAppMetaOK {
    public static void main(String args[]) {
        StatsMetaOK<Integer> iStats = new StatsMetaOK<>(1, 2, 3, 4, 5);
        System.out.println( "Среднее iStats = " + iStats.avg());
        StatsMetaOK<Double> dStats = new StatsMetaOK<>(1.1, 2.2, 3.3, 4.4, 5.5);
        System.out.println( "Среднее dStats = " + dStats.avg());
        StatsMetaOK<Float> fStats = new StatsMetaOK<>(1.0f, 2.0f, 3.0f, 4.0f, 5.0f);
        System.out.println( "Среднее fStats = " + fStats.avg());
        System.out.print( "Средние iStats и dStats " );
        if (iStats.sameAvg(dStats)) {
            System.out.println( " равны" );
        } else {
            System.out.println( " отличаются" );
        }
        System.out.print( "Средние iStats и fStats" );
        if (iStats.sameAvg(fStats)) {
            System.out.println( "равны" );

        } else {
            System.out.println( "отличаются" );
        }
    }
}
//Результат.
//Среднее iStats = 3.0
//Среднее dStats = 3.3
//Среднее fStats = 3.0
//Средние iStats и dStats отличаются
//Средние iStats и fStats равны
