package com.givehopeweb.models;

import javax.persistence.*;

/**
 * <p>The <code>UserRole</code> class stores which roles the user belongs to. Spring
 * annotations are used to designate the <code>UserRole</code> class as a table and each of its
 * properties as a column. Foreign keys link to the role to the user that has it assigned. Getters
 * and setters for each of the properties are present.</p>
 *
 * @author David Ryan Alviola
 * @since March 2017
 */
@Entity
@Table(name = "user_roles")
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column (name = "user_id")
    private int userId;

    @Column (name = "role")
    private String role;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
