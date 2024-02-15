package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBC {
    private static final String URL = "jdbc:mysql://localhost:3306";
    private static final String USER = "root";
    private static final String PASSWORD = "lokation";

    /**
     * Метод для инициализации БД с помощью JDBC, не используя возможности ORM Hibernate
     */
    public static void initialize() {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            Statement statement = connection.createStatement();
            statement.execute("CREATE DATABASE IF NOT EXISTS schoolDB;");
            statement.execute("USE schoolDB;");
            statement.execute("CREATE TABLE IF NOT EXISTS courses (\n" +
                    "    id INT PRIMARY KEY AUTO_INCREMENT,\n" +
                    "    title VARCHAR(100) NOT NULL UNIQUE,\n" +
                    "    duration INT\n" +
                    ");");
            statement.execute("CREATE TABLE IF NOT EXISTS students (\n" +
                    "    id INT PRIMARY KEY auto_increment,\n" +
                    "    NAME VARCHAR(50) NOT NULL,\n" +
                    "    surname VARCHAR(50) NOT NULL,\n" +
                    "    birthDay DATE\n" +
                    ");");
            statement.execute("CREATE TABLE IF NOT EXISTS student_course (\n" +
                    "    student_id INT,\n" +
                    "    course_id INT,\n" +
                    "    FOREIGN KEY (student_id) REFERENCES students (id),\n" +
                    "    FOREIGN KEY (course_id) REFERENCES courses (id)\n" +
                    ");");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
