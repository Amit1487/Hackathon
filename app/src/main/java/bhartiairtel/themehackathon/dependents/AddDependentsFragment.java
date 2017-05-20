package bhartiairtel.themehackathon.dependents;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import bhartiairtel.themehackathon.R;
import bhartiairtel.themehackathon.alertdialog.AlertDialog;
import bhartiairtel.themehackathon.login.LoginActivity;
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
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * create an instance of this fragment.
 */
public class AddDependentsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public AddDependentsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddDependents.
     */
    // TODO: Rename and change types and number of parameters
    public static AddDependentsFragment newInstance(String param1, String param2) {
        AddDependentsFragment fragment = new AddDependentsFragment();
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
        return inflater.inflate(R.layout.fragment_add_dependents, container, false);
    }

    //til_uname,til_mob,til_relation,til_amt,btn_submit
    TextInputLayout mTilUName, mTilMob, mTilRelation, mTilAmnt;
    Button submit;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mTilUName = (TextInputLayout) view.findViewById(R.id.til_uname);
        mTilMob = (TextInputLayout) view.findViewById(R.id.til_mob);
        mTilRelation = (TextInputLayout) view.findViewById(R.id.til_relation);
        mTilAmnt = (TextInputLayout) view.findViewById(R.id.til_amt);
        submit = (Button) view.findViewById(R.id.btn_submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mTilUName.getEditText().getText().toString().length() > 0) {
                    mTilUName.setErrorEnabled(false);
                    if (mTilMob.getEditText().getText().toString().length() > 0) {
                        mTilMob.setErrorEnabled(false);
                        if (mTilRelation.getEditText().getText().toString().length() > 0) {
                            mTilRelation.setErrorEnabled(false);

                            if (mTilAmnt.getEditText().getText().toString().length() > 0) {
                                mTilAmnt.setErrorEnabled(false);

                                AddDependentsRequest addDependentsRequest = new AddDependentsRequest();
                                addDependentsRequest.setUsername(mTilUName.getEditText().getText().toString());
                                addDependentsRequest.setDependentmobile(mTilMob.getEditText().getText().toString());
                                addDependentsRequest.setRelation(mTilRelation.getEditText().getText().toString());
                                addDependentsRequest.setAmount(mTilAmnt.getEditText().getText().toString());

                                Call call = APIClient.getClient().create(APIInterface.class).addDependents(addDependentsRequest);
                                call.enqueue(new Callback<CommonResponse>() {

                                                 @Override
                                                 public void onResponse(Call<CommonResponse> call, Response<CommonResponse> response) {

                                                     CommonResponse commonResponse = response.body();

                                                     MessageBean msgBean = commonResponse.getMessageBean();
                                                     if (msgBean.getStatuscode() == 200) {
                                                         //display UI
//                                                         onUseretail((GetUserDetailsResponseBean) commonResponse.getResult());

                                                         new AlertDialog(getActivity(), AlertDialog.ERROR_TYPE)
                                                                 .setTitleText("Congrats")
                                                                 .setContentText((String) msgBean.getMessage())
                                                                 .show();
                                                     } else {

                                                         String msg = "Some Issues";
                                                         try {
                                                             msg = (String) msgBean.getMessage();
                                                         } catch (ClassCastException e) {

                                                         } catch (Exception e) {

                                                         }

                                                         new AlertDialog(getActivity(), AlertDialog.ERROR_TYPE)
                                                                 .setTitleText("Oops...")
                                                                 .setContentText(msg)
                                                                 .show();
                                                     }
                                                 }

                                                 @Override
                                                 public void onFailure(Call<CommonResponse> call, Throwable t) {
                                                     call.cancel();
                                                     new AlertDialog(getActivity(), AlertDialog.ERROR_TYPE)
                                                             .setTitleText("Oops...")
                                                             .setContentText("Something went wrong.")
                                                             .show();
                                                 }

                                             }

                                );

                            } else {
                                mTilAmnt.setError("enter valid amount");
                            }
                        } else {
                            mTilRelation.setError("Invalid relation text");
                        }
                    } else {
                        mTilMob.setError("Invalid Mobile number");
                    }
                } else {
                    mTilUName.setError("Invalid user name");
                }
            }
        });

    }
}
