package cse.buffalo.edu.ibetterme;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Issac on 11/14/2015.
 */
public class ExampleFragment extends Fragment {


    @Override
    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setHasOptionsMenu(true);
        //parent = (ContentActivity)getActivity();
    }

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//       // View view = inflater.inflate(R.layout.fragment_smart_solve, container, false);
//
//
//        return view;
//    }


}
