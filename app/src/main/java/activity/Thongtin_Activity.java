package activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;

import com.example.mstappdemo.R;

import ultil.CheckConnection;

public class Thongtin_Activity extends AppCompatActivity {
    Toolbar toolbaras;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thongtin_);
        Anhxa();
        if (CheckConnection.haveNetworkConnection(getApplicationContext())){
            ActionToolbar();
        }else{
            CheckConnection.ShowToast_Short(getApplicationContext(),"Bạn Hãy Kiểm Tra Lại Kết Nối");
            finish();
        }
    }

    private void Anhxa() {
        toolbaras = findViewById(R.id.toolbaras);
    }

    private void ActionToolbar() {
        setSupportActionBar(toolbaras);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        toolbaras.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}