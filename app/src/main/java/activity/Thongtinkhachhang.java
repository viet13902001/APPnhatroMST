package activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mstappdemo.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import ultil.CheckConnection;
import ultil.Server;

public class Thongtinkhachhang extends AppCompatActivity {

    EditText edtenkhachhang,edsdt,edemail;
    Button btxacnhan,bttrove;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thongtinkhachhang);
        Anhxa();
        bttrove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        if(CheckConnection.haveNetworkConnection(getApplicationContext())){
            EventButton();
        }
        else{
            CheckConnection.ShowToast_Short(getApplicationContext(),"Bạn hãy kiểm tra lại kết nối");
        }
    }

    private void EventButton() {
        btxacnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String Name = edtenkhachhang.getText().toString().trim();
                final String Phone = edsdt.getText().toString().trim();
                final String email = edemail.getText().toString().trim();
                if(Name.length()>0&& Phone.length()>0&&email.length()>0){
                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.Duongdandonhang, new Response.Listener<String>() {
                        @Override
                        public void onResponse(final String madonhang) {
                            Log.d("madonhang",madonhang);
                            RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                            StringRequest request = new StringRequest(Request.Method.POST, Server.Duongdanchitietdonhang, new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    Log.d("test",response);
                                    if(response!=null) {
                                        MainActivity.manggiohang.clear();
                                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                        startActivity(intent);
                                        CheckConnection.ShowToast_Short(getApplicationContext(),"Mời bạn tiếp tục chọn");
                                        CheckConnection.ShowToast_Short(getApplicationContext(),"Bạn đã thêm thành công. Người quản lý sẽ liên hệ với bạn");
                                    }else{
                                        CheckConnection.ShowToast_Short(getApplicationContext(),"Dữ liệu bị lỗi");
                                    }
                                }
                            }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {

                                }
                            }){
                                @Override
                                protected Map<String, String> getParams() throws AuthFailureError {
                                    JSONArray jsonArray = new JSONArray();
                                    for(int i=0;i<MainActivity.manggiohang.size();i++)
                                    {
                                        JSONObject jsonObject = new JSONObject();
                                        try {
                                            jsonObject.put("madonhang",madonhang);
                                            jsonObject.put("maphong",MainActivity.manggiohang.get(i).getIdsp());
                                            jsonObject.put("tenphong",MainActivity.manggiohang.get(i).getTensp());
                                            jsonObject.put("giaphong",MainActivity.manggiohang.get(i).getGiasp());
                                            jsonObject.put("soluongphong",MainActivity.manggiohang.get(i).getSoluongsp());

                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                        jsonArray.put(jsonObject);
                                    }
                                    HashMap<String,String> hashMap = new HashMap<String, String>();
                                    hashMap.put("json",jsonArray.toString());
                                    return hashMap;
                                }
                            };
                            queue.add(request);
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            HashMap<String,String> hashMap= new HashMap<String,String>();
                            hashMap.put("tenkhachhang",Name);
                            hashMap.put("sodienthoai",Phone);
                            hashMap.put("email",email);
                            return hashMap;
                        }
                    };
                    requestQueue.add(stringRequest);

                }else{
                    CheckConnection.ShowToast_Short(getApplicationContext(),"Kiem Tra Lai Du Lieu");
                }
            }
        });
    }

    private void Anhxa() {
        edtenkhachhang=findViewById(R.id.edittexttenkhachhang);
        edsdt=findViewById(R.id.edittextsodienthoai);
        edemail=findViewById(R.id.edittextemail);
        btxacnhan=findViewById(R.id.bottonxacnhan);
        bttrove=findViewById(R.id.bottontrove);

    }
}
