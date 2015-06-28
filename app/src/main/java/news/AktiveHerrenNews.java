package news;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.jsoup.Jsoup;

import java.util.ArrayList;

import Adapter.ListView.CustomAdapter;
import adelgrimm.sckw_app.R;

/**
 * Created by Adel on 23.06.2015.
 */
public class AktiveHerrenNews extends Fragment {


    TextView title, link;
    ListView lv;
    SwipeRefreshLayout mSwipeRefreshLayout;
    ArrayList<String> strArr;
    ArrayAdapter<String> adapter;
    private String finalUrl = "http://sckw.de/rss/blog/Aktive";
    private HandleXML obj;

    public static String html2text(String html) {
        return Jsoup.parse(html).text();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

        obj = new HandleXML(finalUrl);
        obj.fetchXML();

        while (obj.parsingComplete) ;

        String[] title = obj.getTitle();
        lv.setAdapter(new CustomAdapter(getActivity(), title));
        mSwipeRefreshLayout.setRefreshing(false);
    }
}