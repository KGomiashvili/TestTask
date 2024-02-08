import java.util.ArrayList;
import java.util.List;
public class Lecturer {
    private final String name;
    private List<Subject> subjects;

    public Lecturer(String name) {
        this.name = name;
        this.subjects = new ArrayList<>();
    }

    public void addSubject(Subject subject) {
        subjects.add(subject);
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public String getName() {
        return name;
    }
}