package news;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import adelgrimm.sckw_app.R;

/**
 * Created by Adel on 23.06.2015.
 */
public class AktiveHerrenNews extends Fragment {
    // Store instance variables
    private String title;
    private int page;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.aktive_news_layout, container, false);
        TextView tvLabel = (TextView) rootView.findViewById(R.id.tab2);
        tvLabel.setText("News Erste -- " + page + " -- " + title);
        return rootView;
    }
}