package adelgrimm.sckw_app;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.viewpagerindicator.TitlePageIndicator;

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
            String actualPage;
            switch (position) {
                case 0:
                    actualPage = "Vereins News";
                    return actualPage;
                case 1:
                    actualPage = "Aktiven News";
                    return actualPage;
                case 2:
                    actualPage = "Junioren News";
                    return actualPage;
                default:
                    actualPage = "NEWS";
                    return actualPage;
            }

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