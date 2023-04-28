package Main.Classes.Spec;

import Main.Classes.Gen.User;
import Main.Classes.Util.Input;
import Main.Interfaces.ITeachers;

import java.util.ArrayList;

import static Main.Classes.App.Application.courses;
import static Main.Classes.App.Application.users;

public class Teacher extends User implements ITeachers {
    public static final ArrayList<String> options = new ArrayList<>(Student.options) {
        // ----------------------------------------
        // Attributes
        // ----------------------------------------

        {
            add("4. Create Exercise");
            add("5. Delete Exercise");
            add("6. Add Student");
            add("7. Remove Student");
        }
    };

    // ----------------------------------------
    // Constructor
    // ----------------------------------------

    public Teacher() {
    }

    // ----------------------------------------
    // Methods
    // ----------------------------------------

    private int selectCourse() {
        ArrayList<Integer> iTeachCourseIndex = new ArrayList<>();

        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).teacher().equals(this.getName() + " " + this.getSurname())) {
                System.out.println(courses.get(i));
                iTeachCourseIndex.add(i);
            }
        }

        int option;

        while (true) {
            String input = Input.getInput("", "Course ID");

            if (input.length() == 0) {
                return -1;
            }

            if (Input.isNumInput(input)) {
                option = Integer.parseInt(input);
                boolean valid = false;

                for (int index : iTeachCourseIndex) {
                    if (Integer.parseInt(courses.get(index).id()) == option) {
                        option = index;
                        valid = true;
                        break;
                    }
                }

                if (valid) {
                    break;
                }
            }
            System.out.println("Invalid Input!");
        }

        return option;
    }

    private void createOrDeleteExerciseHelper(int courseIndex, boolean isCreate) {
        if (isCreate) {
            while (true) {
                boolean isUnique = true;
                String input = Input.getInput("", "Assignment Task");

                if (Input.isCharInputWithWhitespaces(input)) {
                    for (int i = 0; i < courses.get(courseIndex).assignments().size(); i++) {
                        if (courses.get(courseIndex).assignments().get(i).equalsIgnoreCase(input)) {
                            isUnique = false;
                            System.out.println("Exercise Already Exists!");
                            break;
                        }
                    }
                    if (isUnique) {
                        courses.get(courseIndex).assignments().add(input);
                        return;
                    }
                }
            }
        } else {
            if (courses.get(courseIndex).assignments().size() > 0) {
                for (int i = 0; i < courses.get(courseIndex).assignments().size(); i++) {
                    System.out.printf("%d. %s%n", i + 1, courses.get(courseIndex).assignments().get(i));
                }

                while (true) {
                    String input = Input.getInput("", "Assignment");

                    if (input.length() == 0) {
                        continue;
                    }

                    if (Input.isNumInput(input)) {
                        int option = Integer.parseInt(input) - 1;
                        if (option >= 0 && option < courses.get(courseIndex).assignments().size()) {
                            courses.get(courseIndex).assignments().remove(option);
                            courses.get(courseIndex).assignments().trimToSize();
                            return;
                        }

                    }
                    System.out.println("Invalid Input!");
                }
            } else {
                System.out.println("\nNo Assignments Found!");
            }
        }
    }

    @Override
    public void createExercise() {
        if (courses.size() > 0) {
            int option = selectCourse();
            if (option == -1) {
                return;
            }

            createOrDeleteExerciseHelper(option, true);
        } else {
            System.out.println("\nNo Courses Found!");
        }
    }

    @Override
    public void deleteExercise() {
        if (courses.size() > 0) {
            int option = selectCourse();
            if (option == -1) {
                return;
            }

            createOrDeleteExerciseHelper(option, false);
        } else {
            System.out.println("\nNo Courses Found!");
        }
    }

    private int[] selectCourseAndStudent(boolean shouldListStudents) {
        int[] result = {-1, -1}; // [0] - courseIndex, [1] - studentIndex

        if (courses.size() > 0) {
            ArrayList<Integer> iTeachCourseIndex = new ArrayList<>();

            for (int i = 0; i < courses.size(); i++) {
                if (courses.get(i).teacher().equals(this.getName() + " " + this.getSurname())) {
                    System.out.println(courses.get(i));
                    iTeachCourseIndex.add(i);
                }
            }

            int courseInput;

            while (true) {
                String input = Input.getInput("", "Course ID");

                if (input.length() == 0) {
                    return result;
                }

                if (Input.isNumInput(input)) {
                    courseInput = Integer.parseInt(input);
                    boolean valid = false;

                    for (int index : iTeachCourseIndex) {
                        if (Integer.parseInt(courses.get(index).id()) == courseInput) {
                            courseInput = index;
                            valid = true;
                            break;
                        }
                    }

                    if (valid) {
                        break;
                    }
                }
                System.out.println("Invalid Input!");
            }

            result[0] = courseInput;

            if (shouldListStudents && courses.get(courseInput).students().size() > 0) {
                ArrayList<Integer> studentIndexes = new ArrayList<>();

                for (int i = 0, s = 1; i < users.size(); i++) {
                    if (users.get(i) instanceof Student) {
                        studentIndexes.add(i);
                        System.out.printf("%n%nUser %d%s", s++, users.get(i));
                    }
                }

                int studentInput;

                while (true) {
                    String input = Input.getInput("Select A Student\n", "Option");

                    if (input.length() == 0) {
                        continue;
                    }

                    if (Input.isNumInput(input)) {
                        studentInput = Integer.parseInt(input);
                        if (studentInput >= 1 && studentInput <= studentIndexes.size()) {
                            result[1] = studentIndexes.get(studentInput - 1);
                            break;
                        }
                    }
                    System.out.println("Invalid Input!");
                }
            } else {
                System.out.println("\nNo Students Found!");
            }
        }
        return result;
    }

    @Override
    public void addStudent() {
        int[] selectedCourseAndStudent = selectCourseAndStudent(true);
        int courseIndex = selectedCourseAndStudent[0];
        int studentIndex = selectedCourseAndStudent[1];

        if (courseIndex != -1) {
            if (studentIndex != -1) {
                boolean isUnique = true;

                for (int i = 0; i < courses.get(courseIndex).students().size(); i++) {
                    if (courses.get(courseIndex).students().get(i).equals(users.get(studentIndex))) {
                        isUnique = false;
                        System.out.println("Student Already Enrolled!");
                        break;
                    }
                }

                if (isUnique) {
                    courses.get(courseIndex).students().add((Student) users.get(studentIndex));
                }
            }
        } else {
            System.out.println("\nNo Courses Found!");
        }
    }

    @Override
    public void removeStudent() {
        int[] selectedCourseAndStudent = selectCourseAndStudent(false);
        int courseIndex = selectedCourseAndStudent[0];

        if (courseIndex != -1) {
            if (courses.get(courseIndex).students().size() > 0) {
                for (int i = 0; i < courses.get(courseIndex).students().size(); i++) {
                    System.out.printf("%n%nUser %d%s%n", i + 1, courses.get(courseIndex).students().get(i));
                }

                int studentInput;

                while (true) {
                    String input = Input.getInput("Select A Student To Remove\n", "Option");

                    if (input.length() == 0) {
                        continue;
                    }

                    if (Input.isNumInput(input)) {
                        studentInput = Integer.parseInt(input);
                        if (studentInput >= 1 && studentInput <= courses.get(courseIndex).students().size()) {
                            courses.get(courseIndex).students().remove(studentInput - 1);
                            courses.get(courseIndex).students().trimToSize();
                            return;
                        }
                    }
                    System.out.println("Invalid Input!");
                }
            }
        } else if (courses.size() == 0) {
            System.out.println("\nNo Courses Found!");
        }
    }
}
