package model;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Entity
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Setter
    private String name;
    @Setter
    private String surname;
    @Setter
    private Date birthDay;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(name = "student_course",
            joinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "course_id", referencedColumnName = "id"))
    private List<Course> courses;

    public Student(String name, String surname, Date birthDay) {
        this.name = name;
        this.surname = surname;
        this.birthDay = birthDay;
        courses = new ArrayList<>();
    }

    /**
     * Добавить новый курс
     */
    public void addCourse(Course course) {
        courses.add(course);
    }

    /**
     * Удалить курс
     */
    public void removeCourse(int id) {
        Course course = courses.stream().filter(st -> st.getId() == id).findFirst()
                .orElseThrow(() -> new NoSuchElementException("Курс с указанным id не найден"));
        courses.remove(course);
    }

    /**
     * Удалить курс (перегрузка)
     */
    public void removeCourse(Course course) {
        courses.remove(course);
    }
}
