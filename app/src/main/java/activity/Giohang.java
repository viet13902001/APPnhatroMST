package activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mstappdemo.R;

import java.text.DecimalFormat;

import adapter.GiohangAdapter;
import ultil.CheckConnection;

public class Giohang extends AppCompatActivity {
    ListView listViewgiohang;
    TextView txtthongbao;
    static TextView txttongtien;
    Button btthanhtoan,bttieptucmuahang;
    Toolbar toolbargiohang;
    GiohangAdapter giohangAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giohang);
        Anhxa();
        ActionToolbar();
        CheckData();
        EvenUltil();
        CactchOnItemListView();
        EvenButton();
    }

    private void CactchOnItemListView() {
        listViewgiohang.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Giohang.this);
                builder.setTitle("Xác nhận xóa");
                builder.setMessage("Bạn có chắc muốn xóa");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(MainActivity.manggiohang.size()<=0){
                            txtthongbao.setVisibility(View.VISIBLE);
                        }else{
                            MainActivity.manggiohang.remove(position);
                            giohangAdapter.notifyDataSetChanged();
                            EvenUltil();
                            if(MainActivity.manggiohang.size()<=0){
                                txtthongbao.setVisibility(View.VISIBLE);
                            }else {
                                txtthongbao.setVisibility(View.INVISIBLE);
                                giohangAdapter.notifyDataSetChanged();
                                EvenUltil();
                            }
                        }
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        giohangAdapter.notifyDataSetChanged();
                        EvenUltil();
                    }
                });
                builder.show();
                return true;
            }
        });
    }


    private void EvenButton() {
        bttieptucmuahang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
        btthanhtoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MainActivity.manggiohang.size()>0){
                    Intent intent=new Intent(getApplicationContext(),Thongtinkhachhang.class);
                    startActivity(intent);
                }else{
                    CheckConnection.ShowToast_Short(getApplicationContext(),"Bạn chưa có phòng để thanh toán");

                }
            }
        });
    }


    public static void EvenUltil() {
        long tongtien=0;
        for(int i=0;i<MainActivity.manggiohang.size();i++){
            tongtien+=MainActivity.manggiohang.get(i).getGiasp();
        }
        DecimalFormat decimalFormat =new DecimalFormat("###,###,###");
        txttongtien.setText(decimalFormat.format(tongtien)+ "VNĐ");
    }

    private void CheckData() {
        if(MainActivity.manggiohang.size()<=0){
            giohangAdapter.notifyDataSetChanged();
            txtthongbao.setVisibility(View.VISIBLE);
            listViewgiohang.setVisibility(View.INVISIBLE);
        }else{
            giohangAdapter.notifyDataSetChanged();
            txtthongbao.setVisibility(View.INVISIBLE);
            listViewgiohang.setVisibility(View.VISIBLE);
        }
    }

    private void ActionToolbar() {
        setSupportActionBar(toolbargiohang);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        toolbargiohang.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void Anhxa() {
        listViewgiohang=findViewById(R.id.listviewgiohang);
        txtthongbao=findViewById(R.id.textviewthongbao);
        txttongtien=findViewById(R.id.textviewtongtien);
        btthanhtoan=findViewById(R.id.buttonthanhtoangiohang);
        bttieptucmuahang=findViewById(R.id.buttontieptucmuahang);
        toolbargiohang=findViewById(R.id.toolbargiohang);
        giohangAdapter=new GiohangAdapter(Giohang.this,MainActivity.manggiohang);
        listViewgiohang.setAdapter(giohangAdapter);
    }
}