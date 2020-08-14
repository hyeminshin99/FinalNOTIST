package com.example.FinalNOTIST;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

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
        viewGroup = (ViewGroup) inflater.inflate(R.layout.activity_record,container,false);


        TextView filename_textView = (TextView) viewGroup.findViewById(R.id.filename_recordpage);
        final String path =  getArguments().getString(ResourceConst.FILE_NAME_KEY);
        if(path != null){
            String fileName = path.substring(path.lastIndexOf("/") + 1, path.length());
            filename_textView.setText(fileName);
        }
        viewGroup.findViewById(R.id.record_btn).setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)//stopRecording때문
            @Override
            public void onClick(View view) {
                view.setSelected(!view.isSelected());

                if (view.isSelected()) {
                    //Handle selected state change//녹음시작
                    recordAudio(path);
                }
                else {
                    //Handle de-select state change//녹음멈춤
                    stopRecording();
                }
            }

        });


        Button save = (Button) viewGroup.findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopandsave();
                ((BottomMainActivity)getActivity()).replaceFragment(new FragmentRecordSetting());

                String baseUrl = "https://direcord-283711.dt.r.appspot.com/";
//                String urlPath = "speech/analysis/meeting.mp3/?";
//                urlPath += "gs://direcord-283711.appspot.com/?";
//                String parameter = "minSpeakerCnt=4&maxSpeakerCnt=4";
                String urlPath = "/login/";
//                String url = baseUrl + urlPath + parameter;
                String url = baseUrl + urlPath;
                Log.d("url : ", url);
                Http sttRequest = new Http(url);
                try {
                    sttRequest.executeGet();
                    Log.d("stt result ", sttRequest.getResponse());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Toast.makeText((BottomMainActivity)getActivity(), "저장했습니다:)", Toast.LENGTH_LONG).show();

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
