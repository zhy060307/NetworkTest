package wg.roomis.testnetwork;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class TestReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(final Context context, Intent intent) {

        context.startService(new Intent(context, TestService.class));
    }
}
