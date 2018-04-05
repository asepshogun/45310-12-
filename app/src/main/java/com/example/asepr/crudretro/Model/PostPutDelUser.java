package com.example.asepr.crudretro.Model;
import com.google.gson.annotations.SerializedName;
/**
 * Created by ASEPR on 3/22/2018.
 */

public class PostPutDelUser {
//dipakai saat dihapus saja datanya
    @SerializedName("status")
    String status;
    @SerializedName("message")
    String message;

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

