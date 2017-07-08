package com.example.em.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.em.R;
import com.example.em.widgets.MyToolBar;


public class CategoryFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_category, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        MyToolBar toolbar = (MyToolBar) getActivity().findViewById(R.id.my_toolbar);
        toolbar.setNavigationIcon(R.drawable.icon_back_32px);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
    }
}
