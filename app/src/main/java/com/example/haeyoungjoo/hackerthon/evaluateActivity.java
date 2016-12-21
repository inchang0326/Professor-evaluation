package com.example.haeyoungjoo.hackerthon;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
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
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class evaluateActivity extends Activity {

    RatingBar rating; //별점 등록할때 쓰는 View
    TextView tv01;// RatingBar에 있는 별점을 클릭했을때 그 숫자값을 TextView에 나타냄.

    static float ratevalue;//매긴 별점 값

    private EditText m_EditText_comment;//댓글을 쓸때 사용하는 EditText

    private String url; //웹서버 통신을 위해서 사용하는 url
    private String jsonResult; //댓글을 웹 서버에 저장하고, 웹서버에를 통해 댓글을 받아온다
    private ListView listView;//리스트뷰를 통해 댓글을 뿌려준다.

    Intent i;
    static String lecture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Log.d("evaluateActivity","onCreate()");

        setContentView(R.layout.evaluate);
        listView = (ListView) findViewById(R.id.list);
        i = getIntent(); // 해당교수 강의이름을 받아옴.
        lecture = i.getExtras().getString("professorlecture");

        url = "http://jhy753.dothome.co.kr/commentList.php?lecture=" + lecture;

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
    protected void onPause() {

        super.onPause();
        Log.d("evaluateActivity","onPause()");

        Intent hi = new Intent(evaluateActivity.this, ViewActivity.class);

        if (lecture.compareTo("cyg_cplusplus") == 0) {
            hi.putExtra("NAME", "최영규");
            startActivity(hi);

        } else if (lecture.compareTo("cyg_datastructure") == 0) {

            hi.putExtra("NAME", "최영규");
            startActivity(hi);

        } else if (lecture.compareTo("cyg_graphic") == 0) {
            hi.putExtra("NAME", "최영규");
            startActivity(hi);
        } else if (lecture.compareTo("jgc_computerEngineerBasic") == 0) {
            hi.putExtra("NAME", "정구철");
            startActivity(hi);
        } else if (lecture.compareTo("jgc_C") == 0) {
            hi.putExtra("NAME", "정구철");
            startActivity(hi);
        } else if (lecture.compareTo("jgs_microprocessor") == 0) {
            hi.putExtra("NAME", "장경식");
            startActivity(hi);
        } else if (lecture.compareTo("jgs_embedded") == 0) {
            hi.putExtra("NAME", "장경식");
            startActivity(hi);
        } else if (lecture.compareTo("kys_base_electric_electron") == 0) {
            hi.putExtra("NAME", "김윤상");
            startActivity(hi);
        } else if (lecture.compareTo("kys_computer_architecture") == 0) {
            hi.putExtra("NAME", "김윤상");
            startActivity(hi);
        } else if (lecture.compareTo("kys_animation") == 0) {
            hi.putExtra("NAME", "김윤상");
            startActivity(hi);
        } else if (lecture.compareTo("ljh_C") == 0) {
            hi.putExtra("NAME", "이재협");
            startActivity(hi);
        } else if (lecture.compareTo("ljh_Cplusplus") == 0) {
            hi.putExtra("NAME", "이재협");
            startActivity(hi);
        } else if (lecture.compareTo("ljh_java") == 0) {
            hi.putExtra("NAME", "이재협");
            startActivity(hi);
        } else if (lecture.compareTo("uhg_trash") == 0) {
            hi.putExtra("NAME", "윤한경");
            startActivity(hi);
        } else if (lecture.compareTo("uhg_ai") == 0) {
            hi.putExtra("NAME", "윤한경");
            startActivity(hi);
        } else if (lecture.compareTo("hyh_network") == 0) {
            hi.putExtra("NAME", "한연희");
            startActivity(hi);
        } else if (lecture.compareTo("hyh_script") == 0) {
            hi.putExtra("NAME", "한연희");
            startActivity(hi);
        } else if (lecture.compareTo("hyh_spring") == 0) {
            hi.putExtra("NAME", "한연희");
            startActivity(hi);
        } else if (lecture.compareTo("jjs_cplusplus") == 0) {
            hi.putExtra("NAME", "조재수");
            startActivity(hi);
        } else if (lecture.compareTo("jjs_digital") == 0) {
            hi.putExtra("NAME", "조재수");
            startActivity(hi);
        } else if (lecture.compareTo("jjs_digitalsignal") == 0) {
            hi.putExtra("NAME", "조재수");
            startActivity(hi);
        } else if (lecture.compareTo("jjs_c") == 0) {
            hi.putExtra("NAME", "조재수");
            startActivity(hi);
        } else if (lecture.compareTo("jyb_window") == 0) {
            hi.putExtra("NAME", "주영복");
            startActivity(hi);
        } else if (lecture.compareTo("jyb_digital") == 0) {
            hi.putExtra("NAME", "주영복");
            startActivity(hi);
        } else if (lecture.compareTo("jyb_java") == 0) {
            hi.putExtra("NAME", "주영복");
            startActivity(hi);
        } else if (lecture.compareTo("jyb_datastructure") == 0) {
            hi.putExtra("NAME", "주영복");
            startActivity(hi);
        } else if (lecture.compareTo("jth_c") == 0) {
            hi.putExtra("NAME", "조태훈");
            startActivity(hi);
        } else if (lecture.compareTo("jth_computervision") == 0) {
            hi.putExtra("NAME", "조태훈");
            startActivity(hi);
        } else if (lecture.compareTo("khj_c") == 0) {
            hi.putExtra("NAME", "강형주");
            startActivity(hi);
        } else if (lecture.compareTo("khj_baseelectricelectron") == 0) {
            hi.putExtra("NAME", "강형주");
            startActivity(hi);
        } else if (lecture.compareTo("khj_digital") == 0) {
            hi.putExtra("NAME", "강형주");
            startActivity(hi);
        } else if (lecture.compareTo("khj_microprocessor") == 0) {
            hi.putExtra("NAME", "강형주");
            startActivity(hi);
        } else if (lecture.compareTo("lkh_base") == 0) {
            hi.putExtra("NAME", "이강환");
            startActivity(hi);
        } else if (lecture.compareTo("lkh_digital") == 0) {
            hi.putExtra("NAME", "이강환");
            startActivity(hi);
        } else if (lecture.compareTo("lkh_digitalsystem") == 0) {
            hi.putExtra("NAME", "이강환");
            startActivity(hi);
        } else if (lecture.compareTo("ksw_computer") == 0) {
            hi.putExtra("NAME", "강승우");
            startActivity(hi);
        } else if (lecture.compareTo("ksw_mobile") == 0) {
            hi.putExtra("NAME", "강승우");
            startActivity(hi);
        } else if (lecture.compareTo("ksw_mobilesystem") == 0) {
            hi.putExtra("NAME", "강승우");
            startActivity(hi);

        }else if (lecture.compareTo("ksw_script") == 0) {
            hi.putExtra("NAME", "강승우");
            startActivity(hi);
        }
        else if (lecture.compareTo("kek_creative") == 0) {
            hi.putExtra("NAME", "김은경");
            startActivity(hi);
        } else if (lecture.compareTo("kek_database") == 0) {
            hi.putExtra("NAME", "김은경");
            startActivity(hi);
        } else if (lecture.compareTo("ksj_java") == 0) {
            hi.putExtra("NAME", "김상진");
            startActivity(hi);
        } else if (lecture.compareTo("ksj_object") == 0) {
            hi.putExtra("NAME", "김상진");
            startActivity(hi);
        } else if (lecture.compareTo("ksj_protect") == 0) {
            hi.putExtra("NAME", "김상진");
            startActivity(hi);
        } else if (lecture.compareTo("ksy_microprocessor") == 0) {
            hi.putExtra("NAME", "김상연");
            startActivity(hi);
        } else if (lecture.compareTo("ksy_smart") == 0) {
            hi.putExtra("NAME", "김상연");
            startActivity(hi);
        } else if (lecture.compareTo("kwt_base") == 0) {
            hi.putExtra("NAME", "김원태");
            startActivity(hi);
        } else if (lecture.compareTo("kwt_network") == 0) {
            hi.putExtra("NAME", "김원태");
            startActivity(hi);
        } else if (lecture.compareTo("kwt_data") == 0) {
            hi.putExtra("NAME", "김원태");
            startActivity(hi);
        } else if (lecture.compareTo("kwt_smart") == 0) {
            hi.putExtra("NAME", "김원태");
            startActivity(hi);
        } else if (lecture.compareTo("koy_operation") == 0) {
            hi.putExtra("NAME", "권오영");
            startActivity(hi);
        } else if (lecture.compareTo("koy_compile") == 0) {
            hi.putExtra("NAME", "권오영");
            startActivity(hi);
        } else if (lecture.compareTo("koy_embeded") == 0) {
            hi.putExtra("NAME", "권오영");
            startActivity(hi);
        } else if (lecture.compareTo("koy_script") == 0) {
            hi.putExtra("NAME", "권오영");
            startActivity(hi);
        } else if (lecture.compareTo("mjk_database") == 0) {
            hi.putExtra("NAME", "민준기");
            startActivity(hi);
        } else if (lecture.compareTo("mjk_software") == 0) {
            hi.putExtra("NAME", "민준기");
            startActivity(hi);
        } else if (lecture.compareTo("mhmd_java") == 0) {
            hi.putExtra("NAME", "무하마드");
            startActivity(hi);
        } else if (lecture.compareTo("mhmd_datastructure") == 0) {
            hi.putExtra("NAME", "무하마드");
            startActivity(hi);
        } else if (lecture.compareTo("mhmd_algo") == 0) {
            hi.putExtra("NAME", "무하마드");
            startActivity(hi);
        } else if (lecture.compareTo("mhmd_databasepro") == 0) {
            hi.putExtra("NAME", "무하마드");
            startActivity(hi);
        } else if (lecture.compareTo("miy_web") == 0) {
            hi.putExtra("NAME", "문일영");
            startActivity(hi);
        } else if (lecture.compareTo("miy_mobile") == 0) {
            hi.putExtra("NAME", "문일영");
            startActivity(hi);
        } else if (lecture.compareTo("miy_advancedweb") == 0) {
            hi.putExtra("NAME", "문일영");
            startActivity(hi);
        } else if (lecture.compareTo("psc_smartnetwork") == 0) {
            hi.putExtra("NAME", "박승철");
            startActivity(hi);
        } else if (lecture.compareTo("shs_operation") == 0) {
            hi.putExtra("NAME", "서희석");
            startActivity(hi);
        } else if (lecture.compareTo("shs_system") == 0) {
            hi.putExtra("NAME", "서희석");
            startActivity(hi);
        }


    }

    //웹서버에 접근해서 데이터들을 json으로 읽어오는 스레드~
    private class JsonReadTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            Log.d("evaluateActivity","doInBackground()");

            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(params[0]); //(params[0]에는 url 값이 들어있습! Post로 서버에 연결.

            try {

                HttpResponse response = httpclient.execute(httppost);//서버로 연결하고 그 결과 값을.
                InputStream inputStream = response.getEntity().getContent();//서버에서 주는 결과를 받아올수 있는 inputStream 객체 생성.
                String temp = inputStreamToString(inputStream).toString();//스트림을 매개변수 값으로 넣고 원래 json은 문자열이기때문에 문자열로 바꿔서 온다.

                //wow 시발 성공했다!! ㅋㅋㅋ
                jsonResult = temp.substring(temp.indexOf('{'));
                System.out.println(jsonResult);

            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        private StringBuilder inputStreamToString(InputStream is) {

            Log.d("evaluateActivity","inputStreamToString()");

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

            Log.d("evaluateActivity","onPostExecute()");
            ListDrwaer();
        }
    }// end async task

    public void accessWebService() {

        Log.d("evaluateActivity","accessWebService()");

        JsonReadTask task = new JsonReadTask();
        // passes values for the urls string array
        task.execute(new String[]{url});
    }
    // build hash set for list view

    //댓글을 jSon으로 받아와서 뿌려준다.
    public void ListDrwaer() {

        Log.d("evaluateActivity","ListDrwaer()");

        List<Map<String, String>> commentList = new ArrayList<Map<String, String>>();

        try {

            JSONObject jsonResponse = new JSONObject(jsonResult);
            JSONArray jsonMainNode = jsonResponse.optJSONArray("commentData");

            for (int i = 0; i < jsonMainNode.length(); i++) {

                JSONObject jsonChildNode = jsonMainNode.getJSONObject(i);
                String commentText = jsonChildNode.optString("comment");
                String timeText = jsonChildNode.optString("time");

                commentList.add(createComment("nameT", "timeT", commentText, timeText));


            }
        } catch (JSONException e) {
            Toast.makeText(getApplicationContext(), "Error" + e.toString(),
                    Toast.LENGTH_SHORT).show();
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(this, commentList,
                android.R.layout.simple_list_item_2,
                new String[]{"nameT", "timeT"}, new int[]{android.R.id.text1, android.R.id.text2});

        listView.setAdapter(simpleAdapter);
    }

    private HashMap<String, String> createComment(String nameT, String timeT, String commentText, String timeText) {

        HashMap<String, String> comment = new HashMap<String, String>();
        comment.put(nameT, commentText);
        comment.put(timeT, timeText);
        return comment;

    }

    //댓글입력을 클릭했을때 웹서버로 데이터를 입력한다
    public void CommentonClick(View view) {

        Log.d("evaluateActivity","CommentonClick()");

        String commentData = m_EditText_comment.getText().toString();
        m_EditText_comment.setText("");
        if (view.getId() == R.id.add) {

            int version = android.os.Build.VERSION.SDK_INT;
            Log.d("sdk version:", version + "");
            if (version > 8) {
                StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitNetwork().build());
            }
            try {

                //url주소로 넘길때 한글값이 들어가 있으면 제대로 url주소로 넘어가지가 않을경우 한글데이터를 인코딩한다.!
                String commentDataEncode = URLEncoder.encode(commentData, "UTF-8");

                URL url = new URL("http://jhy753.dothome.co.kr/dbconfig/insertComment.php?comment=" + commentDataEncode + "&lecture=" + lecture);
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

    public static class ButtonDialogFragment extends DialogFragment {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the Builder class for convenient dialog construction
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("최선입니까?")
                    .setMessage("별점을 등록하시겠습니까?")
                    .setNegativeButton("yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String rate = Float.toString(ratevalue);
                            try {

                                URL url = new URL("http://jhy753.dothome.co.kr/insertScoreValue.php?avg=" + rate + "&lecture=" + lecture);
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
                    })
                    .setPositiveButton("no", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });

            // Create the AlertDialog object and return it
            return builder.create();
        }
    }

    //별점을  등록!
    public void evaluate_Enrollment(View view) {

        Log.d("evaluateActivity","evaluate_Enrollment()");

        //String commentData = m_EditText_comment.getText().toString();
        int version = android.os.Build.VERSION.SDK_INT;
        Log.d("sdk version:", version + "");
        if (version > 8) {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitNetwork().build());
        }
        //String rate = Float.toString(ratevalue);

        // 위에서 정의한 ButtonDialogFragment 클래스의 객체를 생성
        DialogFragment myFragment = new ButtonDialogFragment();

        // show 메소드 호출을 통하여 대화상자가 화면에 표시되도록 함
        myFragment.show(getFragmentManager(), "FinishDialog");



    }
}
