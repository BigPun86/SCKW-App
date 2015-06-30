package news;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import adapter.listview.CustomAdapter;
import adelgrimm.sckw_app.R;

/**
 * Created by Adel on 23.06.2015.
 */
public class AktiveHerrenNews extends Fragment {


    static Bundle args = new Bundle();
    TextView title, link;
    ListView lv;
    SwipeRefreshLayout mSwipeRefreshLayout;
    ArrayList<String> newsTitle;
    ArrayList<String> newsDesc;


    public static AktiveHerrenNews newInstance(ArrayList<String> newsTitle, ArrayList<String> newsDesc) {

        AktiveHerrenNews f = new AktiveHerrenNews();
        args.putStringArrayList("Titel", newsTitle);
        args.putStringArrayList("NewsText", newsDesc);
        f.setArguments(args);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (args != null) {
            newsTitle = args.getStringArrayList("Titel");
            newsDesc = args.getStringArrayList("NewsText");
        } else {
            Toast.makeText(getActivity(), "No Data args!", Toast.LENGTH_LONG).show();
        }

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.aktive_news_layout, container, false);


        mSwipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.activity_main_swipe_refresh_layout);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                fetchNews();
            }

        });

        lv = (ListView) rootView.findViewById(R.id.newsList);


        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        fetchNews();
    }

    private void fetchNews() {

        lv.setAdapter(new CustomAdapter(getActivity(), newsTitle, newsDesc));
        mSwipeRefreshLayout.setRefreshing(false);

    }


}