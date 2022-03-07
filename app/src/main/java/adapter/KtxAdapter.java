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

public class KtxAdapter extends BaseAdapter {

    Context context;
    ArrayList<Sanpham> arrayktx;

    public KtxAdapter(Context context, ArrayList<Sanpham> arrayktx) {
        this.context = context;
        this.arrayktx = arrayktx;
    }


    @Override
    public int getCount() {
        return arrayktx.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayktx.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder{
        public TextView txttenktx,txtgiaktx,txtmotaktx;
        public ImageView imgktx;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder =null;
        if (convertView==null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.dong_ktx,null);
            viewHolder.txttenktx= convertView.findViewById(R.id.textviewktx);
            viewHolder.txtgiaktx= convertView.findViewById(R.id.textviewgiaktx);
            viewHolder.txtmotaktx= convertView.findViewById(R.id.textviewmotaktx);
            viewHolder.imgktx= convertView.findViewById(R.id.imageviewktx);
            convertView.setTag(viewHolder);
        }else{
            viewHolder=(ViewHolder) convertView.getTag();
        }
        Sanpham sanpham = (Sanpham) getItem(position);
        viewHolder.txttenktx.setText(sanpham.getTensanpham());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.txtgiaktx.setText("Giá: " + decimalFormat.format(sanpham.getGiasanpham())+" VNĐ");
        viewHolder.txtmotaktx.setMaxLines(2);
        viewHolder.txtmotaktx.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.txtmotaktx.setText(sanpham.getMotasanpham());
        Picasso.get().load(sanpham.getHinhanhsanpham())
                .placeholder(R.drawable.noimage)
                .error(R.drawable.error)
                .into(viewHolder.imgktx);
        return convertView;
    }
}
