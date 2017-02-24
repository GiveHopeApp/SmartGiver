package com.givehopeweb.models;

import javax.persistence.*;
import java.util.List;

/**
 * Created by David on 2/17/17.
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
    private long ein;

    @Column (nullable = false)
    private String url;

    @Column (nullable = false)
    private String city;

    @Column (nullable = false)
    private String state;

    @Column (nullable = false)
    private String zipCode;

    @Column (nullable = false)
    private String category;

    @Column
    private boolean promoted;

    @OneToMany (mappedBy = "charity")
    private List<Donation> donations;

    @ManyToOne
    @JoinColumn (name = "saved_by_user_id")
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

    public long getEin() {
        return ein;
    }

    public void setEin(long ein) {
        this.ein = ein;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
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
}