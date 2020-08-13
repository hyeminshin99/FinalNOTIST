package com.example.bottom_bar;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.io.File;

public class FragmentList extends Fragment {

    ViewGroup viewGroup;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_list,container,false);

        ImageButton categoryBtn = (ImageButton) viewGroup.findViewById(R.id.textbutton_2);
        categoryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((BottomMainActivity)getActivity()).replaceFragment(new FragmentCategory());
            }
        });

        ImageButton hashTag = (ImageButton) viewGroup.findViewById(R.id.textbutton_3);
        hashTag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((BottomMainActivity)getActivity()).replaceFragment(new FragmentHashTag());
            }
        });

        // 변수선언
        final ListView listView;
        ListViewAdapter adapter;

        // 객체생성
        adapter = new ListViewAdapter();

        // 리스트뷰참조, 어댑터 연결
        listView = (ListView) viewGroup.findViewById(R.id.list_view);
        listView.setAdapter(adapter);



        //File directory = getApplicationContext().getFilesDir();
        // path = directory.getAbsolutePath();
        // path = "/data/data/com.example.customlistview2/files/cafelife.mp4";

        String path = ((BottomMainActivity)getActivity()).getFilesDir().getAbsolutePath();
        File dir = new File(path);

        File []fileList = dir.listFiles();
        for(File file : fileList){
            if(file.isFile()){
                String fileName = file.getName();
                Log.d("[file] ", fileName);
                adapter.addItem(fileName);
            }
        }

        // click event 처리하기
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {

            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id)
            {
                ListViewitem item = (ListViewitem) parent.getItemAtPosition(position);
                String record_title = item.getRecordtitle();


            }
        });


        EditText editTextFilter = (EditText) viewGroup.findViewById(R.id.editTextFilter);
        editTextFilter.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void onTextChanged(CharSequence s, int start, int count, int after)
            {
                listView.setFilterText(s.toString());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int before, int count)
            {

            }

            // 필터링
            @Override
            public void afterTextChanged(Editable edit)
            {
                String filterText;
                filterText = edit.toString();
                //listView.setTextFilterEnabled(true);
                if(filterText.length() > 0) {
                    listView.setFilterText(filterText);
                } else {
                    listView.clearTextFilter();
                }

            }

        });
        return viewGroup;
    }


}
