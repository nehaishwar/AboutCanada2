package com.example.test.aboutcanada;


import android.content.ContentValues;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;

import com.example.test.aboutcanada.model.JsonData;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;


public final class OpenJsonData {
   static JsonData jsonData;

        public static JsonData getRowsStringsFromJson(Context context, String jsonStr)
                throws JSONException {

            final String OWM_ROWS = "rows";
            final String OWM_TITLE = "title";
            final String OWM_DESC = "description";
            final String OWM_IMAGE = "imageHref";

           /* String array to hold each row Data*/
            String[] parsedRows = null;
            JSONObject dataJson = new JSONObject(jsonStr);
            JSONArray rowArray = dataJson.getJSONArray(OWM_ROWS);

             parsedRows = new String[rowArray.length()];
             jsonData = new JsonData(rowArray.length());
            JsonData.JsonRow rows[] = new JsonData.JsonRow[rowArray.length()];

            Log.d("Neha","Row Length"+rowArray.length());
            for (int i = 0; i < rowArray.length(); i++) {

                String titlet;
                String descriptiont;
                String imageHreft;

                JSONObject dayForecast = rowArray.getJSONObject(i);

                titlet = dayForecast.getString(OWM_TITLE);
                descriptiont = dayForecast.getString(OWM_DESC);
                 Log.d("Neha desct ",descriptiont);
                try {
                    descriptiont = new String(dayForecast.getString(OWM_DESC).getBytes("UTF-8"), "ISO-8859-1");
                } catch (java.io.UnsupportedEncodingException e) {
                    descriptiont= " ";
                }
                Log.d("Neha desct ",descriptiont);
                imageHreft = dayForecast.getString(OWM_IMAGE);
                JsonData.JsonRow  row = new JsonData.JsonRow();
                row.setTitle(titlet);
                row.setDesc(descriptiont);
                row.setImageViewURL(imageHreft);

               // jsonData.rows[i].setTitle(titlet);
                //jsonData.rows[i].setDesc(descriptiont);
                //jsonData.rows[i].setImageViewURL(imageHreft);
                parsedRows[i] = titlet + " - " + descriptiont + " - " + imageHreft;
                rows[i] = row;
            }
            jsonData.setRows(rows);
            return jsonData;
        }


}