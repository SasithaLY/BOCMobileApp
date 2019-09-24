package com.example.sasitha.bocmobileapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class AddNewAccountDetails extends Fragment {
    Button contButton;
    Button cancelButton;
    //  Spinner type;
    EditText  accountNum, cusName;
    TextView bttext;


    Fragment fragment = null;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_add_new_account_details, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        fragmentManager = getFragmentManager();

        // type = (Spinner) view.findViewById(R.id.billType);
        accountNum = (EditText) view.findViewById(R.id.AccountNo);
        cusName =  (EditText) view.findViewById(R.id.customerName);
        //  amountVal = (EditText) view.findViewById(R.id.amountToPay);
        //  bttext = (TextView) view.findViewById(R.id.textViewbt);

        cancelButton =  (Button) view.findViewById(R.id.cancelbuttonpay);
        contButton =  (Button) view.findViewById(R.id.continueButton);

        Bundle bundleArguments = getArguments();

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Confirm!");
                builder.setMessage("Are you sure you want to cancel Account details?");
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

        contButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {



                if(accountNum.getText().toString().isEmpty()){
                    accountNum.setError("Please Enter an Account Number");
                    Toast.makeText(getContext(), "Please Enter an Account Number", Toast.LENGTH_SHORT).show();
//                accountNum.startAnimation(animShake);
//                vib.vibrate(120);
                }else if(cusName.getText().toString().isEmpty()){
                    cusName.setError("Please Enter the Customer Name");
                    Toast.makeText(getContext(), "Please Enter the Customer Name", Toast.LENGTH_SHORT).show();
//                    cusName.startAnimation(animShake);
//                    vib.vibrate(120);

                }else {

                    fragment = new AccountVerificationFragment();

                    Bundle bundle = new Bundle();
                    // bundle.putString("type" , type.getSelectedItem().toString());

                    bundle.putString("accountNum", accountNum.getText().toString());
                    bundle.putString("cusName", cusName.getText().toString());
                    //  bundle.putString("amountVal", amountVal.getText().toString());
                    fragment.setArguments(bundle);
                    fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.screen_area, fragment);
                    fragmentTransaction.commit();

                }



            }
        });






    };






//        if(bundleArguments != null){
//
//
//        accountNum.setText(bundleArguments.getString("accountNum"));
//        cusName.setText(bundleArguments.getString("cusName"));
//
//
//
//
//
//    }





}



