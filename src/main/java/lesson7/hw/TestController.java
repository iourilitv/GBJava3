package lesson7.hw;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * GBJava3
 * Java Core. Professional level. 19.08.2019 Webinar. Teacher: Anton Batiaev
 * Урок 7. Reflection API и аннотации.
 * Домашняя работа.
 * @author Litvinenko Yuriy
 * Создать класс, который может выполнять «тесты». В качестве тестов выступают классы с
 * наборами методов с аннотациями @Test. Для этого у него должен быть статический метод start(),
 * которому в качестве параметра передается или объект типа Class, или имя класса.
 * Из «класса-теста» вначале должен быть запущен метод с аннотацией @BeforeSuite, если такой имеется.
 * Далее запущены методы с аннотациями @Test, а по завершении всех тестов – метод с
 * аннотацией @AfterSuite. К каждому тесту необходимо добавить приоритеты (int числа от 1 до 10),
 * в соответствии с которыми будет выбираться порядок их выполнения. Если приоритет одинаковый,
 * то порядок не имеет значения. Методы с аннотациями @BeforeSuite и @AfterSuite должны присутствовать
 * в единственном экземпляре, иначе необходимо бросить RuntimeException при запуске «тестирования».
 * Это домашнее задание никак не связано с темой тестирования через JUnit и использованием
 * этой библиотеки: проект пишется с нуля.
 */
public class TestController {
    //инициализируем логгера
    //private static final Logger log = LoggerFactory.getLogger(TestController.class);
    private static Method[] methods;

    public static void main(String[] args) {
        start(Tests.class);
    }

    //метод контроля выполнения тестов в тестовом классе в аргументе
    public static void start(Class testClass){
        methods = getClassMethods(testClass);
        printArray(methods);
        //Annotation annotationBeforeSuite = new Annotation("BeforeSuit");

        //System.out.println(Annotation.class.getDeclaredFields().length);//0
        //printMethodsAnnotations(methods);

        //ищем метод, с аннотацией BeforeSuite
        //launchBeforeSuite(findMethodViaAnnotation(annotationBeforeSuite));

        try {
            launchTests();
        } catch (InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
        //launchAfterSuite();
    }

    //метод возвращает все объявленные аннотации класса в параметрах
    private static Annotation getMethodAnnotation(Method method) {
        return method.getAnnotation(Test.class);
    }

    //метод возвращает все объявленные методы класса в параметрах
    private static Method[] getClassMethods(Class testClass) {
        return testClass.getDeclaredMethods();
    }

    private static Method findMethodViaAnnotation(Method[] inMethod, Annotation annotation){
        Method outMethod = null;
        //листаем массив методов класса
        for (Method m: inMethod) {
            if(getMethodAnnotation(m) != null && getMethodAnnotation(m).equals(annotation)){
                outMethod = m;
            }
        }
        return outMethod;
    }

    //запускаем метод
    private static void launchBeforeSuite(Method method) {

    }

    private static void launchAfterSuite() {

    }

    private static void launchTests() throws InvocationTargetException, IllegalAccessException {
        for (Method method: methods) {
            if(method.getName().equals("CorrectArrayTestCreateArrayWithElementsBehindSample")){
                method.invoke(new Tests());
            }
        }
        //FIXME К каждому тесту необходимо добавить приоритеты (int числа от 1 до 10)
    }

    static void assertArrayEquals(int[] expectedArray, int[] actualArray){//FIXME КАК принять массив любой типа?
        //если массивы разной длины - тест провален
        if(expectedArray.length != actualArray.length){
            System.out.println("The test has failed!");
            //log.info("The test has failed!");
            return;
        }
        //если любой элемент актуального массива не равен соответствующему элементу
        // ожидаемого массива - тест провален
        for (int i = 0; i < actualArray.length; i++) {
            if (expectedArray[i] != actualArray[i]){
                System.out.println("The test has failed!");
                //log.info("The test has failed!");
                return;
            }
        }
        System.out.println("The test has passed!");
        //log.info("The test has passed!");
    }

    public static void printArray(Method[] inArray){
//        System.out.println("inArray.getClass().getSimpleName(): " + inArray.getClass().getSimpleName());
//        System.out.println("inArray.getClass().getComponentType(): " + inArray.getClass().getComponentType());
//        System.out.println("inArray.getClass().getComponentType().getSuperclass().getTypeName(): " + inArray.getClass().getComponentType().getSuperclass().getTypeName());
        for (Method o: inArray) {
            //System.out.println("o.getClass().getComponentType(): " + o.getClass().getComponentType());
            //System.out.println("o.getClass().getSimpleName(): " + o.getClass().getSimpleName());
            //System.out.println("o.toString(): " + o.toString());
            System.out.println("o.getName(): " + o.getName());
        }
    }

    /*public static void printArray(Executable[] inArray){
        System.out.println("inArray.getClass().getSimpleName(): " + inArray.getClass().getSimpleName());
        System.out.println("inArray.getClass().getComponentType(): " + inArray.getClass().getComponentType());
        System.out.println("inArray.getClass().getComponentType().getSuperclass().getTypeName(): " + inArray.getClass().getComponentType().getSuperclass().getTypeName());
        for (Executable o: inArray) {
            //System.out.println("o.getClass().getComponentType(): " + o.getClass().getComponentType());
            //System.out.println("o.getClass().getSimpleName(): " + o.getClass().getSimpleName());
            //System.out.println("o.toString(): " + o.toString());
            System.out.println("o.getName(): " + o.getName());
        }
    }*/

    public static void printMethodsAnnotations(Method[] methods){
        System.out.println("methods.getClass().getSimpleName(): " + methods.getClass().getSimpleName());
        for (Method o: methods) {
            if(o.getAnnotation(Test.class) != null) {
                System.out.println(o + " - getAnnotation(MyTest.class): " + o.getAnnotation(Test.class));
                System.out.println(o + " - getDeclaredAnnotation(MyTest.class): " + o.getDeclaredAnnotation(Test.class));
            }
        }
    }
}
