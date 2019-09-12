package com.example.sasitha.bocmobileapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import java.util.Random;



public class PaymentVerificationFragment extends Fragment {
    TextView type, accountNum, cusName,amountVal;
    Button edit, cancel, confirmPayment;


    Fragment fragment = null;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_payment_verification,null);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle bundleVerification = getArguments();

        fragmentManager = getFragmentManager();


        type = (TextView) view.findViewById(R.id.billTypeDetails);
        accountNum = (TextView) view.findViewById(R.id.billAccountNumDetails);
        cusName = (TextView) view.findViewById(R.id.customerNameDetails);
        amountVal = (TextView) view.findViewById(R.id.amountToPayDetails);


        edit = (Button) view.findViewById(R.id.editButtonPay);
        cancel = (Button) view.findViewById(R.id.cancelPayButton);
        confirmPayment = (Button) view.findViewById(R.id.confirmPaymentButtonPay);





        if(bundleVerification != null){
            type.setText(bundleVerification.getString("type"));
            accountNum.setText(bundleVerification.getString("accountNum"));
            cusName.setText(bundleVerification.getString("cusName"));
            amountVal.setText(bundleVerification.getString("amountVal"));

        }

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Confirm!");
                builder.setMessage("Are you sure you want to cancel Bill Payments?");
                builder.setCancelable(false);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Fragment fragmentBack = new DashboardFragment();
                        fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.screen_area, fragmentBack);

                        fragmentTransaction.commit();
                        getActivity().setTitle("Home");

                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                builder.show();


            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundleEdit = new Bundle();
                bundleEdit.putString("type" , type.getText().toString());
                bundleEdit.putString("accountNum", accountNum.getText().toString());
                bundleEdit.putString("cusName" , cusName.getText().toString());
                bundleEdit.putString("amountVal", amountVal.getText().toString());

                Fragment fragmentPay = new PayBillsFragment();
                fragmentPay.setArguments(bundleEdit);
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.screen_area, fragmentPay);

                fragmentTransaction.commit();

            }
        });

        confirmPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Confirm!");
                builder.setMessage("Are you sure you want to Pay this Bill");
                builder.setCancelable(false);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                Bundle bundleConfirm = new Bundle();


                bundleConfirm.putString("type" , type.getText().toString());
                bundleConfirm.putString("accountNum", accountNum.getText().toString());
                bundleConfirm.putString("cusName" , cusName.getText().toString());
                bundleConfirm.putString("amountVal", amountVal.getText().toString());
                Fragment fragmentPay = new PaymentDetailsFragment();
                fragmentPay.setArguments(bundleConfirm);
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.screen_area, fragmentPay);

                fragmentTransaction.commit();

            }

                });


                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                builder.show();
            }
        });



            }

}
