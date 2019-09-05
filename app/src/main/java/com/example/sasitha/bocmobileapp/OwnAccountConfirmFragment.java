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
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class OwnAccountConfirmFragment extends Fragment {

    TextView fromAcc, toAcc, amount,date, txtTime, description;
    Button back, cancel, confirm;

    Fragment fragment = null;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_own_account_confirm,null);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle bundle2 = getArguments();

        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        fromAcc = (TextView) view.findViewById(R.id.textViewFromAcc);
        toAcc = (TextView) view.findViewById(R.id.textViewToAcc);
        amount = (TextView) view.findViewById(R.id.textViewAmount);
        date = (TextView) view.findViewById(R.id.textViewDate);
        txtTime = (TextView) view.findViewById(R.id.textViewTime);
        description = (TextView) view.findViewById(R.id.textViewDescription);

        back = (Button) view.findViewById(R.id.buttonBack);
        cancel = (Button) view.findViewById(R.id.buttonCancel);
        confirm = (Button) view.findViewById(R.id.buttonConfirm);



        if(bundle2 != null){
            fromAcc.setText(bundle2.getString("fromAcc"));
            toAcc.setText(bundle2.getString("toAcc"));
            amount.setText(bundle2.getString("amount"));
            date.setText(bundle2.getString("date"));
            txtTime.setText(bundle2.getString("time"));
            description.setText(bundle2.getString("description"));
        }

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Confirm!");
                builder.setMessage("Are you sure you want to cancel?");
                builder.setCancelable(false);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Fragment fragmentD = new DashboardFragment();
                        fragmentTransaction.replace(R.id.screen_area, fragmentD);

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

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle3 = new Bundle();
                bundle3.putString("fromAcc" , fromAcc.getText().toString());
                bundle3.putString("toAcc", toAcc.getText().toString());
                bundle3.putString("amount" , amount.getText().toString());
                bundle3.putString("description", description.getText().toString());
                bundle3.putString("date", date.getText().toString());
                bundle3.putString("time", txtTime.getText().toString());
                Fragment fragmentD = new OwnAccountFragment();
                fragmentD.setArguments(bundle3);
                fragmentTransaction.replace(R.id.screen_area, fragmentD);

                fragmentTransaction.commit();
                //getActivity().setTitle("Own Account Transactions");
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Confirm!");
                builder.setMessage("Are you sure you want to do the transaction?");
                builder.setCancelable(false);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Bundle bundle3 = new Bundle();

                        Random rand = new Random();
                        int id = rand.nextInt(100000000);

                        String date = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss", Locale.getDefault()).format(new Date());

                        bundle3.putString("id" , String.valueOf(id));
                        bundle3.putString("fromAcc" , fromAcc.getText().toString());
                        bundle3.putString("toAcc", toAcc.getText().toString());
                        bundle3.putString("amount" , amount.getText().toString());
                        bundle3.putString("description", description.getText().toString());
                        bundle3.putString("date", date);
                        bundle3.putString("time", txtTime.getText().toString());
                        Fragment fragmentD = new OwnAccountTransactionSuccessFragment();
                        fragmentD.setArguments(bundle3);
                        fragmentTransaction.replace(R.id.screen_area, fragmentD);

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
