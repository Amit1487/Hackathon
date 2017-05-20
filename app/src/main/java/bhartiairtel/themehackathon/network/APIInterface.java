package bhartiairtel.themehackathon.network;


import bhartiairtel.themehackathon.pojo.LoginResponse;
import bhartiairtel.themehackathon.pojo.RegisterResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface APIInterface {
    @POST("/smartbank/login")
    Call<LoginResponse> loginUser(String body);

    @POST("/smartbank/register")
    Call<RegisterResponse> createUser(String body);

    @POST("/smartbank/logmeout")
    Call<LoginResponse> logMeOut(@Header("username") String username, @Header("Authorization") String token, String body);

    @POST("/smartbank/changepassword")
    Call<LoginResponse> changePassword(String body);

    @POST("/smartbank/getuserdetails")
    Call<LoginResponse> getUserDetails(String body);
/*
    @GET("/api/some?")
    Call<Response> method(@Query("page") String page);

    @FormUrlEncoded
    @POST("/api/some?")
    Call<Response> methodWithField(@Field("name") String name, @Field("job") String job);
*/
}