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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class thirdPartyFragment extends Fragment
{
    Button btnAddAccount, btnBack;
    ListView listViewAccounts;

    Fragment fragment = null;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_third_party, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnAddAccount = (Button) view.findViewById(R.id.btnAddAccount);
        btnBack = (Button) view.findViewById(R.id.btnThirdPartyBack);
        listViewAccounts = (ListView) view.findViewById(R.id.listViewAccounts);

        fragmentManager = getFragmentManager();

        btnAddAccount.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                fragment = new addAccountFragment();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.screen_area, fragment);
                fragmentTransaction.commit();
                getActivity().setTitle("Add Accounts");
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                fragment = new DashboardFragment();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.screen_area, fragment);
                fragmentTransaction.commit();
            }
        });

        listViewAccounts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                fragment = new transferMoneyFragment();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.screen_area, fragment);
                fragmentTransaction.commit();
            }
        });
    }
}
