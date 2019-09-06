package lesson1.classbook;

/**
 * GBJava3
 * Java Core. Profesional level. 19.08.2019 Webinar. Teacher: Anton Batiaev
 * Урок 1. Обобщения - Generics.
 * Понятие обобщения. Обобщенные классы, методы и
 * интерфейсы. Наследование обобщенных классов. Ограничения
 * при работе с обобщениями.
 * Пример без обобщений.ERR ClassCastException.
 */

//задача создать класс, который позволит хранить в себе один объект любого типа.
public class SimpleBox {
    //единственное поле типа Object
    private Object obj;

    //
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
