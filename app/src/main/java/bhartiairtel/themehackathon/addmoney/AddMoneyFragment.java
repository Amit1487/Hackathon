package bhartiairtel.themehackathon.addmoney;


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
import bhartiairtel.themehackathon.login.LoginPresenterImpl;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddMoneyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddMoneyFragment extends Fragment implements AddMoneyView {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_USR_NAME = "userName";
    private static final String ARG_MPIN = "mpin";

    // TODO: Rename and change types of parameters
    private String mUserName;
//    private String mPin;

    TextInputLayout mTilAmount, mTilBankName, mTilAccNumber;
    private AddMoneyPresenterImpl mPresenter;

    public AddMoneyFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param userName Parameter 1.
     * @param mpin     Parameter 2.
     * @return A new instance of fragment AddMoneyFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddMoneyFragment newInstance(String userName, String mpin) {
        AddMoneyFragment fragment = new AddMoneyFragment();
        Bundle args = new Bundle();
        args.putString(ARG_USR_NAME, userName);
        args.putString(ARG_MPIN, mpin);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mUserName = getArguments().getString(ARG_USR_NAME);
//            mPin = getArguments().getString(ARG_MPIN);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_money, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mTilAmount = (TextInputLayout) view.findViewById(R.id.til_amt);
        mTilAccNumber = (TextInputLayout) view.findViewById(R.id.til_acc);
        mTilBankName = (TextInputLayout) view.findViewById(R.id.til_bank);

        view.findViewById(R.id.btn_submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CommonUtilities.createAlert(getActivity(), "Please Enter mPIN", new CommonUtilities.OnMpinListener() {
                    @Override
                    public void onEntered(String mPin) {
                        mPresenter.validateInput(mUserName, mPin, mTilAmount.getEditText().getText().toString(), mTilAccNumber.getEditText().getText().toString(), mTilBankName.getEditText().getText().toString());
                    }
                });

            }
        });

        mPresenter = new AddMoneyPresenterImpl(this);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void setAmountError(boolean isError) {
        if (isError)
            mTilAmount.setError(getString(R.string.invalid_amnt));
        else
            mTilAmount.setErrorEnabled(false);
    }

    @Override
    public void setAccountError(boolean isError) {
        if (isError)
            mTilAccNumber.setError(getString(R.string.invalid_acc));
        else
            mTilAccNumber.setErrorEnabled(false);
    }

    @Override
    public void setBankError(boolean isError) {
        if (isError)
            mTilBankName.setError(getString(R.string.invalid_bank));
        else
            mTilBankName.setErrorEnabled(false);
    }

    @Override
    public void onSuccess(String result) {
        new AlertDialog(this.getContext(), AlertDialog.SUCCESS_TYPE).
                setTitleText("Congratulations!")
                .setContentText(result)
                .setConfirmText("OK")
                .setConfirmClickListener(null).show();
    }

    @Override
    public void onError(String err) {
        new AlertDialog(this.getContext(), AlertDialog.ERROR_TYPE).
                setTitleText("Oops!")
                .setContentText(err)
                .setConfirmText("OK")
                .setConfirmClickListener(null).show();
    }
}