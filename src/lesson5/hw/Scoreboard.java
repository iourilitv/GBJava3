package lesson5.hw;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
//Ready
//TODO HW.Added
class Scoreboard {
    //передаем сюда объект гонки
    private Race race;

    //инициируем синхронизированную коллекцию хранения результатов гонки
    private final List<Car> raceResults = Collections.synchronizedList(new ArrayList<>());

    Scoreboard(Race race) {
        this.race = race;
    }

    //метод формирует коллекцию с результатами гонки
    void sortRaceResults() {
        // окончательно сортируем коллекцию с участников по их результатам
        raceResults.sort(new Comparator<Car>() {
            @Override
            public int compare(Car rhs, Car lhs) {
                //самый малый в верху
                return Long.compare(rhs.getParticipantFinishTime(), lhs.getParticipantFinishTime());
            }
        });
    }

    //геттер для возврата коллекции результатов гонки
    List<Car> getRaceResults() {
        return raceResults;
    }

}
