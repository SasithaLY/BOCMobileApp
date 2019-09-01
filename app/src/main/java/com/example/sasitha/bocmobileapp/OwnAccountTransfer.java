package com.example.sasitha.bocmobileapp;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OwnAccountTransfer.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link OwnAccountTransfer#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OwnAccountTransfer extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    TextView date, selectDate;
    ImageView datePicker;
    DatePickerDialog.OnDateSetListener mDateSetListener;
    Spinner fromAcc, toAcc;
    EditText amount, description;
    Button pay, cancel;
    RadioGroup radioGroup;
    RadioButton payNow, payLater;

    Toolbar toolbar;

    public OwnAccountTransfer() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OwnAccountTransfer.
     */
    // TODO: Rename and change types and number of parameters
    public static OwnAccountTransfer newInstance(String param1, String param2) {
        OwnAccountTransfer fragment = new OwnAccountTransfer();
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
        return inflater.inflate(R.layout.fragment_own_account_transfer, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        date = (TextView) view.findViewById(R.id.txtDate);
//        selectDate = (TextView) view.findViewById(R.id.textViewSelectDate);
//        datePicker = (ImageView) view.findViewById(R.id.imageViewDate);
//
//        pay = (Button) view.findViewById(R.id.btnPay);
//        cancel = (Button) view.findViewById(R.id.btnCancel);
//
//        fromAcc = (Spinner) view.findViewById(R.id.spinnerPayFrom);
//        toAcc = (Spinner) view.findViewById(R.id.spinnerPayTo);
//        amount = (EditText) view.findViewById(R.id.editTextAmount);
//        description = (EditText)view.findViewById(R.id.editTextDescription);
//
//        payNow = (RadioButton) view.findViewById(R.id.radioBtnPayNow);
//        payLater = (RadioButton) view.findViewById(R.id.radioBtnPayLater);


//        if(payNow.isChecked()){
//            datePicker.setVisibility(View.GONE);
//            selectDate.setVisibility(View.GONE);
//            date.setVisibility(View.GONE);
//        }
//
//        payNow.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                datePicker.setVisibility(View.GONE);
//                selectDate.setVisibility(View.GONE);
//                date.setVisibility(View.GONE);
//            }
//        });
//
//        payLater.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                datePicker.setVisibility(View.VISIBLE);
//                selectDate.setVisibility(View.VISIBLE);
//                date.setVisibility(View.VISIBLE);
//            }
//        });
//        datePicker.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Calendar cal = Calendar.getInstance();
//                int year = cal.get(Calendar.YEAR);
//                int month = cal.get(Calendar.MONTH);
//                int day = cal.get(Calendar.DAY_OF_MONTH);
//
//                DatePickerDialog dialog = new DatePickerDialog(getContext(), R.style.DialogTheme, mDateSetListener, year,month,day);
//
//                //dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//                dialog.show();
//            }
//        });
//
//        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
//            @Override
//            public void onDateSet(DatePicker view, int year, int month, int day) {
//                month = month + 1;
//
//                String value = month + "/" +day+ "/" +year;
//                date.setText(value);
//            }
//        };
//
//        pay.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
////                Intent intent = new Intent(OwnAccountTransfer.this, ConfirmTransaction.class);
////                intent.putExtra("fromAcc" , fromAcc.getSelectedItem().toString());
////                intent.putExtra("toAcc", toAcc.getSelectedItem().toString());
////                intent.putExtra("amount" , amount.getText().toString());
////                intent.putExtra("description", description.getText().toString());
//
//
//
////                if(payNow.isChecked()){
////
////                    String date = new SimpleDateFormat("MM-dd-yyyy", Locale.getDefault()).format(new Date());
////                    intent.putExtra("date", date);
////                    startActivity(intent);
////                }else if(payLater.isChecked()){
////
////                    intent.putExtra("date", date.getText().toString());
////                    startActivity(intent);
////                }
//
//            }
//        });
//
//
//
//        cancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), Home.class);
//                startActivity(intent);
//            }
//        });
//
//        String[] accounts = new String[]{
//                "Select Account",
//                "0082166378",
//                "0075189026",
//                "0078190057"
//        };
//
//        List<String> acoountList = new ArrayList<>(Arrays.asList(accounts));
//
//        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(getActivity(), R.layout.support_simple_spinner_dropdown_item, acoountList){
//            @Override
//            public boolean isEnabled(int position){
//                if(position == 0)
//                {
//                    // Disable the first item from Spinner
//                    // First item will be use for hint
//                    return false;
//                }
//                else
//                {
//                    return true;
//                }
//            }
//            @Override
//            public View getDropDownView(int position, View convertView,
//                                        ViewGroup parent) {
//                View view = super.getDropDownView(position, convertView, parent);
//                TextView tv = (TextView) view;
//                if(position == 0){
//                    // Set the hint text color gray
//                    tv.setTextColor(Color.GRAY);
//                }
//                else {
//                    tv.setTextColor(Color.BLACK);
//                }
//                return view;
//            }
//        };
//
//        spinnerArrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
//        fromAcc.setAdapter(spinnerArrayAdapter);
//        toAcc.setAdapter(spinnerArrayAdapter);
//
//        fromAcc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                String selectedItemText = (String) parent.getItemAtPosition(position);
//                // If user change the default selection
//                // First item is disable and it is used for hint
//                if(position > 0){
//                    // Notify the selected item text
//
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
//
//        toAcc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                String selectedItemText = (String) parent.getItemAtPosition(position);
//                // If user change the default selection
//                // First item is disable and it is used for hint
//                if(position > 0){
//                    // Notify the selected item text
//
//
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
