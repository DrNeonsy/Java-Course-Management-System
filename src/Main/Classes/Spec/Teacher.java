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

    @Override
    public void createExercise() {
        if (courses.size() > 0) {
            ArrayList<Integer> indexes = new ArrayList<>();

            for (int i = 0; i < courses.size(); i++) {
                if (courses.get(i).teacher().equals(this.getName() + " " + this.getSurname())) {
                    System.out.println(courses.get(i));
                    indexes.add(i);
                }
            }

            int option;

            while (true) {
                String input = Input.getInput("", "Course ID");

                if (input.length() == 0) {
                    return;
                }

                if (Input.isNumInput(input)) {
                    option = Integer.parseInt(input);
                    boolean valid = false;

                    for (int index : indexes) {
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

            while (true) {
                String input = Input.getInput("", "Assignment Task");

                if (Input.isCharInputWithWhitespaces(input)) {
                    courses.get(option).assignments().add(input);
                    return;
                }
            }
        } else {
            System.out.println("\nNo Courses Found!");
        }
    }

    @Override
    public void deleteExercise() {
        if (courses.size() > 0) {
            ArrayList<Integer> indexes = new ArrayList<>();

            for (int i = 0; i < courses.size(); i++) {
                if (courses.get(i).teacher().equals(this.getName() + " " + this.getSurname())) {
                    System.out.println(courses.get(i));
                    indexes.add(i);
                }
            }

            int option;

            while (true) {
                String input = Input.getInput("", "Course ID");

                if (input.length() == 0) {
                    return;
                }

                if (Input.isNumInput(input)) {
                    option = Integer.parseInt(input);
                    boolean valid = false;

                    for (int index : indexes) {
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

            if (courses.get(option).assignments().size() > 0) {
                for (int i = 0; i < courses.get(option).assignments().size(); i++) {
                    System.out.printf("%d. %s%n", i + 1, courses.get(option).assignments().get(i));
                }

                while (true) {
                    String input = Input.getInput("", "Assignment");

                    if (input.length() == 0) {
                        continue;
                    }

                    if (Input.isNumInput(input)) {
                        option = Integer.parseInt(input) - 1;
                        if (option >= 0 && option < courses.get(option).assignments().size()) {
                            courses.get(option).assignments().remove(option);
                            return;
                        }

                    }
                    System.out.println("Invalid Input!");
                }
            } else {
                System.out.println("\nNo Assignments Found!");
            }
        } else {
            System.out.println("\nNo Courses Found!");
        }
    }

    @Override
    public void addStudent() {
        if (courses.size() > 0) {
            ArrayList<Integer> indexes = new ArrayList<>();

            for (int i = 0; i < courses.size(); i++) {
                if (courses.get(i).teacher().equals(this.getName() + " " + this.getSurname())) {
                    System.out.println(courses.get(i));
                    indexes.add(i);
                }
            }

            int courseInput;

            while (true) {
                String input = Input.getInput("", "Course ID");

                if (input.length() == 0) {
                    return;
                }

                if (Input.isNumInput(input)) {
                    courseInput = Integer.parseInt(input);
                    boolean valid = false;

                    for (int index : indexes) {
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

            ArrayList<Integer> studentIndexes = new ArrayList<>();

            for (int i = 0; i < users.size(); i++) {
                if (users.get(i) instanceof Student) {
                    System.out.printf("%s", courses.get(i));
                    indexes.add(i);
                }
            }

        } else {
            System.out.println("\nNo Courses Found!");
        }


    }

    @Override
    public void removeStudent() {

    }
}
