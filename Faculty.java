import java.util.ArrayList;
import java.util.List;

public class Faculty {
    private String name;
    private List<Subject> subjects;
    private List<Student> students;
    private List<Lecturer> lecturers;

    public Faculty(String name) {
        this.name = name;
        this.subjects = new ArrayList<>();
        this.lecturers = new ArrayList<>();
        this.students = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void addSubject(Subject subject) {
        this.subjects.add(subject);
    }

    public boolean hasSubject(Subject subject) {
        return subjects.contains(subject);
    }

    public List<Student> getStudents() {
        return students;
    }

    public void addStudent(Student student) {
        this.students.add(student);
    }

    public void addLecturer(Lecturer lecturer) {
        this.lecturers.add(lecturer);
    }

    public List<Lecturer> getLecturers() {
        return lecturers;
    }
}
