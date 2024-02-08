import java.util.ArrayList;
import java.util.List;
public class University {
    static class ElementFoundException extends Exception {
        public ElementFoundException(String message) {
            super(message);
        }
    }

    static class ElementNotFoundException extends Exception {
        public ElementNotFoundException(String message) {
            super(message);
        }
    }

    private List<Faculty> allFaculties = new ArrayList<>();
    private List<Subject> allSubjects = new ArrayList<>();
    private List<Lecturer> allLecturers = new ArrayList<>();
    private List<Student> allStudents = new ArrayList<>();

    public List<Faculty> getAllFaculties() {
        return allFaculties;
    }

    public List<Subject> getAllSubjects() {
        return allSubjects;
    }

    public List<Lecturer> getAllLecturers() {
        return allLecturers;
    }

    public List<Student> getAllStudents() {
        return allStudents;
    }

    public void addFaculty(String facultyName) throws ElementFoundException {
        for (Faculty faculty : allFaculties) {
            if (faculty.getName().equals(facultyName)) {
                throw new ElementFoundException("Faculty with name: " + facultyName + " already exists");
            }
        }
        allFaculties.add(new Faculty(facultyName));
    }

    public void addSubject(String subjectName) throws ElementFoundException {
        for (Subject subject : allSubjects) {
            if (subject.getName().equals(subjectName)) {
                throw new ElementFoundException("Subject with name: " + subjectName + " already exists");
            }
        }
        allSubjects.add(new Subject(subjectName));
    }

    public void addFacultySubject(String facultyName, String subjectName) throws ElementFoundException, ElementNotFoundException {
        Faculty foundFaculty = findFaculty(facultyName);
        Subject foundSubject = findSubject(subjectName);

        if (foundFaculty.hasSubject(foundSubject)) {
            throw new ElementFoundException("Faculty " + facultyName + " already has subject " + subjectName);
        }

        foundFaculty.addSubject(foundSubject);

        for (Student student : foundFaculty.getStudents()) {
            foundSubject.addStudent(student);
        }
    }


    public void addStudent(String studentName, String facultyName) throws ElementFoundException, ElementNotFoundException {
        for (Student student : allStudents) {
            if (student.getName().equals(studentName)) {
                throw new ElementFoundException("Student with name: " + studentName + " already exists");
            }
        }

        Faculty foundFaculty = findFaculty(facultyName);
        Student newStudent = new Student(studentName, foundFaculty);
        foundFaculty.addStudent(newStudent);
        allStudents.add(newStudent);

        for (Subject subject : foundFaculty.getSubjects()) {
            subject.addStudent(newStudent);
            newStudent.addSubject(subject);
        }

    }




    public void addLecturer(String lecturerName) throws ElementFoundException {
        for (Lecturer lecturer : allLecturers) {
            if (lecturer.getName().equals(lecturerName)) {
                throw new ElementFoundException("Lecturer with name: " + lecturerName + " already exists");
            }
        }

        Lecturer newLecturer = new Lecturer(lecturerName);
        allLecturers.add(newLecturer);
    }

    public void addLecturerSubject(String lecturerName, String subjectName)
            throws ElementFoundException, ElementNotFoundException {
        Lecturer foundLecturer = findLecturer(lecturerName);
        Subject foundSubject = findSubject(subjectName);

        for (Lecturer lecturer : allLecturers) {
            for (Subject subject : lecturer.getSubjects()) {
                if (subject.equals(foundSubject)) {
                    throw new ElementFoundException("Subject " + subjectName + " is already being taught by another lecturer");
                }
            }
        }

        foundLecturer.addSubject(foundSubject);
        foundSubject.addLecturer(foundLecturer);
    }

    public void showFaculties() {
        for (Faculty fac : allFaculties) {
            System.out.println(fac.getName());
        }
    }

    public void showStudents() {
        for (Student student : allStudents) {
            System.out.println(student.getName());
        }
    }

    public void showSubjects() {
        for (Subject sub : allSubjects) {
            System.out.println(sub.getName());
        }
    }

    public void showLecturers() {
        for (Lecturer lec : allLecturers) {
            System.out.println(lec.getName());
        }
    }

    public void showFacultyLecturers(String facultyName) {
        Faculty foundFac = null;
        List<Lecturer> facLecturers = new ArrayList<>();

        for (Faculty fac : allFaculties) {
            if (fac.getName().equals(facultyName)) {
                foundFac = fac;
                break;
            }
        }

        if (foundFac != null) {
            System.out.println("Lecturers for faculty " + facultyName + ":");
            for (Subject sub : foundFac.getSubjects()) {
                for (Lecturer lec : allLecturers) {
                    if (lec.getSubjects().contains(sub) && !facLecturers.contains(lec)) {
                        facLecturers.add(lec);
                        System.out.println("- " + lec.getName());
                    }
                }
            }
        } else {
            System.out.println("No faculty with name " + facultyName + " found.");
        }
    }

