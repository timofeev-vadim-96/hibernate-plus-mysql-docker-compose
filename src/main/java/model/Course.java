package model;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.NoSuchElementException;

@Entity
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@ToString
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    private int id;
    @Setter
    private String title;
    @Setter
    private int duration;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(name = "student_course",
            joinColumns = @JoinColumn(name = "course_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id"))
    private List<Student> students;

    public Course(String title, int duration) {
        this.title = title;
        this.duration = duration;
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void removeStudent(int id) {
        Student student = students.stream().filter(st -> st.getId() == id).findFirst()
                .orElseThrow(() -> new NoSuchElementException("Студент с указанным id не найден"));
        students.remove(student);
    }

    public void removeStudent(Student student) {
        students.remove(student);
    }
}
