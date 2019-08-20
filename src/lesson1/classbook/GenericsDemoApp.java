package lesson1.classbook;

public class GenericsDemoApp {
    public static void main(String args[]) {
        TestGeneric<String> genStr = new TestGeneric<>("Hello");
        genStr.showType();
        System.out.println("genStr.getObject(): " + genStr.getObj());
        TestGeneric<Integer> genInt = new TestGeneric<>(140);
        genInt.showType();
        System.out.println("genInt.getObject(): " + genInt.getObj());
        int valueFromGenInt = genInt.getObj();//было неверно genInt.getObject();?
        String valueFromGenString = genStr.getObj();//было неверно getStr.getObject();

        //genInt.setObj("Java"); // Ошибка компиляции !!! Не верный тип объекта

        genInt.setObj(200); // правильно //добавил пример

    }
}

//Важно!
//- При получении значений из genInt и genStr не требуется преобразование типов,
//genInt.getObj() сразу возвращает Integer, а genStr.getObj() - String. То есть приведение типов
//выполняется неявно и автоматически.
//- Если объект создан как TestGeneric<Integer> genInt, то мы не сможем записать в него строку:
//genInt.setObj(“Java”). При попытке написать такую строку кода, мы получим ошибку на этапе
//компиляции. То есть обобщения отслеживают корректность используемых типов данных.
//Эти две особенности использования обобщений и приводят к повышению безопасности типов
//данных, и упрощению работы при написании кода.

//Результат выполнения:
//Тип Т: java.lang.String
//genStr.getObject(): Hello
//Тип Т: java.lang.Integer
//genInt.getObject(): 140