package lesson5.hw;

import java.util.ArrayList;
import java.util.Arrays;

public class Race {
    //объявляем коллекцию с этапами гонки в качестве элементов
    private ArrayList<Stage> stages;

    //геттер возвращаем коллекцию с этапами гонки в качестве элементов
    public ArrayList<Stage> getStages() { return stages; }

    //в конструкторе гонки инициируем коллекцию с этапами гонки в качестве элементов
    //из переданных ему массива этапов гонки
    public Race(Stage... stages) {
        this.stages = new ArrayList<>(Arrays.asList(stages));
    }
}
