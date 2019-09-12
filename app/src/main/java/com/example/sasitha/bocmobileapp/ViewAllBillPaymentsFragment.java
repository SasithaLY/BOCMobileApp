package com.example.sasitha.bocmobileapp;

import android.content.Context;
import android.net.Uri;
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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class ViewAllBillPaymentsFragment extends Fragment {
    TextView type, accountNum, cusName,amountVal, payDate;
    Button dashboard;
    String datePayed;


    Fragment fragment = null;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_view_all_bill_payments,null);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle bundleVerification = getArguments();

        fragmentManager = getFragmentManager();


        type = (TextView) view.findViewById(R.id.atvbt);
        accountNum = (TextView) view.findViewById(R.id.atvba);
        cusName = (TextView) view.findViewById(R.id.atvcn);
        amountVal = (TextView) view.findViewById(R.id.atva);
        payDate = (TextView) view.findViewById(R.id.atvd);


        dashboard = (Button) view.findViewById(R.id.allbillstodash);
        datePayed = new SimpleDateFormat("dd-MM-yy", Locale.getDefault()).format(new Date());






        if(bundleVerification != null){
            type.setText(bundleVerification.getString("type"));
            accountNum.setText(bundleVerification.getString("accountNum"));
            cusName.setText(bundleVerification.getString("cusName"));
            amountVal.setText(bundleVerification.getString("amountVal"));
            payDate.setText(datePayed);

        }

        dashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment fragmentNew = new DashboardFragment();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.screen_area, fragmentNew);
                fragmentTransaction.commit();


            }
        });




    }

}