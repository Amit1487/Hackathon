package bhartiairtel.themehackathon.encash;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import bhartiairtel.themehackathon.R;
import bhartiairtel.themehackathon.addmoney.AddMoneyRequestBean;
import bhartiairtel.themehackathon.alertdialog.AlertDialog;
import bhartiairtel.themehackathon.commonutils.CommonUtilities;
import bhartiairtel.themehackathon.main.NavigationDrawerActivity;
import bhartiairtel.themehackathon.network.APIClient;
import bhartiairtel.themehackathon.network.APIInterface;
import bhartiairtel.themehackathon.pojo.CommonResponse;
import bhartiairtel.themehackathon.pojo.MessageBean;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EnCashCheckFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EnCashCheckFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public EnCashCheckFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EnCashCheckFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EnCashCheckFragment newInstance(String param1, String param2) {
        EnCashCheckFragment fragment = new EnCashCheckFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_en_cash_check, container, false);
    }


    TextInputLayout mTilCheckNo;
    LinearLayout mLLInfoLayout;
    TextView mTvFrmMobile, mTvPayeeMob, mTvPayeeName, mTvIdProofType, mTvIdProofNum, mTvAmnt, mTvCheckNo;
    Button mBtnEnCash;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mTilCheckNo = (TextInputLayout) view.findViewById(R.id.til_uname);

