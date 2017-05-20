package bhartiairtel.themehackathon.echeck;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import bhartiairtel.themehackathon.R;
import bhartiairtel.themehackathon.alertdialog.AlertDialog;
import bhartiairtel.themehackathon.login.LoginActivity;
import bhartiairtel.themehackathon.login.LoginRequest;
import bhartiairtel.themehackathon.main.NavigationDrawerActivity;
import bhartiairtel.themehackathon.network.APIClient;
import bhartiairtel.themehackathon.network.APIInterface;
import bhartiairtel.themehackathon.pojo.GetUserDetailsResponseBean;
import bhartiairtel.themehackathon.pojo.MessageBean;
import bhartiairtel.themehackathon.pojo.UserDetailsResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ChequeListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChequeListFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ListView listView;


    public ChequeListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ChequeListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ChequeListFragment newInstance(String param1, String param2) {
        ChequeListFragment fragment = new ChequeListFragment();
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
        return inflater.inflate(R.layout.fragment_cheque_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView = (ListView) view.findViewById(R.id.list);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });

        requestChequeLis();


    }

    private void requestChequeLis() {

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername(((NavigationDrawerActivity) getActivity()).getResult().getMobilenumber());

        Call call = APIClient.getClient().create(APIInterface.class).getallcheques(loginRequest);
        call.enqueue(new Callback<CheResponseque>() {

                         @Override
                         public void onResponse(Call<CheResponseque> call, Response<CheResponseque> response) {

                             CheResponseque commonResponse = response.body();

                             MessageBean msgBean = commonResponse.getMessageBean();
                             if (msgBean.getStatuscode() == 200) {
                                 //display UI
                                 listView.setAdapter(new CustomListAdapter(getContext(), commonResponse.getResult().getAllchequelist()));
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
                         public void onFailure(Call<CheResponseque> call, Throwable t) {
                             call.cancel();
                             new AlertDialog(getActivity(), AlertDialog.ERROR_TYPE)
                                     .setTitleText("Oops...")
                                     .setContentText("Something went wrong.")
                                     .show();
                         }

                     }

        );
    }
}
