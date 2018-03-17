package com.testing.myapp.worklifebalancemeter.Home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.testing.myapp.worklifebalancemeter.R;
import com.testing.myapp.worklifebalancemeter.videoPlayer.ExoPlayerActivity;
import com.testing.myapp.worklifebalancemeter.videoPlayer.ExoPlayerAudioActivity;

/**
 * Created by harsha on 1/14/18.
 */

public class LocationFragment extends Fragment {

    private static final String TAG = "LocationFragment";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_location, container , false);

        Button btn = (Button) view.findViewById(R.id.exoPlayerBtn);
        Button btnMp3 = (Button) view.findViewById(R.id.exoPlayerMp3);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(v.getId() == R.id.exoPlayerBtn){
                    Intent intent = new Intent(getActivity(),ExoPlayerActivity.class);
                    getActivity().startActivity(intent);
                }
            }
        });
        btnMp3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(v.getId() == R.id.exoPlayerMp3){
                    Intent intent = new Intent(getActivity(),ExoPlayerAudioActivity.class);
                    getActivity().startActivity(intent);
                }
            }
        });

        return view;
    }
}
