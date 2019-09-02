package com.example.sasitha.bocmobileapp;

import android.content.Intent;
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

public class OwnAccountConfirmFragment extends Fragment {

    TextView fromAcc, toAcc, amount,date;
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
        back = (Button) view.findViewById(R.id.buttonBack);
        cancel = (Button) view.findViewById(R.id.buttonCancel);



        if(bundle2 != null){
            fromAcc.setText(bundle2.getString("fromAcc"));
            toAcc.setText(bundle2.getString("toAcc"));
            amount.setText(bundle2.getString("amount"));
            date.setText(bundle2.getString("date"));
        }

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragmentD = new DashboardFragment();
                fragmentTransaction.replace(R.id.screen_area, fragmentD);

                fragmentTransaction.commit();
                getActivity().setTitle("Home");
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
