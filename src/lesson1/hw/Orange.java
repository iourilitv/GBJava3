package lesson1.hw;

public class Orange extends Fruit{
    private String type;
    private Float weight;

    public Orange(Float weight) {
        super(weight);
        this.type = "Orange";
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
