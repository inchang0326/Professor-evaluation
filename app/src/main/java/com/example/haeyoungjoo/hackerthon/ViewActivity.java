package com.example.haeyoungjoo.hackerthon;

import android.content.Intent;
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
 */

public class ViewActivity extends AppCompatActivity {

    /*한 교수가 가르칠수 있는 최대과목이 3개밖에 없어서 RatingBar를 3개 선언 하였다*/
    RatingBar Average1;
    RatingBar Average2;
    RatingBar Average3;

    /*각 교수의 각 과목당 평점을 매기기 위해서 필요한 url ( 각 교수당 최대 3개의 과목이 있다 ) */

    private String url1;
    private String url2;
    private String url3;
    private String url;

    String jsonResult1; // json으로 결과값을 받기위해 필요함!.
    String jsonResult2;
    String jsonResult3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        String professorName = intent.getExtras().getString("NAME");

        System.out.println(professorName + "2");

        /* 해당 이름에 맞는 activity를 보여준다 */
        if (professorName.compareTo("cyg") == 0) {

            setContentView(R.layout.cyg); //최영규에 하당하는 레이아웃을 여준다.

            url = "http://jhy753.dothome.co.kr/avgCalculater.php?professorName="+"cyg" ;


           /* 최영규 과목에 해당하는 평균을 내주는 RatingBar -- 평점을 계산해서 RatingBar에 뿌려준다.*/
            Average1  = (RatingBar) findViewById(R.id.cyg_cplusplus_Average);
            Average2  = (RatingBar) findViewById(R.id.cyg_datastructure_Average);
            Average3  = (RatingBar) findViewById(R.id.cyg_graphic_Average);

            accessWebServiceRating();


        } else if (professorName.compareTo("jgc") == 0) {

            setContentView(R.layout.jgc);

            url = "http://jhy753.dothome.co.kr/avgCalculater.php?professorName="+"jgc";
            Average2  = (RatingBar) findViewById(R.id.jgc_computerEngineerBasic_Average);
            Average1  = (RatingBar) findViewById(R.id.jgc_C_Average);

            accessWebServiceRating();


        } else if (professorName.compareTo("jgs") == 0) {

            setContentView(R.layout.jgs);
            url = "http://jhy753.dothome.co.kr/avgCalculater.php?professorName="+"jgs";

            Average2  = (RatingBar) findViewById(R.id.jgs_microprocessor_Average);
            Average1  = (RatingBar) findViewById(R.id.jgs_embedded_Average);
            accessWebServiceRating();

        } else if (professorName.compareTo("kys") == 0) {
            setContentView(R.layout.kys);

            url = "http://jhy753.dothome.co.kr/avgCalculater.php?professorName="+"kys";

            Average2  = (RatingBar) findViewById(R.id.kys_base_electric_electron_Average);
            Average3  = (RatingBar) findViewById(R.id.ys_computer_architecture_Average);
            Average1  = (RatingBar) findViewById(R.id.kys_animation_Average);

            accessWebServiceRating();

        } else if (professorName.compareTo("ljh") == 0) {
            setContentView(R.layout.ljh);
            url = "http://jhy753.dothome.co.kr/avgCalculater.php?professorName="+"ljh";


            Average1  = (RatingBar) findViewById(R.id.ljh_C_Average);
            Average2  = (RatingBar) findViewById(R.id.ljh_Cplusplus_Average);
            Average3  = (RatingBar) findViewById(R.id.ljh_java_Average);

            accessWebServiceRating();

        } else if (professorName.compareTo("uhg") == 0) {

            setContentView(R.layout.uhg);

            url = "http://jhy753.dothome.co.kr/avgCalculater.php?professorName="+"uhg";

            Average2  = (RatingBar) findViewById(R.id.uhg_ai_Average);
            Average1  = (RatingBar) findViewById(R.id.uhg_trash_Average);

            accessWebServiceRating();
        }

    }


    //웹서버에 접근해서 데이터들을 json으로 읽어오는 스레드~

    private class JSonReadTaskRating extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... params) {

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

            try {

                JSONObject jsonResponse1 = new JSONObject(jsonResult1);
                JSONArray jsonMainNode1 = jsonResponse1.optJSONArray("avgData");


                for (int i = 0; i < jsonMainNode1.length(); i++) {

                    JSONObject jsonChildNode1 = jsonMainNode1.getJSONObject(i);
                    String subjectavg = jsonChildNode1.optString("avg");
                    Log.d("avg",subjectavg);

                    if( i == 0 )
                        Average1.setRating( Float.parseFloat(subjectavg) );
                    else if ( i == 1 )
                        Average2.setRating( Float.parseFloat(subjectavg) );
                    else
                        Average3.setRating( Float.parseFloat(subjectavg) );

                }

            } catch (JSONException e) {
                Toast.makeText(getApplicationContext(), "Error" + e.toString(),
                        Toast.LENGTH_SHORT).show();
            }

        }
    }


    /* url이 2개일때 3개일때 스레드가 다르다. */

    public void accessWebServiceRating(){

        JSonReadTaskRating task =new JSonReadTaskRating();

        task.execute( new String[]{url});
    }


    //cyg 최경규 교수님 평판 이벤트
    public void cyg_cplusplus(View view) {

        Intent i = new Intent(this, evaluateActivity.class);
        i.putExtra("professorlecture","cyg_cplusplus");
        startActivity(i);

    }
    public void cyg_datastructure(View view) {

        Intent i = new Intent(this, evaluateActivity.class);
        i.putExtra("professorlecture","cyg_datastructure");
        startActivity(i);


    }

    public void cyg_graphic(View view) {
        Intent i = new Intent(this, evaluateActivity.class);
        i.putExtra("professorlecture","cyg_graphic");
        startActivity(i);
    }

    //jgc 정구철 교수님 평판 이벤트
    public void jgc_computerEngineerBasic(View view) {
        Intent i = new Intent(this, evaluateActivity.class);
        i.putExtra("professorlecture","jgc_computerEngineerBasic");
        startActivity(i);
    }

    public void jgc_C(View view) {
        Intent i = new Intent(this, evaluateActivity.class);
        i.putExtra("professorlecture","jgc_C");
        startActivity(i);
    }

    //jgs 장경식 교수님 평판 이벤트
    public void jgs_microprocessor(View view) {
        Intent i = new Intent(this, evaluateActivity.class);
        i.putExtra("professorlecture","jgs_microprocessor");
        startActivity(i);
    }

    public void jgs_embedded(View view) {
        Intent i = new Intent(this, evaluateActivity.class);
        i.putExtra("professorlecture","jgs_embedded");
        startActivity(i);
    }

    //kys 김윤상 교수님 평판 이벤트

    public void kys_base_electric_electron(View view) {
        Intent i = new Intent(this, evaluateActivity.class);
        i.putExtra("professorlecture","kys_base_electric_electron");
        startActivity(i);
    }

    public void kys_computer_architecture(View view) {
        Intent i = new Intent(this, evaluateActivity.class);
        i.putExtra("professorlecture","kys_computer_architecture");
        startActivity(i);
    }

    public void kys_animation(View view) {
        Intent i = new Intent(this, evaluateActivity.class);
        i.putExtra("professorlecture","kys_animation");
        startActivity(i);
    }

    //lih 이재협 교수님 평판 이벤트
    public void ljh_C(View view) {
        Intent i = new Intent(this, evaluateActivity.class);
        i.putExtra("professorlecture","ljh_C");
        startActivity(i);
    }

    public void ljh_Cplusplus(View view) {
        Intent i = new Intent(this, evaluateActivity.class);
        i.putExtra("professorlecture","ljh_Cplusplus");
        startActivity(i);
    }

    public void ljh_java(View view) {
        Intent i = new Intent(this, evaluateActivity.class);
        i.putExtra("professorlecture","ljh_java");
        startActivity(i);
    }

    //uhg 윤한경 교수님 평판 이벤트
    public void uhg_trash(View view) {
        Intent i = new Intent(this, evaluateActivity.class);
        i.putExtra("professorlecture","uhg_trash");
        startActivity(i);
    }

    public void uhg_ai(View view) {
        Intent i = new Intent(this, evaluateActivity.class);
        i.putExtra("professorlecture","uhg_ai");
        startActivity(i);
    }

}
