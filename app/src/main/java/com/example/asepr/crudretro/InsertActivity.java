package com.example.asepr.crudretro;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.asepr.crudretro.Model.PostPutDelUser;
import com.example.asepr.crudretro.Model.User;
import com.example.asepr.crudretro.Rest.ApiClient;
import com.example.asepr.crudretro.Rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InsertActivity extends AppCompatActivity {
    EditText edtNama, edtBirth, edtGender;
    Button btInsert, btBack;
    ApiInterface mApiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        edtNama = (EditText) findViewById(R.id.edtNama);
        edtBirth = (EditText) findViewById(R.id.edtBirthdate);
        edtGender =  (EditText) findViewById(R.id.edtGender);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        btInsert = (Button) findViewById(R.id.btInserting);
        btInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<List<User>> postUserCall = mApiInterface.postUser("52f866f58d909e13236110e5", "api_andro", "user", "5aaf56931f6d049066c26369", edtNama.getText().toString(), edtBirth.getText().toString(), edtGender.getText().toString());
                postUserCall.enqueue(new Callback<List<User>>() {
                    @Override
                    public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                        MainActivity.ma.refresh();
                        finish();
                    }

                    @Override
                    public void onFailure(Call<List<User>> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Error or check connection", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        btBack = (Button) findViewById(R.id.btBackGo);
        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.ma.refresh();
                finish();
            }
        });
    }
}
