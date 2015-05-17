package officeabet.vijet.com.experiment;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.view.animation.Transformation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.lang.reflect.Method;
import java.util.ArrayList;


public class MainActivity extends Activity {
    private static final String TAG = "MainActivity";

    private final int LOADER_ID = 11;

    private Cursor mCursor;

    private ListView mListView;
    private MyAdapter adapter;
    private ArrayList<DataModel> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init(){
    //Log.d(TAG,"SACtivirty");

    }


    public void buttonClick(View view){
        Intent intent = new Intent(getApplicationContext(),MyService.class);
        intent.setAction("Ssafsda");
        startService(intent);
    }

}
