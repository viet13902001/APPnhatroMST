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

public class NhatroAdapter extends BaseAdapter {
    Context context;
    ArrayList<Sanpham> arraynhatro;

    public NhatroAdapter(Context context, ArrayList<Sanpham> arraynhatro) {
        this.context = context;
        this.arraynhatro = arraynhatro;
    }

    @Override
    public int getCount() {
        return arraynhatro.size();
    }

    @Override
    public Object getItem(int position) {
        return arraynhatro.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder{
        public TextView txttennhatro,txtgianhatro,txtmotanhatro;
        public ImageView imgnhatro;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder =null;
        if (convertView==null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.dong_nhatro,null);
            viewHolder.txttennhatro= convertView.findViewById(R.id.textviewnhatro);
            viewHolder.txtgianhatro= convertView.findViewById(R.id.textviewgianhatro);
            viewHolder.txtmotanhatro= convertView.findViewById(R.id.textviewmotanhatro);
            viewHolder.imgnhatro= convertView.findViewById(R.id.imagenhatro);
            convertView.setTag(viewHolder);
        }else{
            viewHolder=(ViewHolder) convertView.getTag();
        }
        Sanpham sanpham = (Sanpham) getItem(position);
        viewHolder.txttennhatro.setText(sanpham.getTensanpham());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.txtgianhatro.setText("Giá: " + decimalFormat.format(sanpham.getGiasanpham())+" VNĐ");
        viewHolder.txtmotanhatro.setMaxLines(2);
        viewHolder.txtmotanhatro.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.txtmotanhatro.setText(sanpham.getMotasanpham());
        Picasso.get().load(sanpham.getHinhanhsanpham())
                .placeholder(R.drawable.noimage)
                .error(R.drawable.error)
                .into(viewHolder.imgnhatro);
        return convertView;
    }
}
