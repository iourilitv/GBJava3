package lesson6.hw;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * GBJava3
 * Java Core. Professional level. 19.08.2019 Webinar. Teacher: Anton Batiaev
 * Урок 6. Обзор средств разработки.
 * Логирование. Тестирование с использованием JUnit. Класс Assert. Аннотации.
 * Домашняя работа.
 * @author Litvinenko Yuriy
 * 3.* Добавить на серверную сторону сетевого чата логирование событий
 * (сервер запущен, произошла ошибка, клиент подключился, клиент прислал сообщение/команду).
 */
public class Task3 {
    private static final Logger log = LoggerFactory.getLogger(Task3.class);

    public static void main(String[] args) {
        log.debug( "Debug" );
        log.info( "Info" );
        log.warn( "Warn" );
        log.error( "Error" );
        //log.fatal( "Fatal" );
        log.info( "String: {}." , "Hello, World" );
    }
}
