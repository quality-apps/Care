package com.testing.myapp.worklifebalancemeter.videoPlayer;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.MergingMediaSource;
import com.google.android.exoplayer2.source.SingleSampleMediaSource;
import com.google.android.exoplayer2.text.CaptionStyleCompat;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.FileDataSource;
import com.google.android.exoplayer2.upstream.TransferListener;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.Util;
import com.testing.myapp.worklifebalancemeter.R;

import java.io.File;


/**
 * Created by harsha on 3/4/18.
 */
public class ExoPlayerAudioActivity extends AppCompatActivity {

    private static final String TAG = "ExoPlayerAudioActivity";
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
//        PlaybackControlView playerView = findViewById(R.id.player_view);
        playerView.requestFocus();
        DefaultExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();

        CaptionStyleCompat captionStyleCompat = new CaptionStyleCompat(Color.WHITE, Color.BLACK, Color.TRANSPARENT, CaptionStyleCompat.EDGE_TYPE_RAISED, Color.WHITE, null);

        playerView.getSubtitleView().setStyle(captionStyleCompat);
        playerView.getSubtitleView().setApplyEmbeddedStyles(false);
        playerView.getSubtitleView().setFractionalTextSize( 0.08f);
        playerView.setBackground(ContextCompat.getDrawable(mContext,R.drawable.bck1));
//        playerView.setBackgroundResource(R.drawable.bck1);
//        playerView.setVisibility(View.VISIBLE);
//        playerView.setShutterBackgroundColor(Color.TRANSPARENT);
//        playerView.setBackgroundColor(Color.YELLOW);

        TrackSelection.Factory audioTrackSelectionFactory = new AdaptiveTrackSelection.Factory(bandwidthMeter);
        trackSelector = new DefaultTrackSelector(audioTrackSelectionFactory);

        player = ExoPlayerFactory.newSimpleInstance(mContext, trackSelector);
        playerView.setPlayer(player);
        player.setPlayWhenReady(shouldAutoPlay);


        String uri = "https://www.hrupin.com/wp-content/uploads/mp3/testsong_20_sec.mp3";
//        OvertheHorizon
        String path = Environment.getExternalStorageDirectory().getPath() + "/Samsung/Music/Coldplay.mp3";
        Log.d(TAG, "initializePlayer log: "+ path);
        Log.d(TAG, "initializePlayer log: "+ path.substring(1));
        File file = new File(path.substring(1));
        Uri uriFile = Uri.fromFile(file);
        Log.d(TAG, "initializePlayer: "+ uriFile);

        DataSpec dataSpec = new DataSpec(uriFile);
        final FileDataSource fileDataSource = new FileDataSource();
        try {
            fileDataSource.open(dataSpec);
        } catch (FileDataSource.FileDataSourceException e) {
            e.printStackTrace();
        }

        DataSource.Factory factory = new DataSource.Factory() {
            @Override
            public DataSource createDataSource() {
                return fileDataSource;
            }
        };
        MediaSource audioSource = new ExtractorMediaSource(fileDataSource.getUri(),
                factory, extractorsFactory, null, null);


//        MediaSource mediaSource = new ExtractorMediaSource(Uri.parse(uri),
//                mediaDataSourceFactory, extractorsFactory , null , null);

        MediaSource mediaSource = new ExtractorMediaSource(uriFile,
                mediaDataSourceFactory, extractorsFactory , null , null);


        //adding subs
        Format textFormat = Format.createTextSampleFormat("100", MimeTypes.APPLICATION_SUBRIP,
                1, "en");
        Uri uriSub = Uri.parse("http://www.storiesinflight.com/js_videosub/jellies.srt");
        String pathUri = Environment.getExternalStorageDirectory().getPath() + "/Samsung/Music/Coldplay.srt";
        File fileSrt = new File(pathUri.substring(1));
        Uri uriFileSrt = Uri.fromFile(fileSrt);
//        MediaSource subtitleSource = new SingleSampleMediaSource(
//                uriSub, mediaDataSourceFactory, textFormat, C.TIME_UNSET);
        MediaSource subtitleSource = new SingleSampleMediaSource(
                uriFileSrt, mediaDataSourceFactory, textFormat, C.TIME_UNSET);


        MergingMediaSource mergedSource = new MergingMediaSource(mediaSource, subtitleSource);


        player.prepare(mergedSource);

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
