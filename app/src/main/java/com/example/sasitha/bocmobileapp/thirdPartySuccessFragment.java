package com.example.sasitha.bocmobileapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class thirdPartySuccessFragment extends Fragment
{
    TextView textViewID, textAmount, textViewFromACC, textViewToACC, textViewDesc;
    Button btnTransSuccHome;

    Fragment fragment = null;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_third_party_success, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        textViewID = (TextView) view.findViewById(R.id.textViewID);
        textAmount = (TextView) view.findViewById(R.id.textAmount);
        textViewFromACC = (TextView) view.findViewById(R.id.textViewFromACC);
        textViewToACC = (TextView) view.findViewById(R.id.textViewToACC);
        textViewDesc = (TextView) view.findViewById(R.id.textViewDesc);

        btnTransSuccHome = (Button) view.findViewById(R.id.btnTransSuccHome) ;

        Bundle bundleSuccess = getArguments();

        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        if(bundleSuccess != null){

            textViewID.setText(bundleSuccess.getString("id"));
            textViewFromACC.setText(bundleSuccess.getString("fromAcc"));
            textViewToACC.setText(bundleSuccess.getString("toAcc"));
            textAmount.setText("Rs. "+bundleSuccess.getString("amount"));
            textViewDesc.setText(bundleSuccess.getString("description"));
        }

        btnTransSuccHome.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Fragment fragmentD = new DashboardFragment();
                fragmentTransaction.replace(R.id.screen_area, fragmentD);
                fragmentTransaction.commit();
                getActivity().setTitle("Home");
            }
        });
    }
}
