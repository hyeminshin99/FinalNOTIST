package com.example.FinalNOTIST;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

public class FragmentDataSender {

    public static void sendData(String key, String sendData, Fragment fragment){
        Bundle bundle = new Bundle();
        bundle.putString(key, sendData);
        fragment.setArguments(bundle);
    }
}
