package lesson1.hwTeacher.homework3;

public abstract class Fruit {
    protected final double weight;
    protected final String type;

    public Fruit(double weight, String type) {
        this.weight = weight;
        this.type = type;
    }

    public double getWeight() {
        return weight;
    }

    public String getType() {
        return type;
    }
}
