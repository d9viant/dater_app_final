package com.comtrade.domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
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
    private int readyForSql = 0;
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

    public int getReadyForSql() {
        return readyForSql;
    }

    public void setReadyForSql(int readyForSql) {
        this.readyForSql = readyForSql;
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

    @SuppressWarnings("Duplicates")
    @Override
    public GeneralDomain fixSelect(ResultSet rs) {
        User u = new User();
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
                u.setId(idUser);
                u.setFirstName(firstName);
                u.setLastName(lastName);
                u.setUsername(userName);
                u.setPass(password);
                u.setEmail(email);
                u.setBio(bio);
                u.setUserPhoto(userPhoto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return u;
    }

    @Override
    public String returnInnerJoin() {
        return "SELECT user.*, gender.*, location.*, age.*, rating.* FROM user INNER JOIN location ON location.userId = user.id INNER JOIN gender ON gender.userId = user=user.id INNER JOIN age ON age.userId = user.id INNER JOIN rating ON rating.id = user.id";
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
    public String returnInsertFormat(GeneralDomain gd) {
        User u = (User) gd;
        return "VALUES ( '" + u.firstName + "','" + u.lastName + "','" + u.username + "','" + u.pass + "','" + u.lastName + "','" + u.email + "','" + u.bio + "','" + u.userPhoto + "')";
    }

    @Override
    public String delete(GeneralDomain gd) {
        User u = (User) gd;
        return "WHERE id =" + u.getId();
    }

    @SuppressWarnings("Duplicates")
    @Override
    public HashMap<String, GeneralDomain> fixInnerSelect(ResultSet rs) throws SQLException {
        HashMap<String, GeneralDomain> list = new HashMap<>();

        Gender g = new Gender();
        Location l = new Location();
        Age a = new Age();
        Rating r = new Rating();
        try {
            while (rs.next()) {
                int idUser = rs.getInt("id");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String userName = rs.getString("username");
                String password = rs.getString("password");
                String email = rs.getString("email");
                String bio = rs.getString("bio");
                String userPhoto = rs.getString("userPhoto");
                User u = new User(idUser, firstName, lastName, userName, password, email, bio, userPhoto);
                int genderID = rs.getInt("userId");
                int gender = rs.getInt("gender");
                int prefferedGender = rs.getInt("preferredGender");
                Gender gen = new Gender(genderID, gender, prefferedGender);
                ;
                list.put(u.getUsername(), u);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public HashMap<String, List<GeneralDomain>> fixInnerSelectList(ResultSet rs) throws SQLException {
        return null;
    }

    @Override
    public String returnUserName(GeneralDomain gd) {
        User u = (User) gd;
        return "WHERE username =" + u.getUsername();
    }

    public void setId(int id) {
        this.id = id;
    }
}