//info_layout-ll
        mLLInfoLayout = (LinearLayout) view.findViewById(R.id.info_layout);
        //tv_from_mobile
        mTvFrmMobile = (TextView) view.findViewById(R.id.tv_from_mobile);
        //tv_payee_mob
        mTvPayeeMob = (TextView) view.findViewById(R.id.tv_payee_mob);
        //tv_payee_name
        mTvPayeeName = (TextView) view.findViewById(R.id.tv_payee_name);
        //tv_id_proof_type
        mTvIdProofType = (TextView) view.findViewById(R.id.tv_id_proof_type);
        //tv_id_proof_num
        mTvIdProofNum = (TextView) view.findViewById(R.id.tv_id_proof_num);
        //tv_amount
        mTvAmnt = (TextView) view.findViewById(R.id.tv_amount);
        //tv_check_no
        mTvCheckNo = (TextView) view.findViewById(R.id.tv_check_no);

        mBtnEnCash = (Button) view.findViewById(R.id.btn_encash);
        view.findViewById(R.id.btn_submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String checkNo = mTilCheckNo.getEditText().getText().toString();

                if (checkNo.length() > 0) {
                    mTilCheckNo.setErrorEnabled(false);

                    CommonUtilities.createAlert(getContext(), "Enter mPIN,",
                            new CommonUtilities.OnMpinListener() {
                                @Override
                                public void onEntered(String mPin) {
                                    requestCheckDetails(((NavigationDrawerActivity) getActivity()).getResult().getMobilenumber(), mPin, checkNo);
                                }
                            });

                } else {
                    mTilCheckNo.setError("Invalid check number");
                }
            }
        });

    }

    private void requestCheckDetails(String userName, String mPin, String checkNo) {
        final EnCashCheckRequest enCashCheckRequest = new EnCashCheckRequest();
        enCashCheckRequest.setUsername(userName);
        enCashCheckRequest.setMpin(mPin);
        enCashCheckRequest.setChecknumber(checkNo);


        Call call = APIClient.getClient().create(APIInterface.class).requestCheckDetails(enCashCheckRequest);
        call.enqueue(new Callback<EncashResponse>() {

            @Override
            public void onResponse(Call<EncashResponse> call, Response<EncashResponse> response) {

                EncashResponse encashResponse = response.body();

                MessageBean msgBean = encashResponse.getMessageBean();
                if (msgBean.getStatuscode() == 200) {
                    populateUi(encashResponse.getResult());
                } else {
                    new AlertDialog(EnCashCheckFragment.this.getContext(), AlertDialog.ERROR_TYPE).
                            setTitleText("Oops!")
                            .setContentText((String) msgBean.getMessage())
                            .setConfirmText("OK")
                            .setConfirmClickListener(null).show();
                }
            }


            @Override
            public void onFailure(Call<EncashResponse> call, Throwable t) {
                call.cancel();
                new AlertDialog(EnCashCheckFragment.this.getContext(), AlertDialog.ERROR_TYPE).
                        setTitleText("Oops!")
                        .setContentText(t.getMessage())
                        .setConfirmText("OK")
                        .setConfirmClickListener(null).show();

            }
        });
    }

    private void populateUi(final EnCashResult result) {

        mLLInfoLayout.setVisibility(View.VISIBLE);
        mTvFrmMobile.setText(result.getFrommobile());//": "8698015062",
        mTvPayeeMob.setText(result.getPayeemobile());
        ;//": "4333333",
        mTvPayeeName.setText(result.getPayeename());
        ;//": "atul",
        mTvIdProofType.setText(result.getIdprooftype());
        ;//": "adhar",
        mTvIdProofNum.setText(result.getIdprofnumber());
        ;//": "ddddd",
        mTvAmnt.setText(result.getAmount());
        ;//": 10,
        mTvCheckNo.setText(result.getChequenumber());
        mBtnEnCash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CommonUtilities.createAlert(getContext(), "Enter Check number", new CommonUtilities.OnMpinListener() {
                    @Override
                    public void onEntered(final String checkNo) {
                        if (checkNo.equalsIgnoreCase(result.getChequenumber())) {
                            CommonUtilities.createAlert(getContext(), "Enter mPin", new CommonUtilities.OnMpinListener() {
                                @Override
                                public void onEntered(String mPin) {
                                    String userName = ((NavigationDrawerActivity) getActivity()).getResult().getMobilenumber();
                                    requestEncashAmount(userName, checkNo, mPin);
                                }
                            });
                        } else {
                            //invalid check number
                            new AlertDialog(EnCashCheckFragment.this.getContext(), AlertDialog.ERROR_TYPE).
                                    setTitleText("Oops!")
                                    .setContentText("Invalid check number")
                                    .setConfirmText("OK")
                                    .setConfirmClickListener(null).show();
                        }
                    }
                });


            }
        });
    }

    private void requestEncashAmount(String userName, String checkNo, String mPin) {
        final EnCashCheckRequest enCashCheckRequest = new EnCashCheckRequest();
        enCashCheckRequest.setUsername(userName);
        enCashCheckRequest.setMpin(mPin);
        enCashCheckRequest.setChecknumber(checkNo);


        Call call = APIClient.getClient().create(APIInterface.class).requestEncash(enCashCheckRequest);
        call.enqueue(new Callback<CommonResponse>() {

            @Override
            public void onResponse(Call<CommonResponse> call, Response<CommonResponse> response) {

                CommonResponse encashResponse = response.body();

                MessageBean msgBean = encashResponse.getMessageBean();
                if (msgBean.getStatuscode() == 200) {
                    new AlertDialog(EnCashCheckFragment.this.getContext(), AlertDialog.SUCCESS_TYPE).
                            setTitleText("Congratulations!")
                            .setContentText((String) msgBean.getMessage())
                            .setConfirmText("OK")
                            .setConfirmClickListener(null).show();

                } else {
                    new AlertDialog(EnCashCheckFragment.this.getContext(), AlertDialog.ERROR_TYPE).
                            setTitleText("Oops!")
                            .setContentText((String) msgBean.getMessage())
                            .setConfirmText("OK")
                            .setConfirmClickListener(null).show();
                }
            }


            @Override
            public void onFailure(Call<CommonResponse> call, Throwable t) {
                call.cancel();
                new AlertDialog(EnCashCheckFragment.this.getContext(), AlertDialog.ERROR_TYPE).
                        setTitleText("Oops!")
                        .setContentText(t.getMessage())
                        .setConfirmText("OK")
                        .setConfirmClickListener(null).show();
            }
        });
    }
}
