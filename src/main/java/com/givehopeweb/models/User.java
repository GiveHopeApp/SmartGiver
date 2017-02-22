//package com.givehopeweb.models;
//
//import org.hibernate.validator.constraints.Email;
//import org.hibernate.validator.constraints.NotBlank;
//
//import javax.persistence.*;
//import javax.validation.constraints.Size;
//import java.util.List;
//
///**
// * Created by David on 2/17/17.
// */
//@Entity
//@Table (name = "users")
//public class User {
//
//    public User () {
//
//    }
//
//    public User (User user) {
//
//        id = user.id;
//        username = user.username;
//        email = user.email;
//        password = user.password;
//        profilePicture = user.profilePicture;
//    }
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
//
//    @Column(nullable = false, unique = true)
//    @NotBlank(message = "Cannot be blank")
//    private String username;
//
//    @Column(nullable = false, unique = true)
//    @NotBlank (message = "Cannot be blank")
//    @Email(message = "Enter a valid email")
//    private String email;
//
//    @Column(nullable = false)
//    @NotBlank (message = "Cannot be blank")
//    @Size(min = 8, message = "Must be at least 8 characters")
//    private String password;
//
//    @Column
//    private String profilePicture;
//
//    @OneToMany (cascade = CascadeType.ALL, mappedBy = "user")
//    private List<Donation> donations;
//
//    @OneToMany (mappedBy = "user")
//    private List<Charity> favoriteCharities;
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public String getProfilePicture() {
//        return profilePicture;
//    }
//
//    public void setProfilePicture(String profilePicture) {
//        this.profilePicture = profilePicture;
//    }
//
//    public List<Donation> getDonations() {
//        return donations;
//    }
//
//    public void setDonations(List<Donation> donations) {
//        this.donations = donations;
//    }
//
//    public List<Charity> getFavoriteCharities() {
//        return favoriteCharities;
//    }
//
//    public void setFavoriteCharities(List<Charity> favoriteCharities) {
//        this.favoriteCharities = favoriteCharities;
//    }
//}
