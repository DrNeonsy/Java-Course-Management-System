package Main.Classes.Gen;

import Main.Classes.Spec.Administrator;
import Main.Classes.Spec.Student;
import Main.Classes.Spec.Teacher;
import Main.Classes.Util.Input;
import Main.Classes.Util.Util;
import Main.Data.Course;
import Main.Interfaces.IUsers;

import java.util.function.Function;

import static Main.Classes.App.Application.courses;
import static Main.Classes.App.Application.users;

public abstract class User implements IUsers {
    // ----------------------------------------
    // Attributes
    // ----------------------------------------
    protected String name;
    protected String surname;
    protected String email;
    protected String password;
    protected String username;
    protected String role;
    private static boolean isAdmin;

    // ----------------------------------------
    // Constructor
    // ----------------------------------------
    public User() {
    }

    // ----------------------------------------
    // Methods
    // ----------------------------------------

    // ----------------------------------------
    // Menu
    // ----------------------------------------

    public void MainMenu() {
        while (true) {
            // Start With Greeting The User
            System.out.printf("%n%s%n", "=".repeat(50));
            System.out.printf("%s %s, Welcome To The Main Menu!", this.name, this.surname);
            System.out.printf("%n%s%n%n", "=".repeat(50));

            int optionsLength = 0;

            // region Then Show The Options
            if (this instanceof Administrator) {
                optionsLength = Administrator.options.size();

                for (String option : Administrator.options) {
                    System.out.println(option);
                }
            } else if (this instanceof Teacher) {
                optionsLength = Teacher.options.size();

                for (String option : Teacher.options) {
                    System.out.println(option);
                }
            } else if (this instanceof Student) {
                optionsLength = Student.options.size();

                for (String option : Student.options) {
                    System.out.println(option);
                }
            }
            // endregion

            int option;

            // region Then Get The User's Input
            while (true) {
                String input = Input.getInput("", "Option");

                if (Input.isNumInput(input) && input.length() > 0) {
                    option = Integer.parseInt(input);
                    if (option >= 0 && option < optionsLength) {
                        break;
                    }
                }
                System.out.println("Invalid Input!");
            }
            // endregion

            System.out.println();

            // region Then Execute The User's Input
            switch (option) {
                case 0 -> {
                    System.out.println("Exiting...");
                    return;
                }
                case 1 -> showAccountInformation();
                case 2 -> showCourseAssignments();
                case 3 -> showCourseMembers();
            }

            if (this instanceof Teacher t) {
                switch (option) {
                    case 4 -> t.createExercise();
                    case 5 -> t.deleteExercise();
                    case 6 -> t.addStudent();
                    case 7 -> t.removeStudent();
                }
            }

            if (this instanceof Administrator a) {
                switch (option) {
                    case 4 -> a.showAllCourses();
                    case 5 -> a.showAllUsers();
                    case 6 -> a.createAccount();
                    case 7 -> a.deleteAccount();
                    case 8 -> a.createCourse();
                    case 9 -> a.deleteCourse();
                }
            }
            Input.getInput("", "Press Enter To Continue");
        }
        // endregion
    }

    // ----------------------------------------
    // Implementing IUsers Interface
    // ----------------------------------------

    @Override
    public void showAccountInformation() {
        isAdmin = true;
        System.out.println(this);

        if (Util.decision("Do You Want To Change Your Username Or Password? (Y | N)")) {
            if (Util.decision("Do You Want To Change The Username (N For Password)? (Y | N)")) {
                users.get(users.indexOf(this)).setUsername();
            } else {
                users.get(users.indexOf(this)).setPassword();
            }
        }
    }

    @Override
    public void showCourseAssignments() {
        // This Will Show All The Exercises Of Every Course The User Is Enrolled In
        if (this instanceof Administrator) {
            for (Course course : courses) {
                System.out.println(course);
                System.out.printf("Assignments: %s%n", course.assignments());
            }
        } else if (this instanceof Student) {
            for (Course course : courses) {
                if (course.students().contains(this)) {
                    System.out.println(course);
                    System.out.printf("Assignments: %s%n", course.assignments());
                }
            }
        } else if (this instanceof Teacher) {
            for (Course course : courses) {
                if (course.teacher().contains(this.getName() + " " + this.getSurname())) {
                    System.out.println(course);
                    System.out.printf("Assignments: %s%n", course.assignments());
                }
            }
        }
    }

    @Override
    public void showCourseMembers() {
        if (this instanceof Administrator) { // Show Members Of Every Course
            isAdmin = true;
            for (Course course : courses) {
                System.out.println(course);
                System.out.printf("Students: %s", course.students());
            }

        } else if (this instanceof Student) {

            isAdmin = false;
            for (Course course : courses) {
                if (course.students().contains(this)) {
                    System.out.println(course);
                    System.out.printf("Students: %s", course.students());
                }
            }

        } else if (this instanceof Teacher) {

            isAdmin = false;
            for (Course course : courses) {
                if (course.teacher().contains(this.getName() + " " + this.getSurname())) {
                    System.out.println(course);
                    System.out.printf("Students: %s", course.students());
                }
            }
        }
    }

