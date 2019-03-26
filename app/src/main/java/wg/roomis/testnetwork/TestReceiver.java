package wg.roomis.testnetwork;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class TestReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(final Context context, Intent intent) {

        Log.d(TestReceiver.class.getSimpleName(), intent.getPackage());
        context.startService(new Intent(context, TestService.class));
    }
}
