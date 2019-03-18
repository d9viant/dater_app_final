package com.comtrade.domain;

import java.time.LocalDate;
import java.time.Period;

public class Age {
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

}
