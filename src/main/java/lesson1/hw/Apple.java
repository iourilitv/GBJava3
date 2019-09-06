package lesson1.hw;

public class Apple extends Fruit{

    //private String type;//TODO Update.Deleted

    private Float weight;

    //TODO Update.Deleted
    /*public Apple(Float weight) {
        super(weight);
        this.type = "Apple";
        this.weight = super.weight;
    }*/
    //TODO Update.Added
    public Apple(Float weight) {
        super(weight, Apple.class.getSimpleName());
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
