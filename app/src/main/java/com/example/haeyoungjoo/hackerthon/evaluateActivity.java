package com.example.haeyoungjoo.hackerthon;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class evaluateActivity extends Activity {

    RatingBar rating;
    TextView tv01;
    float ratevalue;//매긴 별점 값

    private EditText m_EditText_comment;

    private String jsonResult;
    private String url;

    private ListView listView;

    Intent i;
    String lecture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.evaluate);

        listView = (ListView) findViewById(R.id.list);
        i = getIntent();
        lecture = i.getExtras().getString("professorlecture");

        if( lecture.compareTo("cyg_cplusplus") == 0){
            url = "http://jhy753.dothome.co.kr/jsonfetch01.php";

        }else if(lecture.compareTo("cyg_datastructure") == 0 ){

            url = "http://jhy753.dothome.co.kr/jsonfetch02.php";

        }else if(lecture.compareTo("cyg_graphic") == 0 ){
            url = "http://jhy753.dothome.co.kr/jsonfetch03.php";
        }
        else if(lecture.compareTo("jgc_computerEngineerBasic") == 0 ){
            url = "http://jhy753.dothome.co.kr/jsonfetch04.php";
        }
        else if(lecture.compareTo("jgc_C") == 0 ){
            url = "http://jhy753.dothome.co.kr/jsonfetch05.php";

        }
        else if(lecture.compareTo("jgs_microprocessor") == 0 ){
            url = "http://jhy753.dothome.co.kr/jsonfetch06.php";
        }
        else if(lecture.compareTo("jgs_embedded") == 0 ){
            url = "http://jhy753.dothome.co.kr/jsonfetch07.php";
        }
        else if(lecture.compareTo("kys_base_electric_electron") == 0 ){
            url = "http://jhy753.dothome.co.kr/jsonfetch08.php";
        }
        else if(lecture.compareTo("kys_computer_architecture") == 0 ){
            url = "http://jhy753.dothome.co.kr/jsonfetch09.php";
        }
        else if(lecture.compareTo("kys_animation") == 0 ){
            url = "http://jhy753.dothome.co.kr/jsonfetch10.php";
        }
        else if(lecture.compareTo("ljh_C") == 0 ){
            url = "http://jhy753.dothome.co.kr/jsonfetch11.php";
        }
        else if(lecture.compareTo("ljh_Cplusplus") == 0 ){
            url = "http://jhy753.dothome.co.kr/jsonfetch12.php";
        }
        else if(lecture.compareTo("ljh_java") == 0 ){
            url = "http://jhy753.dothome.co.kr/jsonfetch13.php";
        }
        else if(lecture.compareTo("uhg_trash") == 0 ){
            url = "http://jhy753.dothome.co.kr/jsonfetch14.php";
        }
        else if(lecture.compareTo("uhg_ai") == 0 ){
            url = "http://jhy753.dothome.co.kr/jsonfetch15.php";
        }


        accessWebService();

        rating = (RatingBar) findViewById(R.id.ratingbar1);
        tv01 = (TextView) findViewById(R.id.tv01);

        rating.setStepSize((float) 0.5); //별 색깔이 1칸씩줄어들고 늘어남 0.5로하면 반칸씩 들어감
        rating.setRating((float) 2.5);  // 처음보여줄때(색깔이 한개도없음) default 값이 0  이다
        rating.setIsIndicator(false);//true -별점만 표시 사용자가 변경 불가 , false - 사용자가 변경가능

        rating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {

            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                tv01.setText("평점 : " + rating);
                ratevalue = rating;
            }
        });

        m_EditText_comment = (EditText) findViewById(R.id.EditTextComment);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    // Async Task to access the web
    private class JsonReadTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(params[0]);
            try {
                HttpResponse response = httpclient.execute(httppost);
                InputStream inputStream = response.getEntity().getContent();

                jsonResult = inputStreamToString(inputStream).toString();
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        private StringBuilder inputStreamToString(InputStream is) {
            String rLine = "";
            StringBuilder answer = new StringBuilder();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));

            try {
                while ((rLine = rd.readLine()) != null) {
                    answer.append(rLine);
                }
            } catch (IOException e) {
// e.printStackTrace();
                Toast.makeText(getApplicationContext(),
                        "Error..." + e.toString(), Toast.LENGTH_LONG).show();
            }
            return answer;
        }

        @Override
        protected void onPostExecute(String result) {
            ListDrwaer();
        }
    }// end async task

    public void accessWebService() {
        JsonReadTask task = new JsonReadTask();
// passes values for the urls string array
        task.execute(new String[]{url});
    }

    // build hash set for list view
    public void ListDrwaer() {
        List<Map<String, String>> employeeList = new ArrayList<Map<String, String>>();

        try {
            JSONObject jsonResponse = new JSONObject(jsonResult);
            JSONArray jsonMainNode = jsonResponse.optJSONArray("commentData");

            for (int i = 0; i < jsonMainNode.length(); i++) {
                JSONObject jsonChildNode = jsonMainNode.getJSONObject(i);
                String number = jsonChildNode.optString("comment");
                employeeList.add(createEmployee("employees", number));
            }
        } catch (JSONException e) {
            Toast.makeText(getApplicationContext(), "Error" + e.toString(),
                    Toast.LENGTH_SHORT).show();
        }

        SimpleAdapter simpleAdapter = new SimpleAdapter(this, employeeList,
                android.R.layout.simple_list_item_1,
                new String[]{"employees"}, new int[]{android.R.id.text1});
        listView.setAdapter(simpleAdapter);

    }

    private HashMap<String, String> createEmployee(String name, String number) {
        HashMap<String, String> employeeNameNo = new HashMap<String, String>();
        employeeNameNo.put(name, number);
        return employeeNameNo;
    }

    public void CommentonClick(View view) {

        String commentData = m_EditText_comment.getText().toString();

        if (view.getId() == R.id.add) {

            int version = android.os.Build.VERSION.SDK_INT;
            Log.d("sdk version:", version + "");
            if (version > 8) {
                StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitNetwork().build());
            }

            try {

                URL url = new URL("http://jhy753.dothome.co.kr/dbconfig/dbconnect.php?comment=" + commentData + "&lecture=" + lecture);

                //여기 주소를 바꿔주면 된다. 데이터를 추가하려면 변수를 추가 해서 이어붙이면 된다. &기호로 이어붙인다. ex) name=les&num=20130610&phone=1111
                URLConnection conn = url.openConnection();
                //url경로를 열어준다.
                conn.getInputStream();
                //해당 url로 접속한다.
                Log.i("msg", "go");
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                Log.i("msg", "no");
            }
           Intent hi = new Intent( evaluateActivity.this ,    ViewActivity.class);

            if(lecture.compareTo("cyg_cplusplus") == 0 ){
                hi.putExtra("NAME","cyg");
                startActivity(hi);

            }else if(lecture.compareTo("cyg_datastructure") == 0 ){

                hi.putExtra("NAME","cyg");
                startActivity(hi);

            }else if(lecture.compareTo("cyg_graphic") == 0 ){
                hi.putExtra("NAME","cyg");
                startActivity(hi);
            }else if(lecture.compareTo("jgc_computerEngineerBasic") == 0 ){
                hi.putExtra("NAME","jgc");
                startActivity(hi);
            }else if(lecture.compareTo("jgc_C") == 0 ){
                hi.putExtra("NAME","jgc");
                startActivity(hi);
            }else if(lecture.compareTo("jgs_microprocessor") == 0 ){
                hi.putExtra("NAME","jgs");
                startActivity(hi);
            }else if(lecture.compareTo("jgs_embedded") == 0 ){
                hi.putExtra("NAME","jgs");
                startActivity(hi);
            }else if(lecture.compareTo("kys_base_electric_electron") == 0 ){
                hi.putExtra("NAME","kys");
                startActivity(hi);
            }else if(lecture.compareTo("kys_computer_architecture") == 0 ){
                hi.putExtra("NAME","kys");
                startActivity(hi);
            }else if(lecture.compareTo("kys_animation") == 0 ){
                hi.putExtra("NAME","kys");
                startActivity(hi);
            }else if(lecture.compareTo("ljh_C") == 0 ){
                hi.putExtra("NAME","ljh");
                startActivity(hi);
            }else if(lecture.compareTo("ljh_Cplusplus") == 0 ){
                hi.putExtra("NAME","ljh");
                startActivity(hi);
            }else if(lecture.compareTo("ljh_java") == 0 ){
                hi.putExtra("NAME","ljh");
                startActivity(hi);
            }else if(lecture.compareTo("uhg_trash") == 0 ){
                hi.putExtra("NAME","uhg");
                startActivity(hi);
            }else if(lecture.compareTo("uhg_ai") == 0 ){
                hi.putExtra("NAME","uhg");
                startActivity(hi);
            }
        }
    }
    //별점 등록!
    public void evaluate_Enrollment(View view) {


        //String commentData = m_EditText_comment.getText().toString();


        int version = android.os.Build.VERSION.SDK_INT;
        Log.d("sdk version:", version + "");
        if (version > 8) {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitNetwork().build());
        }

       String rate = Float.toString(ratevalue);

        try {
            URL url = new URL("http://jhy753.dothome.co.kr/avg.php?avg="+rate+"&lecture="+lecture);

            //여기 주소를 바꿔주면 된다. 데이터를 추가하려면 변수를 추가 해서 이어붙이면 된다. &기호로 이어붙인다. ex) name=les&num=20130610&phone=1111
            URLConnection conn = url.openConnection();
            //url경로를 열어준다.
            conn.getInputStream();
            //해당 url로 접속한다.
            Log.i("msg", "go");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Log.i("msg", "no");
        }

    }
}
