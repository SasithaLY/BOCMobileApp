package com.example.sasitha.bocmobileapp;

import android.app.AlertDialog;
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
import android.widget.Button;
import android.widget.EditText;

public class otherBankCardsFragment extends Fragment
{
    Button btnOtherBankSubmit, btnOtherBankCancel;
    EditText editTextOtherCardNo, editTextOtherName, editTextOtherNickName, editTextOtherMail;

    Vibrator vib;
    Animation animShake;

    Fragment fragment = null;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_other_bank_cards, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnOtherBankCancel = (Button) view.findViewById(R.id.btnOtherBocCancel);
        btnOtherBankSubmit = (Button) view.findViewById(R.id.btnOtherBocSubmit);

        editTextOtherCardNo = (EditText)  view.findViewById(R.id.editTextOtherCardNo);
        editTextOtherName = (EditText)  view.findViewById(R.id.editTextBocOtherNickName);
        editTextOtherNickName = (EditText)  view.findViewById(R.id.editTextBocOtherAccNo);
        editTextOtherMail = (EditText)  view.findViewById(R.id.editTextBocOtherMail);

        fragmentManager = getFragmentManager();

        btnOtherBankSubmit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(editTextOtherCardNo.getText().toString().isEmpty())
                {
                    editTextOtherCardNo.setError("Enter Card Number!");
                    editTextOtherCardNo.startAnimation(animShake);
                    vib.vibrate(120);
                }
                else if(editTextOtherName.getText().toString().isEmpty())
                {
                    editTextOtherName.setError("Enter Name!");
                    editTextOtherName.startAnimation(animShake);
                    vib.vibrate(120);
                }
                else if(editTextOtherNickName.getText().toString().isEmpty())
                {
                    editTextOtherNickName.setError("Enter Nick Name!");
                    editTextOtherNickName.startAnimation(animShake);
                    vib.vibrate(120);
                }
                else if(editTextOtherMail.getText().toString().isEmpty())
                {
                    editTextOtherMail.setError("Enter E-Mail!");
                    editTextOtherMail.startAnimation(animShake);
                    vib.vibrate(120);
                }
                else{
                    fragment = new addAccountFragment();
                    fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.screen_area, fragment);
                    fragmentTransaction.commit();
                    getActivity().setTitle("Add Accounts");
                }
            }
        });

        btnOtherBankCancel.setOnClickListener(new View.OnClickListener()
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

        
    }
}