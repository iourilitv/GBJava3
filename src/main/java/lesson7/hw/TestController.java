package lesson7.hw;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

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
        //printArray(methods);

        //Проверяем нет ли дубликатов у методов с аннотациями @BeforeSuite или @AfterSuite
        checkMethodsArray();
        //сортируем массив методов по значению приоритета
        sortMethodsArray();

        //System.out.println(methods[3].getAnnotation(Test.class).priority());
        //printMethodsAnnotations(methods);

        /*try {
            //ищем метод, с аннотацией BeforeSuite и запускаем его первым
            Method beforeSuite = findMethodViaAnnotation("BeforeSuite");
            if(beforeSuite != null){
                launchBeforeSuite(beforeSuite);
            }

            //запускаем тесты
            launchTests();

            //ищем метод, с аннотацией BeforeSuite и запускаем его первым
            Method afterSuite = findMethodViaAnnotation("AfterSuite");
            if(beforeSuite != null){
                launchBeforeSuite(afterSuite);
            }
        } catch (InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }*/
    }

    //Методы с аннотациями @BeforeSuite и @AfterSuite должны присутствовать в единственном
    // экземпляре, иначе необходимо бросить RuntimeException при запуске «тестирования»
    private static void checkMethodsArray() {
        int beforeSuiteCount = 0;
        int afterSuiteCount = 0;
        for (Method method: methods) {
            //проверяем нет ли дубликатов методов с аннотацией BeforeSuite
            if(method.getAnnotation(BeforeSuite.class) != null) {
                //System.out.println(method.getDeclaredAnnotation(BeforeSuite.class).annotationType().getSimpleName());
                if(++beforeSuiteCount > 1){
                    throw new RuntimeException("There are more than one methods with BeforeSuite annotation!");
                }
            }
            //проверяем нет ли дубликатов методов с аннотацией AfterSuite
            if(method.getAnnotation(AfterSuite.class) != null){
                if(++afterSuiteCount > 1){
                    throw new RuntimeException("There are more than one methods with AfterSuite annotation!");
                }
            }
        }
        System.out.println("There is no duplicates in the class!");
    }

    //сортируем массив методов по значению приоритета
    private static void sortMethodsArray() {
        int beforeSuiteIndex = -1;
        int afterSuiteIndex = - 1;
        //Method tempMethod;

        //***сначала расставляем BeforeSuite и AfterSuite
        for (int i = 0; i < methods.length; i++) {
            //если метод - это метод с аннотацией BeforeSuite
            if (methods[i].getAnnotation(BeforeSuite.class) != null) {
                //если в массиве есть метод с аннотацией BeforeSuite, занимаем индекс
                beforeSuiteIndex = 0;
                //если он не в начале массива, перемещаем его в начало
                if (i != 0) {
                    //запоминаем метод в начале массива
                    Method tempMethod = methods[0];
                    //записываем на его место текущий метод
                    methods[0] = methods[i];
                    //записываем на текущее место метод с начала массива
                    methods[i] = tempMethod;
                }
            }
            //если метод с аннотацией AfterSuite не в конце массива
            if (methods[i].getAnnotation(AfterSuite.class) != null) {
                //если в массиве есть метод с аннотацией AfterSuite, занимаем индекс
                afterSuiteIndex = methods.length - 1;
                //если он не в конце массива, перемещаем его в конец
                if (i != methods.length - 1) {
                    //запоминаем метод в конце массива
                    Method tempMethod = methods[methods.length - 1];
                    //записываем на его место текущий метод
                    methods[methods.length - 1] = methods[i];
                    //записываем на текущее место метод с конца массива
                    methods[i] = tempMethod;
                }
            }
        }
        //***затем сортируем методы с аннотацией тест по приоритету
        //если в массиве есть метод с аннотацией BeforeSuite, начинаем со второго места, если нет - с первого.
        int startIndex = beforeSuiteIndex == 0 ? 1 : 0;
        //если в массиве есть метод с аннотацией AfterSuite, начинаем с предпоследнего места, если нет - с последнего.
        int endIndex = afterSuiteIndex == methods.length - 1 ? methods.length - 1 : methods.length;
        //листаем массив
        for (int i = startIndex; i < endIndex - 1; i++) {
            //если метод с аннотацией Test - сортируем
            if (methods[i].getAnnotation(Test.class) != null) {
                //ищем метод с большим приоритетом
                for (int j = i + 1; j < endIndex; j++) {
                    //если у предыдущего метода приоритет меньше, то меняем местами
                    if (methods[i].getAnnotation(Test.class).priority() < methods[j].getAnnotation(Test.class).priority()) {
                        System.out.println("i: " + i + " . j: " + j);
                        //запоминаем текущий j метод
                        Method tempMethod = methods[j];
                        //записываем на его место текущий i метод
                        methods[j] = methods[i];
                        //записываем на текущее место j метод
                        methods[i] = tempMethod;
                    }
                }
            }
        }
        //System.out.println("beforeSuiteIndex: " + beforeSuiteIndex + " . afterSuiteIndex: " + afterSuiteIndex);
        System.out.println("after.");
        printArray(methods);
        System.out.println("sorted.");
        printSortedArray(methods);
    }

    //метод возвращает все объявленные методы класса в параметрах
    private static Method[] getClassMethods(Class testClass) {
        return testClass.getDeclaredMethods();
    }

    //запускаем метод
    /*private static void launchBeforeSuite(Method beforeSuite) throws InvocationTargetException, IllegalAccessException {
        beforeSuite.invoke(new Tests());
    }*/

    private static void launchAfterSuite() {

    }

    /*private static void launchTests() throws InvocationTargetException, IllegalAccessException {
        for (Method method: methods) {
            if(method.getName().equals("CorrectArrayTestCreateArrayWithElementsBehindSample")){
                method.invoke(new Tests());
            }
        }
        //FIXME К каждому тесту необходимо добавить приоритеты (int числа от 1 до 10)
    }*/

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

    public static void printSortedArray(Method[] inArray){
        for (Method o: inArray) {
            if(o.getAnnotation(Test.class) != null) {
                System.out.println("o.getName(): " + o.getName() + " - priority: " + o.getAnnotation(Test.class).priority());            }
        }
    }

    public static void printMethodsAnnotations(Method[] methods){
        System.out.println("methods.getClass().getSimpleName(): " + methods.getClass().getSimpleName());
        for (Method o: methods) {
            if(o.getAnnotation(BeforeSuite.class) != null) {
                //System.out.println(o + " - getAnnotation(MyTest.class): " + o.getAnnotation(Test.class));
                //System.out.println(o + " - getDeclaredAnnotation(MyTest.class): " + o.getDeclaredAnnotation(Test.class));
                System.out.println(o + " - getDeclaredAnnotation(MyTest.class).annotationType().getSimpleName(): " + o.getDeclaredAnnotation(BeforeSuite.class).annotationType().getSimpleName());
            }
            if(o.getAnnotation(Test.class) != null) {
                //System.out.println(o + " - getAnnotation(MyTest.class): " + o.getAnnotation(Test.class));
                //System.out.println(o + " - getDeclaredAnnotation(MyTest.class): " + o.getDeclaredAnnotation(Test.class));
                System.out.println(o + " - getDeclaredAnnotation(MyTest.class).annotationType().getSimpleName(): " + o.getDeclaredAnnotation(Test.class).annotationType().getSimpleName());
            }
        }
    }


    /*//метод возвращает все объявленные аннотации класса в параметрах
    private static Annotation getMethodAnnotation(Method method) {
        return method.getAnnotation(Test.class);
    }*/

    /*private static Method findMethodViaAnnotation(Annotation annotation){
        Method outMethod = null;
        //листаем массив методов класса
        for (Method m: methods) {
            if(getMethodAnnotation(m) != null
                    && getMethodAnnotation(m).equals(annotation)){
                outMethod = m;
            }
        }
        return outMethod;
    }*/

    /*private static Method findMethodViaAnnotation(String annotationName){
        Method outMethod = null;
        //листаем массив методов класса
        for (Method m: methods) {
            if(getMethodAnnotation(m) != null
                    && (getMethodAnnotation(m).annotationType().getSimpleName().equals(annotationName))){
                outMethod = m;
                //System.out.println(m.getName());
            }
        }
        return outMethod;
    }*/


}
