package liveTicker;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by Adel on 22.06.2015.
 */
public class LiveTickerActivity extends Fragment {

    // newInstance constructor for creating fragment with arguments
    public static LiveTickerActivity newInstance(int page, String title) {
        LiveTickerActivity liveTickerActivity = new LiveTickerActivity();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        liveTickerActivity.setArguments(args);
        return liveTickerActivity;
    }

}
