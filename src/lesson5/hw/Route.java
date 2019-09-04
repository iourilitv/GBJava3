package lesson5.hw;

import java.util.ArrayList;
import java.util.Arrays;
//TODO HW.Added
public class Route {
    //объявляем коллекцию с этапами гонки в качестве элементов
    private ArrayList<Stage> stages;

    public Route(Stage... stages) {
        this.stages = new ArrayList<>(Arrays.asList(stages));
    }

    public ArrayList<Stage> getStages() {
        return stages;
    }
}
