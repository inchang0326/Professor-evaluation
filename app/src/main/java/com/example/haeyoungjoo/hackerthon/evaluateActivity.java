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

    RatingBar rating; //별점 등록할때 쓰는 View
    TextView tv01;// RatingBar에 있는 별점을 클릭했을때 그 숫자값을 TextView에 나타냄.

    float ratevalue;//매긴 별점 값

    private EditText m_EditText_comment;//댓글을 쓸때 사용하는 EditText

    private String url; //웹서버 통신을 위해서 사용하는 url
    private String jsonResult; //댓글을 웹 서버에 저장하고, 웹서버에를 통해 댓글을 받아온다
    private ListView listView;//리스트뷰를 통해 댓글을 뿌려준다.

    Intent i;
    String lecture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.evaluate);

        listView = (ListView) findViewById(R.id.list);
        i = getIntent(); // 해당교수 강의이름을 받아옴.
        lecture = i.getExtras().getString("professorlecture");


        url ="http://jhy753.dothome.co.kr/commentList.php?lecture="+lecture;

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

    //웹서버에 접근해서 데이터들을 json으로 읽어오는 스레드~
    private class JsonReadTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {

            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(params[0]); //(params[0]에는 url 값이 들어있습! Post로 서버에 연결.

            try {

                HttpResponse response = httpclient.execute(httppost);//서버로 연결하고 그 결과 값을.
                InputStream inputStream = response.getEntity().getContent();//서버에서 주는 결과를 받아올수 있는 inputStream 객체 생성.
                String temp = inputStreamToString(inputStream).toString();//스트림을 매개변수 값으로 넣고 원래 json은 문자열이기때문에 문자열로 바꿔서 온다.

                //wow 시발 성공했다!! ㅋㅋㅋ
                jsonResult = temp.substring( temp.indexOf('{') );
                System.out.println(jsonResult);

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
            BufferedReader rd = new BufferedReader( new InputStreamReader(is) );//해당 스트림으로부터 읽어 와서 버퍼에 저장한다~


            try {

                while ( (rLine = rd.readLine()) != null ) {

                    answer.append(rLine);
                    Log.d("line",rLine );
                    Log.d("number","1");
                }
            } catch (IOException e) {
                Toast.makeText(getApplicationContext(),
                        "Error..." + e.toString(), Toast.LENGTH_LONG).show();
            }
            Log.d("answer",answer.toString() );
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

    //댓글을 jSon으로 받아와서 뿌려준다.
    public void ListDrwaer() {

        List< Map<String, String> > commentList = new ArrayList< Map<String, String> >();

        try {

            JSONObject jsonResponse = new JSONObject(jsonResult);
            JSONArray jsonMainNode = jsonResponse.optJSONArray("commentData");

            for (int i = 0; i < jsonMainNode.length(); i++) {

                JSONObject jsonChildNode = jsonMainNode.getJSONObject(i);
                String commentText = jsonChildNode.optString("comment");
                String timeText = jsonChildNode.optString("time");

                commentList.add( createComment("nameT","timeT", commentText,timeText ) );


            }
        } catch (JSONException e) {
            Toast.makeText(getApplicationContext(), "Error" + e.toString(),
                    Toast.LENGTH_SHORT).show();
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(this, commentList,
                android.R.layout.simple_list_item_2,
                new String[]{"nameT","timeT"}, new int[]{android.R.id.text1,android.R.id.text2});

        listView.setAdapter(simpleAdapter);
    }

    private HashMap<String, String> createComment(String nameT, String timeT, String commentText, String timeText) {

        HashMap<String, String> comment= new HashMap<String, String>();
        comment.put(nameT, commentText);
        comment.put(timeT, timeText);
        return comment;

    }

    //댓글입력을 클릭했을때 웹서버로 데이터를 입력한다
    public void CommentonClick(View view) {
        String commentData = m_EditText_comment.getText().toString();
        if (view.getId() == R.id.add) {

            int version = android.os.Build.VERSION.SDK_INT;
            Log.d("sdk version:", version + "");
            if (version > 8) {
                StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitNetwork().build());
            }
            try {

                URL url = new URL("http://jhy753.dothome.co.kr/dbconfig/insertComment.php?comment=" + commentData + "&lecture=" + lecture);
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

            accessWebService();

        }
    }

    //별점을  등록!
    public void evaluate_Enrollment(View view) {

        //String commentData = m_EditText_comment.getText().toString();
        int version = android.os.Build.VERSION.SDK_INT;
        Log.d("sdk version:", version + "");
        if (version > 8) {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitNetwork().build());
        }
       String rate = Float.toString(ratevalue);

        try {

            URL url = new URL("http://jhy753.dothome.co.kr/insertScoreValue.php?avg="+rate+"&lecture="+lecture);
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
