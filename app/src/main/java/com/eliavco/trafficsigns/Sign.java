package com.eliavco.trafficsigns;

import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import java.io.IOException;
import java.io.InputStream;

public class Sign {
    private long _id;
    private String id;
    private String category;
    private String group;
    private String meaning;
    private String purpose;
    private String power;
    private String image;

    public Sign() {}

    public Sign(long _id, String id, String category, String group, String meaning, String purpose, String power, String image) {
        this._id = _id;
        this.id = id;
        this.category = category;
        this.group = group;
        this.meaning = meaning;
        this.purpose = purpose;
        this.power = power;
        this.image = image;
    }

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public String getId() { return this.id.trim(); }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategory() {
        return this.category.trim();
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getGroup() {
        return this.group.trim();
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getMeaning() {
        return this.meaning.trim();
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public String getPurpose() {
        return this.purpose.trim();
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getPower() {
        return this.power.trim();
    }

    public void setPower(String power) {
        this.power = power;
    }

    public Drawable getImage(AssetManager assets) throws IOException {
        InputStream stream;
        try {
            stream = assets.open("signs/" + this.image);
            Drawable myImg = Drawable.createFromStream(stream, null);
            return myImg;
        } catch (Exception e) {
            stream = assets.open("signs/default.png");
            Drawable image = Drawable.createFromStream(stream, null);
            return image;
        }
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Sign{" +
                "_id=" + _id +
                ", id='" + id + '\'' +
                ", category='" + category + '\'' +
                ", group='" + group + '\'' +
                ", meaning='" + meaning + '\'' +
                ", purpose='" + purpose + '\'' +
                ", power='" + power + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
