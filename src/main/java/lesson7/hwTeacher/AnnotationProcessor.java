package lesson7.hwTeacher;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
//import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

import static lesson7.hwTeacher.Test.MAX_PRIORITY;
import static lesson7.hwTeacher.Test.MIN_PRIORITY;

/**
 * Test executor
 *
 * @author batiaev
 */
public class AnnotationProcessor {

    public static void main(String[] args) {
        //запускаем статический метод с переданным ему классом
        start(CalcTests.class);
    }

    {
        /*
         *
         *
         *
         *
         *
         *
         *
         *
         *
         *
         *
         *
         *
         *
         *
         *
         *
         *
         *
         *
         *
         *
         *
         *
         *
         */
    }

    //перегрузка метода со строкой-именем класса в аргументе
    //он запускает другой метод start, с переданым ему уже классом, соответствующему имени в аргументе
    public static void start(String className) throws ClassNotFoundException {
        start(Class.forName(className));
    }
    //перегрузка метода с самим классом в аргументе
    //передаем класс в метод обработки логики программы
    public static void start(Class<?> classObj) {
        runTests(classObj);
    }

    private static <T> void runTests(Class<T> clazz) {
        //объявляем объект тестового класса
        final T testClass;
        //инициализируем два метода, выполняемые в начале и конце теста
        Method beforeTestsMethod = null;
        Method afterTestsMethod = null;
        //инициализируем набор методов в отсортированном по компаратору виде(здесь по возрастанию)
        Set<Method> testMethods = new TreeSet<>(new TestComparator());

        try {
            //создаем новый объект переданного в параметре класса, что может выкинуть исключение
            testClass = clazz.newInstance();
            //инициализируем массив со всеми задекларированными методами класса, с любым модификатором доступа
            Method[] declaredMethods = clazz.getDeclaredMethods();
            //листаем массив методов
            for (Method method : declaredMethods) {
                //если у текущего метода есть аннотация BeforeSuite
                if (method.isAnnotationPresent(BeforeSuite.class)) {
                    //и он еще не появлялся в листинге, запоминаем его
                    if (beforeTestsMethod == null) beforeTestsMethod = method;
                    //если это метод с такой аннотацией уже есть, выбрасываем исключение
                    else throw new CustomTestException("BeforeTestsMethod already exist");
                //*аналогично делаем для аннотации AfterSuite
                } else if (method.isAnnotationPresent(AfterSuite.class)) {
                    if (afterTestsMethod == null) afterTestsMethod = method;
                    else throw new CustomTestException("AfterTestsMethod already exist");
                //если метод не имеет этих аннотаций
                } else {
                    //инициализируем аннотацию Test и запоминаем в нее аннотацию текущего метода
                    final Test annotation = method.getAnnotation(Test.class);
                    //на всякий случай проверяем создан ли объект аннотации
                    if (annotation != null) {
                        //инициализируем приоритет в аннотации Test и запоминаем в нее приоритет текущего метода
                        final int priority = annotation.priority();
                        //проверяем не выходит ли значение приоритета за разрешенный диапазон
                        if (priority < MIN_PRIORITY || priority > MAX_PRIORITY)
                            //если выходит, выбрасываем исключение
                            throw new CustomTestException("invalid priority value: " + priority
                                    + ", should be in range [" + MIN_PRIORITY + "," + MAX_PRIORITY + "]");
                        else
                            //если - в диапазоне, добавляем в набор
                            testMethods.add(method);
                    }
                }
            }
            //если есть метод с аннотация BeforeSuite, запускаем его для переданного ему объекта класса
            if (beforeTestsMethod != null) beforeTestsMethod.invoke(testClass);
            //пролистываем набор методов(с аннотацией Test)
            for (Method method : testMethods) {
                //и запускаем их(они уже отсортированы в наборе)
                method.invoke(testClass);
            }
            //если есть метод с аннотация AfterSuite, запускаем его
            if (afterTestsMethod != null) afterTestsMethod.invoke(testClass);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    //статический класс, позволяющий отделять наше исключение от RuntimeException
    private static class CustomTestException extends RuntimeException {
        CustomTestException(String value) {
            super(value);
        }
    }
}
