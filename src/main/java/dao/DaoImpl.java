package dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;

import java.util.List;

public class DaoImpl implements Dao {
    /**
     * Найти/получить
     */
    @Override
    public <T> T get(int id, Class<T> clazz) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            return session.get(clazz, id);
        }
    }

    /**
     * Сохранить/добавить
     */
    @Override
    public <T> void save(T t) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction tx1 = session.beginTransaction();
            session.save(t);
            tx1.commit();
        }
    }

    /**
     * Обновить запись
     */
    @Override
    public <T> void update(T t) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction tx1 = session.beginTransaction();
            session.update(t);
            tx1.commit();
        }
    }

    /**
     * Удалить
     */
    @Override
    public <T> void delete(T t) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction tx1 = session.beginTransaction();
            session.delete(t);
            tx1.commit();
        }
    }

    /**
     * Удалить (перегрузка)
     */
    @Override
    public <T> void delete(Class<T> clazz, int id) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction tx1 = session.beginTransaction();
            session.delete(session.get(clazz, id));
            tx1.commit();
        }
    }

    /**
     * Найти все элементы одного типа
     */
    @Override
    public <T> List<T> findAll(Class<T> clazz) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            return (List<T>) session.createQuery("From " + clazz.getName()).list();
        }
    }
}