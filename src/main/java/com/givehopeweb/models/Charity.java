//package com.givehopeweb.models;
//
//import javax.persistence.*;
//
///**
// * Created by David on 2/17/17.
// */
//@Entity
//@Table (name = "charities")
//public class Charity {
//
//    @Id
//    @GeneratedValue (strategy = GenerationType.IDENTITY)
//    private int id;
//
//    @Column (nullable = false)
//    private String charityName;
//
//    @Column (nullable = false)
//    private String charityInfo;
//
//    @Column (nullable = false)
//    private String location;
//
//    @Column (nullable = false)
//    private boolean favoritedByUser;
//
//    @Column (nullable = false)
//    private boolean promoted;
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getCharityName() {
//        return charityName;
//    }
//
//    public void setCharityName(String charityName) {
//        this.charityName = charityName;
//    }
//
//    public String getCharityInfo() {
//        return charityInfo;
//    }
//
//    public void setCharityInfo(String charityInfo) {
//        this.charityInfo = charityInfo;
//    }
//
//    public String getLocation() {
//        return location;
//    }
//
//    public void setLocation(String location) {
//        this.location = location;
//    }
//
//    public boolean isFavoritedByUser() {
//        return favoritedByUser;
//    }
//
//    public void setFavoritedByUser(boolean favoritedByUser) {
//        this.favoritedByUser = favoritedByUser;
//    }
//
//    public boolean isPromoted() {
//        return promoted;
//    }
//
//    public void setPromoted(boolean promoted) {
//        this.promoted = promoted;
//    }
//}
