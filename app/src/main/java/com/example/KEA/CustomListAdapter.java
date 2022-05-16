package com.example.KEA;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class CustomListAdapter extends ArrayAdapter {
    private Context context; //context
    private List<DateAvail> items; //data source of the list adapter
    private Map<DateAvail, Boolean> noah;


    /**
     * Public constructor for the CustomListAdapter Class
     * @param context
     * @param resource
     * @param items A list of 14 lists of DateAvail objects
     */
    //public constructor
    public CustomListAdapter(Context context, int resource, ArrayList<DateAvail> items) {
        super(context,resource, items);
        this.context = context;
        this.items = items;
        this.noah = new HashMap<>();
        for (DateAvail s : items) {
            for (Boolean boo : s.availLists) {
                noah.put(s, boo);
            }
        }
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //get the dates information
        String date = Integer.toString(items.get(position).getMonth()) + "/" + Integer.toString(items.get(position).getDay()) + "/" + Integer.toString(items.get(position).getYear());
        List<Boolean> avail = items.get(position).getAvailLists();

        // inflate the layout for each list row

        if (convertView == null) {
            convertView = LayoutInflater.from(context).
                    inflate(R.layout.jack, parent, false);
        }

        // get current item to be displayed
        //Map.Entry<DateAvail, Boolean> currentItem = (Map.Entry<DateAvail, Boolean>) getItem(position);

        // get the TextView for item name and item description
        TextView textViewItemName = (TextView) convertView.findViewById(R.id.crossCheckDates);
        TextView crosscheckResult1  = (TextView) convertView.findViewById(R.id.crossCheckResults1);
        TextView crosscheckResult2 = (TextView) convertView.findViewById(R.id.crossCheckResults2);
        TextView crosscheckResult3  = (TextView) convertView.findViewById(R.id.crossCheckResults3);
        TextView crosscheckResult4 = (TextView) convertView.findViewById(R.id.crossCheckResults4);
        TextView crosscheckResult5 = (TextView) convertView.findViewById(R.id.crossCheckResults5);
        TextView crosscheckResult6  = (TextView) convertView.findViewById(R.id.crossCheckResults6);
        TextView crosscheckResult7  = (TextView) convertView.findViewById(R.id.crossCheckResults7);
        TextView crosscheckResult8  = (TextView) convertView.findViewById(R.id.crossCheckResults8);
        TextView crosscheckResult9  = (TextView) convertView.findViewById(R.id.crossCheckResults9);
        TextView crosscheckResult10  = (TextView) convertView.findViewById(R.id.crossCheckResults10);
        TextView crosscheckResult11  = (TextView) convertView.findViewById(R.id.crossCheckResults11);
        TextView crosscheckResult12  = (TextView) convertView.findViewById(R.id.crossCheckResults12);
        TextView crosscheckResult13  = (TextView) convertView.findViewById(R.id.crossCheckResults13);
        TextView crosscheckResult14  = (TextView) convertView.findViewById(R.id.crossCheckResults14);
        TextView crosscheckResult15  = (TextView) convertView.findViewById(R.id.crossCheckResults15);
        TextView crosscheckResult16  = (TextView) convertView.findViewById(R.id.crossCheckResults16);
        TextView crosscheckResult17  = (TextView) convertView.findViewById(R.id.crossCheckResults17);
        TextView crosscheckResult18  = (TextView) convertView.findViewById(R.id.crossCheckResults18);
        TextView crosscheckResult19  = (TextView) convertView.findViewById(R.id.crossCheckResults19);
        TextView crosscheckResult20 = (TextView) convertView.findViewById(R.id.crossCheckResults20);
        TextView crosscheckResult21  = (TextView) convertView.findViewById(R.id.crossCheckResults21);
        TextView crosscheckResult22  = (TextView) convertView.findViewById(R.id.crossCheckResults22);
        TextView crosscheckResult23  = (TextView) convertView.findViewById(R.id.crossCheckResults23);
        TextView crosscheckResult24  = (TextView) convertView.findViewById(R.id.crossCheckResults24);
        TextView crosscheckResult25  = (TextView) convertView.findViewById(R.id.crossCheckResults25);
        TextView crosscheckResult26  = (TextView) convertView.findViewById(R.id.crossCheckResults26);
        TextView crosscheckResult27  = (TextView) convertView.findViewById(R.id.crossCheckResults27);
        TextView crosscheckResult28  = (TextView) convertView.findViewById(R.id.crossCheckResults28);
        TextView crosscheckResult29  = (TextView) convertView.findViewById(R.id.crossCheckResults29);
        TextView crosscheckResult30  = (TextView) convertView.findViewById(R.id.crossCheckResults30);
        TextView crosscheckResult31  = (TextView) convertView.findViewById(R.id.crossCheckResults31);
       // TextView crosscheckResult32  = (TextView) convertView.findViewById(R.id.crossCheckResults32);


        //sets the text for item name and item description from the current item object
        textViewItemName.setText(date);
        crosscheckResult1.setText(avail.get(0).toString());
        crosscheckResult2.setText(avail.get(1).toString());
        crosscheckResult3.setText(avail.get(2).toString());
        crosscheckResult4.setText(avail.get(3).toString());
        crosscheckResult5.setText(avail.get(4).toString());
        crosscheckResult6.setText(avail.get(5).toString());
        crosscheckResult7.setText(avail.get(6).toString());
        crosscheckResult8.setText(avail.get(7).toString());
        crosscheckResult9.setText(avail.get(8).toString());
        crosscheckResult10.setText(avail.get(9).toString());
        crosscheckResult11.setText(avail.get(10).toString());
        crosscheckResult12.setText(avail.get(11).toString());
        crosscheckResult13.setText(avail.get(12).toString());
        crosscheckResult14.setText(avail.get(13).toString());
        crosscheckResult15.setText(avail.get(14).toString());
        crosscheckResult16.setText(avail.get(15).toString());
        crosscheckResult17.setText(avail.get(16).toString());
        crosscheckResult18.setText(avail.get(17).toString());
        crosscheckResult19.setText(avail.get(18).toString());
        crosscheckResult20.setText(avail.get(19).toString());
        crosscheckResult21.setText(avail.get(20).toString());
        crosscheckResult22.setText(avail.get(21).toString());
        crosscheckResult23.setText(avail.get(22).toString());
        crosscheckResult24.setText(avail.get(23).toString());
        crosscheckResult25.setText(avail.get(24).toString());
        crosscheckResult26.setText(avail.get(25).toString());
        crosscheckResult27.setText(avail.get(26).toString());
        crosscheckResult28.setText(avail.get(27).toString());
        crosscheckResult29.setText(avail.get(28).toString());
        crosscheckResult30.setText(avail.get(29).toString());
        crosscheckResult31.setText(avail.get(30).toString());
       // crosscheckResult32.setText(avail.get(31).toString());

        // returns the view for the current row
        return convertView;
    }
}
