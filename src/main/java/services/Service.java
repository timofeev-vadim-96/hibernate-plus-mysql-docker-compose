package services;

import dao.Dao;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class Service {

    private Dao dao;

    /**
     * Найти/получить
     */
    public <T> T get(int id, Class<T> clazz) {
        return dao.get(id, clazz);
    }

    /**
     * Сохранить/добавить
     */
    public <T> void save(T t) {
        dao.save(t);
    }

    /**
     * Удалить
     */
    public <T> void delete(Class<T> clazz, int id) {
        dao.delete(clazz, id);
    }

    /**
     * Обновить
     */
    public <T> void update(T t) {
        dao.update(t);
    }

    /**
     * Найти все элементы одного типа
     */
    public <T> List<T> findAll(Class<T> clazz) {
        return dao.findAll(clazz);
    }

    /**
     * Удалить (перегрузка метода)
     */
    public <T> void delete(T t) {
        dao.delete(t);
    }
}
