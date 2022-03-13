package activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.mstappdemo.Lienhe_Activity;
import com.example.mstappdemo.R;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

import adapter.LoaispAdapter;
import adapter.SanphamAdapter;
import model.Giohang;
import model.Loaisp;
import model.Sanpham;
import ultil.CheckConnection;
import ultil.Server;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    ViewFlipper viewFlipper;
    RecyclerView recyclerViewmanhinhchinh;
    NavigationView navigationView;
    ListView listViewmanhinhchinh;
    DrawerLayout drawerLayout;
    ArrayList<Loaisp> mangloaisp;
    LoaispAdapter loaispAdapter;
    int id=0;
    String tenloaisp="";
    String hinhanhloaisp="";
    UserLocalStore userLocalStore;
    ArrayList<Sanpham> mangsanpham;
    SanphamAdapter sanphamAdapter;
    public static ArrayList<Giohang> manggiohang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userLocalStore  = new UserLocalStore(this);
        Anhxa();
        if(CheckConnection.haveNetworkConnection(getApplicationContext())){
            ActionBar();
            ActionViewFlipper();
            GetDuLieuLoaisp();
            GetDuLieuSPMoiNhat();
            CatchOnItemListview();
        }else {
            CheckConnection.ShowToast_Short(getApplicationContext(),"Bạn hãy kiểm tra lại kết nối");
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        User user = userLocalStore.getLoggedInUser();
        switch (item.getItemId()){
            case R.id.menugiohang:
                Intent intent = new Intent(getApplicationContext(), activity.Giohang.class);
                startActivity(intent);
                break;
            case R.id.menuLogout:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Logout");
                builder.setMessage("Bạn có muốn Đăng xuất\n"+"Username : "+user.username);
                builder.setCancelable(false);
                builder.setPositiveButton("Trở về", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this, "Mời bạn tiếp tục", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("Logout", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Code Logout
                        userLocalStore.clearUserData();
                        userLocalStore.setUserLoggedIn(false);
                        Intent loginIntent = new Intent(getApplicationContext(), ManDangNhap.class);
                        startActivity(loginIntent);
                        Toast.makeText(MainActivity.this, "Đăng xuất thành công", Toast.LENGTH_SHORT).show();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
        break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (authenticate() == true) {
            displayUserDetails();
//            userLocalStore.clearUserData();
//            userLocalStore.setUserLoggedIn(false);
//            Intent loginIntent = new Intent(this, ManDangNhap.class);
//            startActivity(loginIntent);
        }
    }

    private boolean authenticate() {
        if (userLocalStore.getLoggedInUser() == null) {
            Intent intent = new Intent(this, ManDangNhap.class);
            startActivity(intent);
            return false;
        }
        return true;
    }

    private void displayUserDetails() {
        User user = userLocalStore.getLoggedInUser();
        Toast.makeText(getApplicationContext(),
                 user.username,
                Toast.LENGTH_SHORT).show();

    }

    private void CatchOnItemListview() {
        listViewmanhinhchinh.setOnItemClickListener((parent, view, position, id) -> {
            switch (position){
                case 0:
                    if (CheckConnection.haveNetworkConnection(getApplicationContext())){
                        Intent intent = new Intent(MainActivity.this,MainActivity.class);
                        startActivity(intent);
                    }else{
                        CheckConnection.ShowToast_Short(getApplicationContext(),"Bạn hãy kiểm tra lại kết nối");
                    }
                    drawerLayout.closeDrawer(GravityCompat.START);
                    break;
                case 1:
                    if (CheckConnection.haveNetworkConnection(getApplicationContext())){
                        Intent intent = new Intent(MainActivity.this,Nhatrogiare_Activity.class);
                        intent.putExtra("idloaisanpham",mangloaisp.get(position).getID());
                        startActivity(intent);
                    }else{
                        CheckConnection.ShowToast_Short(getApplicationContext(),"Bạn hãy kiểm tra lại kết nối");
                    }
                    drawerLayout.closeDrawer(GravityCompat.START);
                    break;
                case 2:
                    if (CheckConnection.haveNetworkConnection(getApplicationContext())){
                        Intent intent = new Intent(MainActivity.this,KTX_Activity.class);
                        intent.putExtra("idloaisanpham",mangloaisp.get(position).getID());
                        startActivity(intent);
                    }else{
                        CheckConnection.ShowToast_Short(getApplicationContext(),"Bạn hãy kiểm tra lại kết nối");
                    }
                    drawerLayout.closeDrawer(GravityCompat.START);
                    break;
                case 3:
                    if (CheckConnection.haveNetworkConnection(getApplicationContext())){
                        Intent intent = new Intent(MainActivity.this,Hotel_Activity.class);
                        intent.putExtra("idloaisanpham",mangloaisp.get(position).getID());
                        startActivity(intent);
                    }else{
                        CheckConnection.ShowToast_Short(getApplicationContext(),"Bạn hãy kiểm tra lại kết nối");
                    }
                    drawerLayout.closeDrawer(GravityCompat.START);
                    break;
                case 4:
                    if (CheckConnection.haveNetworkConnection(getApplicationContext())){
                        Intent intent = new Intent(MainActivity.this,Homestay_Activity.class);
                        intent.putExtra("idloaisanpham",mangloaisp.get(position).getID());
                        startActivity(intent);
                    }else{
                        CheckConnection.ShowToast_Short(getApplicationContext(),"Bạn hãy kiểm tra lại kết nối");
                    }
                    drawerLayout.closeDrawer(GravityCompat.START);
                    break;
                case 5:
                    if (CheckConnection.haveNetworkConnection(getApplicationContext())){
                        Intent intent = new Intent(MainActivity.this, Lienhe_Activity.class);


                        startActivity(intent);
                    }else{
                        CheckConnection.ShowToast_Short(getApplicationContext(),"Bạn hãy kiểm tra lại kết nối");
                    }
                    drawerLayout.closeDrawer(GravityCompat.START);
                    break;
                case 6:
                    if (CheckConnection.haveNetworkConnection(getApplicationContext())){
                        Intent intent = new Intent(MainActivity.this,Thongtin_Activity.class);
                        startActivity(intent);
                    }else{
                        CheckConnection.ShowToast_Short(getApplicationContext(),"Bạn hãy kiểm tra lại kết nối");
                    }
                    drawerLayout.closeDrawer(GravityCompat.START);
                    break;
            }
        });
    }


    private void GetDuLieuSPMoiNhat() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.Duongdansanphammoinhat, response -> {
            if (response != null) {
                int ID = 0;
                String Tensanpham = "";
                Integer Giasanpham = 0;
                String Hinhanhsanpham = "";
                String Motasanpham = "";
                int IDSanpham = 0;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        ID = jsonObject.getInt("id");
                        Tensanpham = jsonObject.getString("tensp");
                        Giasanpham = jsonObject.getInt("giasp");
                        Hinhanhsanpham = jsonObject.getString("hinhanhsp");
                        Motasanpham = jsonObject.getString("motasp");
                        IDSanpham = jsonObject.getInt("idsanpham");
                        mangsanpham.add(new Sanpham(ID, Tensanpham, Giasanpham, Hinhanhsanpham, Motasanpham, IDSanpham));
                        sanphamAdapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    private void GetDuLieuLoaisp() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest =new JsonArrayRequest(Server.Duongdanloaisp, response -> {
            if(response!=null){
                for(int i=0;i<response.length();i++){
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        id = jsonObject.getInt("id");
                        tenloaisp =jsonObject.getString("tenloaisp");
                        hinhanhloaisp=jsonObject.getString("hinhanhloaisp");
                        mangloaisp.add(new Loaisp(id,tenloaisp,hinhanhloaisp));
                        loaispAdapter.notifyDataSetChanged();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                mangloaisp.add(5,new Loaisp(0,"MAPS","https://cdn0.iconfinder.com/data/icons/real-estate-241/32/10_Location_home_house_pin_gps-512.png"));
                mangloaisp.add(6,new Loaisp(0,"Thông Tin","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRKs9VQQbDTZj32nAtSjAuM6zwtgvt_MPPCxg&usqp=CAU"));
                loaispAdapter.notifyDataSetChanged();
            }
        }, error -> CheckConnection.ShowToast_Short(getApplicationContext(),error.toString()));
        requestQueue.add(jsonArrayRequest);
    }

    private void ActionViewFlipper() {
        ArrayList<String> mangquangcao = new ArrayList<>();
        mangquangcao.add ("https://ngocdiepwindow.vn/uploads/images/du-an/dh-pheenika-2.jpg");
        mangquangcao.add ("https://www.centralcons.vn/wp-content/uploads/2019/04/2-1024x576.jpg");
        mangquangcao.add ("https://www.cambridgeenglish.org/vn/Images/PhenikaaUni.jpg");
        mangquangcao.add ("https://upload.wikimedia.org/wikipedia/commons/d/df/Logo_PHENIKAA-UNI.jpg");
        for (int i =0; i<mangquangcao.size();i++){
            ImageView imageView =new ImageView(getApplicationContext());
            Picasso.get().load(mangquangcao.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }

        viewFlipper.setFlipInterval(5000);
        viewFlipper.setAutoStart(true);
        Animation animation_slide_in = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_in_right);
        Animation animation_slide_out = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_out_right);
        viewFlipper.setInAnimation(animation_slide_in);
        viewFlipper.setOutAnimation(animation_slide_out);


    }


    private void ActionBar() {
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        toolbar.setNavigationOnClickListener(v -> drawerLayout.openDrawer(GravityCompat.START));
    }



    private void Anhxa() {
        toolbar=this.findViewById(R.id.toolbarmanhinhchinh);
        viewFlipper =this.findViewById(R.id.viewflipper);
        recyclerViewmanhinhchinh=this.findViewById(R.id.recyclerview);
        navigationView =this.findViewById(R.id.navigationview);
        listViewmanhinhchinh =this.findViewById(R.id.listviewmanhinhchinh);
        drawerLayout =this.findViewById(R.id.drawerlayout);
        mangloaisp = new ArrayList<>();
        mangloaisp.add(new Loaisp(0, "Trang Chủ", "https://scontent.fhph2-1.fna.fbcdn.net/v/t1.15752-9/275209703_1687643311574894_5749057463967385439_n.png?_nc_cat=111&ccb=1-5&_nc_sid=ae9488&_nc_ohc=6NMNBMvnXvcAX9cv-XT&_nc_ht=scontent.fhph2-1.fna&oh=03_AVLbvRQx2v1v6igfnTTmIObrJcw4OKkOgG4SNzP86Wd1Uw&oe=62520664"));
        loaispAdapter = new LoaispAdapter(mangloaisp,getApplicationContext());
        listViewmanhinhchinh.setAdapter(loaispAdapter);
        mangsanpham =new ArrayList<>();
        sanphamAdapter = new SanphamAdapter(getApplicationContext(),mangsanpham);
        recyclerViewmanhinhchinh.setHasFixedSize(true);
        recyclerViewmanhinhchinh.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        recyclerViewmanhinhchinh.setAdapter(sanphamAdapter);
        if(manggiohang!=null){

        }else{
            manggiohang=new ArrayList<>();
        }
    }

}