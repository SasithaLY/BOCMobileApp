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


public class ViewAccounts extends Fragment {
    TextView accountNum, cusName;
    Button dashboard, viewAll;


    Fragment fragment = null;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_view_accounts,null);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle bundleVerification = getArguments();

        fragmentManager = getFragmentManager();


        //  type = (TextView) view.findViewById(R.id.textViewBillTypeDisplay);
        accountNum = (TextView) view.findViewById(R.id.textViewAccountNumberDisplay);
        cusName = (TextView) view.findViewById(R.id.textViewCustomerNameDisplay);
        // amountVal = (TextView) view.findViewById(R.id.textViewPayedAmountDisplay);


        dashboard = (Button) view.findViewById(R.id.buttonPayBillstoHome);
        viewAll = (Button) view.findViewById(R.id.allbillpaybutton);



        if(bundleVerification != null){
            //   type.setText(bundleVerification.getString("type"));
            accountNum.setText(bundleVerification.getString("accountNum"));
            cusName.setText(bundleVerification.getString("cusName"));
            //   amountVal.setText(bundleVerification.getString("amountVal"));

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


        viewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundleConfirm = new Bundle();


                //   bundleConfirm.putString("type" , type.getText().toString());
                bundleConfirm.putString("accountNum", accountNum.getText().toString());
                bundleConfirm.putString("cusName" , cusName.getText().toString());
                //   bundleConfirm.putString("amountVal", amountVal.getText().toString());
                Fragment fragmentViewAll = new ViewAllBillPaymentsFragment();
                fragmentViewAll.setArguments(bundleConfirm);
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.screen_area, fragmentViewAll);

                fragmentTransaction.commit();

            }
        });





    }

}
