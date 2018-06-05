package com.example.test.aboutcanada;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.test.aboutcanada.model.JsonData;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

/**
 * TODO: document your custom view class.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

  private JsonData values;
  public class ViewHolder extends RecyclerView.ViewHolder {
      public TextView title;
      public TextView desc;
      public ImageView imageView;
      public View layout;

      public ViewHolder(View v) {
          super(v);
          layout = v;
          title = (TextView) v.findViewById(R.id.Aboutcanada_list_title);
          desc = (TextView) v.findViewById(R.id.Aboutcanada_list_desc);
          imageView = (ImageView) v.findViewById(R.id.Aboutcanada_list_image);

      }
  }

      public MyAdapter(){
      }

      public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){

          LayoutInflater inflater = LayoutInflater.from(parent.getContext());
          View v = inflater.inflate(R.layout.aboutcanada_list_item,parent,false);
          ViewHolder vh = new ViewHolder(v);
          return vh;
      }

      public void onBindViewHolder(ViewHolder holder,final int position){
          String title = values.rows[position].getTitle();
          String dsc = values.rows[position].getDesc();
          String url = values.rows[position].getImageViewURL();

          holder.title.setText(title);
          holder.desc.setText(dsc);
          Picasso.get().load(url).into(holder.imageView);

          //String url = "http://upload.wikimedia.org/wikipedia/commons/thumb/6/6b/American_Beaver.jpg/220px-American_Beaver.jpg";
          Log.d("Neha viewHolderTitle  ",title);
          Log.d("Neha viewHolderdesc  ",dsc);
          Log.d("Neha viewHolderURL  ",url);
      }

    @Override

    public int getItemCount() {
        if (null == values) return 0;
        return values.rows.length;
  }

    public void setRowData(JsonData rowData) {
          values = rowData;
          notifyDataSetChanged();
    }


}
