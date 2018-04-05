package com.example.asepr.crudretro;


        import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
        import android.util.Log;
        import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

        import com.example.asepr.crudretro.Model.PostPutDelUser;
        import com.example.asepr.crudretro.Rest.ApiClient;
import com.example.asepr.crudretro.Rest.ApiInterface;
import com.example.asepr.crudretro.Model.User;

        import java.util.List;

        import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditActivity extends AppCompatActivity {
    EditText edtId, edtGnd, edtName, edtBirth;
    Button btUpdate, btDelete, btBack;
    ApiInterface mApiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        edtId = (EditText) findViewById(R.id.edtId);
        edtGnd = (EditText) findViewById(R.id.edtGender);
        edtName = (EditText) findViewById(R.id.edtName);
        edtBirth = (EditText) findViewById(R.id.edtBirthday);
        Intent mIntent = getIntent();
        edtId.setText(mIntent.getStringExtra("Id"));
        edtId.setTag(edtId.getKeyListener());
        edtId.setKeyListener(null);
        edtName.setText(mIntent.getStringExtra("Name"));
        edtBirth.setText(mIntent.getStringExtra("Birthday"));
        edtGnd.setText(mIntent.getStringExtra("Gender"));
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        btUpdate = (Button) findViewById(R.id.btUpdate2);
        btUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<PostPutDelUser> updateUserCall = mApiInterface.putKontak(
                        "52f866f58d909e13236110e5",
                        "api_andro",
                        "user",
                        "5aaf56931f6d049066c26369",
                        edtId.getText().toString(),
                        edtName.getText().toString(),
                        edtBirth.getText().toString(),
                        edtGnd.getText().toString()
                        );
                updateUserCall.enqueue(new Callback<PostPutDelUser>() {
                    @Override
                    public void onResponse(Call<PostPutDelUser> call, Response<PostPutDelUser> response) {
                        MainActivity.ma.refresh();
                        finish();
                    }

                    @Override
                    public void onFailure(Call<PostPutDelUser> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
        btDelete = (Button) findViewById(R.id.btDelete2);
        btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtId.getText().toString().trim().isEmpty()==false){
                    Call<PostPutDelUser> deleteUser = mApiInterface.deleteUser(edtId.getText().toString());
                    deleteUser.enqueue(new Callback<PostPutDelUser>() {
                        @Override
                        public void onResponse(Call<PostPutDelUser> call, Response<PostPutDelUser> response) {
                            MainActivity.ma.refresh();
                            finish();
                        }

                        @Override
                        public void onFailure(Call<PostPutDelUser> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), "Error or check connection", Toast.LENGTH_LONG).show();
                        }
                    });
                }else{
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                }
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