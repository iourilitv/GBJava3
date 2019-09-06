package lesson5.hw;

import java.util.ArrayList;
import java.util.Arrays;
//TODO HW.Added
class Route {
    //объявляем коллекцию с этапами гонки в качестве элементов
    private ArrayList<Stage> stages;

    Route(Stage... stages) {
        this.stages = new ArrayList<>(Arrays.asList(stages));
    }

    ArrayList<Stage> getStages() {
        return stages;
    }
}
