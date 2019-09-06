package lesson1.hw;

//TODO Update.Added abstract
public abstract class Fruit {
    protected final Float weight;//TODO Update.Added final
    protected final String type;//TODO Update.Added final

    //TODO Update.Deleted
    /*public Fruit(Float weight) {
        this.weight = weight;
    }*/
    //TODO Update.Added
    public Fruit(Float weight, String type) {
        this.weight = weight;
        this.type = type;
    }

    public Float getWeight() {
        return weight;
    }

    public String getType() {
        return type;
    }
}
