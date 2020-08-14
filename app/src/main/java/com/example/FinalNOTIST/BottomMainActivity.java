package com.example.FinalNOTIST;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class BottomMainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    FragmentHome fragmentHome;
    FragmentList fragmentList;
    FragmentRecordSetting fragmentRecord;
    FragmentGroup fragmentGroup;
    FragmentSettings fragmentSettings;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bottom_activity_main);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        fragmentHome = new FragmentHome();
        fragmentList = new FragmentList();
        fragmentRecord = new FragmentRecordSetting();
        fragmentGroup = new FragmentGroup();
        fragmentSettings = new FragmentSettings();

        //제일 처음 띄워줄 뷰를 세팅해줍니다. commit();까지 해줘야 합니다.
        getSupportFragmentManager().beginTransaction().replace(R.id.main_layout,fragmentHome).commitAllowingStateLoss();
        //bottomnavigationview의 아이콘을 선택 했을때 원하는 프래그먼트가 띄워질 수 있도록
        // 리스너를 추가합니다.
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                onStop();
                switch (menuItem.getItemId()){
                    //menu_bottom.xml에서 지정해줬던 아이디 값을 받아와서 각 아이디값마다 다른 이벤트를 발생시킵니다.
                    case R.id.home:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.main_layout,fragmentHome).commitAllowingStateLoss();
                        return true;
                    case R.id.list:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.main_layout,fragmentList).commitAllowingStateLoss();
                        return true;
                    case R.id.record:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.main_layout,fragmentRecord).commitAllowingStateLoss();
                        return true;

                    case R.id.group:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.main_layout,fragmentGroup).commitAllowingStateLoss();
                        return true;

                    case R.id.settings:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.main_layout,fragmentSettings).commitAllowingStateLoss();
                        return true;
                }
                return false;
            }

        });
    }

    public void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_layout, fragment).commitAllowingStateLoss();      // Fragment로 사용할 MainActivity내의 layout공간을 선택합니다.
    }

//    @Override
//    public void onStop() {
//        super.onStop();
//        requireActivity().finish();
//    }//리로드가 잘 되는지는 모르겠다..
    }

