package com.example.asepr.crudretro.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ASEPR on 3/28/2018.
 */

public class Img {
    @SerializedName("id")
    private String Id;
    @SerializedName("path")
    private String Path;
    @SerializedName("image_name")
    private String Image_Name;
    @SerializedName("user_id")
    private String User_id;
    @SerializedName("status")
    private String Status;
    @SerializedName("filename")
    private String Filename;
    @SerializedName("message")
    private String Message;

    public Img(String id, String path, String image_Name, String user_id, String status, String filename, String message) {
        Id = id;
        Path = path;
        Image_Name = image_Name;
        User_id = user_id;
        Status = status;
        Filename = filename;
        Message = message;
    }

    public String getStatus() {
        return Status;
    }

    public String getMessage() {
        return Message;
    }

    public String getFilename() {
        return Filename;
    }

    public String getId() {
        return Id;
    }

    public String getPath() {
        return Path;
    }

    public String getImage_Name() {
        return Image_Name;
    }

    public String getUser_id() {
        return User_id;
    }

    public void setId(String id) {
        Id = id;
    }

    public void setPath(String path) {
        Path = path;
    }

    public void setImage_Name(String image_Name) {
        Image_Name = image_Name;
    }

    public void setUser_id(String user_id) {
        User_id = user_id;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public void setFilename(String filename) {
        Filename = filename;
    }
}
