package utils;

import model.Course;
import model.Student;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Класс для создания фабрики сессий для работы с БД
 */
public class HibernateSessionFactoryUtil {
    private static SessionFactory sessionFactory;

    private HibernateSessionFactoryUtil() {}

    /**
     * Метод создания фабрики сессий
     */
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(Course.class);
                configuration.addAnnotatedClass(Student.class);
                sessionFactory = configuration.buildSessionFactory();
            } catch (Exception e) {
                System.out.println("Exception when trying to create a database session!" + e);
            }
        }
        return sessionFactory;
    }
}
