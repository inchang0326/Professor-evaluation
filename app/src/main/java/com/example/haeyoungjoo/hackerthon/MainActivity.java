package com.example.haeyoungjoo.hackerthon;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {


   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       ListView listview ;
       ListViewAdapter adapter;

       // Adapter 생성
       adapter = new ListViewAdapter() ;

       // 리스트뷰 참조 및 Adapter달기
       listview = (ListView) findViewById(R.id.list);
       listview.setAdapter(adapter);

       // 첫 번째 아이템 추가.
       adapter.addItem(ContextCompat.getDrawable( this, R.drawable.cyg ),"cyg" );
       // 두 번째 아이템 추가.

       adapter.addItem(ContextCompat.getDrawable(this, R.drawable.jgc),"jgc") ;//정구철
       adapter.addItem(ContextCompat.getDrawable(this, R.drawable.jgs),"jgs") ;//장경식
       adapter.addItem(ContextCompat.getDrawable(this, R.drawable.kys), "kys") ;//김윤상
       adapter.addItem(ContextCompat.getDrawable(this, R.drawable.ljh), "ljh") ;//이재협
       adapter.addItem(ContextCompat.getDrawable(this, R.drawable.uhg), "uhg") ;//윤한경



        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView parent, View v, int position, long id) {
               // get item
               ListViewItem item = (ListViewItem) parent.getItemAtPosition(position) ;

               String s =  item.getName();

               System.out.println(s);


               Intent intent = new Intent(MainActivity.this, ViewActivity.class);
               intent.putExtra("NAME",s);

               startActivity(intent);



              //Drawable iconDrawable = item.getIcon() ;

               // TODO : use item data.
           }
       }) ;

    }

}
