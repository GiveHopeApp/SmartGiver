package com.givehopeweb.models;

import javax.persistence.*;

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

    @Column (nullable = false)
    private boolean favoritedByUser;

    @Column (nullable = false)
    private boolean promoted;

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

    public boolean isFavoritedByUser() {
        return favoritedByUser;
    }

    public void setFavoritedByUser(boolean favoritedByUser) {
        this.favoritedByUser = favoritedByUser;
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
}
