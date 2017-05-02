package com.example.haeyoungjoo.hackerthon;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.RatingBar;
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

/**
 * Created by hae young Joo on 2016-11-12.
 * Modified by Kang in-Chang on 2017-04-22.
 */

public class ViewActivity extends AppCompatActivity {

    private RatingBar[] Average;
    private int[] ratingBar;
    private String url;                      // HTTP 통신을 위해 URL를 저장하는 변수
    private String jsonResult1;             // JSON 결과를 받기 위한 변수
    private ProfessorData professorData;            // Enum Class인 ProfessorData에 있는 데이터를 사용하기 위한 변수
    private ProfessorGenerator professorGenerator;  // Factory Method Pattern 적용
    private Professor professor;                     // ProfessorGenerator 에서 생성되는 객체를 받기 위한 변수

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("ViewActivity", "onCreate");

        Intent intent = getIntent();
        // MainActivity와 EvaluationActivity에서 intent로 넘겨 받은 NAME의 값을 professorName 변수에 저장한다.
        String professorName = intent.getExtras().getString("NAME");
        // ProfessorData로 professorName 값을 Enum 값으로 변환한다.
        professorData = ProfessorData.valueOf(professorName);

        setContentView(professorData.getLayout());

        // Facotry Method Pattern used
        professorGenerator = new CSEProfessorGenerator();
        professor = professorGenerator.professorGenerate(professorData);

        url = professorData.getURL();
        ratingBar = professorData.getRatingBar();
        int theNumberOfRatingBar = ratingBar.length;
        Average = new RatingBar[theNumberOfRatingBar];
        for (int i = 0; i < theNumberOfRatingBar; i++) {
            Average[i] = (RatingBar) findViewById(ratingBar[i]);
        }

        // AccessWebService 클래스의 accessWebhService메소드를 static으로 만들지 않았다면, 객체를 생성 및 초기화한 후 메소드에 접근해야 한다.
        AccessWebService.accessWebService(new JSonReadTaskRating(), url);
    }

    public void onClickEvaluationBtn(View view) {
        Log.d("ViewActivity", Integer.toString(view.getId()));
        Intent intent = new Intent(this, EvaluationActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("lectureForEvaluationBtn", Integer.toString(view.getId()));
        bundle.putString("professorForEvaluationBtn", professor.getName());
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }

    public void onClickCall(View view) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(professor.getTelephoneNumber()));
        if (intent != null) {
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
        }
    }

    public void onClickMail(View view) {
        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse(professor.getEmailAddress()));
        if (intent != null) {
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
        }
    }

    // 웹 서버에 접근해서 JSON으로 데이터들을 읽어오는 스레드
    private class JSonReadTaskRating extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            Log.d("ViewActivity", "doInBackground");

            HttpClient httpclient1 = new DefaultHttpClient();
            HttpPost httppost1 = new HttpPost(params[0]); //(params[0]에는 url 값이 들어있습! Post로 서버에 연결.

            try {
                HttpResponse response1 = httpclient1.execute(httppost1);//서버로 연결하고 그 결과 값을.
                InputStream inputStream1 = response1.getEntity().getContent();//서버에서 주는 결과를 받아올수 있는 inputStream 객체 생성.
                jsonResult1 = inputStreamToString(inputStream1).toString();//스트림을 매개변수 값으로 넣고 원래 json은 문자열이기때문에 문자열로 바꿔서 온다.

            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        private StringBuilder inputStreamToString(InputStream is) {
            Log.d("ViewActivity", "inputStreamToString");

            String rLine = "";
            StringBuilder answer = new StringBuilder();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));//해당 스트림으로부터 읽어 와서 버퍼에 저장한다~

            try {
                while ((rLine = rd.readLine()) != null) {
                    answer.append(rLine);
                    Log.d("line", rLine);
                    Log.d("number", "1");
                }
            } catch (IOException e) {
                Toast.makeText(getApplicationContext(),
                        "Error..." + e.toString(), Toast.LENGTH_LONG).show();
            }
            Log.d("answer", answer.toString());

            return answer;
        }

        @Override
        protected void onPostExecute(String result) {
            Log.d("ViewActivity", "onPostExecute");

            try {
                JSONObject jsonResponse1 = new JSONObject(jsonResult1);
                JSONArray jsonMainNode1 = jsonResponse1.optJSONArray("avgData");

                for (int i = 0; i < jsonMainNode1.length(); i++) {
                    JSONObject jsonChildNode1 = jsonMainNode1.getJSONObject(i);
                    String subjectavg = jsonChildNode1.optString("avg");
                    Log.d("avg", subjectavg);

                    if (i == 0)
                        Average[0].setRating(Float.parseFloat(subjectavg));
                    else if (i == 1)
                        Average[1].setRating(Float.parseFloat(subjectavg));
                    else if (i == 2)
                        Average[2].setRating(Float.parseFloat(subjectavg));
                    else
                        Average[3].setRating(Float.parseFloat(subjectavg));
                }
            } catch (JSONException e) {
                Toast.makeText(getApplicationContext(), "Error" + e.toString(),
                        Toast.LENGTH_SHORT).show();
            }
        }
    }
}