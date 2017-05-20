package bhartiairtel.themehackathon.network;


import bhartiairtel.themehackathon.addmoney.AddMoneyRequestBean;
import bhartiairtel.themehackathon.echeck.CreateChequeRequestBean;
import bhartiairtel.themehackathon.login.LoginRequest;
import bhartiairtel.themehackathon.pojo.CommonResponse;
import bhartiairtel.themehackathon.pojo.GetUserDetailsResponseBean;
import bhartiairtel.themehackathon.pojo.UserDetailsResponse;
import bhartiairtel.themehackathon.register.RegisterRequest;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface APIInterface {



    @POST("smartbank/createcheque")
    Call<CommonResponse> createCheck(@Body CreateChequeRequestBean body);

    @POST("/smartbank/addmoney")
    Call<CommonResponse> addMoney(@Body AddMoneyRequestBean body);

    @POST("/smartbank/login")
    Call<CommonResponse> loginUser(@Body LoginRequest body);

    @POST("/smartbank/register")
    Call<CommonResponse> createUser(@Body RegisterRequest body);

    @POST("/smartbank/logmeout")
    Call<CommonResponse> logMeOut(@Header("username") String username, @Header("Authorization") String token, String body);

    @POST("/smartbank/changepassword")
    Call<CommonResponse> changePassword(String body);

    @POST("/smartbank/getuserdetails")
    Call<UserDetailsResponse> getUserDetails(@Body LoginRequest body);
/*
    @GET("/api/some?")
    Call<Response> method(@Query("page") String page);

    @FormUrlEncoded
    @POST("/api/some?")
    Call<Response> methodWithField(@Field("name") String name, @Field("job") String job);
*/
}