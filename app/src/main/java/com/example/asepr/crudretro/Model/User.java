package com.example.asepr.crudretro.Model;

import com.google.gson.annotations.SerializedName;
import java.util.List;
/**
 * Created by ASEPR on 3/22/2018.
 */

public class User {
    @SerializedName("id")
    private String Id;
    @SerializedName("name")
    private String Name;
    @SerializedName("birth_date")
    private String Birth_date;
    @SerializedName("gender")
    private String Gender;

    public User(String id, String name, String birth_date, String gender) {
        this.Id = id;
        this.Name = name;
        this.Birth_date = birth_date;
        this.Gender = gender;
    }

    public String getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public String getBirth_date() {
        return Birth_date;
    }

    public String getGender() {
        return Gender;
    }

    public void setId(String id) {
        Id = id;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setBirth_date(String birth_date) {
        Birth_date = birth_date;
    }

    public void setGender(String gender) {
        Gender = gender;
    }
}
