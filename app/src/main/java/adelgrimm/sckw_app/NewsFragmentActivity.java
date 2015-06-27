package adelgrimm.sckw_app;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.viewpagerindicator.TitlePageIndicator;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import news.AktiveHerrenNews;
import news.JuniorenNews;
import news.VereinsNews;


/**
 * Created by Adel on 22.06.2015.
 */
public class NewsFragmentActivity extends Fragment {

    FragmentPagerAdapter adapterViewPager;

    // Tab titles
    private String[] tabs = {"Vereins News", "Aktiven News", "Junioren News"};

    public static NewsFragmentActivity newInstance(int page, String title) {
        NewsFragmentActivity newsFragmentActivity = new NewsFragmentActivity();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        newsFragmentActivity.setArguments(args);
        return newsFragmentActivity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.news_layout, container, false);
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.vpPager);
        adapterViewPager = new ViewPageAdapter(getChildFragmentManager());
        viewPager.setClipToPadding(false);
        viewPager.setPageMargin(12);

        viewPager.setAdapter(adapterViewPager);

//        //Bind the title indicator to the adapter
//        TitlePageIndicator titleIndicator = (TitlePageIndicator) view.findViewById(R.id.titles);
//        titleIndicator.setViewPager(viewPager);
        // ViewPager Indicator
        TitlePageIndicator titleIndicator = (TitlePageIndicator) view.findViewById(R.id.indicator);
//        titleIndicator.setFades(false);
        titleIndicator.setViewPager(viewPager);


        // viewPager.setPageTransformer(true, new CardTransformer(0.7f));
        viewPager.setPageTransformer(false, new ViewPager.PageTransformer() {

            @SuppressLint("NewApi")
            @Override
            public void transformPage(View page, float position) {

                page.setRotationY(position * -60);
            }
        });

        // Attach the page change listener inside the activity
        titleIndicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            // This method will be invoked when a new page becomes selected.
            @Override
            public void onPageSelected(int position) {

            }

            // This method will be invoked when the current page is scrolled
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                // Code goes here
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                // Code goes here
            }
        });

        return view;
    }

    public void readWebpage(View view) {
        RequestTask task = new RequestTask();
        task.execute("http://www.google.com");

    }

    public static class ViewPageAdapter extends FragmentPagerAdapter {
        private static int NUM_ITEMS = 3;

        public ViewPageAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        // Returns total number of pages
        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        // Returns the fragment to display for that page
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0: // Fragment # 0 - This will show FirstFragment
                    return new VereinsNews();
                case 1: // Fragment # 0 - This will show FirstFragment different title
                    return new AktiveHerrenNews();
                case 2: // Fragment # 1 - This will show SecondFragment
                    return new JuniorenNews();
                default:
                    return null;
            }
        }

        // Returns the page title for the top indicator
        @Override
        public CharSequence getPageTitle(int position) {
            return "Page " + position;
        }

    }   // newInstance constructor for creating fragment with arguments

    public class RequestTask extends AsyncTask<String, Void, String> {

        @Override
// username, password, message, mobile
        protected String doInBackground(String... url) {
            // constants
            int timeoutSocket = 5000;
            int timeoutConnection = 5000;

            HttpParams httpParameters = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpParameters, timeoutConnection);
            HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);
            HttpClient client = new DefaultHttpClient(httpParameters);

            HttpGet httpget = new HttpGet(url[0]);

            try {
                HttpResponse getResponse = client.execute(httpget);
                final int statusCode = getResponse.getStatusLine().getStatusCode();

                if (statusCode != HttpStatus.SC_OK) {
                    Log.w("MyApp", "Download Error: " + statusCode + "| for URL: " + url);
                    return null;
                }

                String line = "";
                StringBuilder total = new StringBuilder();

                HttpEntity getResponseEntity = getResponse.getEntity();

                BufferedReader reader = new BufferedReader(new InputStreamReader(getResponseEntity.getContent()));

                while ((line = reader.readLine()) != null) {
                    total.append(line);
                }

                line = total.toString();
                return line;
            } catch (Exception e) {
                Log.w("MyApp", "Download Exception : " + e.toString());
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            VereinsNews fragment = new VereinsNews();
            Bundle args = new Bundle();
            args.putString("RESULT", result);
            fragment.setArguments(args);


        }
    }

    /**
     * Awesome Launcher-inspired page transformer
     */
    @SuppressLint("NewApi")
    public class CardTransformer implements ViewPager.PageTransformer {

        private final float scalingStart;

        public CardTransformer(float scalingStart) {
            super();
            this.scalingStart = 1 - scalingStart;
        }

        @SuppressLint("NewApi")
        @Override
        public void transformPage(View page, float position) {

            if (position >= 0) {
                final int w = page.getWidth();
                float scaleFactor = 1 - scalingStart * position;

                page.setAlpha(1 - position);
                page.setScaleX(scaleFactor);
                page.setScaleY(scaleFactor);
                page.setTranslationX(w * (1 - position) - w);
            }
        }
    }
}