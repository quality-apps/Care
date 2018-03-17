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

public class VideoFragment extends Fragment {

    private static final String TAG = "VideoFragment";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.hplib_activity_video_player , container , false);
        return view;
    }
}
