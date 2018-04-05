package com.example.asepr.crudretro;

        import android.content.Intent;
        import android.support.annotation.NonNull;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.support.v7.widget.LinearLayoutManager;
        import android.support.v7.widget.RecyclerView;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.Toast;

        import com.example.asepr.crudretro.Adapter.UserAdapter;
        import com.example.asepr.crudretro.Model.GetUser;
        import com.example.asepr.crudretro.Model.User;
        import com.example.asepr.crudretro.Rest.ApiClient;
        import com.example.asepr.crudretro.Rest.ApiInterface;
        import com.google.android.gms.common.api.GoogleApiClient;
        import com.google.android.gms.common.api.Status;
        import com.karan.churi.PermissionManager.PermissionManager;

        import java.util.ArrayList;
        import java.util.List;
        import retrofit2.Call;
        import retrofit2.Callback;
        import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    Button btIns, btImage, btMaps, btOther;
    ApiInterface mApiInterface;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    public static MainActivity ma;
    PermissionManager permissionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        permissionManager = new PermissionManager() {};
        permissionManager.checkAndRequestPermissions(this);
        btIns = (Button) findViewById(R.id.btIns);
        btImage = (Button) findViewById(R.id.btImage);
        btMaps = (Button) findViewById(R.id.btMaps);
        btOther = (Button) findViewById(R.id.btOther);

        btIns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, InsertActivity.class));
            }
        });
        btImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ImageActivity.class));
            }
        });
        btMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MapActivity.class));
            }
        });
        btOther.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, OtherActivity.class));
            }
        });
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        ma=this;
        refresh();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        ArrayList<String> granted = permissionManager.getStatus().get(0).granted;
        ArrayList<String> denied = permissionManager.getStatus().get(0).denied;

        /*for(String itemg:granted)
            Toast.makeText(this, "" + itemg + " is already granted.", Toast.LENGTH_SHORT).show();*/

       /* for(String itemd:denied)
            Toast.makeText(this, "" + itemd + " is already denied.", Toast.LENGTH_SHORT).show();*/

        Toast.makeText(this, "setting permission in settings -> app -> permissions and enable all permissions to make app run well", Toast.LENGTH_SHORT).show();
    }

    public void refresh() {
        Call<List<User>> userCall = mApiInterface.User();
        userCall.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                //User[] UserList = gson.fromJson(response.body().getListDataUser(), User[].class);
                //List<User> UserList = (response.body().getListDataUser());
                List<User> UserList =  response.body();

                Log.d("Retrofit Get", "Jumlah data User: " + String.valueOf(UserList.size()));
                mAdapter = new UserAdapter(UserList);
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
            }
        });
    }
}
