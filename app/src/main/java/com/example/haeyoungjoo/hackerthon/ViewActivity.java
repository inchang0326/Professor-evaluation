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
            url1 = "http://jhy753.dothome.co.kr/avgOutput01.php"; // c++
            url2 = "http://jhy753.dothome.co.kr/avgOutput02.php"; // 자료구조
            url3 = "http://jhy753.dothome.co.kr/avgOutput03.php"; // 그래픽

           /* 최영규 과목에 해당하는 평균을 내주는 RatingBar -- 평점을 계산해서 RatingBar에 뿌려준다.*/
            Average1  = (RatingBar) findViewById(R.id.cyg_cplusplus_Average);
            Average2  = (RatingBar) findViewById(R.id.cyg_datastructure_Average);
            Average3  = (RatingBar) findViewById(R.id.cyg_graphic_Average);

            accessWebService2();


        } else if (professorName.compareTo("jgc") == 0) {

            setContentView(R.layout.jgc);

            url1 = "http://jhy753.dothome.co.kr/avgOutput04.php";//컴퓨터개론
            url2 = "http://jhy753.dothome.co.kr/avgOutput05.php";//c

            Average1  = (RatingBar) findViewById(R.id.jgc_computerEngineerBasic_Average);
            Average2  = (RatingBar) findViewById(R.id.jgc_C_Average);

            accessWebService1();


        } else if (professorName.compareTo("jgs") == 0) {

            setContentView(R.layout.jgs);

            url1 = "http://jhy753.dothome.co.kr/avgOutput06.php";//마이크로프로세서
            url2 = "http://jhy753.dothome.co.kr/avgOutput07.php";//임베디드

            Average1  = (RatingBar) findViewById(R.id.jgs_microprocessor_Average);
            Average2  = (RatingBar) findViewById(R.id.jgs_embedded_Average);
            accessWebService1();

        } else if (professorName.compareTo("kys") == 0) {
            setContentView(R.layout.kys);

            url1 = "http://jhy753.dothome.co.kr/avgOutput08.php";//기초전기전자
            url2 = "http://jhy753.dothome.co.kr/avgOutput09.php";//컴퓨터구조
            url3 = "http://jhy753.dothome.co.kr/avgOutput10.php";// 애니메이션

            Average1  = (RatingBar) findViewById(R.id.kys_base_electric_electron_Average);
            Average2  = (RatingBar) findViewById(R.id.ys_computer_architecture_Average);
            Average3  = (RatingBar) findViewById(R.id.kys_animation_Average);

            accessWebService2();

        } else if (professorName.compareTo("ljh") == 0) {
            setContentView(R.layout.ljh);

            url1 = "http://jhy753.dothome.co.kr/avgOutput11.php";//c언어
            url2 = "http://jhy753.dothome.co.kr/avgOutput12.php";//c++
            url3 = "http://jhy753.dothome.co.kr/avgOutput13.php";// java

            Average1  = (RatingBar) findViewById(R.id.ljh_C_Average);
            Average2  = (RatingBar) findViewById(R.id.ljh_Cplusplus_Average);
            Average3  = (RatingBar) findViewById(R.id.ljh_java_Average);

            accessWebService2();

        } else if (professorName.compareTo("uhg") == 0) {

            setContentView(R.layout.uhg);
            url1 = "http://jhy753.dothome.co.kr/avgOutput14.php";//c언어
            url2 = "http://jhy753.dothome.co.kr/avgOutput15.php";//c++

            Average1  = (RatingBar) findViewById(R.id.uhg_ai_Average);
            Average2  = (RatingBar) findViewById(R.id.uhg_trash_Average);

            accessWebService1();

        }

    }

    //웹서버에 접근해서 데이터들을 json으로 읽어오는 스레드~
    private class JsonReadTask2 extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {

            HttpClient httpclient1 = new DefaultHttpClient();
            HttpPost httppost1 = new HttpPost(params[0]); //(params[0]에는 url 값이 들어있습! Post로 서버에 연결.

            HttpClient httpclient2 = new DefaultHttpClient();
            HttpPost httppost2 = new HttpPost(params[1]); //(params[0]에는 url 값이 들어있습! Post로 서버에 연결.


            try {

                HttpResponse response1 = httpclient1.execute(httppost1);//서버로 연결하고 그 결과 값을.
                InputStream inputStream1 = response1.getEntity().getContent();//서버에서 주는 결과를 받아올수 있는 inputStream 객체 생성.
                jsonResult1 = inputStreamToString(inputStream1).toString();//스트림을 매개변수 값으로 넣고 원래 json은 문자열이기때문에 문자열로 바꿔서 온다.

                HttpResponse response2 = httpclient2.execute(httppost2);//서버로 연결하고 그 결과 값을.
                InputStream inputStream2 = response2.getEntity().getContent();//서버에서 주는 결과를 받아올수 있는 inputStream 객체 생성.
                jsonResult2 = inputStreamToString(inputStream2).toString();//스트림을 매개변수 값으로 넣고 원래 json은 문자열이기때문에 문자열로 바꿔서 온다.

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

                JSONObject jsonResponse2 = new JSONObject(jsonResult2);
                JSONArray jsonMainNode2 = jsonResponse2.optJSONArray("avgData");

                for (int i = 0; i < jsonMainNode1.length(); i++) {

                    JSONObject jsonChildNode1 = jsonMainNode1.getJSONObject(i);
                    String subjectavg = jsonChildNode1.optString("average");
                    Log.d("avg",subjectavg);

                    Average1.setRating( Float.parseFloat(subjectavg) );

                }

                for (int i = 0; i < jsonMainNode2.length(); i++) {

                    JSONObject jsonChildNode2 = jsonMainNode2.getJSONObject(i);
                    String subjectavg = jsonChildNode2.optString("average");
                    Log.d("avg",subjectavg);

                    Average2.setRating( Float.parseFloat(subjectavg) );
                }

            } catch (JSONException e) {
                Toast.makeText(getApplicationContext(), "Error" + e.toString(),
                        Toast.LENGTH_SHORT).show();
            }

        }
    }// end async task

    private class JsonReadTask3 extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {

            HttpClient httpclient1 = new DefaultHttpClient();
            HttpPost httppost1 = new HttpPost(params[0]); //(params[0]에는 url 값이 들어있습! Post로 서버에 연결.

            HttpClient httpclient2 = new DefaultHttpClient();
            HttpPost httppost2 = new HttpPost(params[1]); //(params[0]에는 url 값이 들어있습! Post로 서버에 연결.

            HttpClient httpclient3 = new DefaultHttpClient();
            HttpPost httppost3 = new HttpPost(params[2]); //(params[0]에는 url 값이 들어있습! Post로 서버에 연결.

            try {

                HttpResponse response1 = httpclient1.execute(httppost1);//서버로 연결하고 그 결과 값을.
                InputStream inputStream1 = response1.getEntity().getContent();//서버에서 주는 결과를 받아올수 있는 inputStream 객체 생성.
                jsonResult1 = inputStreamToString(inputStream1).toString();//스트림을 매개변수 값으로 넣고 원래 json은 문자열이기때문에 문자열로 바꿔서 온다.

                HttpResponse response2 = httpclient2.execute(httppost2);//서버로 연결하고 그 결과 값을.
                InputStream inputStream2 = response2.getEntity().getContent();//서버에서 주는 결과를 받아올수 있는 inputStream 객체 생성.
                jsonResult2 = inputStreamToString(inputStream2).toString();//스트림을 매개변수 값으로 넣고 원래 json은 문자열이기때문에 문자열로 바꿔서 온다.

                HttpResponse response3 = httpclient3.execute(httppost3);//서버로 연결하고 그 결과 값을.
                InputStream inputStream3 = response3.getEntity().getContent();//서버에서 주는 결과를 받아올수 있는 inputStream 객체 생성.
                jsonResult3 = inputStreamToString(inputStream3).toString();//스트림을 매개변수 값으로 넣고 원래 json은 문자열이기때문에 문자열로 바꿔서 온다.

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

                JSONObject jsonResponse2 = new JSONObject(jsonResult2);
                JSONArray jsonMainNode2 = jsonResponse2.optJSONArray("avgData");

                JSONObject jsonResponse3 = new JSONObject(jsonResult3);
                JSONArray jsonMainNode3 = jsonResponse3.optJSONArray("avgData");

                for (int i = 0; i < jsonMainNode1.length(); i++) {

                    JSONObject jsonChildNode = jsonMainNode1.getJSONObject(i);
                    String subjectavg = jsonChildNode.optString("average");
                    Log.d("avg",subjectavg);

                    Average1.setRating( Float.parseFloat(subjectavg) );

                }

                for (int i = 0; i < jsonMainNode2.length(); i++) {

                    JSONObject jsonChildNode = jsonMainNode2.getJSONObject(i);
                    String subjectavg = jsonChildNode.optString("average");
                    Log.d("avg",subjectavg);

                    Average2.setRating( Float.parseFloat(subjectavg) );

                }

                for (int i = 0; i < jsonMainNode3.length(); i++) {

                    JSONObject jsonChildNode = jsonMainNode3.getJSONObject(i);
                    String subjectavg = jsonChildNode.optString("average");
                    Log.d("avg",subjectavg);

                    Average3.setRating( Float.parseFloat(subjectavg) );

                }

            } catch (JSONException e) {
                Toast.makeText(getApplicationContext(), "Error" + e.toString(),
                        Toast.LENGTH_SHORT).show();
            }

        }
    }// end async task

    /* url이 2개일때 3개일때 스레드가 다르다. */
    public void accessWebService1() {

        JsonReadTask2 task2 = new JsonReadTask2();
        // passes values for the urls string array
        task2.execute(new String[]{ url1,url2 });
    }

    public void accessWebService2(){

        JsonReadTask3 task3 = new JsonReadTask3();
        task3.execute(new String[]{url1, url2, url3 });
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
