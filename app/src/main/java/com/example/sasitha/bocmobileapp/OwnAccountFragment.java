package com.example.sasitha.bocmobileapp;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class OwnAccountFragment extends Fragment {

    TextView date, selectDate;
    ImageView datePicker;
    DatePickerDialog.OnDateSetListener mDateSetListener;
    Spinner fromAcc, toAcc;
    EditText amount, description;
    Button pay, cancel;
    RadioGroup radioGroup;
    RadioButton payNow, payLater;

    Toolbar toolbar;

    Fragment fragment = null;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_own_account_transfer, null);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        date = (TextView) view.findViewById(R.id.txtDate);
        selectDate = (TextView) view.findViewById(R.id.textViewSelectDate);
        datePicker = (ImageView) view.findViewById(R.id.imageViewDate);

        pay = (Button) view.findViewById(R.id.btnPay);
        cancel = (Button) view.findViewById(R.id.btnCancel);

        fromAcc = (Spinner) view.findViewById(R.id.spinnerPayFrom);
        toAcc = (Spinner) view.findViewById(R.id.spinnerPayTo);
        amount = (EditText) view.findViewById(R.id.editTextAmount);
        description = (EditText)view.findViewById(R.id.editTextDescription);

        payNow = (RadioButton) view.findViewById(R.id.radioBtnPayNow);
        payLater = (RadioButton) view.findViewById(R.id.radioBtnPayLater);

        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();


        if(payNow.isChecked()){
            datePicker.setVisibility(View.GONE);
            selectDate.setVisibility(View.GONE);
            date.setVisibility(View.GONE);
        }

        payNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker.setVisibility(View.GONE);
                selectDate.setVisibility(View.GONE);
                date.setVisibility(View.GONE);
            }
        });

        payLater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker.setVisibility(View.VISIBLE);
                selectDate.setVisibility(View.VISIBLE);
                date.setVisibility(View.VISIBLE);
            }
        });
        datePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(getContext(), R.style.DialogTheme, mDateSetListener, year,month,day);

                //dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                month = month + 1;

                String value = month + "/" +day+ "/" +year;
                date.setText(value);
            }
        };

        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle = new Bundle();
                bundle.putString("fromAcc" , fromAcc.getSelectedItem().toString());
                bundle.putString("toAcc", toAcc.getSelectedItem().toString());
                bundle.putString("amount" , amount.getText().toString());
                bundle.putString("description", description.getText().toString());

//                Intent intent = new Intent(OwnAccountTransfer.this, ConfirmTransaction.class);
//                intent.putExtra("fromAcc" , fromAcc.getSelectedItem().toString());
//                intent.putExtra("toAcc", toAcc.getSelectedItem().toString());
//                intent.putExtra("amount" , amount.getText().toString());
//                intent.putExtra("description", description.getText().toString());



                if(payNow.isChecked()){

                    String date = new SimpleDateFormat("MM-dd-yyyy", Locale.getDefault()).format(new Date());
                    bundle.putString("date", date);

                    fragment = new OwnAccountFragment(); //edit this
                    fragmentTransaction.replace(R.id.screen_area, fragment);
                    fragmentTransaction.commit();

                }else if(payLater.isChecked()){

                    bundle.putString("date", date.getText().toString());
                    fragment = new OwnAccountFragment(); //edit this
                    fragmentTransaction.replace(R.id.screen_area, fragment);
                    fragmentTransaction.commit();
                }

            }
        });



        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragmentD = new DashboardFragment();
                //fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.screen_area, fragmentD);

                fragmentTransaction.commit();
                getActivity().setTitle("Home");
            }
        });

        String[] accounts = new String[]{
                "Select Account",
                "0082166378",
                "0075189026",
                "0078190057"
        };

        List<String> acoountList = new ArrayList<>(Arrays.asList(accounts));

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(getActivity(), R.layout.support_simple_spinner_dropdown_item, acoountList){
            @Override
            public boolean isEnabled(int position){
                if(position == 0)
                {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;
                }
                else
                {
                    return true;
                }
            }
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
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };

        spinnerArrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        fromAcc.setAdapter(spinnerArrayAdapter);
        toAcc.setAdapter(spinnerArrayAdapter);

        fromAcc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItemText = (String) parent.getItemAtPosition(position);
                // If user change the default selection
                // First item is disable and it is used for hint
                if(position > 0){
                    // Notify the selected item text
                    Toast.makeText
                            (getContext(), "Selected : " + selectedItemText, Toast.LENGTH_SHORT)
                            .show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        toAcc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItemText = (String) parent.getItemAtPosition(position);
                // If user change the default selection
                // First item is disable and it is used for hint
                if(position > 0){
                    // Notify the selected item text
                    Toast.makeText
                            (getContext(), "Selected : " + selectedItemText, Toast.LENGTH_SHORT)
                            .show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



    }
}