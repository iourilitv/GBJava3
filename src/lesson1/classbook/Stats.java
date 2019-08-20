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

//используем ограничение сверху, чтобы исправить класс Stats, указав класс Number
// как верхнюю границу используемого параметра типа
//Объявление public class Stats<T extends Number> сообщает компилятору, что все объекты типа Т
//являются подклассами класса Number, и поэтому могут вызывать метод doubleValue(), как и любой
//другой из класса Number. Ограничивая параметр Т, мы предотвращаем создание нечисловых
//объектов класса Stats.
public class Stats<T extends Number> {
    private T[] nums;

    public Stats(T... nums) {
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
}

//ОШИБКА
/*public class Stats<T> {
    private T[] nums;

    public Stats(T... nums) {
        this .nums = nums;
    }

    public double avg() {
        double sum = 0.0 ;
        for ( int i = 0 ; i < nums.length; i++) {
            //sum += nums[i]; // Ошибка
        }
        return sum / nums.length;
    }
}*/
//Важно! В роли ограничителя сверху может выступать не только класс, но и один или несколько
//интерфейсов. Для указания нескольких элементов используется оператор &.
//<T extends Cat>
//<T extends Animal & Serializable>
//<T extends Serializable>
//<T extends Cloneable & Serializable>
//Обратите внимание, что даже если вы ограничиваете T интерфейсом, все равно используется
//ключевое слово extends.
//Если в качестве ограничителя используется класс и интерфейс, то класс должен быть указан
//первым.