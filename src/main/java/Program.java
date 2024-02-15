import dao.DaoImpl;
import model.Course;
import model.Student;
import services.Service;
import utils.JDBC;

import java.sql.Date;
import java.util.List;

public class Program {
    public static void main(String[] args) {
        JDBC.initialize();

        Service service = new Service(new DaoImpl());

        //create
        service.save(new Course("python", 400));
        service.save(new Course("docker", 50));
        service.save(new Student("Ivan", "Ivanov", Date.valueOf("1997-12-02")));

        //read
        Student firstStudent = service.findAll(Student.class).stream().findFirst().get();
        List<Course> courses = service.findAll(Course.class);

        //update
        for (Course course : courses) {
            firstStudent.addCourse(course);
        }
        service.update(firstStudent);

        //delete
        Course courseForDelete = courses.get(0);
        firstStudent.removeCourse(courseForDelete.getId());
        service.delete(Course.class, courseForDelete.getId());
    }
}
