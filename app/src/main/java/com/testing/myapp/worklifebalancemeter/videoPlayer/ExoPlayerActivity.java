package com.testing.myapp.worklifebalancemeter.videoPlayer;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.MergingMediaSource;
import com.google.android.exoplayer2.source.SingleSampleMediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.TransferListener;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.Util;
import com.testing.myapp.worklifebalancemeter.R;



/**
 * Created by harsha on 3/4/18.
 */
public class ExoPlayerActivity extends AppCompatActivity {

    private static final String TAG = "ExoPlayerActivity";
    Context mContext = this;
    private boolean shouldAutoPlay = false;
    private DataSource.Factory mediaDataSourceFactory;
    private BandwidthMeter bandwidthMeter;
    private TrackSelector trackSelector;
    private SimpleExoPlayer player;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_activity);

        shouldAutoPlay = true;
        bandwidthMeter = new DefaultBandwidthMeter();
        mediaDataSourceFactory = new DefaultDataSourceFactory(this,
                Util.getUserAgent(mContext,"exoSample"), (TransferListener<? super DataSource>) bandwidthMeter);

    }

    private void initializePlayer(){

        SimpleExoPlayerView playerView = findViewById(R.id.player_view);
        playerView.requestFocus();

        // 1. Create a default TrackSelector
        Handler mainHandler = new Handler();
        TrackSelection.Factory videoTrackSelectionFactory = new AdaptiveTrackSelection.Factory(bandwidthMeter);
        trackSelector = new DefaultTrackSelector(videoTrackSelectionFactory);

        // 2. Create the player
        player = ExoPlayerFactory.newSimpleInstance(mContext, trackSelector);
        playerView.setPlayer(player);
        player.setPlayWhenReady(shouldAutoPlay);

        DefaultExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();
        String uri = "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4";
        MediaSource mediaSource = new ExtractorMediaSource(Uri.parse(uri),
                mediaDataSourceFactory, extractorsFactory , null , null);

        //adding subs
        Format textFormat = Format.createTextSampleFormat("100", MimeTypes.APPLICATION_SUBRIP,
                0, "en");
        Uri uriSub = Uri.parse("http://www.storiesinflight.com/js_videosub/jellies.srt");
        MediaSource subtitleSource = new SingleSampleMediaSource(
                uriSub, mediaDataSourceFactory, textFormat, C.TIME_UNSET);

        MergingMediaSource mergedSource = new MergingMediaSource(mediaSource, subtitleSource);

        player.prepare(mergedSource);


    }

    private void addSubs(){

    }

    private void releasePlayer() {
        if (player != null) {
            shouldAutoPlay = player.getPlayWhenReady();
            player.release();
            player = null;
            trackSelector = null;
        }
    }


    @Override
    protected void onStart() {
        super.onStart();
        if (Util.SDK_INT>23){
            initializePlayer();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if ((Util.SDK_INT <= 23 || player == null)) {
            initializePlayer();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (Util.SDK_INT <= 23) {
            releasePlayer();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (Util.SDK_INT > 23) {
            releasePlayer();
        }
    }

}
