package com.example.haeyoungjoo.hackerthon;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

/**
 * Modified by Kang in-Chang on 2017-04-22.
 */

public class MainActivity extends AppCompatActivity {

    private ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Log.d("MainActivity","onCreate");

        ListViewAdapter adapter = new ListViewAdapter();

        listview = (ListView) findViewById(R.id.listview1);
        listview.setAdapter(adapter);

        // Enum class used
        for(ProfessorData professorList : ProfessorData.values())
            adapter.addItem(ContextCompat.getDrawable(this, professorList.getDrawable()), professorList.toString(), professorList.getDesc());

        EditText editTextFilter = (EditText)findViewById(R.id.editTextFilter) ;
        editTextFilter.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable edit) {
                String filterText = edit.toString() ;
                if (filterText.length() > 0) {
                   listview.setFilterText(filterText) ;
                } else {
                   listview.clearTextFilter() ;
                }
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        }) ;

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView parent, View v, int position, long id) {
               Log.d("MainActivity","onItemClick");

               ListViewItem item = (ListViewItem) parent.getItemAtPosition(position) ;
               String professorName = item.getTitle();

               Intent intent = new Intent(MainActivity.this, ViewActivity.class);
               intent.putExtra("NAME", professorName);
               startActivity(intent);
            }
       }) ;
    }
}