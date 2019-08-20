package lesson1.classbook;

/**
 * Урок 1. Обобщения - Generics.
 * Понятие обобщения. Обобщенные классы, методы и
 * интерфейсы. Наследование обобщенных классов. Ограничения
 * при работе с обобщениями.
 * Пример без обобщений.ERR ClassCastException.
 */

public class SimpleBox {
    private Object obj;
    public Object getObj() {
        return obj;
    }
    public void setObj(Object obj) {
        this.obj = obj;
    }
    public SimpleBox(Object obj) {
        this.obj = obj;
    }
}
