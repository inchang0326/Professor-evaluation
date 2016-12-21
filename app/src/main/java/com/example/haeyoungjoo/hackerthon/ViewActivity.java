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
 */

public class ViewActivity extends AppCompatActivity {

    /*한 교수가 가르칠수 있는 최대과목이 3개밖에 없어서 RatingBar를 3개 선언 하였다*/
    RatingBar Average1;
    RatingBar Average2;
    RatingBar Average3;
    RatingBar Average4;

    /*각 교수의 각 과목당 평점을 매기기 위해서 필요한 url ( 각 교수당 최대 3개의 과목이 있다 ) */

    private String url;

    String jsonResult1; // json으로 결과값을 받기위해 필요함!.


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        Log.d("ViewActivity","onCreate");

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

        } else if (professorName.compareTo("hyh") == 0) {//한연희

            setContentView(R.layout.hanyunhee);

            url = "http://jhy753.dothome.co.kr/avgCalculater.php?professorName="+"hyh";

            Average1  = (RatingBar) findViewById(R.id.hyh_network_Average);
            Average2  = (RatingBar) findViewById(R.id.hyh_script_Average);
            Average3  = (RatingBar) findViewById(R.id.hyh_spring_Average);

            accessWebServiceRating();

        } else if (professorName.compareTo("jjs") == 0) {//조재수

            setContentView(R.layout.jojaesu);

            url = "http://jhy753.dothome.co.kr/avgCalculater.php?professorName="+"jjs";

            Average1  = (RatingBar) findViewById(R.id.jjs_cplusplus_Average);
            Average2  = (RatingBar) findViewById(R.id.jjs_digital_Average);
            Average3  = (RatingBar) findViewById(R.id.jjs_digitalsignal_Average);
            Average4 = (RatingBar)findViewById(R.id.jjs_c_Average);

            accessWebServiceRating();

        } else if (professorName.compareTo("jyb") == 0) {//주영복

            setContentView(R.layout.jyb);

            url = "http://jhy753.dothome.co.kr/avgCalculater.php?professorName="+"jyb";

            Average1  = (RatingBar) findViewById(R.id.jyb_window_Average);
            Average2  = (RatingBar) findViewById(R.id.jyb_digital_Average);
            Average3  = (RatingBar) findViewById(R.id.jyb_java_Average);
            Average4  = (RatingBar) findViewById(R.id.jyb_datastructure_Average);

            accessWebServiceRating();

        } else if (professorName.compareTo("jth") == 0) {//조태훈

            setContentView(R.layout.jth);

            url = "http://jhy753.dothome.co.kr/avgCalculater.php?professorName="+"jth";

            Average2  = (RatingBar) findViewById(R.id.jth_c_Average);
            Average1  = (RatingBar) findViewById(R.id.jth_computervision_Average);

            accessWebServiceRating();

        } else if (professorName.compareTo("khj") == 0) {//강형주

            setContentView(R.layout.kanghj);

            url = "http://jhy753.dothome.co.kr/avgCalculater.php?professorName="+"khj";

            Average1  = (RatingBar) findViewById(R.id.khj_baseelectricelectron_Average);
            Average2  = (RatingBar) findViewById(R.id.khj_c_Average);
            Average3  = (RatingBar) findViewById(R.id.khj_digital_Average);
            Average4  = (RatingBar) findViewById(R.id.khj_microprocessor_Average);

            accessWebServiceRating();

        } else if (professorName.compareTo("lkh") == 0) {//이강환

            setContentView(R.layout.leekh);

            url = "http://jhy753.dothome.co.kr/avgCalculater.php?professorName="+"ikh";

            Average1  = (RatingBar) findViewById(R.id.lkh_base_Average);
            Average2  = (RatingBar) findViewById(R.id.lkh_digital_Average);
            Average2  = (RatingBar) findViewById(R.id.lkh_digitalsystem_Average);

            accessWebServiceRating();
        } else if (professorName.compareTo("ksw") == 0) {//강승우

            setContentView(R.layout.kangsw);

            url = "http://jhy753.dothome.co.kr/avgCalculater.php?professorName="+"ksw";

            Average1  = (RatingBar) findViewById(R.id.ksw_computer_Average);
            Average2  = (RatingBar) findViewById(R.id.ksw_mobile_Average);
            Average3  = (RatingBar) findViewById(R.id.ksw_mobilesystem_Average);
            Average4  = (RatingBar) findViewById(R.id.ksw_script);

            accessWebServiceRating();

        } else if (professorName.compareTo("kek") == 0) {//김은경

            setContentView(R.layout.kimek);

            url = "http://jhy753.dothome.co.kr/avgCalculater.php?professorName="+"kek";

            Average1  = (RatingBar) findViewById(R.id.kek_creative_Average);
            Average2  = (RatingBar) findViewById(R.id.kek_database_Average);

            accessWebServiceRating();

        } else if (professorName.compareTo("ksj") == 0) {//김상진

            setContentView(R.layout.kimsj);

            url = "http://jhy753.dothome.co.kr/avgCalculater.php?professorName="+"ksj";

            Average1  = (RatingBar) findViewById(R.id.ksj_java_Average);
            Average2  = (RatingBar) findViewById(R.id.ksj_object_Average);
            Average3  = (RatingBar) findViewById(R.id.ksj_protect_Average);

            accessWebServiceRating();

        } else if (professorName.compareTo("ksy") == 0) {//김상연

            setContentView(R.layout.kimsy);

            url = "http://jhy753.dothome.co.kr/avgCalculater.php?professorName="+"ksy";

            Average1  = (RatingBar) findViewById(R.id.ksy_microprocessor_Average);
            Average2  = (RatingBar) findViewById(R.id.ksy_smart_Average);

            accessWebServiceRating();

        } else if (professorName.compareTo("kwt") == 0) {//김원태

            setContentView(R.layout.kimwt);

            url = "http://jhy753.dothome.co.kr/avgCalculater.php?professorName="+"kwt";

            Average1  = (RatingBar) findViewById(R.id.kwt_base_Average);
            Average2  = (RatingBar) findViewById(R.id.kwt_data_Average);
            Average3  = (RatingBar) findViewById(R.id.kwt_network_Average);
            Average4  = (RatingBar) findViewById(R.id.kwt_smart_Average);

            accessWebServiceRating();
        } else if (professorName.compareTo("koy") == 0) {//권오영

            setContentView(R.layout.kwonoy);

            url = "http://jhy753.dothome.co.kr/avgCalculater.php?professorName="+"koy";

            Average1 = (RatingBar) findViewById(R.id.koy_compile_Average);
            Average2  = (RatingBar) findViewById(R.id.koy_embeded_Average);
            Average3  = (RatingBar) findViewById(R.id.koy_operation_Average);
            Average4  = (RatingBar) findViewById(R.id.koy_script_Average);

            accessWebServiceRating();

        } else if (professorName.compareTo("mjk") == 0) {//민준기

            setContentView(R.layout.minjk);

            url = "http://jhy753.dothome.co.kr/avgCalculater.php?professorName="+"mjk";

            Average1  = (RatingBar) findViewById(R.id.mjk_database_Average);
            Average2  = (RatingBar) findViewById(R.id.mjk_software_Average);

            accessWebServiceRating();
        } else if (professorName.compareTo("mhmd") == 0) {//무하마드

            setContentView(R.layout.mohamad);

            url = "http://jhy753.dothome.co.kr/avgCalculater.php?professorName="+"mhmd";

            Average1  = (RatingBar) findViewById(R.id.mhmd_algo_Average);
            Average2  = (RatingBar) findViewById(R.id.mhmd_databasepro_Average);
            Average3  = (RatingBar) findViewById(R.id.mhmd_datastructure_Average);
            Average4  = (RatingBar) findViewById(R.id.mhmd_java_Average);

            accessWebServiceRating();
        } else if (professorName.compareTo("miy") == 0) {//문일영

            setContentView(R.layout.mooniy);

            url = "http://jhy753.dothome.co.kr/avgCalculater.php?professorName="+"miy";

            Average1 = (RatingBar) findViewById(R.id.miy_web_Average);
            Average2  = (RatingBar) findViewById(R.id.miy_advancedweb_Average);
            Average3  = (RatingBar) findViewById(R.id.miy_mobile_Average);

            accessWebServiceRating();

        } else if (professorName.compareTo("psc") == 0) {//박승철

            setContentView(R.layout.parksc);

            url = "http://jhy753.dothome.co.kr/avgCalculater.php?professorName="+"psc";

            Average1  = (RatingBar) findViewById(R.id.psc_smartnetwork_Average);

            accessWebServiceRating();
        }
        else if (professorName.compareTo("shs") == 0) {//서희석

            setContentView(R.layout.seohs);

            url = "http://jhy753.dothome.co.kr/avgCalculater.php?professorName="+"shs";

            Average1  = (RatingBar) findViewById(R.id.shs_operation_Average);
            Average2  = (RatingBar) findViewById(R.id.shs_system_Average);

            accessWebServiceRating();
        }


    }

    //웹서버에 접근해서 데이터들을 json으로 읽어오는 스레드~

    private class JSonReadTaskRating extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... params) {

            Log.d("ViewActivity","doInBackground");

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

            Log.d("ViewActivity","inputStreamToString");

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

            Log.d("ViewActivity","onPostExecute");

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
                    else if( i == 2 )
                        Average3.setRating( Float.parseFloat(subjectavg) );
                    else
                        Average4.setRating( Float.parseFloat(subjectavg) );
                }

            } catch (JSONException e) {
                Toast.makeText(getApplicationContext(), "Error" + e.toString(),
                        Toast.LENGTH_SHORT).show();
            }

        }
    }


    /* url이 2개일때 3개일때 스레드가 다르다. */

    public void accessWebServiceRating(){

        Log.d("ViewActivity","accessWebServiceRating");

        JSonReadTaskRating task =new JSonReadTaskRating();

        task.execute( new String[]{url});
    }


    //cyg 최경규 교수님 평판 이벤트
    public void cyg_cplusplus(View view) {

        Log.d("ViewActivity","cyg_cplusplus");

        Intent i = new Intent(this, evaluateActivity.class);
        i.putExtra("professorlecture","cyg_cplusplus");
        startActivity(i);
        finish();

    }
    public void cyg_datastructure(View view) {

        Log.d("ViewActivity","cyg_datastructure");

        Intent i = new Intent(this, evaluateActivity.class);
        i.putExtra("professorlecture","cyg_datastructure");
        startActivity(i);
        finish();

    }

    public void cyg_graphic(View view) {

        Log.d("ViewActivity","cyg_graphic");
        Intent i = new Intent(this, evaluateActivity.class);
        i.putExtra("professorlecture","cyg_graphic");
        startActivity(i);
        finish();
    }

    //jgc 정구철 교수님 평판 이벤트
    public void jgc_computerEngineerBasic(View view) {

        Log.d("ViewActivity","jgc_computerEngineerBasic");
        Intent i = new Intent(this, evaluateActivity.class);
        i.putExtra("professorlecture","jgc_computerEngineerBasic");
        startActivity(i);
        finish();
    }

    public void jgc_C(View view) {

        Log.d("ViewActivity","jgc_C");
        Intent i = new Intent(this, evaluateActivity.class);
        i.putExtra("professorlecture","jgc_C");
        startActivity(i);
        finish();
    }

    //jgs 장경식 교수님 평판 이벤트
    public void jgs_microprocessor(View view) {

        Log.d("ViewActivity","jgs_microprocessor");
        Intent i = new Intent(this, evaluateActivity.class);
        i.putExtra("professorlecture","jgs_microprocessor");
        startActivity(i);
        finish();
    }

    public void jgs_embedded(View view) {

        Log.d("ViewActivity","jgs_embedded");
        Intent i = new Intent(this, evaluateActivity.class);
        i.putExtra("professorlecture","jgs_embedded");
        startActivity(i);
        finish();
    }

    //kys 김윤상 교수님 평판 이벤트

    public void kys_base_electric_electron(View view) {

        Log.d("ViewActivity","kys_base_electric_electron");
        Intent i = new Intent(this, evaluateActivity.class);
        i.putExtra("professorlecture","kys_base_electric_electron");
        startActivity(i);
        finish();
    }

    public void kys_computer_architecture(View view) {

        Log.d("ViewActivity","kys_computer_architecture");
        Intent i = new Intent(this, evaluateActivity.class);
        i.putExtra("professorlecture","kys_computer_architecture");
        startActivity(i);
        finish();
    }

    public void kys_animation(View view) {

        Log.d("ViewActivity","kys_animation");
        Intent i = new Intent(this, evaluateActivity.class);
        i.putExtra("professorlecture","kys_animation");
        startActivity(i);
        finish();
    }

    //lih 이재협 교수님 평판 이벤트
    public void ljh_C(View view) {

        Log.d("ViewActivity","ljh_C");
        Intent i = new Intent(this, evaluateActivity.class);
        i.putExtra("professorlecture","ljh_C");
        startActivity(i);
        finish();
    }

    public void ljh_Cplusplus(View view) {

        Log.d("ViewActivity","ljh_Cplusplus");
        Intent i = new Intent(this, evaluateActivity.class);
        i.putExtra("professorlecture","ljh_Cplusplus");
        startActivity(i);
        finish();
    }

    public void ljh_java(View view) {

        Log.d("ViewActivity","ljh_java");
        Intent i = new Intent(this, evaluateActivity.class);
        i.putExtra("professorlecture","ljh_java");
        startActivity(i);
        finish();
    }

    //uhg 윤한경 교수님 평판 이벤트
    public void uhg_trash(View view) {

        Log.d("ViewActivity","uhg_trash");
        Intent i = new Intent(this, evaluateActivity.class);
        i.putExtra("professorlecture","uhg_trash");
        startActivity(i);
        finish();
    }

    public void uhg_ai(View view) {

        Log.d("ViewActivity","uhg_ai");
        Intent i = new Intent(this, evaluateActivity.class);
        i.putExtra("professorlecture","uhg_ai");
        startActivity(i);
        finish();
    }

    //한연희
    public void hyh_network(View view){

        Log.d("ViewActivity","hyh_network");
        Intent i = new Intent(this, evaluateActivity.class);
        i.putExtra("professorlecture","hyh_network");
        startActivity(i);
        finish();
    }
    public void hyh_script(View view){
        Log.d("ViewActivity","hyh_script");
        Intent i = new Intent(this, evaluateActivity.class);
        i.putExtra("professorlecture","hyh_script");
        startActivity(i);
        finish();

    }
    public void hyh_spring(View view){
        Log.d("ViewActivity","hyh_spring");
        Intent i = new Intent(this, evaluateActivity.class);
        i.putExtra("professorlecture","hyh_spring");
        startActivity(i);
        finish();
    }

    //조재수

    public void jjs_cplusplus(View view){

        Log.d("ViewActivity","jjs_cplusplus");
        Intent i = new Intent(this, evaluateActivity.class);
        i.putExtra("professorlecture","jjs_cplusplus");
        startActivity(i);
        finish();
    }
    public void jsj_digitall(View view){

        Log.d("ViewActivity","jjs_digital");
        Intent i = new Intent(this, evaluateActivity.class);
        i.putExtra("professorlecture","jjs_digital");
        startActivity(i);
        finish();
    }
    public void jjs_digitalsignal(View view){
        Log.d("ViewActivity","jjs_digitalsignal");
        Intent i = new Intent(this, evaluateActivity.class);
        i.putExtra("professorlecture","jjs_digitalsignal");
        startActivity(i);
        finish();

    }
    public void jjs_c(View view){
        Log.d("ViewActivity","jjs_c");
        Intent i = new Intent(this, evaluateActivity.class);
        i.putExtra("professorlecture","jjs_c");
        startActivity(i);
        finish();

    }



    //주영복 교수님
    public void jyb_window(View view){
        Log.d("ViewActivity","jyb_window");
        Intent i = new Intent(this, evaluateActivity.class);
        i.putExtra("professorlecture","jyb_window");
        startActivity(i);
        finish();

    }
    public void jyb_digital(View view){
        Log.d("ViewActivity","jyb_digital");
        Intent i = new Intent(this, evaluateActivity.class);
        i.putExtra("professorlecture","jyb_digital");
        startActivity(i);
        finish();

    }
    public void jyb_java(View view){
        Log.d("ViewActivity","jyb_java");
        Intent i = new Intent(this, evaluateActivity.class);
        i.putExtra("professorlecture","jyb_java");
        startActivity(i);
        finish();

    }
    public void jyb_datastructure(View view){
        Log.d("ViewActivity","jyb_datastructure");
        Intent i = new Intent(this, evaluateActivity.class);
        i.putExtra("professorlecture","jyb_datastructure");
        startActivity(i);
        finish();

    }

    //조태훈
    public void jth_c(View view){
        Log.d("ViewActivity","jth_c");
        Intent i = new Intent(this, evaluateActivity.class);
        i.putExtra("professorlecture","jth_c");
        startActivity(i);
        finish();

    }
    public void jth_computervision(View view){
        Log.d("ViewActivity","jth_computervision");
        Intent i = new Intent(this, evaluateActivity.class);
        i.putExtra("professorlecture","jth_computervision");
        startActivity(i);
        finish();
    }

    //강형주
    public void khj_c(View view){
        Log.d("ViewActivity","khj_c");
        Intent i = new Intent(this, evaluateActivity.class);
        i.putExtra("professorlecture","khj_c");
        startActivity(i);
        finish();
    }
    public void khj_baseelectricelectron(View view){
        Log.d("ViewActivity","khj_baseelectricelectron");
        Intent i = new Intent(this, evaluateActivity.class);
        i.putExtra("professorlecture","khj_baseelectricelectron");
        startActivity(i);
        finish();
    }
    public void khj_digital(View view){
        Log.d("ViewActivity","khj_digital");
        Intent i = new Intent(this, evaluateActivity.class);
        i.putExtra("professorlecture","khj_digital");
        startActivity(i);
        finish();
    }
    public void khj_microprocessor(View view){
        Log.d("ViewActivity","khj_microprocessor");
        Intent i = new Intent(this, evaluateActivity.class);
        i.putExtra("professorlecture","khj_microprocessor");
        startActivity(i);
        finish();
    }

    //이강환
    public void lkh_base(View view){
        Log.d("ViewActivity","lkh_base");
        Intent i = new Intent(this, evaluateActivity.class);
        i.putExtra("professorlecture","lkh_base");
        startActivity(i);
        finish();
    }
    public void ikh_digital(View view){
        Log.d("ViewActivity","ikh_digital");
        Intent i = new Intent(this, evaluateActivity.class);
        i.putExtra("professorlecture","lkh_digital");
        startActivity(i);
        finish();
    }
    public void ikh_digitalsystem(View view){
        Log.d("ViewActivity","ikh_digitalsystem");
        Intent i = new Intent(this, evaluateActivity.class);
        i.putExtra("professorlecture","lkh_digitalsystem");
        startActivity(i);
        finish();
    }

    //강승우
    public void ksw_computer(View view){
        Log.d("ViewActivity","ksw_computer");
        Intent i = new Intent(this, evaluateActivity.class);
        i.putExtra("professorlecture","ksw_computer");
        startActivity(i);
        finish();
    }
    public void ksw_mobile(View view){
        Log.d("ViewActivity","ksw_mobile");
        Intent i = new Intent(this, evaluateActivity.class);
        i.putExtra("professorlecture","ksw_mobile");
        startActivity(i);
        finish();
    }
    public void ksw_mobilesystem(View view){
        Log.d("ViewActivity","ksw_mobilesystem");
        Intent i = new Intent(this, evaluateActivity.class);
        i.putExtra("professorlecture","ksw_mobilesystem");
        startActivity(i);
        finish();
    }

    //김은경
    public void kek_creative(View view){
        Log.d("ViewActivity","kek_creative");
        Intent i = new Intent(this, evaluateActivity.class);
        i.putExtra("professorlecture","kek_creative");
        startActivity(i);
        finish();
    }
    public void kek_database(View view){
        Log.d("ViewActivity","kek_database");
        Intent i = new Intent(this, evaluateActivity.class);
        i.putExtra("professorlecture","kek_database");
        startActivity(i);
        finish();
    }

    //김상진
    public void ksj_java(View view){
        Log.d("ViewActivity","ksj_java");
        Intent i = new Intent(this, evaluateActivity.class);
        i.putExtra("professorlecture","ksj_java");
        startActivity(i);
        finish();
    }
    public void ksj_object(View view){
        Log.d("ViewActivity","ksj_object");
        Intent i = new Intent(this, evaluateActivity.class);
        i.putExtra("professorlecture","ksj_object");
        startActivity(i);
        finish();
    }
    public void ksj_protect(View view){
        Log.d("ViewActivity","ksj_protect");
        Intent i = new Intent(this, evaluateActivity.class);
        i.putExtra("professorlecture","ksj_protect");
        startActivity(i);
        finish();
    }

    //김상연
    public void ksy_microprocessor(View view){
        Log.d("ViewActivity","ksj_object");
        Intent i = new Intent(this, evaluateActivity.class);
        i.putExtra("professorlecture","ksj_object");
        startActivity(i);
        finish();
    }
    public void ksy_smart(View view){
        Log.d("ViewActivity","ksj_protect");
        Intent i = new Intent(this, evaluateActivity.class);
        i.putExtra("professorlecture","ksj_protect");
        startActivity(i);
        finish();
    }

    //김원태

    public void kwt_base(View view){
        Log.d("ViewActivity","kwt_base");
        Intent i = new Intent(this, evaluateActivity.class);
        i.putExtra("professorlecture","kwt_base");
        startActivity(i);
        finish();
    }
    public void kwt_network(View view){
        Log.d("ViewActivity","kwt_network");
        Intent i = new Intent(this, evaluateActivity.class);
        i.putExtra("professorlecture","kwt_network");
        startActivity(i);
        finish();
    }
    public void kwt_data(View view){
        Log.d("ViewActivity","kwt_data");
        Intent i = new Intent(this, evaluateActivity.class);
        i.putExtra("professorlecture","kwt_data");
        startActivity(i);
        finish();
    }
    public void kwt_smart(View view){
        Log.d("ViewActivity","kwt_smart");
        Intent i = new Intent(this, evaluateActivity.class);
        i.putExtra("professorlecture","kwt_smart");
        startActivity(i);
        finish();
    }

    //권오영

    public void koy_operation(View view){
        Log.d("ViewActivity","koy_operation");
        Intent i = new Intent(this, evaluateActivity.class);
        i.putExtra("professorlecture","koy_operation");
        startActivity(i);
        finish();
    }
    public void koy_compile(View view){
        Log.d("ViewActivity","koy_compile");
        Intent i = new Intent(this, evaluateActivity.class);
        i.putExtra("professorlecture","koy_compile");
        startActivity(i);
        finish();
    }
    public void koy_embeded(View view){
        Log.d("ViewActivity","koy_embeded");
        Intent i = new Intent(this, evaluateActivity.class);
        i.putExtra("professorlecture","koy_embeded");
        startActivity(i);
        finish();
    }
    public void koy_script(View view){
        Log.d("ViewActivity","koy_script");
        Intent i = new Intent(this, evaluateActivity.class);
        i.putExtra("professorlecture","koy_script");
        startActivity(i);
        finish();
    }

    //민준기

    public void mjk_database(View view){
        Log.d("ViewActivity","mjk_database");
        Intent i = new Intent(this, evaluateActivity.class);
        i.putExtra("professorlecture","mjk_database");
        startActivity(i);
        finish();
    }
    public void mjk_software(View view){
        Log.d("ViewActivity","mjk_software");
        Intent i = new Intent(this, evaluateActivity.class);
        i.putExtra("professorlecture","mjk_software");
        startActivity(i);
        finish();
    }

    //무하마드
    public void mhmd_java(View view){
        Log.d("ViewActivity","mhmd_java");
        Intent i = new Intent(this, evaluateActivity.class);
        i.putExtra("professorlecture","mhmd_java");
        startActivity(i);
        finish();
    }
    public void mhmd_datastructure(View view){
        Log.d("ViewActivity","mhmd_datastructure");
        Intent i = new Intent(this, evaluateActivity.class);
        i.putExtra("professorlecture","mhmd_datastructure");
        startActivity(i);
        finish();
    }
    public void mhmd_algo(View view){
        Log.d("ViewActivity","mhmd_algo");
        Intent i = new Intent(this, evaluateActivity.class);
        i.putExtra("professorlecture","mhmd_algo");
        startActivity(i);
        finish();
    }
    public void mhmd_databasepro(View view){
        Log.d("ViewActivity","mhmd_databasepro");
        Intent i = new Intent(this, evaluateActivity.class);
        i.putExtra("professorlecture","mhmd_databasepro");
        startActivity(i);
        finish();
    }

    //문일영
    public void miy_web(View view){
        Log.d("ViewActivity","miy_web");
        Intent i = new Intent(this, evaluateActivity.class);
        i.putExtra("professorlecture","miy_web");
        startActivity(i);
        finish();
    }
    public void miy_mobile(View view){
        Log.d("ViewActivity","miy_mobile");
        Intent i = new Intent(this, evaluateActivity.class);
        i.putExtra("professorlecture","miy_mobile");
        startActivity(i);
        finish();
    }
    public void miy_advancedweb(View view){
        Log.d("ViewActivity","miy_advancedweb");
        Intent i = new Intent(this, evaluateActivity.class);
        i.putExtra("professorlecture","miy_advancedweb");
        startActivity(i);
        finish();
    }

    //박승철
    public void psc_smartnetwork(View view){
        Log.d("ViewActivity","psc_smartnetwork");
        Intent i = new Intent(this, evaluateActivity.class);
        i.putExtra("professorlecture","psc_smartnetwork");
        startActivity(i);
        finish();
    }

    //서희석
    public void shs_operation(View view){
        Log.d("ViewActivity","shs_operation");
        Intent i = new Intent(this, evaluateActivity.class);
        i.putExtra("professorlecture","shs_operation");
        startActivity(i);
        finish();
    }
    public void shs_system(View view){
        Log.d("ViewActivity","shs_system");
        Intent i = new Intent(this, evaluateActivity.class);
        i.putExtra("professorlecture","shs_system");
        startActivity(i);
        finish();
    }











    public void onClickCall(View view){

        Intent intent= null;

        switch ( view.getId() ){

            case R.id.cygcall:
                intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:(+82)0415601353") );
                break;
            case R.id.jgccall:
                intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:(+82)0415601181") );
                break;
            case R.id.jgscall:
                intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:(+82)0415601352") );
                break;
            case R.id.kyscall:
                intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:(+82)0415601496") );
                break;
            case R.id.ljhcall:
                intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:(+82)0415601184") );
                break;
            case R.id.uhgcall:
                intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:(+82)0415601182") );
                break;

        }

        if(intent != null ){
            if(intent.resolveActivity(getPackageManager()) != null){
                startActivity(intent);
            }
        }
    }

    public void onClickMail(View view){

        Intent intent= null;

        switch ( view.getId() ){

            case R.id.cygmail:
                intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:ykchoi@koreatech.ac.kr"));
                break;
            case R.id.jgcmail:
                intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:jeong@koreatech.ac.kr") );
                break;
            case R.id.jgsmail:
                intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:ksjang@koreatech.ac.kr") );
                break;
            case R.id.kysmail:
                intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:yoonsang@koreatech.ac.kr") );
                break;
            case R.id.ljhmail:
                intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:jae@koreatech.ac.kr") );
                break;
            case R.id.uhgmail:
                intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:hkyun@koreatech.ac.kr") );
                break;
        }

        if(intent != null ){
            if(intent.resolveActivity(getPackageManager()) != null){
                startActivity(intent);
            }
        }
    }
}
