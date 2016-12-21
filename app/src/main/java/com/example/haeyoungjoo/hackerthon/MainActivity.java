package com.example.haeyoungjoo.hackerthon;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


public class MainActivity extends AppCompatActivity {

    ListView listview ;
   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       Log.d("MainActivity","onCreate");

        setContentView(R.layout.activity_main);


       ListViewAdapter adapter;

       // Adapter 생성
       adapter = new ListViewAdapter() ;

       // 리스트뷰 참조 및 Adapter달기
       listview = (ListView) findViewById(R.id.list);
       listview.setAdapter(adapter);




       /*어댑터에다가 이미지로된 파일을 저장]한다 */
       adapter.addItem( ContextCompat.getDrawable( this, R.drawable.cyg ),"cyg" );//최영규
       adapter.addItem( ContextCompat.getDrawable(this, R.drawable.jgc),"jgc") ;//정구철
       adapter.addItem( ContextCompat.getDrawable(this, R.drawable.jgs),"jgs") ;//장경식
       adapter.addItem( ContextCompat.getDrawable(this, R.drawable.kys), "kys") ;//김윤상
       adapter.addItem( ContextCompat.getDrawable(this, R.drawable.ljh), "ljh") ;//이재협
       adapter.addItem( ContextCompat.getDrawable(this, R.drawable.uhg), "uhg") ;//윤한경
       adapter.addItem( ContextCompat.getDrawable(this, R.drawable.hanyunhee), "hyh") ;//한연희
       adapter.addItem( ContextCompat.getDrawable(this, R.drawable.jojaesu), "jjs") ;//조재수
       adapter.addItem( ContextCompat.getDrawable(this, R.drawable.joojb), "jyb") ;//주영복
       adapter.addItem( ContextCompat.getDrawable(this, R.drawable.jth), "jth") ;//조태훈
       adapter.addItem( ContextCompat.getDrawable(this, R.drawable.kanghj), "khj") ;//강형주
       adapter.addItem( ContextCompat.getDrawable(this, R.drawable.kanghwan), "lkh") ;//이강환
       adapter.addItem( ContextCompat.getDrawable(this, R.drawable.kangsw), "ksw") ;//강승우
       adapter.addItem( ContextCompat.getDrawable(this, R.drawable.kimenkung), "kek") ;//김은경
       adapter.addItem( ContextCompat.getDrawable(this, R.drawable.kimsangjin), "ksj") ;//김상진
       adapter.addItem( ContextCompat.getDrawable(this, R.drawable.kimsy), "ksy") ;//김상연
       adapter.addItem( ContextCompat.getDrawable(this, R.drawable.kimwantae), "kwt") ;//김원태
       adapter.addItem( ContextCompat.getDrawable(this, R.drawable.kwonoy), "koy") ;//권오영
       adapter.addItem( ContextCompat.getDrawable(this, R.drawable.minjk), "mjk") ;//민준기
       adapter.addItem( ContextCompat.getDrawable(this, R.drawable.mohamad), "mhmd") ;//무하마드
       adapter.addItem( ContextCompat.getDrawable(this, R.drawable.moon), "miy") ;//문일영
    //   adapter.addItem( ContextCompat.getDrawable(this, R.drawable.parksc), "psc") ;//박승철
    //   adapter.addItem( ContextCompat.getDrawable(this, R.drawable.shisuck), "shs") ;//서희석



       /* ListView 클릭시 발생하는 이벤트  - > viewActivity로 넘어 간다.*/
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView parent, View v, int position, long id) {

               Log.d("MainActivity","onItemClick");

               ListViewItem item = (ListViewItem) parent.getItemAtPosition(position) ;

               String professorName =  item.getName();

               System.out.println( professorName );

               Intent intent = new Intent(MainActivity.this, ViewActivity.class);

               intent.putExtra("NAME",professorName);

               startActivity(intent);
              //Drawable iconDrawable = item.getIcon() ;
               // TODO : use item data.
           }
       }) ;

    }

}
