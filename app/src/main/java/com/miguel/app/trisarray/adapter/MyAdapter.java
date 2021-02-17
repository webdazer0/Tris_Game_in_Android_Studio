package com.miguel.app.trisarray.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.miguel.app.trisarray.R;
import com.miguel.app.trisarray.pojo.Square;
import com.miguel.app.trisarray.pojo.Tris;

public class MyAdapter extends BaseAdapter {

    Context context;
    Square[] tris;
    LayoutInflater inflater;
    int n = 3;

    public MyAdapter(Context context, Square[] tris) {
        this.context = context;
        this.tris = tris;
        inflater = LayoutInflater.from(context);
    }


    public void reload(Square[] tris) {
        this.tris = tris;
    }


    @Override
    public int getCount() {
        return tris.length;
    }

    @Override
    public Object getItem(int position) {
        return tris[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        int widthParent = parent.getMeasuredWidth();

//        Log.i("MITO DEBUG", "larghezza: " + widthParent);
//        if(widthParent > 720) {
//            widthParent = widthParent/4;
//        }

        int width  = widthParent/n;


        convertView = inflater.inflate( R.layout.grid_element , null);

        TextView gridItem = convertView.findViewById(R.id.txtGridItem);
        gridItem.setWidth(width);
        gridItem.setHeight(width);

        Square square = (Square) getItem(position);
//
//        Log.i("MITO DEBUG", "pos " + position + " : " + square.isOpen());

        if(!square.isOpen()) {
            gridItem.setText("-");
        }

        if(square.isPlayer()) {
            gridItem.setText("X");
        }

        if(square.isPc()) {
            gridItem.setText("O");
//            convertView.setBackgroundResource(R.drawable.pc_2);
//            convertView.setBackgroundResource(R.drawable.michi_sad);
//            gridItem.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.michi_sad, 0, 0, 0);
        }


        if(!Tris.inProgress()) {

            if(Tris.isWinner()) {
                for (int number: Tris.getLine()) {

                    if(number == position) {
                        gridItem.setPaintFlags(gridItem.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                        convertView.setBackgroundResource(R.drawable.custom_border);
                        gridItem.setTextSize(62);
                        gridItem.setTextColor(Color.RED);
                    };
                }
            }

        }

        return convertView;
    }


}
