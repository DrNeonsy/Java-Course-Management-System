package Main.Classes.Gen;

import Main.Classes.Util.Input;

public abstract class Users implements Main.Interfaces.Users {
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
    // Getters and Setters
    // ----------------------------------------

    public void setName() {
        System.out.println("Setup For Name Initiated!\n");

        setTree(1);
    }

    public void setSurname() {
        System.out.println("Setup For Surname Initiated!\n");

        setTree(2);
    }

    public void setEmail() {
        System.out.println("Setup For Email Initiated!\n");

        setTree(3);
    }

    public void setPassword() {
        System.out.println("Setup For Password Initiated!\n");

        setTree(4);
    }

    public void setUsername() {
        System.out.println("Setup For Username Initiated!\n");

        setTree(5);
    }

    public void setRole() {
        System.out.println("Setup For Role Initiated!\n");

        setTree(6);
    }

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
                        this.name = input;
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
                        this.surname = input;
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
                case 5 -> {
                    String input = Input.getInput("""
                            Only Letters And Numbers Are Allowed
                            Min 5 Max 20
                                                        
                            """, "Your Username");
                    if (Input.isLengthInput(input, 5, 20)
                            && Input.isCharInput(input)
                            && Input.isNumInput(input)) {
                        this.username = input;
                        return;
                    }
                }
                case 6 -> this.role = this.getClass().getSimpleName();

                default -> System.out.println("Invalid Setup Mode!");
            }

            System.out.println("Incorrect Input! Please Try Again!");
            System.out.printf("%n%s%n", "=".repeat(50));
        }
    }
}
