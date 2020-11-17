package com.eliavco.trafficsigns;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.lang.reflect.Method;
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
        final Sign s = this.arr.get(position);
        ImageButton imgb = v.findViewById(R.id.signImageButton);
        imgb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idSign = s.getId();
                Intent intent = new Intent(ctx, SignDetailsActivity.class);
                intent.putExtra("SIGN_ID", idSign);
                ctx.startActivity(intent);
            }
        });
        try {
            Drawable d = s.getImage(this.ctx.getAssets());
            imgb.setImageDrawable(d);
        } catch(Exception e) {}
        return v;
    }
}
