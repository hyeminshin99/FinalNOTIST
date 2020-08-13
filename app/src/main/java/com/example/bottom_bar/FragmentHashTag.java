package com.example.bottom_bar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class FragmentHashTag extends Fragment {

    ViewGroup viewGroup;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_hashtag,container,false);

        ImageButton categoryBtn = (ImageButton) viewGroup.findViewById(R.id.textbutton_2);
        categoryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((BottomMainActivity)getActivity()).replaceFragment(new FragmentCategory());
            }
        });

        ImageButton hashTag = (ImageButton) viewGroup.findViewById(R.id.textbutton_1);
        hashTag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((BottomMainActivity)getActivity()).replaceFragment(new FragmentList());
            }
        });

//        ImageButton button = (ImageButton) findViewById(R.id.textbutton_3);
//        button.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View view)
//            {
//                Intent intent = new Intent(getApplicationContext(), MainActivity3_hashtag.class);
//                startActivity(intent);
//            }
//        });

        return viewGroup;

    }

}