package lesson2.hwTeacher;

import org.sqlite.JDBC;

import java.sql.*;
import java.util.Scanner;

/**
 * Урок 2. Базы данных
 * Homework
 * @author anton
 * @since 26/08/19
 * 1. Сформировать таблицу товаров (id, prodid, title, cost) запросом из Java-приложения
 * (подробнее - в методическом пособии к уроку);
 * При запуске приложения очистить таблицу и заполнить 10000 товаров
 * (подробнее - в методическом пособии к уроку);
 * 2. Написать консольное приложение, которое позволяет узнать цену товара по его имени,
 * либо вывести сообщение «Такого товара нет»,
 * если товар не обнаружен в базе. Консольная команда: «/цена товар545».
 * Добавить возможность изменения цены товара. Указываем имя товара и новую цену.
 * Консольная команда: «/сменитьцену товар10 10000».
 * Вывести товары в заданном ценовом диапазоне. Консольная команда: «/товарыпоцене 100 600».
 */
public class Homework {

    private Connection connect;
    private Statement stmt;

    private static final String DB_NAME = "product.db";

    public static void main(String[] args) {
        Homework db = new Homework();
        process(db);
    }

    private static void process(Homework db) {
        Scanner in = new Scanner(System.in);

        while (true) {
            String str = in.nextLine();
            String[] data = str.split(" ");
            switch (data[0]) {
                case "/цена":
                    if (data.length > 1)
                        db.getProduct(data[1]);
                    else {
                        System.out.println("Invalid command!");
                    }
                    break;
                case "/сменитьцену":
                    if (data.length > 2)
                        db.updatePrice(data[1], data[2]);
                    break;
                case "/товарыпоцене":
                    db.getProductsByCost(data[1], data[2]);
                    break;
                default:
                    System.out.println("Неправильная команда");
                    break;
            }
            db.close();
            break;
        }
    }

    Homework() {
        try {
            connect = DriverManager.getConnection(JDBC.PREFIX + DB_NAME);
            stmt = connect.createStatement();
            initTable();
            clearTable();
            insertData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void initTable() {
        try {
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS product (\n" +
                    "    id int,\n" +
                    "    prodid int,\n" +
                    "    title varchar(255),\n" +
                    "    cost int\n" +
                    ");");
            System.out.println("Создали таблицу product");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void clearTable() {
        try {
            stmt.executeUpdate("DELETE FROM product;");
            System.out.println("Очистили таблицу product");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertData() {
        long start = System.currentTimeMillis();
        try {
            connect.setAutoCommit(false);
            PreparedStatement ps = connect.prepareStatement(
                    "INSERT INTO product VALUES (?,?,?,?);");
            int rowCount = 10000;
            for (int i = 1; i <= rowCount; i++) {
                ps.setInt(1, i);
                ps.setInt(2, i);
                ps.setString(3, "товар" + i);
                ps.setDouble(4, i);
                ps.addBatch();
            }
            ps.executeBatch();
            connect.commit();
            System.out.println("Вставили " + rowCount + " строк за " + (System.currentTimeMillis() - start) + "ms");
        } catch (SQLException e) {
            try {
                connect.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    void close() {
        try {
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            connect.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void updatePrice(String product_id, String new_price) {
        String query = String.format(
                "UPDATE product SET cost = %s WHERE title = '%s';",
                new_price, product_id);
        System.out.println(query);
        try {
            Integer result = stmt.executeUpdate(query);
            if (result > 0) {
                connect.commit();
                System.out.println("Затронуто строк: " + result);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void getProduct(String productName) {
        String query = String.format(
                "SELECT cost FROM product WHERE title = '%s';", productName);
        try {
            ResultSet result = stmt.executeQuery(query);
            if (result != null) {
                System.out.println("Цена товара " + result.getString("cost"));
            }

        } catch (SQLException e) {
            System.out.println("Такого товара нет");
            e.printStackTrace();
        }
    }

    void getProductsByCost(String min, String max) {
        String query = String.format(
                "SELECT title FROM product WHERE cost BETWEEN %s AND %s ;", min, max);
        try {
            ResultSet result = stmt.executeQuery(query);
            if (result != null) {
                while (result.next()) {
                    System.out.println(result.getString("title"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Такого товара нет");
            e.printStackTrace();
        }
    }

}
