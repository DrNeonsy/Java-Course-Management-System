package Main.Classes.Spec;

import Main.Classes.Gen.User;
import Main.Classes.Util.Input;
import Main.Data.Course;
import Main.Interfaces.IAdmins;

import java.util.ArrayList;
import java.util.function.Function;

import static Main.Classes.App.Application.courses;
import static Main.Classes.App.Application.users;
import static Main.Classes.Util.Util.isValidEnum;

public class Administrator extends User implements IAdmins {
    // ----------------------------------------
    // Attributes
    // ----------------------------------------
    public static final ArrayList<String> options = new ArrayList<>(Student.options) {
        {
            add("4. Show All Courses");
            add("5. Show All Users");
            add("6. Create Account");
            add("7. Delete Account");
            add("8. Create Course");
            add("9. Delete Course");
        }
    };
    private static boolean isRoot = true; // False After Root Creation / Startup

    // ----------------------------------------
    // Constructor
    // ----------------------------------------

    public Administrator() {
    }

    // ----------------------------------------
    // Methods
    // ----------------------------------------

    @Override
    public void showAllCourses() {
        if (!courses.isEmpty()) {
            this.setAdmin(true);
            for (Course course : courses) {
                System.out.println(course);
                System.out.println(course.students());
                System.out.println(course.assignments() + System.lineSeparator());
            }
        } else {
            System.out.println("No Courses Found!");
        }
    }

    @Override
    public void showAllUsers() {
        this.setAdmin(true);
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Override
    public void deleteAccount() {

        if (users.size() > 1) {
            for (int i = 1; i < users.size(); i++) {
                System.out.printf("%d. %s %s (%s)%n", i, users.get(i).getName(), users.get(i).getSurname(), users.get(i).getRole());
            }

            int option;

            while (true) {
                String input = Input.getInput("", "Option");

                if (input.length() == 0) {
                    return;
                }

                if (Input.isNumInput(input)) {
                    option = Integer.parseInt(input);
                    if (option >= 1 && option < users.size()) {
                        break;
                    }
                }
                System.out.println("Invalid Input!");
            }

            System.out.printf("%n%s %s (%s)%n%n", users.get(option).getName(), users.get(option).getSurname(), users.get(option).getRole());

            users.remove(option);
            users.trimToSize();
            System.out.println("Account Deleted!");
        } else {
            System.out.println("\nNo Accounts Found!");
        }
    }

    public void createAccount() {
        System.out.println("Account Setup Initiated!\n");

        if (!isRoot) { // Default Execution For All IUsers Except Root
            users.add(defineType());
        } else { // Executed Only Once
            users.add(this);
            isRoot = false;
        }

        // Calling Account Information Setters For The New User

        users.get(users.size() - 1).setName();
        users.get(users.size() - 1).setSurname();
        users.get(users.size() - 1).setEmail();
        users.get(users.size() - 1).setUsername();
        users.get(users.size() - 1).setPassword();
        users.get(users.size() - 1).setRole();

        System.out.println("Account Created!");
    }

    private static User defineType() {
        enum Type {
            STUDENT,
            TEACHER,
            ADMINISTRATOR
        }

        while (true) {
            String input = Input.getInput("""
                    Enter The Type Of User You Wish To Create:
                    Student
                    Teacher
                    Administrator
                                        
                    """, "Type");

            input = input.toUpperCase();
            if (isValidEnum(input, Type.class)) {

                switch (Type.valueOf(input)) {
                    case STUDENT -> {
                        return new Student();
                    }
                    case TEACHER -> {
                        return new Teacher();
                    }
                    case ADMINISTRATOR -> {
                        return new Administrator();
                    }
                }
            } else {
                System.out.println("\nInvalid Input!\n");
            }
        }
    }

    @Override
    public void createCourse() {
        System.out.println("Course Setup Initiated!");

        boolean teacherAvailable = false;

        for (User user : users) {
            if (user instanceof Teacher) {
                teacherAvailable = true;
                break;
            }
        }

        if (teacherAvailable) {
            String name = courseSetters("Course Name", 3, 30, Input::isCharInputWithWhitespaces);
            String subject = courseSetters("Subject Name", 2, 20, Input::isCharInput);
            String teacher = selectTeacher();

            String id = String.valueOf(courses.size() + 1);

            courses.add(new Course(id, name, subject, teacher, new ArrayList<>(), new ArrayList<>()));
            System.out.println("\nCourse Created!");
        } else {
            System.out.println("\nNo Teachers Found! Create One First Before Creating A Course");
        }
    }

    private String courseSetters(String prompt, int minLength, int maxLength, Function<String, Boolean> validationFunction) {
        while (true) {
            String input = Input.getInput("Only Letters Are Allowed\nMin " + minLength + " Max " + maxLength + System.lineSeparator(), prompt);
            if (Input.isLengthInput(input, minLength, maxLength) && validationFunction.apply(input)) {
                return input.substring(0, 1).toUpperCase() + input.substring(1);
            }
            System.out.println("Incorrect Input! Please Try Again!");
            System.out.printf("%n%s%n", "=".repeat(50));
        }
    }

    private String selectTeacher() {
        ArrayList<Integer> teacherIndices = new ArrayList<>();
        System.out.println("\nTeachers\n");

        for (int i = 0, t = 1; i < users.size(); i++) {
            if (users.get(i) instanceof Teacher) {
                teacherIndices.add(i);
                System.out.printf("%d. %s %s%n", t++, users.get(i).getName(), users.get(i).getSurname());
            }
        }

        int option;
        while (true) {
            String input = Input.getInput("Select A Teacher For This Course\n", "Option");

            if (input.length() == 0) {
                continue;
            }

            if (Input.isNumInput(input)) {
                option = Integer.parseInt(input);
                if (option >= 1 && option <= teacherIndices.size()) {
                    break;
                }
            }
            System.out.println("Invalid Input!");
        }
        return users.get(teacherIndices.get(option - 1)).getName() + " " + users.get(teacherIndices.get(option - 1)).getSurname();
    }

    @Override
    public void deleteCourse() {
        if (courses.size() > 0) {
            for (int i = 0; i < courses.size(); i++) {
                System.out.printf("%d. %s%n", i + 1, courses.get(i));
            }
            int option;

            while (true) {
                String input = Input.getInput("", "Option");

                if (input.length() == 0) {
                    return;
                }

                if (Input.isNumInput(input)) {
                    option = Integer.parseInt(input);
                    if (option >= 1 && option <= courses.size()) {
                        break;
                    }
                }
                System.out.println("Invalid Input!");
            }
            courses.remove(option - 1);
            courses.trimToSize();
        } else {
            System.out.println("\nNo Courses Found!");
        }
    }

}
