package edu.nu.corporate_portal;

import jakarta.persistence.*;

@Entity // Marks this class as a database table.
@Table(name = "test_table") // Names the table "test_table".
public class TestEntity {

    @Id // Marks this field as the primary key.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generates the ID.
    private Long id;

    @Column(nullable = false) // Creates a column for this field in the table.
    private String name;

    // Getters and Setters (used to access and modify fields)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
