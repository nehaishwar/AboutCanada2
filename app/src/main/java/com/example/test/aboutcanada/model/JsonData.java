package com.example.test.aboutcanada.model;

import android.widget.ImageView;

public class JsonData {

    public String title;
    public JsonRow rows[];
    public JsonData(int len){
        super();
        title = "";
        rows = new JsonRow[len];
    }
    public static class JsonRow{
        public String title;
        public String desc;
        public String imageViewURL;

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setImageViewURL(String imageViewURL) {
            this.imageViewURL = imageViewURL;
        }

        public String getDesc() {
            return desc;
        }

        public String getTitle() {
            return title;
        }

        public String getImageViewURL() {
            return imageViewURL;
        }

        public void clear(){
            title = "";
            desc = "";
            imageViewURL = "";
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public JsonRow[] getRows() {
        return rows;
    }

    public void setRows(JsonRow[] rows) {
        this.rows = rows;
    }

    public void clear(){ rows = null;}
}
