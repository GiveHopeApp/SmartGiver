package com.givehopeweb.models;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * <p>The <code>User</code> class stores information about each user. Spring
 * annotations are used to designate the <code>User</code> class as a table and each of its
 * properties as a column. Foreign keys link the user to the donations they make. Getters and
 * setters for each of the properties are present.</p>
 *
 * @author David Ryan Alviola
 * @since March 2017
 */
@Entity
@Table (name = "users")
public class User {

    /**
     * Default constructor for the <code>User</code> class. This constructor is used when
     * creating a new user.
     */
    public User () {

    }

    /**
     * <p>Constructor for <code>User</code> class when retrieving user information from the
     * database.</p>
     *
     * @param user object with user information
     */
    public User (User user) {

        id = user.id;
        firstName = user.firstName;
        lastName = user.lastName;
        username = user.username;
        email = user.email;
        password = user.password;
        profilePicture = user.profilePicture;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column (nullable = false)
    @NotBlank (message = "Cannot be blank")
    private String firstName;

    @Column (nullable = false)
    @NotBlank (message = "Cannot be blank")
    private String lastName;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "Cannot be blank")
    private String username;

    @Column(nullable = false, unique = true)
    @NotBlank (message = "Cannot be blank")
    @Email(message = "Enter a valid email")
    private String email;

    @Column(nullable = false)
    @Size(min = 8, message = "Must be at least 8 characters")
    private String password;

    @Column
    private String profilePicture;

    @OneToMany (cascade = CascadeType.ALL, mappedBy = "user")
    private List<Donation> donations;

    @OneToMany (mappedBy = "user")
    private List<Charity> favoriteCharities;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public List<Donation> getDonations() {
        return donations;
    }

    public void setDonations(List<Donation> donations) {
        this.donations = donations;
    }

    public List<Charity> getFavoriteCharities() {
        return favoriteCharities;
    }

    public void setFavoriteCharities(List<Charity> favoriteCharities) {
        this.favoriteCharities = favoriteCharities;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
