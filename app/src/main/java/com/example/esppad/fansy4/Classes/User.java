package com.example.esppad.fansy4.Classes;

/**
 * Created by pouya on 4/25/2019.
 * a model class for containing old users
 */

public class User {


    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private int gender;
    private int mobile;
    private String photo;
    private int affiliateCode;
    private int affiliateReferralCode;
    private int walletBallance;
    private int rating;
    private int status;
    private String address;
    private String city;


    //difining constructor for class
    public User(String firstName, String lastName, String userName, String email, int gender, int mobile, String photo, int affiliateCode, int affiliateReferralCode, int walletBallance, int rating, int status, String address, String city) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.email = email;
        this.gender = gender;
        this.mobile = mobile;
        this.photo = photo;
        this.affiliateCode = affiliateCode;
        this.affiliateReferralCode = affiliateReferralCode;
        this.walletBallance = walletBallance;
        this.rating = rating;
        this.status = status;
        this.address = address;
        this.city = city;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public int getGender() {
        return gender;
    }

    public int getMobile() {
        return mobile;
    }

    public String getPhoto() {
        return photo;
    }

    public int getAffiliateCode() {
        return affiliateCode;
    }

    public int getAffiliateReferralCode() {
        return affiliateReferralCode;
    }

    public int getWalletBallance() {
        return walletBallance;
    }

    public int getRating() {
        return rating;
    }

    public int getStatus() {
        return status;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

}
