package lesson1.hwTeacher.homework3;

public class Orange extends Fruit {

    public Orange(double weight) {
        super(weight, Orange.class.getSimpleName());//вместо this.getClass().getSimpleName();, т.к. еще не инициализирован this
    }
}
