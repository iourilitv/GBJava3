package lesson1.hwTeacher.homework3;

public class Apple extends Fruit{

    public Apple(double weight) {
        super(weight, Apple.class.getSimpleName());//вместо this.getClass().getSimpleName();, т.к. еще не инициализирован this
    }

}
