package com.example.asepr.crudretro.Model;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ASEPR on 3/22/2018.
 */

public class GetUser {
    @SerializedName("status")
    String Status;
    @SerializedName("filename")
    String Filename;
    @SerializedName("message")
    String Message;

    public String getStatus() {
        return Status;
    }
    public void setStatus(String status) {
        Status = status;
    }

    public String getMessage() {
        return Message;
    }
    public void setMessage(String message) {
        Message = message;
    }

    public String getFilename() {
        return Filename;
    }
    public void setFilename(String filename) {
        Filename = filename;
    }


}