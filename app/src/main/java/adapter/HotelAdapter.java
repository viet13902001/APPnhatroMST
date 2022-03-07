package adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mstappdemo.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

import model.Sanpham;

public class HotelAdapter extends BaseAdapter {
    Context context;
    ArrayList<Sanpham> arrayhotel;

    public HotelAdapter(Context context, ArrayList<Sanpham> arrayhotel) {
        this.context = context;
        this.arrayhotel = arrayhotel;
    }

    @Override
    public int getCount() {
        return arrayhotel.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayhotel.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder{
        public TextView txttenhotel,txtgiahotel,txtmotahotel;
        public ImageView imghotel;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder =null;
        if (convertView==null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.dong_hotel,null);
            viewHolder.txttenhotel= convertView.findViewById(R.id.textviewhotel);
            viewHolder.txtgiahotel= convertView.findViewById(R.id.textviewgiahotel);
            viewHolder.txtmotahotel= convertView.findViewById(R.id.textviewmotahotel);
            viewHolder.imghotel= convertView.findViewById(R.id.imagehotel);
            convertView.setTag(viewHolder);
        }else{
            viewHolder=(ViewHolder) convertView.getTag();
        }
        Sanpham sanpham = (Sanpham) getItem(position);
        viewHolder.txttenhotel.setText(sanpham.getTensanpham());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.txtgiahotel.setText("Giá: " + decimalFormat.format(sanpham.getGiasanpham())+" VNĐ");
        viewHolder.txtmotahotel.setMaxLines(2);
        viewHolder.txtmotahotel.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.txtmotahotel.setText(sanpham.getMotasanpham());
        Picasso.get().load(sanpham.getHinhanhsanpham())
                .placeholder(R.drawable.noimage)
                .error(R.drawable.error)
                .into(viewHolder.imghotel);
        return convertView;
    }
}
