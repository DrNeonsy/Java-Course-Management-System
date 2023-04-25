package Main.Classes.Gen;

import Main.Classes.Spec.Administrator;
import Main.Classes.Spec.Student;
import Main.Classes.Spec.Teacher;
import Main.Classes.Util.Input;
import Main.Data.Courses;
import Main.Interfaces.IUsers;

import static Main.Classes.App.Application.courses;

public abstract class Users implements IUsers {
    // ----------------------------------------
    // Attributes
    // ----------------------------------------
    protected String name;
    protected String surname;
    protected String email;
    protected String password;
    protected String username;
    protected String role;

    // ----------------------------------------
    // Constructor
    // ----------------------------------------
    public Users() {
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
                case 2 -> enterCourse();
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
                    case 8 -> a.showAllCourses();
                    case 9 -> a.showAllUsers();
                    case 10 -> a.createAccount();
                    case 11 -> a.deleteAccount();
                    case 12 -> a.createCourse();
                    case 13 -> a.deleteCourse();
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
        System.out.println(this);
    }

    @Override
    public void enterCourse() {
        // This Will Show All The Exercises Of Every Course The User Is Enrolled In
        if (this instanceof Administrator) {
            for (Courses course : courses) {
                System.out.println(course);
                System.out.println(course.assignments());
            }
        } else if (this instanceof Student) {
            for (Courses course : courses) {
                if (course.students().contains(this)) {
                    System.out.println(course);
                    System.out.println(course.assignments());
                }
            }
        } else if (this instanceof Teacher) {
            for (Courses course : courses) {
                if (course.teacher().contains(this.getName() + " " + this.getSurname())) {
                    System.out.println(course);
                    System.out.println(course.assignments());
                }
            }
        }
    }

    @Override
    public void showCourseMembers() {
        if (this instanceof Administrator) { // Show Members Of Every Course
            for (Courses course : courses) {
                System.out.println(course);
                System.out.println(course.students());
            }

        } else if (this instanceof Student) {

            for (Courses course : courses) {
                if (course.students().contains(this)) {
                    System.out.println(course);
                    System.out.println(course.students());
                }
            }

        } else if (this instanceof Teacher) {

            for (Courses course : courses) {
                if (course.teacher().contains(this.getName() + " " + this.getSurname())) {
                    System.out.println(course);
                    System.out.println(course.students());
                }
            }
        }
    }


    // ----------------------------------------
    // Getters and Setters
    // ----------------------------------------

    private void setTree(int mode) {

        while (true) { // Loop Until Valid Input Which Is Then Returned
            switch (mode) {
                case 1 -> {
                    String input = Input.getInput("""
                            Only Letters Are Allowed
                            Min 2 Max 20
                                                        
                            """, "Your Name");
                    if (Input.isLengthInput(input, 2, 20)
                            && Input.isCharInput(input)) {
                        this.name = Character.toUpperCase(input.charAt(0)) + input.substring(1).toLowerCase();
                        return;
                    }
                }
                case 2 -> {
                    String input = Input.getInput("""
                            Only Letters Are Allowed
                            Min 2 Max 20
                                                        
                            """, "Your Surname");
                    if (Input.isLengthInput(input, 2, 20)
                            && Input.isCharInput(input)) {
                        this.surname = Character.toUpperCase(input.charAt(0)) + input.substring(1).toLowerCase();
                        return;
                    }
                }
                case 3 -> {
                    String input = Input.getInput("""
                            Only Letters, Numbers, Dots, Underscores, Hyphens And @ Are Allowed
                            Min 5 Max 30
                                                        
                            """, "Your Email");
                    if (Input.isLengthInput(input, 5, 30)
                            && Input.isEmailInput(input)) {
                        this.email = input;
                        return;
                    }
                }
                case 4 -> {
                    String input = Input.getInput("""
                            Only Letters And Numbers Are Allowed
                            Min 5 Max 20
                                                        
                            """, "Your Username");
                    if (Input.isLengthInput(input, 5, 20)
                            && Input.isCharNumInput(input)) {
                        this.username = input;
                        return;
                    }
                }
                case 5 -> {
                    String input = Input.getInput("""
                            Min 8 Max 30
                            At Least 1 Uppercase Letter
                            At Least 1 Lowercase Letter
                            At Least 1 Number
                            At Least 1 Special Character
                                                        
                            """, "Your Password");
                    if (Input.isLengthInput(input, 8, 30)
                            && Input.isPasswordInput(input)) {
                        this.password = input;
                        return;
                    }
                }
                case 6 -> {
                    this.role = this.getClass().getSimpleName();
                    return;
                }

                default -> System.out.println("Invalid Setup Mode!");
            }

            System.out.println("Incorrect Input! Please Try Again!");
            System.out.printf("%n%s%n", "=".repeat(50));
        }
    }

    // region Setters

    public void setName() {
        System.out.println("Setup For Name Initiated!\n");

        setTree(1);
        System.out.println();
    }

    public void setSurname() {
        System.out.println("Setup For Surname Initiated!\n");

        setTree(2);
        System.out.println();
    }

    public void setEmail() {
        System.out.println("Setup For Email Initiated!\n");

        setTree(3);
        System.out.println();
    }

    public void setUsername() {
        System.out.println("Setup For Username Initiated!\n");

        setTree(4);
        System.out.println();
    }

    public void setPassword() {
        System.out.println("Setup For Password Initiated!\n");

        setTree(5);
        System.out.println();
    }

    public void setRole() {
        System.out.println("Setup For Role Initiated!\n");

        setTree(6);
        System.out.println();
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
        return String.format("""
                        Name: %s
                        Surname: %s
                        Email: %s
                        Password: %s
                        Username: %s
                        Role: %s
                        """,
                this.getName(),
                this.getSurname(),
                this.getEmail(),
                this.getPassword(),
                this.getUsername(),
                this.getRole());
    }
}
