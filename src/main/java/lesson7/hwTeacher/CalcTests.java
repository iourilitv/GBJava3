package lesson7.hwTeacher;

//import sun.jvm.hotspot.StackTrace;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;

//import java.util.Vector;
//запуск самих тестов
public class CalcTests {

    @BeforeSuite()
    @SuppressWarnings("unused")
    public void beforeTests() {
        System.out.println("Start testing...");
    }

    @Test(priority = 4)
    @SuppressWarnings("unused")//подавить варнинг не используется
    //после сборки проекта эта аннотация выглядит - /unused/
    //это чтобы IDEA не подсвечивала и не указывала на warnings
    public void addTest() {
        //TODO в RunTime. Но лучше так не делать - очень затратно
        //здесь пример, как обратиться к методу(и к его аннотациям) непосредственно из метода во время его работы
        //способ 1. Через текущий поток в стеке
        final String methodName = Thread.currentThread()
                .getStackTrace()[1].getMethodName();
        //Способ 2. Через объект исключения найти его в стеке.
//        final String methodName = new Exception()
//                .getStackTrace()[0].getMethodName();
        //TODO Как обратится из самого метода к его аннотации
        final Method[] declaredMethods = this.getClass().getDeclaredMethods();

        Method method = null;
        for (Method declaredMethod : declaredMethods) {
            if (declaredMethod.getName().equals(methodName)) {
                method = declaredMethod;
                break;
            }
        }
        final Annotation[] addTestsAnnotations = method.getAnnotations();
        System.out.println(Arrays.toString(addTestsAnnotations));

        System.out.println("Тестируем сложение...");
    }

    @Test(priority = 8)
    @SuppressWarnings("unused")
    public void mulTest() {
        System.out.println("Тестируем умножение...");
    }

    @Test(priority = 8)
    @SuppressWarnings("unused")
    public void divTest() {
        System.out.println("Тестируем деление...");
    }

    @Test
    @SuppressWarnings("unused")
    public void subTest() {
        System.out.println("Тестируем вычитание...");
    }

    @AfterSuite
    @SuppressWarnings("unused")
    public void afterTests() {
        System.out.println("Testing finished");
    }
}
