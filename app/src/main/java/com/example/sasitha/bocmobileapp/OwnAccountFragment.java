package com.example.sasitha.bocmobileapp;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

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



    }
}