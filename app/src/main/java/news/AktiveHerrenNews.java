package news;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

import org.jsoup.Jsoup;

import adelgrimm.sckw_app.R;

/**
 * Created by Adel on 23.06.2015.
 */
public class AktiveHerrenNews extends Fragment {


    EditText title, link, description;
    Button b1, b2;
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.aktive_news_layout, container, false);

        title = (EditText) rootView.findViewById(R.id.title);
        link = (EditText) rootView.findViewById(R.id.link);
        description = (EditText) rootView.findViewById(R.id.description);
        final WebView w1 = (WebView) rootView.findViewById(R.id.webView);

        Button b1 = (Button) rootView.findViewById(R.id.b1);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                obj = new HandleXML(finalUrl);
                obj.fetchXML();


                while (obj.parsingComplete) ;
                title.setText(html2text(obj.getTitle()));
                link.setText(html2text(obj.getLink()));
                description.setText(html2text(obj.getDescription()));
            }
        });

//        TextView newsTitle = (TextView) rootView.findViewById(R.id.newsTitle);
//        newsTitle.onTouchEvent(new View.OnTouchListener()) {
//
//            @Override
//            public boolean onTouch (View arg0, MotionEvent mE){
//                obj = new HandleXML(finalUrl);
//                obj.fetchXML();
//
//                while (obj.parsingComplete) ;
//            }
//        });

        Button b2 = (Button) rootView.findViewById(R.id.b2);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                w1.loadUrl(finalUrl);
            }
        });


//        TextView tvLabel = (TextView) rootView.findViewById(R.id.tab2);
//        tvLabel.setText("News Erste -- " + page + " -- " + title);
        return rootView;
    }
}