package com.example.haeyoungjoo.hackerthon;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RatingBar;

/**
 * Created by hae young Joo on 2016-11-12.
 */

public class ViewActivity extends AppCompatActivity {

    RatingBar Average1;
    RatingBar Average2;
    RatingBar Average3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        String professorName = intent.getExtras().getString("NAME");


        System.out.println(professorName + "2");

        if (professorName.compareTo("cyg") == 0) {
            setContentView(R.layout.cyg);
            Average1  = (RatingBar) findViewById(R.id.cyg_cplusplus_Average);
            Average2  = (RatingBar) findViewById(R.id.cyg_datastructure_Average);
            Average3  = (RatingBar) findViewById(R.id.cyg_graphic_Average);


            Average1.setRating( (float)4 );  // 처음보여줄때(색깔이 한개도없음) default 값이 0  이다
            Average1.setIsIndicator(true);//true -별점만 표시 사용자가 변경 불가 , false - 사용자가 변경가능



            Average2.setRating((float) 4);  // 처음보여줄때(색깔이 한개도없음) default 값이 0  이다
            Average2.setIsIndicator(true);//true -별점만 표시 사용자가 변경 불가 , false - 사용자가 변경가능



            Average3.setRating((float) 3.5);  // 처음보여줄때(색깔이 한개도없음) default 값이 0  이다
            Average3.setIsIndicator(true);//true -별점만 표시 사용자가 변경 불가 , false - 사용자가 변경가능


        } else if (professorName.compareTo("jgc") == 0) {

            setContentView(R.layout.jgc);
            Average1  = (RatingBar) findViewById(R.id.jgc_computerEngineerBasic_Average);
            Average2  = (RatingBar) findViewById(R.id.jgc_C_Average);

            Average1.setRating((float) 3);  // 처음보여줄때(색깔이 한개도없음) default 값이 0  이다
            Average1.setIsIndicator(true);//true -별점만 표시 사용자가 변경 불가 , false - 사용자가 변경가능

            Average2.setRating((float) 5);  // 처음보여줄때(색깔이 한개도없음) default 값이 0  이다
            Average2.setIsIndicator(true);//true -별점만 표시 사용자가 변경 불가 , false - 사용자가 변경가능



        } else if (professorName.compareTo("jgs") == 0) {
            setContentView(R.layout.jgs);
            Average1  = (RatingBar) findViewById(R.id.jgs_microprocessor_Average);
            Average2  = (RatingBar) findViewById(R.id.jgs_embedded_Average);

            Average1.setRating((float) 2);  // 처음보여줄때(색깔이 한개도없음) default 값이 0  이다
            Average1.setIsIndicator(true);//true -별점만 표시 사용자가 변경 불가 , false - 사용자가 변경가능

            Average2.setRating((float) 1);  // 처음보여줄때(색깔이 한개도없음) default 값이 0  이다
            Average2.setIsIndicator(true);//true -별점만 표시 사용자가 변경 불가 , false - 사용자가 변경가능



        } else if (professorName.compareTo("kys") == 0) {
            setContentView(R.layout.kys);
            Average1  = (RatingBar) findViewById(R.id.kys_base_electric_electron_Average);
            Average2  = (RatingBar) findViewById(R.id.ys_computer_architecture_Average);
            Average3  = (RatingBar) findViewById(R.id.kys_animation_Average);

            //여기에다가 데이터베이스에서 평균을 낸 값을 가지고 온다.
            Average1.setRating((float) 0);  // 처음보여줄때(색깔이 한개도없음) default 값이 0  이다
            Average1.setIsIndicator(true);//true -별점만 표시 사용자가 변경 불가 , false - 사용자가 변경가능

            Average2.setRating((float) 0);  // 처음보여줄때(색깔이 한개도없음) default 값이 0  이다
            Average2.setIsIndicator(true);//true -별점만 표시 사용자가 변경 불가 , false - 사용자가 변경가능

            Average3.setRating((float) 0);  // 처음보여줄때(색깔이 한개도없음) default 값이 0  이다
            Average3.setIsIndicator(true);//true -별점만 표시 사용자가 변경 불가 , false - 사용자가 변경가능

        } else if (professorName.compareTo("ljh") == 0) {
            setContentView(R.layout.ljh);
            Average1  = (RatingBar) findViewById(R.id.ljh_C_Average);
            Average2  = (RatingBar) findViewById(R.id.ljh_Cplusplus_Average);
            Average3  = (RatingBar) findViewById(R.id.ljh_java_Average);

            //여기에다가 데이터베이스에서 평균을 낸 값을 가지고 온다.
            Average1.setRating((float) 5);  // 처음보여줄때(색깔이 한개도없음) default 값이 0  이다
            Average1.setIsIndicator(true);//true -별점만 표시 사용자가 변경 불가 , false - 사용자가 변경가능

            Average2.setRating((float) 5);  // 처음보여줄때(색깔이 한개도없음) default 값이 0  이다
            Average2.setIsIndicator(true);//true -별점만 표시 사용자가 변경 불가 , false - 사용자가 변경가능

            Average3.setRating((float) 5);  // 처음보여줄때(색깔이 한개도없음) default 값이 0  이다
            Average3.setIsIndicator(true);//true -별점만 표시 사용자가 변경 불가 , false - 사용자가 변경가능

        } else if (professorName.compareTo("uhg") == 0) {
            setContentView(R.layout.uhg);

            Average1  = (RatingBar) findViewById(R.id.uhg_trash_Average);
            Average2  = (RatingBar) findViewById(R.id.uhg_ai_Average);

            Average1.setRating((float) 0);  // 처음보여줄때(색깔이 한개도없음) default 값이 0  이다
            Average1.setIsIndicator(true);//true -별점만 표시 사용자가 변경 불가 , false - 사용자가 변경가능

            Average2.setRating((float) 1);  // 처음보여줄때(색깔이 한개도없음) default 값이 0  이다
            Average2.setIsIndicator(true);//true -별점만 표시 사용자가 변경 불가 , false - 사용자가 변경가능
        }

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
