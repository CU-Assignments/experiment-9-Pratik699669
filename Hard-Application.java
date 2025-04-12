import jakarta.persistence.*;

@Entity
public class Account {
    @Id
    private int id;
    private String name;
    private double balance;

    // Getters and Setters
}
