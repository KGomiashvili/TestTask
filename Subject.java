import java.util.ArrayList;
import java.util.List;

public class Subject {
    private final String name;
    private List<Faculty> faculties;
    private List<Student> students;
    private Lecturer lecturer;

    public Subject(String name) {
        this.name = name;
        this.students = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Faculty> getFaculty() {
        return faculties;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public Lecturer getLecturer() {
        return lecturer;
    }

    public void addLecturer(Lecturer lecturer) {
        this.lecturer=lecturer;
    }
}
