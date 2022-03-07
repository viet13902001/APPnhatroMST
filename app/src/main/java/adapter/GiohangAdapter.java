package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mstappdemo.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

import activity.MainActivity;
import model.Giohang;

public class GiohangAdapter extends BaseAdapter {
    Context context;
    ArrayList<Giohang> arrayListgiohang;

    public GiohangAdapter(Context context, ArrayList<Giohang> arrayListgiohang) {
        this.context = context;
        this.arrayListgiohang = arrayListgiohang;
    }

    @Override
    public int getCount() {
        return arrayListgiohang.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayListgiohang.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public class ViewHolder{
        public TextView txttengiohang,txtgiagiohang;
        public ImageView imggiohang;
        public Button btleft,btvalue,btright;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if(convertView==null){
            viewHolder=new ViewHolder();
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.dong_giohang,null);
            viewHolder.txttengiohang=convertView.findViewById(R.id.textviewtengiohang);
            viewHolder.txtgiagiohang=convertView.findViewById(R.id.textviewgiagiohang);
            viewHolder.imggiohang=convertView.findViewById(R.id.imageviewgiohang);
            viewHolder.btleft=convertView.findViewById(R.id.buttonminus);
            viewHolder.btvalue=convertView.findViewById(R.id.buttonvalues);
            viewHolder.btright=convertView.findViewById(R.id.buttonplus);
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) convertView.getTag();
        }
        Giohang giohang= (Giohang) getItem(position);
        viewHolder.txttengiohang.setText(giohang.getTensp());
        DecimalFormat decimalFormat =new DecimalFormat("###,###,###");
        viewHolder.txtgiagiohang.setText("Giá: "+ decimalFormat.format(giohang.getGiasp())+"VNĐ");
        Picasso.get().load(giohang.getHinhanhsp())
                .placeholder(R.drawable.noimage)
                .error(R.drawable.error)
                .into(viewHolder.imggiohang);
        viewHolder.btvalue.setText(giohang.getSoluongsp()+"");
        int sl= Integer.parseInt(viewHolder.btvalue.getText().toString());
        if(sl>=10){
            viewHolder.btright.setVisibility(View.INVISIBLE);
            viewHolder.btleft.setVisibility(View.VISIBLE);
        }else if(sl<=1){
            viewHolder.btleft.setVisibility(View.INVISIBLE);
        }else if(sl>=1){
            viewHolder.btleft.setVisibility(View.VISIBLE);
            viewHolder.btright.setVisibility(View.VISIBLE);
        }
        ViewHolder finalViewHolder = viewHolder;
        viewHolder.btright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int slmoinhat=Integer.parseInt(finalViewHolder.btvalue.getText().toString())+1;
                int slhientai= MainActivity.manggiohang.get(position).getSoluongsp();
                long giaht=MainActivity.manggiohang.get(position).getGiasp();
                MainActivity.manggiohang.get(position).setSoluongsp(slmoinhat);
                long giamoinhat=(giaht*slmoinhat)/slhientai;
                MainActivity.manggiohang.get(position).setGiasp(giamoinhat);
                DecimalFormat decimalFormat =new DecimalFormat("###,###,###");
                finalViewHolder.txtgiagiohang.setText("Giá: "+ decimalFormat.format(giamoinhat)+"VNĐ");
                activity.Giohang.EvenUltil();
                if(slmoinhat>9){
                    finalViewHolder.btright.setVisibility(View.INVISIBLE);
                    finalViewHolder.btleft.setVisibility(View.VISIBLE);
                    finalViewHolder.btvalue.setText(String.valueOf(slmoinhat));
                }else{
                    finalViewHolder.btright.setVisibility(View.VISIBLE);
                    finalViewHolder.btleft.setVisibility(View.VISIBLE);
                    finalViewHolder.btvalue.setText(String.valueOf(slmoinhat));
                }
            }
        });
        viewHolder.btleft.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int slmoinhat=Integer.parseInt(finalViewHolder.btvalue.getText().toString())-1;
                int slhientai=MainActivity.manggiohang.get(position).getSoluongsp();
                long giaht=MainActivity.manggiohang.get(position).getGiasp();
                MainActivity.manggiohang.get(position).setSoluongsp(slmoinhat);
                long giamoinhat=(giaht*slmoinhat)/slhientai;
                MainActivity.manggiohang.get(position).setGiasp(giamoinhat);
                DecimalFormat decimalFormat =new DecimalFormat("###,###,###");
                finalViewHolder.txtgiagiohang.setText("Giá: "+ decimalFormat.format(giamoinhat)+"VNĐ");
                activity.Giohang.EvenUltil();
                if(slmoinhat < 2){
                    finalViewHolder.btleft.setVisibility(View.INVISIBLE);
                    finalViewHolder.btright.setVisibility(View.VISIBLE);
                    finalViewHolder.btvalue.setText(String.valueOf(slmoinhat));
                }else{
                    finalViewHolder.btright.setVisibility(View.VISIBLE);
                    finalViewHolder.btleft.setVisibility(View.VISIBLE);
                    finalViewHolder.btvalue.setText(String.valueOf(slmoinhat));
                }

            }
        });
        return convertView;
    }
}
