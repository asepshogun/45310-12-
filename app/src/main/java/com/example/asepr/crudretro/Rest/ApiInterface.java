package com.example.asepr.crudretro.Rest;

/**
 * Created by ASEPR on 3/22/2018.
 */
        import com.example.asepr.crudretro.Model.GetUser;
        import com.example.asepr.crudretro.Model.Img;
        import com.example.asepr.crudretro.Model.PostPutDelUser;
        import com.example.asepr.crudretro.Model.User;

        import java.util.List;

        import okhttp3.MultipartBody;
        import okhttp3.RequestBody;
        import okhttp3.ResponseBody;
        import retrofit2.Call;
        import retrofit2.Response;
        import retrofit2.http.DELETE;
        import retrofit2.http.Field;
        import retrofit2.http.FormUrlEncoded;
        import retrofit2.http.GET;
        import retrofit2.http.HTTP;
        import retrofit2.http.Multipart;
        import retrofit2.http.POST;
        import retrofit2.http.PUT;
        import retrofit2.http.Part;
        import retrofit2.http.Path;

public interface ApiInterface {
    @GET("select_all/token/52f866f58d909e13236110e5/project/api_andro/collection/user/appid/5aaf56931f6d049066c26369")
    Call<List<User>> User();
    @FormUrlEncoded
    @POST("insert")
    Call<List<User>> postUser(@Field("token") String token,@Field("project") String project,@Field("collection") String collection, @Field("appid") String appid, @Field("name") String name, @Field("birth_date") String birth_date, @Field("gender") String gender);
    @FormUrlEncoded
    @POST("update_id")
    Call<PostPutDelUser> putKontak(@Field("token") String token,@Field("project") String project,@Field("collection") String collection, @Field("appid") String appid, @Field("id") String id, @Field("name") String name, @Field("birth_date") String birthday, @Field("gender") String gender);
    //@FormUrlEncoded
    //@DELETE("remove_id/token/52f866f58d909e13236110e5/project/api_andro/collection/user/appid/5aaf56931f6d049066c26369/id/{id}")
    //Call<PostPutDelUser> deleteUser(@Path("id") String id);
    /*@DELETE("remove_id/token/52f866f58d909e13236110e5/project/api_andro/collection/user/appid/5aaf56931f6d049066c26369/id/"+ {id})
    Call<PostPutDelUser> deleteUser(@Path("id") String id);*/
    @DELETE("remove_id/token/52f866f58d909e13236110e5/project/api_andro/collection/user/appid/5aaf56931f6d049066c26369/id/{id}")
    Call<PostPutDelUser> deleteUser(@Path("id") String id);

    //crud image
    @Multipart
    @POST("uploader_relative")
    Call<Img> postImage(@Part MultipartBody.Part file, @Part("token") RequestBody token, @Part("project") RequestBody project);

    @FormUrlEncoded
    @POST("insert")
    Call<List<Img>> postDataImage(@Field("token") String token,@Field("project") String project,@Field("collection") String collection, @Field("appid") String appid, @Field("image_name") String image_name, @Field("user_id") String user_id, @Field("ext") String ext);

    /*@FormUrlEncoded
    (@Part("image\"; filename=\"myfile.jpg\" ") RequestBody file
    @POST("insert")
    Call<List<Img>> postUser(@Field("token") String token, @Field("project") String project, @Field("collection") String collection, @Field("appid") String appid, @Field("image_name") String image_name, @Field("path") String path, @Field("ext") String ext, @Field("user_id") String user_id);
*/
    /*@Multipart
    @POST("/")
    Call<ResponseBody> postImage(@Part MultipartBody.Part image, @Part("name") RequestBody name);*/


    /*@Multipart
    @POST("ImageUpload")
    Call<ServerResponseKeshav> uploadFile(@Part MultipartBody.Part file,
                                    @Part("file") RequestBody name);*/

    /*@Multipart
    @POST("retrofit_example/upload_multiple_files.php")
    Call<ServerResponse> uploadMulFile(@Part MultipartBody.Part file1,
                                       @Part MultipartBody.Part file2);*/

}
