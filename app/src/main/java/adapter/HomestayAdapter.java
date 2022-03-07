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

public class HomestayAdapter extends BaseAdapter {

    Context context;
    ArrayList<Sanpham> arrayhomestay;

    public HomestayAdapter(Context context, ArrayList<Sanpham> arrayhomestay) {
        this.context = context;
        this.arrayhomestay = arrayhomestay;
    }

    @Override
    public int getCount() {
        return arrayhomestay.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayhomestay.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder{
        public TextView txttenhomestay,txtgiahomestay,txtmotahomestay;
        public ImageView imghomestay;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder =null;
        if (convertView==null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.dong_homestay,null);
            viewHolder.txttenhomestay= convertView.findViewById(R.id.textviewhomestay);
            viewHolder.txtgiahomestay= convertView.findViewById(R.id.textviewgiahomestay);
            viewHolder.txtmotahomestay= convertView.findViewById(R.id.textviewmotahomestay);
            viewHolder.imghomestay= convertView.findViewById(R.id.imagehomestay);
            convertView.setTag(viewHolder);
        }else{
            viewHolder=(ViewHolder) convertView.getTag();
        }
        Sanpham sanpham = (Sanpham) getItem(position);
        viewHolder.txttenhomestay.setText(sanpham.getTensanpham());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.txtgiahomestay.setText("Giá: " + decimalFormat.format(sanpham.getGiasanpham())+" VNĐ");
        viewHolder.txtmotahomestay.setMaxLines(2);
        viewHolder.txtmotahomestay.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.txtmotahomestay.setText(sanpham.getMotasanpham());
        Picasso.get().load(sanpham.getHinhanhsanpham())
                .placeholder(R.drawable.noimage)
                .error(R.drawable.error)
                .into(viewHolder.imghomestay);
        return convertView;
    }
}
