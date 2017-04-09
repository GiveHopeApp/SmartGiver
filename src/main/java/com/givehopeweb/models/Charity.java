package com.givehopeweb.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

/**
 * <p>The <code>Charity</code> class stores information about each charity. Spring
 * annotations are used to designate the <code>Charity</code> class as a table and each of its
 * properties as a column. Getters and setters for each of the properties are present.</p>
 *
 * @author David Ryan Alviola
 * @since March 2017
 */
@Entity
@Table (name = "charities")
public class Charity {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    @Column (nullable = false)
    private String charityName;

    @Column (nullable = false)
    private String ein;

    @Column (nullable = false)
    private String city;

    @Column (nullable = false)
    private String state;

    @Column (nullable = false)
    private String category;

    @Column (length = 1000)
    private String description;

    @Column (nullable = false, columnDefinition = "boolean default false")
    @JsonIgnore
    private boolean promoted;

    @OneToMany (mappedBy = "charity")
    @JsonIgnore
    private List<Donation> donations;

    @ManyToOne
    @JoinColumn (name = "saved_by_user_id")
    @JsonIgnore
    private User user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCharityName() {
        return charityName;
    }

    public void setCharityName(String charityName) {
        this.charityName = charityName;
    }

    public boolean isPromoted() {
        return promoted;
    }

    public void setPromoted(boolean promoted) {
        this.promoted = promoted;
    }

    public String getEin() {
        return ein;
    }

    public void setEin(String ein) {
        this.ein = ein;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<Donation> getDonations() {
        return donations;
    }

    public void setDonations(List<Donation> donations) {
        this.donations = donations;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
