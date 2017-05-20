package bhartiairtel.themehackathon.main;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import bhartiairtel.themehackathon.R;
import bhartiairtel.themehackathon.addmoney.AddMoneyFragment;
import bhartiairtel.themehackathon.alertdialog.AlertDialog;
import bhartiairtel.themehackathon.commonutils.CirclePageIndicator;
import bhartiairtel.themehackathon.commonutils.SlidingImageAdapter;
import bhartiairtel.themehackathon.echeck.ChequeListFragment;
import bhartiairtel.themehackathon.echeck.ECheckFragment;
import bhartiairtel.themehackathon.encash.EnCashCheckFragment;
import bhartiairtel.themehackathon.login.LoginActivity;
import bhartiairtel.themehackathon.login.LoginRequest;
import bhartiairtel.themehackathon.network.APIClient;
import bhartiairtel.themehackathon.network.APIInterface;
import bhartiairtel.themehackathon.offer.AdsFragment;
import bhartiairtel.themehackathon.pojo.CommonResponse;
import bhartiairtel.themehackathon.pojo.GetUserDetailsResponseBean;
import bhartiairtel.themehackathon.pojo.MessageBean;
import bhartiairtel.themehackathon.pojo.UserDetailsResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NavigationDrawerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, AdsFragment.OnFragmentInteractionListener {


    String userName, mPin;
    GetUserDetailsResponseBean result;
    TextView mBalance, mUserName, email;
    Runnable Update;
    Timer swipeTimer = new Timer();
    final Handler handler = new Handler();

    public GetUserDetailsResponseBean getResult() {
        return result;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);
        result = (GetUserDetailsResponseBean) getIntent().getSerializableExtra("result");
        userName = result.getMobilenumber();//getIntent().getStringExtra("user_name");
        mPin = getIntent().getStringExtra("mpin");


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mBalance = (TextView) navigationView.getHeaderView(0).findViewById(R.id.textView_bal);
        mBalance.setText("Curr Bal : " + result.getAmount());

        email = (TextView) navigationView.getHeaderView(0).findViewById(R.id.textView);
        email.setText(result.getMailid());


        mUserName = (TextView) navigationView.getHeaderView(0).findViewById(R.id.user_name);
        mUserName.setText(result.getMobilenumber());


        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, AdsFragment.newInstance("", "")).commit();


        Update = new Runnable() {
            public void run() {
                requestUsersDetail();
            }
        };

        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 7000, 7000);


    }


    private void requestUsersDetail() {

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername(result.getMobilenumber());

        Call call = APIClient.getClient().create(APIInterface.class).getUserDetails(loginRequest);
        call.enqueue(new Callback<UserDetailsResponse>() {

                         @Override
                         public void onResponse(Call<UserDetailsResponse> call, Response<UserDetailsResponse> response) {

                             UserDetailsResponse commonResponse = response.body();

                             MessageBean msgBean = commonResponse.getMessageBean();
                             if (msgBean.getStatuscode() == 200) {
                                 //display UI
//                                 onUseretail((GetUserDetailsResponseBean) commonResponse.getResult());
                                 mBalance.setText("Curr Bal : " + commonResponse.getResult().getAmount());
                             } else {

                                 String msg = "Some Issues";
                                 try {
                                     msg = (String) msgBean.getMessage();
                                 } catch (ClassCastException e) {

                                 } catch (Exception e) {

                                 }

                                 new AlertDialog(NavigationDrawerActivity.this, AlertDialog.ERROR_TYPE)
                                         .setTitleText("Oops...")
                                         .setContentText(msg)
                                         .show();
                             }
                         }

                         @Override
                         public void onFailure(Call<UserDetailsResponse> call, Throwable t) {
                             call.cancel();
                             new AlertDialog(NavigationDrawerActivity.this, AlertDialog.ERROR_TYPE)
                                     .setTitleText("Oops...")
                                     .setContentText("Something went wrong.")
                                     .show();
                         }

                     }

        );
    }

    private void displayUi(GetUserDetailsResponseBean result) {
        Log.d("TAG ", " GetUserDetailsResponseBean " + result.toString());
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        Fragment fragment = null;

        int id = item.getItemId();

        if (id == R.id.add_money) {
            fragment = AddMoneyFragment.newInstance(userName, mPin);
        } else if (id == R.id.transactions) {
            fragment = ChequeListFragment.newInstance("", "");
        } else if (id == R.id.better_together) {

        } else if (id == R.id.create_cheque) {
            fragment = ECheckFragment.newInstance(userName, mPin);
        } else if (id == R.id.dependent_accounts) {

        } else if (id == R.id.en_cash) {
            fragment = EnCashCheckFragment.newInstance("", "");
        }
        if (null != fragment)
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.content_frame, fragment).commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
