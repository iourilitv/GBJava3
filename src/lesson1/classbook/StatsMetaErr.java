package lesson1.classbook;

/**
 * GBJava3
 * Java Core. Profesional level. 19.08.2019 Webinar. Teacher: Anton Batiaev
 * Урок 1. Обобщения - Generics.
 * Понятие обобщения. Обобщенные классы, методы и интерфейсы.
 * Наследование обобщенных классов. Ограничения при работе с обобщениями.
 * Пример использования обобщений.
 * Использование метасимвольных аргументов < ? >
 */
//добавим в класс Stats метод sameAvg(), который будет проверять равенство
//средних значений массивов двух объектов типа Stats, независимо от того, какого типа числовые
//значения в них содержатся.
public class StatsMetaErr<T extends Number> {
    private T[] nums;

    public StatsMetaErr(T... nums) {
        this .nums = nums;
    }

    public double avg() {
        double sum = 0.0 ;
        for ( int i = 0 ; i < nums.length; i++) {
        // У nums[i] появился метод doubleValue() из класса Number,
        // который позволяет любой числовой объект привести к double
            sum += nums[i].doubleValue();
        }
        return sum / nums.length;
    }

    //Метод sameAvg() на вход должен принимать объект типа Stats и сравнивать его среднее значение со
    //средним значением вызывающего объекта.
    //Заметка. Чтобы не столкнуться с ошибкой округления при сравнении двух дробный чисел, мы
    //сравниваем средние значения в пределах дельты 0.0001
    public boolean sameAvg(StatsMetaErr<T> another) {
        return Math.abs( this .avg() - another.avg()) < 0.0001 ;
    }

}
