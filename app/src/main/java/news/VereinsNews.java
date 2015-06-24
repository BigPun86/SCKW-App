package news;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import adelgrimm.sckw_app.R;

/**
 * Created by Adel on 23.06.2015.
 */
public class VereinsNews extends Fragment {
    public TextView textView;
    public Button button;

    // Store instance variables
    private String title;
    private int page;

    public static void readWebpage(VereinsNews vereinsNews, View view) throws IOException {
        vereinsNews.new GetStringFromUrl().execute("http://www.google.com/");


    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.vereins_news_layout, container, false);
        Intent intent = new Intent();

        TextView tvLabel = (TextView) rootView.findViewById(R.id.tab1);
        tvLabel.setText("VereinsNews -- " + page + " -- " + title);

        textView = (TextView) rootView.findViewById(R.id.TextView01);
        button = (Button) rootView.findViewById(R.id.readWebpage);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    readWebpage(VereinsNews.this, v);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        textView.setText("   Bundle b = getArguments();\n" +
                "        String s = b.getString(\"RESULT\");");

        return rootView;
    }

    private class GetStringFromUrl extends AsyncTask<String, Void, String> {

        ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            // show progress dialog when downloading
            dialog = ProgressDialog.show(getActivity(), null, "Downloading...");
        }

        @Override
        protected String doInBackground(String... params) {

            // @BadSkillz codes with same changes
            try {
                HttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost("http://www.google.com");
                HttpGet httpGet = new HttpGet(params[0]);
                HttpResponse response = httpClient.execute(httpGet);
                HttpEntity entity = response.getEntity();

                BufferedHttpEntity buf = new BufferedHttpEntity(entity);

                InputStream is = buf.getContent();

                BufferedReader r = new BufferedReader(new InputStreamReader(is));

                StringBuilder total = new StringBuilder();
                String line;
                while ((line = r.readLine()) != null) {
                    total.append(line + "\n");
                }
                String result = total.toString();
                Log.i("Get URL", "Downloaded string: " + result);
                return result;
            } catch (Exception e) {
                Log.e("Get Url", "Error in downloading: " + e.toString());
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            // show result in textView
            if (result == null) {
                textView.setText("Error in downloading. Please try again.");
            } else {
                textView.setText(Html.fromHtml(result));

            }

            Toast.makeText(getActivity(), "Ping", Toast.LENGTH_LONG).show();
            // close progresses dialog
            dialog.dismiss();
        }
    }
}