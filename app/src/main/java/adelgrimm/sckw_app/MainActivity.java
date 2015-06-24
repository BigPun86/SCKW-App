package adelgrimm.sckw_app;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import Adapter.NavigationDrawerFragment;
import liveTicker.LiveTickerActivity;

public class MainActivity extends ActionBarActivity implements NavigationDrawerFragment.NavigationDrawerCallbacks {


    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(R.id.navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout));
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {

        Fragment fragment;
// update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();

        switch (position) {

            case 0:
                // fragment = new HomeFragment().newInstance(position + 1, "HomeFragment ");
                fragmentManager.beginTransaction().replace(R.id.container, new HomeFragment().newInstance(position + 1, "HomeFragment ")).commit();
                break;
            case 1:
                //fragment = new NewsFragmentActivity().newInstance(position + 1, "NewsFragmentActivity ");
                fragmentManager.beginTransaction().replace(R.id.container, new NewsFragmentActivity().newInstance(position + 1, "NewsFragmentActivity ")).commit();
                break;
            case 2:
                //fragment = new LiveTickerActivity().newInstance(position + 1, "LiveTickerActivity ");
                fragmentManager.beginTransaction().replace(R.id.container, new LiveTickerActivity().newInstance(position + 1, "LiveTickerActivity ")).commit();
                break;
            case 3:
                // fragment = new ImpressumFragment().newInstance(position + 1, "ImpressumFragment ");
                fragmentManager.beginTransaction().replace(R.id.container, new ImpressumFragment().newInstance(position + 1, "ImpressumFragment ")).commit();
                break;
            case 4:
                //fragment = new WhatsHotFragment().newInstance(position + 1, "WhatsHotFragment ");
                fragmentManager.beginTransaction().replace(R.id.container, new WhatsHotFragment().newInstance(position + 1, "WhatsHotFragment ")).commit();
                break;
        }


        //fragmentManager.beginTransaction().replace(R.id.container, PlaceholderFragment.newInstance(position + 1)).commit();

    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.HOME);
                break;
            case 2:
                mTitle = getString(R.string.NEWS);
                break;
            case 3:
                mTitle = getString(R.string.LIVE_TICKER);
                break;
            case 4:
                mTitle = getString(R.string.IMPRESSUM);
                break;
            case 5:
                mTitle = getString(R.string.WHATS_HOT);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.menu_main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
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
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MainActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }


}

