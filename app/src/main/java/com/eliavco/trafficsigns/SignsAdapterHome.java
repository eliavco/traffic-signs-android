package com.eliavco.trafficsigns;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class SignsAdapterHome extends ArrayAdapter<Sign> {
    private Context ctx;
    private ArrayList<Sign> arr;
    private int resourceId;

    public SignsAdapterHome(@NonNull Context context, int resource, @NonNull ArrayList<Sign> objects) {
        super(context, resource, objects);
        this.ctx = context;
        this.resourceId = resource;
        this.arr = objects;
    }

    @Override
    public int getCount() {
        return this.arr.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater li = (LayoutInflater)(this.ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE));
        View v = li.inflate(this.resourceId, null);
        Sign s = this.arr.get(position);
        TextView groupTitle = v.findViewById(R.id.groupTitle);
        groupTitle.setText(s.getGroup());
        return v;
    }
}