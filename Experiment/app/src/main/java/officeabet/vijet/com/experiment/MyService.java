package officeabet.vijet.com.experiment;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by BMH1014669 on 03/12/14.
 */
public class MyService extends Service {
    public static final String TAG = "MySErvice";
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, " ***** inside onCreate *****");
        toast("onCreate SErvice");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, " ***** inside onStartCommand *****");
        toast("onStartCommangd");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void toast(String msg){
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }
}
