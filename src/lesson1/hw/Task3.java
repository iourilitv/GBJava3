package lesson1.hw;

/**
 * GBJava3
 * Java Core. Profesional level. 19.08.2019 Webinar. Teacher: Anton Batiaev
 * Урок 1. Обобщения - Generics.
 * Домашняя работа.
 * @author Litvinenko Yuriy
 * 3. Большая задача:
 * DONE Есть классы Fruit -> Apple, Orange (больше фруктов не надо);
 * DONE Класс Box, в который можно складывать фрукты.
 * DONE Коробки условно сортируются по типу фрукта, поэтому в одну коробку нельзя сложить и яблоки, и апельсины;
 * DONE Для хранения фруктов внутри коробки можно использовать ArrayList;
 * DONE Сделать метод getWeight(), который высчитывает вес коробки, зная количество фруктов и вес одного фрукта
 *      (вес яблока – 1.0f, апельсина – 1.5f. Не важно, в каких это единицах);
 * DONE Внутри класса Коробка сделать метод compare, который позволяет сравнить текущую коробку с той,
 *      которую подадут в compare в качестве параметра,
 *      true – если она равны по весу, false – в противном случае
 *      (коробки с яблоками мы можем сравнивать с коробками с апельсинами);
 * DONE Написать метод, который позволяет пересыпать фрукты из текущей коробки в другую
 *      (помним про сортировку фруктов: нельзя яблоки высыпать в коробку с апельсинами).
 *      Соответственно, в текущей коробке фруктов не остается, а в другую перекидываются объекты,
 *      которые были в этой коробке;
 * DONE Не забываем про метод добавления фрукта в коробку.
 */
public class Task3 {

    public static void main(String[] args) {
        new Store().getOperations();
    }
}

//Result.
//Loaded container.50
//Unloaded container.size(): 0
//Box(0): Type of units:Apple. Box weight:10.0. Box capacity:10. Number of units:10
//Box(1): Type of units:Orange. Box weight:15.0. Box capacity:10. Number of units:10
//Box(2): Type of units:Apple. Box weight:10.0. Box capacity:10. Number of units:10
//Box(3): Type of units:Orange. Box weight:15.0. Box capacity:10. Number of units:10
//Box(4): Type of units:Apple. Box weight:5.0. Box capacity:10. Number of units:5
//Box(5): Type of units:Orange. Box weight:7.5. Box capacity:10. Number of units:5
//Box(6): Type of units:Apple. Box weight:0.0. Box capacity:10. Number of units:0
//Box(7): Type of units:Orange. Box weight:0.0. Box capacity:10. Number of units:0
//Box(8): Type of units:Apple. Box weight:0.0. Box capacity:10. Number of units:0
//Box(9): Type of units:Orange. Box weight:0.0. Box capacity:10. Number of units:0
//
//***Trying to weigh the boxes
//Weights of the boxes(0) and boxes(1) equal: false
//Weights of the boxes(0) and boxes(2) equal: true
//
//***Trying to unload
//from boxes(5)
//Type of units:Orange. Box weight:7.5. Box capacity:10. Number of units:5
//to boxes(6)
//Type of units:Apple. Box weight:0.0. Box capacity:10. Number of units:0
//The types of boxes are not matched!
//
//***Trying to unload
//from boxes(0)
//Type of units:Apple. Box weight:10.0. Box capacity:10. Number of units:10
//to boxes(8)
//Type of units:Apple. Box weight:0.0. Box capacity:10. Number of units:0
//The rest in boxes(0):0
