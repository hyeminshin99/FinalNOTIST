package com.example.bottom_bar;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import java.io.File;
import java.io.IOException;

public class FragmentRecord extends Fragment {

    public MediaRecorder recorder;
    public MediaPlayer player;
    String path;

    ViewGroup viewGroup;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        viewGroup = (ViewGroup) inflater.inflate(R.layout.activity_main_record,container,false);


        TextView filename_textView = (TextView) viewGroup.findViewById(R.id.filename_recordpage);
        final String path =  getArguments().getString(ResourceConst.FILE_NAME_KEY);
        if(path != null){
            filename_textView.setText(path);
        }
        viewGroup.findViewById(R.id.record_btn).setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                recordAudio(path);
            }
        });

        return viewGroup;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void recordAudio(String path) {
        recorder = new MediaRecorder();

        recorder.setAudioSource(MediaRecorder.AudioSource.MIC); // 어디에서 음성 데이터를 받을 것인지
        recorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4); // 압축 형식 설정
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);

        Log.d("path is null", (path == null) + "");
        recorder.setOutputFile(path);

        try {
            recorder.prepare();
            recorder.start();

            Toast.makeText(((BottomMainActivity)getActivity()), "녹음 시작됨.", Toast.LENGTH_SHORT).show();
//            if(recorder == null){
//                recorder.prepare();
//                recorder.start();
//                Toast.makeText(getApplicationContext(), "녹음 시작됨.", Toast.LENGTH_SHORT).show();
//            }
//            else {
//                recorder.resume(); //resume녹음다시시작..오류남...!!!!!!!
//                Toast.makeText(getApplicationContext(), "녹음 다시 시작됨.", Toast.LENGTH_SHORT).show();
//            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)//? pause땜에..
    public void stopRecording() {


        if (recorder != null) {
            recorder.pause(); //됨..?



            Toast.makeText(((BottomMainActivity)getActivity()), "녹음 중지됨.", Toast.LENGTH_LONG).show();
        }
    }

    public void stopandsave() {

        if (recorder != null) {
            recorder.stop(); //
            recorder.release();
            recorder = null;

//            Toast.makeText(getApplicationContext(), "녹음 중지됨.", Toast.LENGTH_LONG).show();
        }


    }

    public void playAudio() {
        try {
            closePlayer(); //항상 처음시작에 죽임

            player = new MediaPlayer();
            //player.setDataSource(url); //인터넷에서 가져와서 플레이
            player.setDataSource(((BottomMainActivity)getActivity()), Uri.parse(path)); //녹음된파일 플레이
            player.prepare();
            player.start();

            Toast.makeText(((BottomMainActivity)getActivity()), "재생 시작됨.", Toast.LENGTH_LONG).show(); //메세지띄움
        } catch (Exception e) {
            Log.e("SampleAudioRecorder", "Audio play failed.", e);
        }
    }

    public void stopAudio() { //재시작
        if (player != null && player.isPlaying()) {
            closePlayer();

            Toast.makeText(((BottomMainActivity)getActivity()), "정지됨.", Toast.LENGTH_LONG).show();
        }
    }

    public void closePlayer() { //항상 처음시작에 죽임
        if (player != null) {
            player.stop();
            player.release();
            player = null;
        }
    }

}
