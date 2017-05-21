package bhartiairtel.themehackathon.network;


import bhartiairtel.themehackathon.addmoney.AddMoneyRequestBean;
import bhartiairtel.themehackathon.dependents.AddDependentsRequest;
import bhartiairtel.themehackathon.echeck.CheResponseque;
import bhartiairtel.themehackathon.echeck.CreateChequeRequestBean;
import bhartiairtel.themehackathon.echeck.ECheckResponse;
import bhartiairtel.themehackathon.encash.EnCashCheckRequest;
import bhartiairtel.themehackathon.encash.EnCashChequeRequest;
import bhartiairtel.themehackathon.encash.EncashResponse;
import bhartiairtel.themehackathon.encash.GetMoneyFromParentRequestBean;
import bhartiairtel.themehackathon.encash.PullMoneyResponse;
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
    Call<ECheckResponse> createCheck(@Body CreateChequeRequestBean body);

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

    @POST("/smartbank/getchequedetails")
    Call<EncashResponse> requestCheckDetails(@Body EnCashCheckRequest body);

    @POST("/smartbank/cashcheque")
    Call<CommonResponse> requestEncash(@Body EnCashChequeRequest body);

    @POST("/smartbank/getallcheques")
    Call<CheResponseque> getallcheques(@Body LoginRequest body);

    @POST("/smartbank/adddependent")
    Call<CommonResponse> addDependents(@Body AddDependentsRequest body);

    @POST("/smartbank/getmoneyfromparent")
    Call<PullMoneyResponse> pullMoney(@Body GetMoneyFromParentRequestBean body);

/*
    @GET("/api/some?")
    Call<Response> method(@Query("page") String page);

    @FormUrlEncoded
    @POST("/api/some?")
    Call<Response> methodWithField(@Field("name") String name, @Field("job") String job);
*/
}