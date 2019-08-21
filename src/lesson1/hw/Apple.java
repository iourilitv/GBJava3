package lesson1.hw;

public class Apple extends Fruit{
    private String type;
    private Float weight;

    public Apple(Float weight) {
        super(weight);
        this.type = "Apple";
        this.weight = super.weight;
    }

    public String getType() {
        return type;
    }

    @Override
    public Float getWeight() {
        return weight;
    }
}
