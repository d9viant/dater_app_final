package com.comtrade.domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

public class Age implements GeneralDomain, Serializable {
    private int idUser;
    private static LocalDate birthday;
    private static int age;

    public Age(){
        calculateAge();
    }

    public Age(LocalDate birthday){
        Age.birthday=birthday;

    }

    private void calculateAge() {
        LocalDate currentDate = LocalDate.now();
        while(birthday!=null){
            age = Period.between(birthday, currentDate).getYears();
            break;
        }


    }

    public int getAgeInYears() {
        return age;
    }

    public static void setAge(int age) {
        Age.age = age;
    }

    public static LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        Age.birthday = birthday;
    }


    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public static int getAge() {
        return age;
    }

    @Override
    public List<GeneralDomain> fixSelect(ResultSet rs) throws SQLException {
        return null;
    }

    @Override
    public String returnTableName() {
        return null;
    }

    @Override
    public String returnTableRows() {
        return null;
    }

    @Override
    public String returnInsertFormat() {
        return null;
    }

    @Override
    public String delete(GeneralDomain gd) {
        return null;
    }
}
