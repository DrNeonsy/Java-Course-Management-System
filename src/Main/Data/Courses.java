package Main.Data;

import Main.Classes.Spec.Student;

import java.util.ArrayList;

public record Courses(String id,
                      String name,
                      String subject,
                      String teacher,
                      ArrayList<Student> students,
                      ArrayList<String> assignments) {

    @Override
    public String toString() {
        return String.format("""
                        ID: %s
                        Name: %s
                        Subject: %s
                        """,
                this.id(),
                this.name(),
                this.subject());
    }
}
