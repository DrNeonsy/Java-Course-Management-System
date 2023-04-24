package Main.Classes.Gen;

public abstract class Users {
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
        System.out.println("Setup For Name Initiated!");

        setupTree(1);
    }

    public void setSurname() {
        System.out.println("Setup For Surname Initiated!");

        setupTree(2);
    }

    public void setEmail() {
        System.out.println("Setup For Email Initiated!");

        setupTree(3);
    }

    public void setPassword() {
        System.out.println("Setup For Password Initiated!");

        setupTree(4);
    }

    public void setUsername() {
        System.out.println("Setup For Username Initiated!");
        setupTree(5);
    }

    public void setRole() {
        System.out.println("Setup For Role Initiated!");

        setupTree(6);
    }

    private void setupTree(int mode) {
        switch (mode) {
            case 1 -> {
                // Setup Name
            }
            case 2 -> {
                // Setup Surname
            }
            case 3 -> {
                // Setup Email
            }
            case 4 -> {
                // Setup Password
            }
            case 5 -> {
                // Setup Username
            }
            case 6 -> {
                // Setup Role
            }
            default -> System.out.println("Invalid Setup Mode!");
        }
    }
}
