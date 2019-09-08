package com.example.sasitha.bocmobileapp;

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

public class addAccountFragment extends Fragment
{
    Button btnAddThirdParty, btnAddOtherAccounts, btnAddThirdPartyBack;
    Fragment fragment = null;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_account, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        btnAddThirdParty = (Button) view.findViewById(R.id.btnAddThirdParty);
        btnAddOtherAccounts = (Button) view.findViewById(R.id.btnAddOtherAccounts);
        btnAddThirdPartyBack = (Button) view.findViewById(R.id.btnAddThirdPartyBack);
    }
}
