package Testing;

import java.io.IOException;
import java.io.RandomAccessFile;

public class Test {

    public static void main(String[] args) {

        try {
            //r- read, файл открыт только для чтения
            RandomAccessFile raf = new RandomAccessFile("input.txt", "r");
            //перемещаем «курсор» на 100-й символ.
            raf.seek(100);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
