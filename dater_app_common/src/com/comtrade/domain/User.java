package com.comtrade.domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import static com.comtrade.domain.Constants.DBWRITTEN;
import static com.comtrade.domain.Constants.RDYFORDB;

public class User implements GeneralDomain, Serializable {
    private int id;
    private String username;
    private String firstName;
    private String lastName;
    private String pass;
    private String email;
    private String bio;
    private Location location;
    private Age age = null;
    private Rating rating = null;
    private Gender gender = null;
    private int readyForSql = DBWRITTEN;
    private int seen = 0;
    private Pictures p = null;

    public User() {
        location=new Location();
        age = new Age();
        rating = new Rating();
        gender = new Gender();
    }

    public User(int ID, String firstname, String lastName, String userName, String password, String email, String bio) {
        this.id=ID;
        this.firstName=firstname;
        this.lastName=lastName;
        this.username=userName;
        this.pass=password;
        this.email=email;
        this.bio=bio;

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
        this.lastName = lastName;
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


    public int getId_usera() {
        return id;
    }


    public Age getAge() {
        return age;
    }

    public void setAge(Age a) {
        this.age = a;
    }

    public Pictures getP() {
        return p;
    }

    public void setP(Pictures p) {
        this.p = p;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public int getSeen() {
        return seen;
    }

    public void setSeen(int seen) {
        this.seen = seen;
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
                u.setId(idUser);
                u.setFirstName(firstName);
                u.setLastName(lastName);
                u.setUsername(userName);
                u.setPass(password);
                u.setEmail(email);
                u.setBio(bio);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return u;
    }

    @Override
    public String returnInnerJoin() {
        return "SELECT * FROM user" +
                " INNER JOIN location ON location.username = user.username" +
                " INNER JOIN gender ON gender.username = user.username" +
                " INNER JOIN age ON age.username = user.username" +
                " INNER JOIN rating ON rating.username = user.username";
    }


    @Override
    public String returnTableName() {
        // TODO Auto-generated method stub
        return "user";
    }

    @Override
    public String returnTableRows() {
        return " (firstName, lastName, username, password, email, bio) ";
    }

    @Override
    public String returnInsertFormat() {

        return "VALUES ('" + firstName + "','" + lastName + "','" + username + "','" + pass + "','" + email + "','" + bio + "')"+"ON DUPLICATE KEY UPDATE bio='" + bio + "'";

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
        try {
            while (rs.next()) {
                int idUser = rs.getInt("id");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String userName = rs.getString("username");
                String password = rs.getString("password");
                String email = rs.getString("email");
                String bio = rs.getString("bio");
                User u = new User(idUser, firstName, lastName, userName, password, email, bio);
                String genderID = rs.getString("username");
                int gender = rs.getInt("gender");
                int preferredGender = rs.getInt("preferredGender");
                Gender g = new Gender(genderID, gender, preferredGender);
                u.setGender(g);
                double longitude = rs.getDouble("longitude");
                double latitude = rs.getDouble("latitude");
                String address = rs.getString("address");
                int prefDistance = rs.getInt("prefferedDistance");
                Location l = new Location(longitude, latitude, address, prefDistance);
                l.setUsername(username);
                u.setLocation(l);
                int Age = rs.getInt("age");
                Age a = new Age();
                a.setAge(Age);
                u.setAge(a);
                int rating = rs.getInt("rating");
                int newStatus = rs.getInt("newStatus");
                int superUser = rs.getInt("superUser");
                int k = rs.getInt("k");
                Rating r = new Rating();
                r.setUsername(username);
                r.setRating(rating);
                r.setNewStatus(newStatus);
                r.setSuperUser(superUser);
                r.setK(k);
                u.setR(r);
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
    public String returnUserName() {

        return " WHERE user.username = " + getUsername();
    }

    @Override
    public String getForSelectForSpecific(GeneralDomain u) {
        return null;
    }

    public void setId(int id) {
        this.id = id;
    }
}
