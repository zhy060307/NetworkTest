package wg.roomis.testnetwork;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class TestService extends Service {


    private static final String TAG = TestService.class.getSimpleName();
    private ExecutorService executorService = Executors.newScheduledThreadPool(10);

    private Runnable worker = new Runnable() {
        @Override
        public void run() {
            Log.d(TAG, "isNetworkConnected " + isNetworkConnected(TestService.this));
            requestApi();
        }
    };


    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        executorService.execute(worker);
        return START_STICKY_COMPATIBILITY;

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    public boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isConnected();
            }
        }
        return false;
    }


    private void requestApi() {
        String url = "http://console.qa.roomis.com.cn/api/client-apps/latest";
        final OkHttpClient client = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(url)
                .addHeader("X-Consumer-Custom-ID", "roomis-k12-own")
                .addHeader("deviceSn", "000ffd4a014f")
                .addHeader("apikey", "0001e01807cd4a9ebab4bbb5576f1815")
                .build();


        try (Response response = client.newCall(request).execute()) {
            final String body = response.body().string();
            Log.d(TAG, "latest is : " + body);

        } catch (IOException e) {
            Log.e(TAG, "call api error : " + e.toString());
        }
    }


}
