package lesson1.classbook;

/**
 * GBJava3
 * Java Core. Profesional level. 19.08.2019 Webinar. Teacher: Anton Batiaev
 * Урок 1. Обобщения - Generics.
 * Понятие обобщения. Обобщенные классы, методы и
 * интерфейсы. Наследование обобщенных классов. Ограничения
 * при работе с обобщениями.
 * Пример без обобщений.ERR ClassCastException.
 */
public class BoxDemoApp {
    public static void main(String[] args) {

        //создаем две коробки intBox1 и intBox2, в которые при
        //инициализации складываем значения 20 и 30
        SimpleBox intBox1 = new SimpleBox( 20 );
        SimpleBox intBox2 = new SimpleBox( 30 );

        //проверяем, что изъятый объект является экземпляром(instanceof) класса
        //целочисленных значений, чтобы не получить исключение ClassCastException
        // при сложении обектов.
        if (intBox1.getObj() instanceof Integer && intBox2.getObj() instanceof
                Integer) {

            //приводим объекты к целочисленному значению и суммируем
            int sum = (Integer)intBox1.getObj() + (Integer)intBox2.getObj();
            System.out.println( "sum = " + sum);
        } else {
            System.out.println( "Содержимое коробок отличается по типу" );
        }
// вызвали какой-нибудь метод, которому отдали intBox1
// и этот метод кладет в коробку String
        intBox1.setObj( "Java" );
// продолжаем наш код, и при выполнении получим ClassCastException
        int secondSum = (Integer)intBox1.getObj() + (Integer)intBox2.getObj();
    }
}
