package lesson1.hw;

public class Orange extends Fruit{
    //private String type;//TODO Update.Deleted

    private Float weight;

    //TODO Update.Deleted
    /*public Orange(Float weight) {
        super(weight);
        this.type = "Orange";
        this.weight = super.weight;
    }*/
    //TODO Update.Added
    public Orange(Float weight) {
        super(weight, Orange.class.getSimpleName());
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
