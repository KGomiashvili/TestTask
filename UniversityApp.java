import java.util.Scanner;

public class UniversityApp {

    public static void main(String[] args) {
        University university = new University();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Enter a command (type 'help' for the list of commands):");
            String input = scanner.nextLine();
            String[] tokens = input.split(" ");

            try {
                switch (tokens[0]) {
                    case "add":
                        handleAddCommand(university, tokens);
                        break;
                    case "show":
                        handleShowCommand(university, tokens);
                        break;
                    case "delete":
                        handleDeleteCommand(university, tokens);
                        break;
                    case "help":
                        showHelp();
                        break;
                    case "exit":
                        System.out.println("Exiting the program. Goodbye!");
                        System.exit(0);
                    default:
                        System.out.println("Invalid command. Type 'help' for the list of commands.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static void handleAddCommand(University university, String[] tokens) throws Exception {
        switch (tokens[1]) {
            case "faculty":
                university.addFaculty(tokens[2]);
                break;
            case "subject":
                university.addSubject(tokens[2]);
                break;
            case "facultySubject":
                university.addFacultySubject(tokens[2], tokens[3]);
                break;
            case "student":
                university.addStudent(tokens[2], tokens[3]);
                break;
            case "lecturer":
                university.addLecturer(tokens[2]);
                break;
            case "lecturerSubject":
                university.addLecturerSubject(tokens[2], tokens[3]);
                break;
            default:
                System.out.println("Invalid 'add' command.");
        }
    }

    private static void handleShowCommand(University university, String[] tokens) throws University.ElementNotFoundException {
        switch (tokens[1]) {
            case "faculties":
                university.showFaculties();
                break;
            case "students":
                university.showStudents();
                break;
            case "subjects":
                university.showSubjects();
                break;
            case "lecturers":
                university.showLecturers();
                break;
            case "facultyLecturers":
                university.showFacultyLecturers(tokens[2]);
                break;
            case "facultySubjects":
                university.showFacultySubjects(tokens[2]);
                break;
            case "studentLecturers":
                university.showStudentLecturers(tokens[2]);
                break;
            case "subjectStudents":
                university.showSubjectStudents(tokens[2]);
                break;
            case "fullUniversityTree":
                university.showFullUniversityTree();
                break;
            default:
                System.out.println("Invalid 'show' command.");
        }
    }

    private static void handleDeleteCommand(University university, String[] tokens) throws Exception {
        switch (tokens[1]) {
            case "lecturer":
                university.deleteLecturer(tokens[2]);
                break;
            case "faculty":
                university.deleteFaculty(tokens[2]);
                break;
            default:
                System.out.println("Invalid 'delete' command.");
        }
    }

    private static void showHelp() {
        System.out.println("List of commands:");
        System.out.println("add faculty {facultyName}");
        System.out.println("add subject {subjectName}");
        System.out.println("add facultySubject {facultyName} {subjectName}");
        System.out.println("add student {studentName} {facultyName}");
        System.out.println("add lecturer {lecturerName}");
        System.out.println("add lecturerSubject {lecturerSubject} {subjectName}");
        System.out.println("show faculties");
        System.out.println("show students");
        System.out.println("show subjects");
        System.out.println("show lecturers");
        System.out.println("show facultyLecturers {facultyName}");
        System.out.println("show facultySubjects {facultyName}");
        System.out.println("show studentLecturers {studentName}");
        System.out.println("show subjectStudents {subjectName}");
        System.out.println("show fullUniversityTree");
        System.out.println("delete lecturer {lecturerName}");
        System.out.println("delete faculty {facultyName}");
        System.out.println("exit");
    }
}
