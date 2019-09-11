package lesson7.classbook;

import java.lang.reflect.Method;

public class ReflectionApp {
    /*public static void main(String[] args) {
        Class catClass = Cat.class;
        Field[] publicFields = catClass.getFields();
        for (Field o : publicFields) {
            System.out.println( "Тип_поля Имя_поля : " + o.getType().getName() + " " + o.getName());
        }
    }
//Result.
//Тип_поля Имя_поля : java.lang.String name
//Тип_поля Имя_поля : java.lang.String color
//Тип_поля Имя_поля : int age*/

    public static void main(String[] args) {
        Method[] methods = MyClass.class.getDeclaredMethods();
        for (Method o : methods) {
            if (o.getAnnotation(MarkingAnnotation.class) != null ) {
                System.out.println(o);
            }
        }
        //Result.
        //public void lesson7.classbook.MyClass.markedMethod()
    }
}
