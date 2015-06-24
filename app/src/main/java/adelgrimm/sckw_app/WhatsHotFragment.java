package adelgrimm.sckw_app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Adel on 23.06.2015.
 */
public class WhatsHotFragment extends Fragment {

    // newInstance constructor for creating fragment with arguments
    public static WhatsHotFragment newInstance(int page, String title) {
        WhatsHotFragment whatsHotFragment = new WhatsHotFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        whatsHotFragment.setArguments(args);
        return whatsHotFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    // Inflate the view for the fragment based on layout XML

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.whatshot_layout, container, false);

        return view;
    }
}
