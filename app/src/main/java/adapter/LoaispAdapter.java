package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mstappdemo.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import model.Loaisp;

public class LoaispAdapter extends BaseAdapter {
    ArrayList<Loaisp> arraylistloaisp;
    Context context;

    public LoaispAdapter(ArrayList<Loaisp> arraylistloaisp, Context context) {
        this.arraylistloaisp = arraylistloaisp;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arraylistloaisp.size();
    }

    @Override
    public Object getItem(int position) {
        return arraylistloaisp.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder{
        TextView txttenloaisp;
        ImageView imgloaisp;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if(convertView==null){
            viewHolder=new ViewHolder();
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.dong_listview_loaisp,null);
            viewHolder.txttenloaisp = (TextView) convertView.findViewById(R.id.textviewloaisp);
            viewHolder.imgloaisp = (ImageView) convertView.findViewById(R.id.imageviewloaisp);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Loaisp loaisp = (Loaisp) getItem(position);
        viewHolder.txttenloaisp.setText(loaisp.getTenloaisp());
        Picasso.get().load(loaisp.getHinhanhloaisp())
                .placeholder(R.drawable.noimage)
                .error(R.drawable.error)
                .into(viewHolder.imgloaisp);
        return convertView;
    }
}
