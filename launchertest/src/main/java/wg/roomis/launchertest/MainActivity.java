package wg.roomis.launchertest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {


    private Timer timer;

    private UpdateUrlProvider apiContentProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        apiContentProvider = new UpdateUrlProvider(this);
    }

    public void start(View view) {

        if (null == timer) {
            timer = new Timer();
        }
        timer.purge();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                Intent intent = new Intent("wg.roomis.action.INSTALL_COMPLETE");
                intent.setFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
                sendBroadcast(intent);
            }
        }, 1000, 60 * 1000);


        Log.d("updateUrl", apiContentProvider.getUpdateUrl());

    }
}
