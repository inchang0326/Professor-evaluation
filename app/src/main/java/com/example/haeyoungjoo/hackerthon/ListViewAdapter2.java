package com.example.haeyoungjoo.hackerthon;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by hae young Joo on 2016-12-21.
 */

public class ListViewAdapter2 extends BaseAdapter implements Filterable {

    private ArrayList<ListViewItem> listViewItemList = new ArrayList<ListViewItem>();
    private ArrayList<ListViewItem> filteredItemList = listViewItemList;

    Filter listFilter;

    public ListViewAdapter2(){

    }

    //어댑터에 사용되는 데이터의 개수를 리턴한다.
    @Override
    public int getCount(){
        return filteredItemList.size();
    }

    //position에 위치한 데이터를 호면에 출력하는데 사용될 view 리턴 : 필수구현
    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        final int pos = position;
        final Context context = parent.getContext();

        //ListView_item" Layout을 inflate 하여 convertView 참조 획득
        if( convertView == null ){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_item, parent, false);
        }
        //화면에 표시될 View(Layout이 inflate 된) 으로부터 위젯에 대한 참조 획득.
        ImageView iconImageView = (ImageView) convertView.findViewById(R.id.professorImg);

        //Data set(filteredItemList) 에서 position에 위치한 데이터 참조 획득
        ListViewItem listViewItem = filteredItemList.get(position);

        //아이템 내 각 위젯에 데이터 반영
        iconImageView.setImageDrawable(listViewItem.getIcon());

        return convertView;
    }

    //지정한 위치에 있는 데이터와 관계된 아이템(row)의 ID를 리턴 : 필수구현
    @Override
    public long getItemId(int position){
        return position;
    }

    //지정한 위치에 있는 데이터 리턴 : 필수구현
    @Override
    public Object getItem(int position){
        return filteredItemList.get(position);
    }

    //아이템 데이터 추가를 위한 함수, 개발자가 원하는 대로 작성 가능
    public void addItem(Drawable icon, String name){
        ListViewItem item = new ListViewItem();

        item.setIcon(icon);
        item.setName(name);

        listViewItemList.add(item);
    }


    @Override
    public Filter getFilter(){
        if(listFilter == null){
            listFilter  = new ListFilter();
        }
        return listFilter;
    }

    private class ListFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults() ;

            if (constraint == null || constraint.length() == 0) {
                results.values = listViewItemList ;
                results.count = listViewItemList.size() ;
            } else {
                ArrayList<ListViewItem> itemList = new ArrayList<ListViewItem>() ;

                for (ListViewItem item : listViewItemList) {
                    if (item.getName().toUpperCase().contains(constraint.toString().toUpperCase() ) ) {
                        itemList.add(item) ;
                    }
                }

                results.values = itemList ;
                results.count = itemList.size() ;
            }
            return results;
        }
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            // update listview by filtered data list.
            filteredItemList = (ArrayList<ListViewItem>) results.values ;

            // notify
            if (results.count > 0) {
                notifyDataSetChanged() ;
            } else {
                notifyDataSetInvalidated() ;
            }
        }
    }

}