    public void showFacultySubjects(String facultyName) throws ElementNotFoundException {
        Faculty foundFac = findFaculty(facultyName);
        for (Subject sub : foundFac.getSubjects()) {
            System.out.println(sub.getName());
        }
    }

    public void showStudentLecturers(String studentName) {
        try {
            Student student = findStudent(studentName);

            if (student.getFaculty() != null) {
                System.out.println("Lecturers for " + studentName + ":");
                for (Subject subject : student.getFaculty().getSubjects()) {
                    System.out.println(subject.getLecturer().getName());
                }
            } else {
                System.out.println(studentName + " is not associated with any faculty.");
            }
        } catch (ElementNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


    public void showSubjectStudents(String subjectName) {
        Subject foundSubject = null;
        for (Subject subject : allSubjects) {
            if (subject.getName().equals(subjectName)) {
                foundSubject = subject;
                break;
            }
        }

        if (foundSubject != null) {
            System.out.println("Students studying " + subjectName + ":");
            for (Student student : foundSubject.getStudents()) {
                System.out.println("- " + student.getName());
            }
        } else {
            System.out.println("No subject with name " + subjectName + " found.");
        }
    }

    public void showFullUniversityTree() {
        for (Faculty faculty : allFaculties) {
            System.out.println(faculty.getName() + ": \n");
            for (Subject subject : faculty.getSubjects()) {
                System.out.println("\t- " + subject.getName() + " " + getLecturerName(subject));
                for (Student student : faculty.getStudents()) {
                    if (student.getSubjects().contains(subject)) {
                        System.out.println("\t\t- " + student.getName());
                    }
                }
            }
        }
    }

    private String getLecturerName(Subject subject) {
        Lecturer lecturer = subject.getLecturer();
        return (lecturer != null) ? lecturer.getName() : "No Lecturer assigned";
    }

    public boolean lecturerTeachesSubjects(Lecturer lecturer) {
        for (Subject subject : lecturer.getSubjects()) {
            for (Student student : subject.getStudents()) {
                if (!student.getSubjects().isEmpty()) {
                    return true;
                }
            }
        }
        return false;
    }


    public void deleteLecturer(String lecturerName) throws ElementNotFoundException {
        Lecturer lecturerToDelete = findLecturer(lecturerName);
        if (lecturerTeachesSubjects(lecturerToDelete)) {
            throw new ElementNotFoundException("Cannot delete lecturer " + lecturerName + ", as they teach subjects with students");
        }
        allLecturers.remove(lecturerToDelete);
    }

    public void deleteFaculty(String facultyName) throws ElementNotFoundException {
        Faculty facultyToDelete = findFaculty(facultyName);
        if (facultyHasStudents(facultyToDelete)) {
            throw new ElementNotFoundException("Cannot delete faculty " + facultyName + ", as it has students");
        }
        allFaculties.remove(facultyToDelete);
    }

    private Faculty findFaculty(String facultyName) throws ElementNotFoundException {
        for (Faculty faculty : allFaculties) {
            if (faculty.getName().equals(facultyName)) {
                return faculty;
            }
        }
        throw new ElementNotFoundException("No faculty with name " + facultyName + " found");
    }

    private Subject findSubject(String subjectName) throws ElementNotFoundException {
        for (Subject subject : allSubjects) {
            if (subject.getName().equals(subjectName)) {
                return subject;
            }
        }
        throw new ElementNotFoundException("No subject with name " + subjectName + " found");
    }

    private Lecturer findLecturer(String lecturerName) throws ElementNotFoundException {
        for (Lecturer lecturer : allLecturers) {
            if (lecturer.getName().equals(lecturerName)) {
                return lecturer;
            }
        }
        throw new ElementNotFoundException("No lecturer with name " + lecturerName + " found");
    }


    private boolean facultyHasStudents(Faculty faculty) {
        for (Student student : faculty.getStudents()) {
            if (!student.getSubjects().isEmpty()) {
                return true;
            }
        }
        return false;
    }


    public Student findStudent(String studentName) throws ElementNotFoundException {
        for (Student student : allStudents) {
            if (student.getName().equals(studentName)) {
                return student;
            }
        }
        throw new ElementNotFoundException("No student with name " + studentName + " found");
    }

}