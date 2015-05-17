package officeabet.vijet.com.abetappfirstfragment;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.Switch;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public static final String TAG = "PlaceHolderFragment";
        private Switch smsServiceSwitch;
        private RelativeLayout smsLayout;

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            smsServiceSwitch = (Switch) rootView.findViewById(R.id.switch_activate_sms_service);
            smsServiceSwitch.setOnCheckedChangeListener(smsListener);
            smsLayout = (RelativeLayout)rootView.findViewById(R.id.expColapseSmsTextLayout);

            return rootView;
        }

        private CompoundButton.OnCheckedChangeListener smsListener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.d(TAG,"Hit cliked");
                if(isChecked){
                    expandOrCollapse(smsLayout,"expand");
                }else{
                    expandOrCollapse(smsLayout,"collpasde");
                }

            }
        };


        private void expandOrCollapse(final View v,String exp_or_colpse) {
            TranslateAnimation anim = null;
            if(exp_or_colpse.equals("expand"))
            {
                anim = new TranslateAnimation(0.0f, 0.0f, -v.getHeight(), 0.0f);
                v.setVisibility(View.VISIBLE);
            }
            else{
                anim = new TranslateAnimation(0.0f, 0.0f, 0.0f, -v.getHeight());
                Animation.AnimationListener collapselistener= new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        v.setVisibility(View.GONE);
                    }
                };

                anim.setAnimationListener(collapselistener);
            }
            anim.setDuration(300);
            anim.setInterpolator(new AccelerateInterpolator(0.5f));
            v.startAnimation(anim);
        }

    }
}
