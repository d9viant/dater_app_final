package com.comtrade.domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class User implements GeneralDomain, Serializable {
    private int id;
    private String username;
    private String firstName;
    private String lastName;
    private String pass;
    private String email;
    private String bio;
    private String userPhoto;
    private Location location = null;
    private Age age = null;
    private Rating rating = null;
    private Gender gender = null;
    private static int readyForSql = 0;
    private static int userArrayIndex;


    private Matches matches = null;

    public User() {
        age = new Age();
        rating=new Rating();
        gender=new Gender();
        location=new Location();
    }

    public User(int ID, String firstname, String lastName, String userName, String password, String email, String bio, String userPhoto) {
        this.id=ID;
        this.firstName=firstname;
        this.lastName=lastName;
        this.username=userName;
        this.pass=password;
        this.email=email;
        this.bio=bio;
        this.userPhoto=userPhoto;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
        lastName = lastName;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Rating getRating() {
        return rating;
    }

    public void setR(Rating r) {
        this.rating = r;
    }

    public String getUserPhoto() {
        return userPhoto;
    }

    public void setUserPhoto(String userPhoto) {
        this.userPhoto = userPhoto;
    }

    public int getId() {
        return id;
    }

    public Location getLocation() {
        return location;
    }

    public void setL(Location l) {
        this.location = l;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Matches getMatches() {
        return matches;
    }

    public void setMatches(Matches matches) {
        this.matches = matches;
    }

    public int getId_usera() {
        return id;
    }


    public Age getAge() {
        return age;
    }

    public void setAge(Age a) {
        this.age = a;
    }

    @Override
    public List<GeneralDomain> fixSelect(ResultSet rs) {
        List<GeneralDomain> list = new ArrayList<>();
        try {
            while (rs.next()) {
                int idUser = rs.getInt("id");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String userName=rs.getString("username");
                String password = rs.getString("password");
                String email = rs.getString("email");
                String bio = rs.getString("bio");
                String userPhoto = rs.getString("userPhoto");

                User u = new User(idUser, firstName, lastName, userName, password, email, bio, userPhoto);
                list.add(u);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public String returnTableName() {
        // TODO Auto-generated method stub
        return "user";
    }

    @Override
    public String returnTableRows() {
        return " (firstName,lastName,username,password,email,bio,userPhoto) ";
    }

    @Override
    public String returnInsertFormat() {
        // TODO Auto-generated method stub
        return "VALUES ( '"+firstName+"','"+lastName+"','"+username+"','"+pass+"','"+lastName+"','"+email+"','"+bio+"','"+userPhoto+"')";
    }

    @Override
    public String delete(GeneralDomain gd) {
        User u = (User) gd;
        return "WHERE id =" + u.getId();
    }




}
