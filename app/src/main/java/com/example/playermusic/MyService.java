package com.example.playermusic;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;
import android.provider.Settings;
import android.util.Log;

import androidx.annotation.Nullable;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import java.io.IOException;

public class MyService extends Service {
    //creating a mediaplayer object
    private MediaPlayer player;

    private SimpleExoPlayer player1;

    private static MyService instance = null;

    public static boolean isInstanceCreated() {
        return instance != null;
    }



    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //getting systems default ringtone

        instance = this;
        if (player1 == null) {
            startPlayer();
        }
        /*
        final Context ctx = this;
        player = new MediaPlayer();
        Uri uri = Uri.parse("http://stm01.virtualcast.com.br:8176/live");*/

        /*try {
            player.setDataSource("http://stm01.virtualcast.com.br:8176/live");
            player.prepare();
        } catch (IOException e) {
            Log.d("DEBUG_RADIO", e.getMessage());
            e.printStackTrace();
        }*/



        /*
        player.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            public boolean onError(MediaPlayer mp, int what, int extra) {
                mp.reset();
                return false;
            }
        });

        player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            public void onPrepared(MediaPlayer mp) {
                mp.start();
            }
        });

        try {
            player.setDataSource("http://stm01.virtualcast.com.br:8176/live");
            player.prepareAsync();
        } catch (IllegalArgumentException e) {
            Log.d("DEBUG_RADIO", e.getMessage());
        } catch (IllegalStateException e) {
            Log.d("DEBUG_RADIO", e.getMessage());
        } catch (IOException e) {
            Log.d("DEBUG_RADIO", e.getMessage());
        }

         */





        /*try {
            String url = "http://stm01.virtualcast.com.br:8176/live"; // your URL here

            player.setAudioStreamType(AudioManager.STREAM_MUSIC);
            player.setDataSource(url);
            player.prepare(); // might take long! (for buffering, etc)
        } catch (IllegalArgumentException e) {
            Log.d("DEBUG_RADIO", e.getMessage());
        } catch (IllegalStateException e) {
            Log.d("DEBUG_RADIO", e.getMessage());
        } catch (IOException e) {
            Log.d("DEBUG_RADIO", e.getMessage());
        }*/

        /*player = MediaPlayer.create(this,
                Settings.System.DEFAULT_RINGTONE_URI);
        //setting loop play to true
        //this will make the ringtone continuously playing
        player.setLooping(true);*/

        //staring the player
        //player.start();

        //we have some options for service
        //start sticky means service will be explicity started and stopped
        return START_STICKY;
    }

    private void startPlayer() {
        /*Context ctx = this;
        player1  = new SimpleExoPlayer.Builder(ctx).build();
        String userAgent = Util.getUserAgent(ctx, "SimpleExoPlayer");
        Uri uri = Uri.parse("http://stm01.virtualcast.com.br:8176/live");
        DataSource.Factory dataSourceFactory = new DefaultHttpDataSourceFactory(
                userAgent, null,
                DefaultHttpDataSource.DEFAULT_CONNECT_TIMEOUT_MILLIS,
                DefaultHttpDataSource.DEFAULT_READ_TIMEOUT_MILLIS,
                true);
        // This is the MediaSource representing the media to be played.
        MediaSource mediaSource = new ExtractorMediaSource.Factory(dataSourceFactory)
                .createMediaSource(uri);

        TrackSelector trackSelector = new DefaultTrackSelector();

        player1 = ExoPlayerFactory.newSimpleInstance(ctx, trackSelector);
        //player1.addListener((Player.EventListener) ctx);

        player1.prepare(mediaSource);
        player1.setPlayWhenReady(true); */


        Context ctx = this;

        String userAgent = Util.getUserAgent(ctx, "SimpleExoPlayer");
        Uri uri = Uri.parse("http://stm01.virtualcast.com.br:8176/live");
        DataSource.Factory dataSourceFactory = new DefaultHttpDataSourceFactory(
                userAgent, null,
                DefaultHttpDataSource.DEFAULT_CONNECT_TIMEOUT_MILLIS,
                DefaultHttpDataSource.DEFAULT_READ_TIMEOUT_MILLIS,
                true);
        // This is the MediaSource representing the media to be played.
        MediaSource mediaSource = new ProgressiveMediaSource.Factory(dataSourceFactory)
                .createMediaSource(uri);

        TrackSelector trackSelector = new DefaultTrackSelector(ctx);

        player1  = new SimpleExoPlayer.Builder(ctx).setTrackSelector(trackSelector).build();
        //player1.set;
        //player1.addListener((Player.EventListener) ctx);

        player1.prepare(mediaSource);
        player1.setPlayWhenReady(true);
    }

    public void  showNotification() {
        Context ctx = this;
        new MyNotification(ctx);
        //finish()
    }



    @Override
    public void onCreate()
    {
        super.onCreate();
        showNotification();
        instance = this;

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        showNotification();
        player1.stop();
        player1.release();
        instance = null;

        //stopping the player when service is destroyed
//        player.stop();
    }
}
