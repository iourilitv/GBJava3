package lesson5.classbook;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * GBJava3
 * Java Core. Professional level. 19.08.2019 Webinar. Teacher: Anton Batiaev
 * Урок 5. Многопоточность. Часть II.
 * Классы для работы с многопоточностью. Concurrent collections.
 * Разбор практических примеров.
 * Интерфейс Lock. interface ReadWriteLock.
 * Задача: .
 * Интерфейс j ava.util.concurrent.locks.ReadWriteLock – это продвинутый механизм для блокировки
 * потоков. Он позволяет множеству потоков одновременно читать данные, или только одному
 * потоку – их записывать. Ресурс открыт для чтения множеству потоков без риска ошибок.
 * Проблемный момент – если несколько потоков одновременно читают и записывают данные.
 * Правила работы ReadWriteLock:
 * Read Lock - Если нет потоков, которые захватили объект этого типа для записи, то множество
 *  потоков могут захватить его для чтения.
 * Write Lock - Если нет потоков, которые захватили этот объект для записи или чтения, то только
 * один поток может захватить его для записи.
 */
public class ReadWriteLockExample {
    public static void main(String[] args) {
        //Для работы с интерфейсом ReadWriteLock необходимо создать объект типа ReentrantReadWriteLock. Пример работы с ним:
        ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

        // множество читателей может зайти в эту секцию, если нет записывающих потоков
        readWriteLock.readLock().lock();
        // какой-то блок кода
        readWriteLock.readLock().unlock();

        // только один поток-писатель может зайти в эту секцию, при условии, что ни один
        // из потоков не занимается чтением
        readWriteLock.writeLock().lock();
        // какой-то блок кода
        readWriteLock.writeLock().unlock();
    }
}
