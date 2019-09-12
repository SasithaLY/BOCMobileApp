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

public class DashboardFragment extends Fragment {

    Button btnOwnAccount, btnThirdParty, btnAcc, btnPayBill;

    Fragment fragment = null;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dashboard, null);

    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnOwnAccount = (Button) view.findViewById(R.id.btnOwnAcc);
        btnThirdParty = (Button) view.findViewById(R.id.btnThirdPartyTr);
        btnAcc = (Button) view.findViewById(R.id.btnAccounts);
        btnPayBill = (Button) view.findViewById(R.id.btnPayBills);

        fragmentManager = getFragmentManager();


        btnOwnAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment = new OwnAccountFragment();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.screen_area, fragment);

                fragmentTransaction.commit();
                getActivity().setTitle("Own Account Transactions");
            }
        });

        btnThirdParty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment = new thirdPartyFragment();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.screen_area, fragment);
                fragmentTransaction.commit();
                getActivity().setTitle("Third Party Transfers");
            }
        });

        btnAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment = new OwnAccountFragment();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.screen_area, fragment);
                fragmentTransaction.commit();
            }
        });

        btnPayBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment = new PayBillsFragment();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.screen_area, fragment);
                fragmentTransaction.commit();
            }
        });


    }
}