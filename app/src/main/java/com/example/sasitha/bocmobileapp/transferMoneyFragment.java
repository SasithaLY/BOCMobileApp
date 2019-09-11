package com.example.sasitha.bocmobileapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Locale;

public class transferMoneyFragment extends Fragment
{
    TextView textViewtransferFromAccNo, textViewTransferToAccNo;
    EditText editTextAmount, editTextDescription;
    Button btnTransferCancel, btnTransfer;

    Fragment fragment = null;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    Vibrator vib;
    Animation animShake;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        animShake = AnimationUtils.loadAnimation(getContext(), R.anim.shake);
        vib = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);

        textViewtransferFromAccNo = (TextView)view.findViewById(R.id.textViewtransferFromAccNo);
        textViewTransferToAccNo = (TextView)view.findViewById(R.id.textViewTransferToAccNo);

        editTextAmount = (EditText) view.findViewById(R.id.editTextAmount);
        editTextDescription = (EditText) view.findViewById(R.id.editTextDescription);

        btnTransferCancel = (Button) view.findViewById(R.id.btnTransferCancel);
        btnTransfer = (Button) view.findViewById(R.id.btnTransfer);

        //Bundle bundle = getArguments();

        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        btnTransfer.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Bundle bundleTransMoney = new Bundle();
                bundleTransMoney.putString("tranFromAcc", textViewtransferFromAccNo.getText().toString());
                bundleTransMoney.putString("tranToAcc", textViewTransferToAccNo.getText().toString());

                if(editTextAmount.getText().toString().isEmpty())
                {
                    editTextAmount.setError("Enter amount!");
                    editTextAmount.startAnimation(animShake);
                    vib.vibrate(120);
                }
                else if(editTextDescription.getText().toString().isEmpty())
                {
                    editTextDescription.setError("Enter Description!");
                    editTextDescription.startAnimation(animShake);
                    vib.vibrate(120);
                }
                else
                {
                    double d = Double.parseDouble(editTextAmount.getText().toString());
                    bundleTransMoney.putString("transAmount" , String.format(Locale.getDefault(),"%.2f", d));
                    bundleTransMoney.putString("tranDesc", editTextDescription.getText().toString());

                    fragment.setArguments(bundleTransMoney);
                    //fragment = new OwnAccountConfirmFragment();
                    fragmentTransaction.replace(R.id.screen_area, fragment);
                    fragmentTransaction.commit();
                }
            }
        });

        btnTransferCancel.setOnClickListener(new View.OnClickListener() {
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
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_transfer_money, null);
    }
}
