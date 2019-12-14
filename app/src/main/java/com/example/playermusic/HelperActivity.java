package com.example.playermusic;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.content.Intent;

//public class HelperActivity extends Activity {
public class HelperActivity extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        String action = (String) intent.getExtras().get("DO");
        Log.d("DEBUG_RADIO","action = "+action);
        if (action.equals("app")) {
            //Your code

            context.stopService(new Intent(context, MyService.class));
            context.startService(new Intent(context, MyService.class));
            Log.d("DEBUG_RADIO","app foi chamado");
        } else if (action.equals("stop")) {
            context.stopService(new Intent(context, MyService.class));
            Log.d("DEBUG_RADIO","stop foi chamado");
        }
    }
    /*
    private HelperActivity ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("DEBUG_RADIO","Helper foi chamado");

        super.onCreate(savedInstanceState);
        ctx = this;
        String action = (String) getIntent().getExtras().get("DO");
        if (action.equals("radio")) {
            //Your code
        } else if (action.equals("volume")) {
            //Your code
        } else if (action.equals("reboot")) {
            //Your code
        } else if (action.equals("top")) {
            //Your code
        } else if (action.equals("app")) {
            //Your code
            stopService(new Intent(ctx, MyService.class));
            startService(new Intent(ctx, MyService.class));
            Log.d("DEBUG_RADIO","app foi chamado");
        } else if (action.equals("stop")) {
            //Your code
            stopService(new Intent(ctx, MyService.class));
            Log.d("DEBUG_RADIO","stop foi chamado");
        }

        if (!action.equals("reboot"))
            finish();
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
    }*/
}
