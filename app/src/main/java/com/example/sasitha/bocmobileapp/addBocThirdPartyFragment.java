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

public class addBocThirdPartyFragment extends Fragment
{
    EditText editTextBocOtherName, editTextBocOtherNickName, editTextBocOtherAccNo, editTextBocOtherMail;
    Button btnOtherBocCancel, btnOtherBocSubmit;

    Vibrator vib;
    Animation animShake;

    Fragment fragment = null;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_boc_thirdparty, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        animShake = AnimationUtils.loadAnimation(getContext(), R.anim.shake);
        vib = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);

        btnOtherBocCancel = (Button) view.findViewById(R.id.btnOtherBocCancel);
        btnOtherBocSubmit = (Button) view.findViewById(R.id.btnOtherBocSubmit);

        editTextBocOtherName = (EditText)  view.findViewById(R.id.editTextBocOtherName);
        editTextBocOtherNickName = (EditText)  view.findViewById(R.id.editTextBocOtherNickName);
        editTextBocOtherAccNo = (EditText)  view.findViewById(R.id.editTextBocOtherAccNo);
        editTextBocOtherMail = (EditText)  view.findViewById(R.id.editTextBocOtherMail);

        fragmentManager = getFragmentManager();

        btnOtherBocSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(editTextBocOtherName.getText().toString().isEmpty())
                {
                    editTextBocOtherName.setError("Enter Name!");
                    editTextBocOtherName.startAnimation(animShake);
                    vib.vibrate(120);
                }
                else if(editTextBocOtherNickName.getText().toString().isEmpty())
                {
                    editTextBocOtherNickName.setError("Enter Nick Name!");
                    editTextBocOtherNickName.startAnimation(animShake);
                    vib.vibrate(120);
                }
                else if(editTextBocOtherAccNo.getText().toString().isEmpty())
                {
                    editTextBocOtherAccNo.setError("Enter Account Number!");
                    editTextBocOtherAccNo.startAnimation(animShake);
                    vib.vibrate(120);
                }
                else if(editTextBocOtherMail.getText().toString().isEmpty())
                {
                    editTextBocOtherMail.setError("Enter E-Mail!");
                    editTextBocOtherMail.startAnimation(animShake);
                    vib.vibrate(120);
                }
                else{
                    fragment = new thirdPartyFragment();
                    fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.screen_area, fragment);
                    fragmentTransaction.commit();
                    getActivity().setTitle("Add Accounts");
                }
            }
        });

        btnOtherBocCancel.setOnClickListener(new View.OnClickListener() {
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
                        Fragment fragmentD = new addAccountFragment();
                        fragmentTransaction = fragmentManager.beginTransaction();
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
