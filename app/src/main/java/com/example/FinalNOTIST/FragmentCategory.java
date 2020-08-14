package com.example.FinalNOTIST;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentCategory extends Fragment {

    ViewGroup viewGroup;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_category,container,false);
//
        ImageButton categoryBtn = (ImageButton) viewGroup.findViewById(R.id.textbutton_1);
        categoryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((BottomMainActivity)getActivity()).replaceFragment(new FragmentList());
            }
        });

        ImageButton hashTag = (ImageButton) viewGroup.findViewById(R.id.textbutton_3);
        hashTag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((BottomMainActivity)getActivity()).replaceFragment(new FragmentHashTag());
            }
        });

        return viewGroup;

    }
}