    private String getValidInput(String message, String what, Function<String, Boolean> validationFunction) {
        while (true) {
            String input = Input.getInput(message, what);
            if (validationFunction.apply(input)) {
                return input;
            }
            System.out.println("Incorrect Input! Please Try Again!");
            System.out.printf("%n%s%n", "=".repeat(50));
        }
    }


    // ----------------------------------------
    // Getters and Setters
    // ----------------------------------------

    private void accountSetters(int mode) {
        switch (mode) {
            case 1 -> {
                String message = """
                        Only Letters Are Allowed
                        Min 2 Max 20
                        """;
                String input = getValidInput(message, "Your Name",
                        inp -> Input.isLengthInput(inp, 2, 20)
                                && Input.isCharInput(inp));
                this.name = Character.toUpperCase(input.charAt(0)) + input.substring(1).toLowerCase();
            }
            case 2 -> {
                String message = """
                        Only Letters Are Allowed
                        Min 2 Max 20
                        """;
                String input = getValidInput(message, "Your Surname",
                        inp -> Input.isLengthInput(inp, 2, 20)
                                && Input.isCharInput(inp));
                this.surname = Character.toUpperCase(input.charAt(0)) + input.substring(1).toLowerCase();
            }
            case 3 -> {
                String message = """
                        Only Letters, Numbers, Dots, Underscores And Hyphens Are Allowed And Starts With An Alphabetical Character
                        Must Contain An @ Symbol And After That Must Be One Alphabetical Character
                        After That Must Be A Dot And After That Must Be 2 Or 3 Alphabetical Characters
                        Min 5 Max 30
                        """;
                String input = getValidInput(message, "Your Email", inp -> Input.isLengthInput(inp, 5, 30) && Input.isEmailInput(inp));

                if (users.size() > 0) {
                    boolean isUnique = true;
                    for (User user : users) {
                        if (!this.equals(user) && user.getEmail().equals(input)) {
                            isUnique = false;
                            System.out.println("Email Already Exists!");
                        }
                    }
                    if (isUnique) {
                        this.email = input;
                    }
                }
            }
            case 4 -> {
                String message = """
                        Only Letters And Numbers Are Allowed
                        Min 3 Max 20
                        """;
                String input = getValidInput(message, "Your Username", inp -> Input.isLengthInput(inp, 3, 20) && Input.isCharNumInput(inp));

                if (users.size() > 0) {
                    boolean isUnique = true;
                    for (User user : users) {
                        if (!this.equals(user) && user.getUsername().equals(input)) {
                            isUnique = false;
                            System.out.println("Username Already Exists!");
                        }
                    }
                    if (isUnique) {
                        this.username = input;
                    }
                }
            }
            case 5 -> {
                String message = """
                        Min 8 Max 30
                        At Least 1 Uppercase Letter
                        At Least 1 Lowercase Letter
                        At Least 1 Number
                        At Least 1 Special Character
                        """;
                this.password = getValidInput(message, "Your Password", inp -> Input.isLengthInput(inp, 8, 30) && Input.isPasswordInput(inp));
            }
            case 6 -> this.role = this.getClass().getSimpleName();
            default -> System.out.println("Invalid Setup Mode!");
        }
    }

    // region Setters

    public void setName() {
        System.out.println("Setup For Name Initiated!\n");

        accountSetters(1);
        System.out.println();
    }

    public void setSurname() {
        System.out.println("Setup For Surname Initiated!\n");

        accountSetters(2);
        System.out.println();
    }

    public void setEmail() {
        System.out.println("Setup For Email Initiated!\n");

        accountSetters(3);
        System.out.println();
    }

    public void setUsername() {
        System.out.println("Setup For Username Initiated!\n");

        accountSetters(4);
        System.out.println();
    }

    public void setPassword() {
        System.out.println("Setup For Password Initiated!\n");

        accountSetters(5);
        System.out.println();
    }

    public void setRole() {
        System.out.println("Setup For Role Initiated!\n");

        accountSetters(6);
        System.out.println();
    }

    public void setAdmin(boolean admin) {
        if (this instanceof Administrator) {
            isAdmin = admin;
        }
    }

    //endregion

    // region Getters

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }

    // endregion

    // ----------------------------------------
    // ToString
    // ----------------------------------------
    @Override
    public String toString() {
        if (isAdmin) {
            return String.format("""
                                                    
                            Name: %s
                            Surname: %s
                            Email: %s
                            Username: %s
                            Password: %s
                            Role: %s
                            """,
                    this.getName(),
                    this.getSurname(),
                    this.getEmail(),
                    this.getUsername(),
                    this.getPassword(),
                    this.getRole());
        } else {
            return String.format("""
                                                    
                            Name: %s
                            Surname: %s
                            Email: %s
                            Role: %s
                            """,
                    this.getName(),
                    this.getSurname(),
                    this.getEmail(),
                    this.getRole());
        }
    }
}
