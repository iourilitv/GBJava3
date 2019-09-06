package lesson1.classbook;

/**
 * GBJava3
 * Java Core. Profesional level. 19.08.2019 Webinar. Teacher: Anton Batiaev
 * Урок 1. Обобщения - Generics.
 * Понятие обобщения. Обобщенные классы, методы и интерфейсы.
 * Наследование обобщенных классов. Ограничения при работе с обобщениями.
 * Пример использования обобщений.
 * Ограниченные типы.
 * Создадим обобщенный класс, который содержит в себе массив (мы предполагаем что это будет
 * массивом чисел любого типа) и метод, возвращающий среднее значение этого массива.
 */
public class StatsDemoApp {
    public static void main(String args[]) {
        Stats<Integer> intStats = new Stats<Integer>( 1 , 2 , 3 , 4 , 5 );
        System.out.println( "Ср. знач. intStats равно " + intStats.avg());
        Stats<Double> doubleStats = new Stats<Double>( 1.0 , 2.0 , 3.0 , 4.0 , 5.0 );
        System.out.println( "Ср. знач. doubleStats равно " + doubleStats.avg());

        // Это не скомпилируется, потому что String не является подклассом Number
        // Stats<String> strStats = new Stats<>("1", "2", "3", "4", "5");
        // System.out.println("Ср. знач. strStats равно " + strStats.avg());
    }
}
//Результат.
//Ср. знач. intStats равно 3.0
//Ср. знач. doubleStats равно 3.0
