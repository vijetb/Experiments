package contentprovider.test.com.tabsexample;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FirstFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FirstFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FirstFragment extends Fragment {
    private  static final String TAG = "FirstFragment";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "---- onCreateView -------");
        return inflater.inflate(R.layout.fragment_first, container, false);
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "---- onCreate -------");
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG, "---- onViewCreated -------");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG, "---- onActivityCreated -------");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "---- onStart -------");

    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "---- onResume -------");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "---- onPause -------");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "---- onStop -------");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "---- onDestroyView -------");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "---- onDestroy -------");

    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, "---- onDetach -------");
    }

}
