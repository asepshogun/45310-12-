package com.example.asepr.crudretro;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.CursorLoader;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.asepr.crudretro.Model.Img;
import com.example.asepr.crudretro.Rest.ApiClient;
import com.example.asepr.crudretro.Rest.ApiInterface;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ImageActivity extends AppCompatActivity {
    ImageView imageView;
    Button btCam, btUpl, btBrowse;
    Bitmap photo;
    ApiInterface mApiInterfaceImg, mApiInterface;
    Intent intent = new Intent();
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Uploading...");

        imageView = (ImageView)findViewById(R.id.ImageView);
        btCam = (Button)findViewById(R.id.btOptImg);
        btUpl = (Button) findViewById(R.id.btUploadImg);
        btBrowse = (Button) findViewById(R.id.btBrowse);
        btUpl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //uploadFile();
            }
        });

        btCam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                Uri file = Uri.fromFile(getOutputMediaFile());
                intent.putExtra(MediaStore.EXTRA_OUTPUT, file);
                startActivityForResult(intent, 2);

            }
        });
        btBrowse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //intent.setType("image/*");
                //intent.setAction(Intent.ACTION_GET_CONTENT);
                //startActivityForResult(Intent.createChooser(intent, "Select Picture"),1);

                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, 1);
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            switch (requestCode) {
                case 1:
                    // Get the Image from data
                    photo = MediaStore.Images.Media.getBitmap(getContentResolver(), data.getData());
                    imageView.setImageBitmap(photo);
                    Uri selectedImage = data.getData();
                    uploadFile(selectedImage);
                    break;
                case 2:
                    if (resultCode == Activity.RESULT_OK) {
                        /*photo = (Bitmap) data.getExtras().get("data");
                        imageView.setImageBitmap(photo);*/

                    } else if (resultCode == Activity.RESULT_CANCELED) {
                        Toast.makeText(getApplicationContext(), "Selecting picture cancelled", Toast.LENGTH_LONG).show();
                    }
                    break;
            }
        } catch (Exception e) {
            Log.e("", "Exception in onActivityResult : " + e.getMessage());
        }
    }


    private static File getOutputMediaFile(){
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "CameraDemo");

        if (!mediaStorageDir.exists()){
            if (!mediaStorageDir.mkdirs()){
                return null;
            }
        }

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        return new File(mediaStorageDir.getPath() + File.separator +
                "IMG_"+ timeStamp + ".jpg");
    }

    // Uploading Image/Video
    private void uploadFile(Uri fileUri) {
        progressDialog.show();
        // Map is used to multipart the file using okhttp3.RequestBody
        File file = new File(getRealPathFromURI(fileUri));
        RequestBody requestBody = RequestBody.create(MediaType.parse("**/"), file);
        MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("fileToUpload", file.getName(), requestBody);
        RequestBody token = RequestBody.create(MediaType.parse("token"), "52f866f58d909e13236110e5");
        RequestBody project = RequestBody.create(MediaType.parse("project"), "api_andro");

        mApiInterfaceImg = ApiClient.getClientImg().create(ApiInterface.class);
        Call<Img> call = mApiInterfaceImg.postImage(fileToUpload, token, project);

        call.enqueue(new Callback<Img>() {
            @Override
            public void onResponse(Call<Img> call, Response<Img> response) {
                if(response.body().getMessage().equals("Success")){
                    loadImageData(response.body().getFilename());
                }else{
                    Toast.makeText(getApplicationContext(), "Image isn't saved", Toast.LENGTH_LONG).show();
                }progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<Img> call, Throwable t) {
                Log.e("Keshav","onUpload image onFailure Method called");
                progressDialog.dismiss();
            }
        });
    }

    private String getRealPathFromURI(Uri contentUri) {
        String[] proj = {MediaStore.Images.Media.DATA};
        CursorLoader loader = new CursorLoader(this, contentUri, proj, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(column_index);
        cursor.close();
        return result;
    }

    private void loadImageData(String filename) {
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<List<Img>> dataImage = mApiInterface.postDataImage("52f866f58d909e13236110e5", "api_andro", "img", "5aaf56931f6d049066c26369", filename, "userid", "*");
        dataImage.enqueue(new Callback<List<Img>>() {
            @Override
            public void onResponse(Call<List<Img>> call, Response<List<Img>> response) {
                MainActivity.ma.refresh();
                finish();
            }

            @Override
            public void onFailure(Call<List<Img>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error or check connection, so data image isn't saved", Toast.LENGTH_LONG).show();
            }
        });
    }
}
