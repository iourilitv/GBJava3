package lesson7.hwTeacher;

import java.lang.reflect.Method;
import java.util.Comparator;

/**
 * TestComparator
 * Описывает логику сравнения - сравниваем переданные методы по приоритету в аннотации Test к нему
 * @author anton
 * @since 12/09/19
 */
public class TestComparator implements Comparator<Method> {
    //переопределяем встроенный метод сравнения двух методов
    @Override
    public int compare(Method m1, Method m2) {
        //получаем аннотацию Test у первого метода
        final Test annotation1 = m1.getAnnotation(Test.class);
        //если аннотации нет, возвращаем 1
        if (annotation1 == null)
            return 1;//FIXME лучше выбросить исключение?
        //получаем и запоминаем значение приоритета из аннотации Test метода
        Integer priority1 = annotation1.priority();

        //*аналогично делаем для второго метода
        final Test annotation2 = m2.getAnnotation(Test.class);
        if (annotation2 == null)
            return 0;//FIXME лучше выбросить исключение?
        Integer priority2 = annotation2.priority();

        //инициируем переменную результата сравнения, используя стандартный метод класса Integer,
        //который возвращает результат выражения (x < y) ? -1 : ((x == y) ? 0 : 1) -
        //-1 - меньше первое(x), 1 - меньше второе(y) и 0 - равны.
        int priorityCompareResult = priority1.compareTo(priority2);
        //если приоритеты одинаковые, то выбираем по имени метода
        if (priorityCompareResult == 0) {
            //возвращаем результат сравнения, используя стандартный метод класса String
            return m1.getName().compareTo(m2.getName());
        }
        return priorityCompareResult;
    }
}
