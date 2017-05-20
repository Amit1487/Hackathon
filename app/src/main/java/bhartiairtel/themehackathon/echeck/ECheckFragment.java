package bhartiairtel.themehackathon.echeck;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import bhartiairtel.themehackathon.R;
import bhartiairtel.themehackathon.commonutils.CommonUtilities;
import bhartiairtel.themehackathon.main.NavigationDrawerActivity;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * Use the {@link ECheckFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ECheckFragment extends Fragment implements ECheckView {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    ECheckPresenter mPresenter;

    TextInputLayout mTilUsrName, mTilMobile, mTilAmount, mTilDocNo;
    Spinner mSpinnerDocType;

    public ECheckFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ECheckFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ECheckFragment newInstance(String param1, String param2) {
        ECheckFragment fragment = new ECheckFragment();
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
        return inflater.inflate(R.layout.fragment_echeck, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter = new ECheckPresenterImpl(this);
        mTilUsrName = (TextInputLayout) view.findViewById(R.id.til_uname);
        mTilMobile = (TextInputLayout) view.findViewById(R.id.til_uname);
        mTilAmount = (TextInputLayout) view.findViewById(R.id.til_uname);
        mTilDocNo = (TextInputLayout) view.findViewById(R.id.til_uname);
        mSpinnerDocType = (Spinner) view.findViewById(R.id.spn_region);

        view.findViewById(R.id.btn_submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CommonUtilities.createAlert(ECheckFragment.this.getContext(), "Enter mPIN", new CommonUtilities.OnMpinListener() {
                    @Override
                    public void onEntered(String mPin) {
                        String fromMobile = ((NavigationDrawerActivity) getActivity()).getResult().getMobilenumber();
                        String payeeMobile = mTilUsrName.getEditText().getText().toString();
                        String payeename = mTilUsrName.getEditText().getText().toString();
                        String idProofType = mSpinnerDocType.getSelectedItem().toString();
                        String idProofNo = mTilDocNo.getEditText().getText().toString();
                        String amnt = mTilAmount.getEditText().getText().toString();
                        mPresenter.validateInput(fromMobile, payeeMobile, payeename, idProofType, idProofNo, amnt, fromMobile, mPin);
                    }
                });
            }
        });
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void setNameError(boolean isError) {

    }

    @Override
    public void setMobNumberError(boolean isError) {

    }

    @Override
    public void setAmountError(boolean isError) {

    }

    @Override
    public void setDocumentError(boolean isError) {

    }

    @Override
    public void onSuccess(String result) {

    }

    @Override
    public void onError(String err) {

    }
}
