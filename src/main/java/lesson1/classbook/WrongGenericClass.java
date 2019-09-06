package lesson1.classbook;

/**
 * GBJava3
 * Java Core. Profesional level. 19.08.2019 Webinar. Teacher: Anton Batiaev
 * Урок 1. Обобщения - Generics.
 * Понятие обобщения. Обобщенные классы, методы и интерфейсы.
 * Наследование обобщенных классов. Ограничения при работе с обобщениями.
 * Пример использования обобщений.
 * Ограничения на статические члены.
 * Обобщения не работают со static и исключениями.
 * Нельзя объявить статические члены, использующие обобщенный тип. Но можно объявлять
 * обобщенные статические методы, определяющие их собственные параметры типа.
 * Ограничения обобщенных исключений.
 * Обобщенный класс не может расширять класс Throwable. Значит, создать обобщенные классы
 * исключений невозможно.
 */
public class WrongGenericClass<T> {
    //static Т data; // Неверно, нельзя создать статические переменные типа Т
    //static Т getData() { return data; } // Неверно, ни один статический метод не может использовать Т
}
