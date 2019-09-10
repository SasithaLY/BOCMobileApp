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

import java.util.Random;

public class confirmThirdPartyTransferFragment extends Fragment
{
    TextView textViewFromAccNo, textViewToAccNo, textViewTranAmount, textViewTranDescrip;
    Button btnConfirmBack, btnConfirmCancel, btnConfirmConfirm;

    Fragment fragment = null;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_confirm_third_party_transfer,null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle bundleConfirm = getArguments();

        textViewFromAccNo = (TextView) view.findViewById(R.id.textViewFromAccNo);
        textViewToAccNo = (TextView) view.findViewById(R.id.textViewToAccNo);
        textViewTranAmount = (TextView) view.findViewById(R.id.textViewTranAmount);
        textViewTranDescrip = (TextView) view.findViewById(R.id.textViewTranDescrip);

        btnConfirmBack = (Button) view.findViewById(R.id.btnConfirmBack);
        btnConfirmCancel = (Button) view.findViewById(R.id.btnConfirmCancel);
        btnConfirmConfirm = (Button) view.findViewById(R.id.btnConfirmConfirm);

        if(bundleConfirm != null)
        {
            textViewFromAccNo.setText(bundleConfirm.getString("tranFromAcc"));
            textViewToAccNo.setText(bundleConfirm.getString("tranToAcc"));
            textViewTranAmount.setText(bundleConfirm.getString("transAmount"));
            textViewTranDescrip.setText(bundleConfirm.getString("tranDesc"));
        }

        btnConfirmCancel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Confirm!");
                builder.setMessage("Are you sure you want to cancel?");
                builder.setCancelable(false);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Fragment fragmentD = new thirdPartyFragment();
                        fragmentTransaction.replace(R.id.screen_area, fragmentD);
                        fragmentTransaction.commit();
                        getActivity().setTitle("Third Party Transfers");

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

        btnConfirmBack.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Confirm!");
                builder.setMessage("Are you sure you want to cancel?");
                builder.setCancelable(false);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        Bundle bundleConfirm = new Bundle();

                        Random rand = new Random();
                        int id = rand.nextInt(1000000);

                        bundleConfirm.putString("id" , String.valueOf(id));
                        bundleConfirm.putString("fromAcc" , textViewFromAccNo.getText().toString());
                        bundleConfirm.putString("toAcc", textViewToAccNo.getText().toString());
                        bundleConfirm.putString("amount" , textViewTranAmount.getText().toString());
                        bundleConfirm.putString("description", textViewTranDescrip.getText().toString());

//                        Fragment fragmentD = new transferMoneyFragment();
//                        fragmentD.setArguments(bundleConfirm);
//                        fragmentTransaction.replace(R.id.screen_area, fragmentD);
//                        fragmentTransaction.commit();

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
