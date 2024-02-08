import java.util.ArrayList;
import java.util.List;
public class Student {
    private final String name;
    private List<Subject> subjects;
    private Faculty faculty;

    public Student(String name, Faculty fac) {
        this.name = name;
        this.subjects = new ArrayList<>();
        this.faculty = fac;
        }

    public String getName() {
        return name;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void addSubject(Subject subject) {
        subjects.add(subject);
    }
}