package bhartiairtel.themehackathon.encash;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import bhartiairtel.themehackathon.R;
import bhartiairtel.themehackathon.alertdialog.AlertDialog;
import bhartiairtel.themehackathon.commonutils.CommonUtilities;
import bhartiairtel.themehackathon.echeck.ECheckFragment;
import bhartiairtel.themehackathon.login.LoginActivity;
import bhartiairtel.themehackathon.login.LoginRequest;
import bhartiairtel.themehackathon.main.NavigationDrawerActivity;
import bhartiairtel.themehackathon.network.APIClient;
import bhartiairtel.themehackathon.network.APIInterface;
import bhartiairtel.themehackathon.pojo.CommonResponse;
import bhartiairtel.themehackathon.pojo.GetUserDetailsResponseBean;
import bhartiairtel.themehackathon.pojo.MessageBean;
import bhartiairtel.themehackathon.pojo.UserDetailsResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PullMoneyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PullMoneyFragment extends Fragment {

    public PullMoneyFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment PullMoneyFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PullMoneyFragment newInstance() {
        PullMoneyFragment fragment = new PullMoneyFragment();
        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pull_money, container, false);
    }

    TextInputLayout mTilAmnt;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mTilAmnt = (TextInputLayout) view.findViewById(R.id.til_amount);

        view.findViewById(R.id.btn_submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String userName = ((NavigationDrawerActivity) getActivity()).getResult().getMobilenumber();
                String amount = mTilAmnt.getEditText().getText().toString();

                GetMoneyFromParentRequestBean getMoneyFromParentRequestBean = new GetMoneyFromParentRequestBean();
                getMoneyFromParentRequestBean.setUsername(userName);
                getMoneyFromParentRequestBean.setAmount(amount);

                Call call = APIClient.getClient().create(APIInterface.class).pullMoney(getMoneyFromParentRequestBean);
                call.enqueue(new Callback<PullMoneyResponse>() {

                                 @Override
                                 public void onResponse(Call<PullMoneyResponse> call, Response<PullMoneyResponse> response) {

                                     PullMoneyResponse commonResponse = response.body();

                                     MessageBean msgBean = commonResponse.getMessageBean();
                                     if (msgBean.getStatuscode() == 200) {
                                         //display UI
                                         ///    onUseretail((GetUserDetailsResponseBean) commonResponse.getResult());
                                         ((NavigationDrawerActivity) getActivity()).updateBalance(commonResponse.getResult());


                                         new AlertDialog(PullMoneyFragment.this.getContext(), AlertDialog.SUCCESS_TYPE).
                                                 setTitleText("Congratulations!")
                                                 .setContentText(msgBean.getMessage() + " New Balance  " + (commonResponse.getResult()))
                                                 .setConfirmText("OK")
                                                 .setConfirmClickListener(null).show();

                                     } else {

                                         String msg = "Some Issues";
                                         try {
                                             msg = (String) msgBean.getMessage();
                                         } catch (ClassCastException e) {

                                         } catch (Exception e) {

                                         }

                                         new AlertDialog(PullMoneyFragment.this.getContext(), AlertDialog.ERROR_TYPE)
                                                 .setTitleText("Oops...")
                                                 .setContentText(msg)
                                                 .show();
                                     }
                                 }

                                 @Override
                                 public void onFailure(Call<PullMoneyResponse> call, Throwable t) {
                                     call.cancel();
                                     new AlertDialog(PullMoneyFragment.this.getContext(), AlertDialog.ERROR_TYPE)
                                             .setTitleText("Oops...")
                                             .setContentText("Something went wrong.")
                                             .show();
                                 }

                             }

                );

                /*CommonUtilities.createAlert(PullMoneyFragment.this.getContext(), "Enter mPIN", new CommonUtilities.OnMpinListener() {
                    @Override
                    public void onEntered(String mPin) {

                    }
                });*/
            }
        });

    }
}
