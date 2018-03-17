package com.testing.myapp.worklifebalancemeter.Home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.testing.myapp.worklifebalancemeter.R;

/**
 * Created by harsha on 1/14/18.
 */

public class ReminderFragment extends Fragment {

    private static final String TAG = "ReminderFragment";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_reminder, container , false);
//
//        String item = "giphy.mp4";
//        String file  = "android.resource://"+ getActivity().getPackageName() + "Care/raw/" + item;
//        Button btn = (Button) view.findViewById(R.id.btn_play);
//        VideoView videoView = (VideoView) view.findViewById(R.id.player);
//        videoView.setVideoPath("https://www.youtube.com/watch?v=YRlyRnhztA0");
//        MediaController mc = new MediaController(getActivity());
//        videoView.setMediaController(mc);
//        mc.setAnchorView(videoView);
//        videoView.start();


//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                if(videoView.isPlaying()){
//                    videoView.stopPlayback();
//                }else{
//                    videoView.start();
//                }
//            }
//        });

        return view;
    }

}
