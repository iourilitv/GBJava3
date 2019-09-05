package lesson5.hw;
//Ready
public abstract class Stage {
    protected int length;
    String description;

    public String getDescription() {
        return description;
    }

    public abstract void go(Car c);
}
