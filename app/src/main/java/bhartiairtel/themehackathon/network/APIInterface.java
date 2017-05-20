package bhartiairtel.themehackathon.network;


import bhartiairtel.themehackathon.pojo.LoginResponse;
import bhartiairtel.themehackathon.pojo.RegisterResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface APIInterface {
    @GET("/api/login")
    Call<LoginResponse> doGetLoginResources();

    @POST("/api/register")
    Call<RegisterResponse> createUser(@Body RegisterResponse user);

/*
    @GET("/api/some?")
    Call<Response> method(@Query("page") String page);

    @FormUrlEncoded
    @POST("/api/some?")
    Call<Response> methodWithField(@Field("name") String name, @Field("job") String job);
*/
}
