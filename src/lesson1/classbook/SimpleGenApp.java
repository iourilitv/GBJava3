package lesson1.classbook;

/**
 * GBJava3
 * Java Core. Profesional level. 19.08.2019 Webinar. Teacher: Anton Batiaev
 * Урок 1. Обобщения - Generics.
 * Понятие обобщения. Обобщенные классы, методы и интерфейсы.
 * Наследование обобщенных классов. Ограничения при работе с обобщениями.
 * Пример использования обобщений.
 * Обобщенный класс с несколькими параметрами типа.
 *
 */

public class SimpleGenApp {
    public static void main(String args[]) {
        TwoGen<Integer, String> twoGenObj = new TwoGen<Integer, String>( 555 , "Hello" );
        twoGenObj.showTypes();
        int intValue = twoGenObj.getObj1();
        String strValue = twoGenObj.getObj2();
        System.out.println(intValue);
        System.out.println(strValue);
    }
}

//Результат.
//Тип T: java.lang.Integer
//Тип V: java.lang.String
//555
//Hello
