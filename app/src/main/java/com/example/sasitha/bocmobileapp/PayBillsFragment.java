package com.example.sasitha.bocmobileapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.Arrays;

import java.util.List;



/**

 Button contButton;
 Button cancelButton;
 EditText type, accNum, cusName, amountVal;
 String type1, accNum1, cusName1, amountVal1;


 @Override
 protected void onCreate(Bundle savedInstanceState) {
 super.onCreate(savedInstanceState);
 setContentView(R.layout.activity_pay_bills);



 type = findViewById(R.id.billType);
 accNum = findViewById(R.id.billAccountNum);
 cusName =  findViewById(R.id.customerName);
 amountVal = findViewById(R.id.amountToPay);

 cancelButton = findViewById(R.id.cancelbuttonpay);
 contButton = findViewById(R.id.continueButton);

 contButton.setOnClickListener(new View.OnClickListener() {
 @Override
 public void onClick(View view) {
 Intent i = new Intent(PayBills.this, PaymentVerification.class);
 type1 = type.getText().toString();
 i.putExtra("type",type1);
 accNum1 = accNum.getText().toString();
 i.putExtra("accNum",accNum1);
 cusName1 = cusName.getText().toString();
 i.putExtra("cusName",cusName1);
 amountVal1 = amountVal.getText().toString();
 i.putExtra("amountVal",amountVal1);
 startActivity(i);
 finish();
 }
 });



 cancelButton.setOnClickListener(new View.OnClickListener() {
 @Override
 public void onClick(View view) {
 Intent i2 = new Intent(PayBills.this, MainActivity.class);
 startActivity(i2);
 }
 });
 }
 }

 */
public class PayBillsFragment extends Fragment {
    Button contButton;
    Button cancelButton;
    Spinner type;
    EditText  accountNum, cusName, amountVal;
    TextView bttext;


    Fragment fragment = null;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pay_bills, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        fragmentManager = getFragmentManager();

        type = (Spinner) view.findViewById(R.id.billType);
        accountNum = (EditText) view.findViewById(R.id.billAccountNum);
        cusName =  (EditText) view.findViewById(R.id.customerName);
        amountVal = (EditText) view.findViewById(R.id.amountToPay);
        bttext = (TextView) view.findViewById(R.id.textViewbt);

        cancelButton =  (Button) view.findViewById(R.id.cancelbuttonpay);
        contButton =  (Button) view.findViewById(R.id.continueButton);

        Bundle bundleArguments = getArguments();

        cancelButton.setOnClickListener(new View.OnClickListener() {
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

        contButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {




                if(type.getSelectedItem().toString().equals("Select Bill Type")){
                    Toast.makeText(getContext(), "Select a Bill Type!", Toast.LENGTH_SHORT).show();
 //                   bttext.setError("Select Bill Type");
//                    type.startAnimation(animShake);
//                    vib.vibrate(120);
                }else if(accountNum.getText().toString().isEmpty()){
                accountNum.setError("Please Enter an Account Number");
                Toast.makeText(getContext(), "Please Enter an Account Number", Toast.LENGTH_SHORT).show();
//                accountNum.startAnimation(animShake);
//                vib.vibrate(120);
                }else if(cusName.getText().toString().isEmpty()){
                    cusName.setError("Please Enter the Customer Name");
                    Toast.makeText(getContext(), "Please Enter the Customer Name", Toast.LENGTH_SHORT).show();
//                    cusName.startAnimation(animShake);
//                    vib.vibrate(120);
                }else if(amountVal.getText().toString().isEmpty()){
                    amountVal.setError("Please Enter the Amount to Pay");
                    Toast.makeText(getContext(), "Please Enter the Amount to Pay", Toast.LENGTH_SHORT).show();
//                    amountVal.startAnimation(animShake);
//                    vib.vibrate(120);
                }else {

                    fragment = new PaymentVerificationFragment();

                    Bundle bundle = new Bundle();
                    bundle.putString("type" , type.getSelectedItem().toString());

                    bundle.putString("accountNum", accountNum.getText().toString());
                    bundle.putString("cusName", cusName.getText().toString());
                    bundle.putString("amountVal", amountVal.getText().toString());
                    fragment.setArguments(bundle);
                    fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.screen_area, fragment);
                    fragmentTransaction.commit();

                }


//                Intent i = new Intent(PayBills.this, PaymentVerification.class);
//                type1 = type.getText().toString();
//                i.putExtra("type",type1);
//                accNum1 = accNum.getText().toString();
//                i.putExtra("accNum",accNum1);
//                cusName1 = cusName.getText().toString();
//                i.putExtra("cusName",cusName1);
//                amountVal1 = amountVal.getText().toString();
//                i.putExtra("amountVal",amountVal1);

            }
        });


        String[] billType = new String[]{
                "Select Bill Type",
                "Water Bill",
                "Electricity Bill",
                "Broadband Bill",
                "Cable TV Bill",
                "Mobile Bill"
        };


        List<String> billTypeList = new ArrayList<>(Arrays.asList(billType));

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(getActivity(), R.layout.color_spinner_layout, billTypeList){





            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;


                if(position == 0){
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                }
                else {
                    tv.setTextColor(Color.WHITE);

                }
                return view;
            }
        };




        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_dropdown_layout);
        type.setAdapter(spinnerArrayAdapter);



        if(bundleArguments != null){

            type.setSelection(spinnerArrayAdapter.getPosition(bundleArguments.getString("type")));
            accountNum.setText(bundleArguments.getString("accountNum"));
            cusName.setText(bundleArguments.getString("cusName"));
            amountVal.setText(bundleArguments.getString("amountVal"));




        }





    }
}


