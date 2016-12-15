package com.example.haeyoungjoo.hackerthon;

/**
 * Created by hae young Joo on 2016-11-13.
 */

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by swkang on 2016-10-12.
 */
public class TextFileManager {


    private static String FILE_NAME = null;//파일 이름을 지정변수
    Context mContext = null;

    public TextFileManager(Context _context) {
        mContext = _context;
    }//클래스 생성자.

    public void setMemoTitle(String s){//파일 이름 지정함수

        FILE_NAME = s ;
    }

    // 파일에 문자열 데이터를 쓰는 메소드
    public void save(String data) {
        if (data == null || data.isEmpty() == true) {
            return;
        }
        FileOutputStream fos = null;
        try {
            fos = mContext.openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
            fos.write(data.getBytes());
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    // 파일에서 데이터를 읽고 문자열 데이터로 반환하는 메소드
    public String load(String fileName ) {
        try {
            FileInputStream fis = mContext.openFileInput( fileName );

            byte[] data = new byte[fis.available()];
            fis.read(data);
            fis.close();

            return new String(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    // 파일 삭제 메소드
    public void delete(String s) {

        mContext.deleteFile(s);
    }
}
