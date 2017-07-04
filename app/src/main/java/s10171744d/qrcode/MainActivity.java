package s10171744d.qrcode;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.DialogOnDeniedPermissionListener;
import com.karumi.dexter.listener.single.PermissionListener;

import org.w3c.dom.Text;

import mehdi.sakout.fancybuttons.FancyButton;

public class MainActivity extends AppCompatActivity {
    FancyButton btnScan;
    public static TextView txtQRCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PermissionListener dialogPermissionListener = //initialise listener + dialog builder
                DialogOnDeniedPermissionListener.Builder
                        .withContext(this)
                        .withTitle("Camera permission")
                        .withMessage("Camera permission is needed to take pictures of your cat")
                        .withButtonText(android.R.string.ok)
                        .build();
        Dexter.withActivity(this)
                .withPermission(Manifest.permission.CAMERA)
                .withListener(dialogPermissionListener)
                .check(); //opens dialog box using dialoglistener to request for permissions

        setContentView(R.layout.activity_main);
        btnScan = (FancyButton) findViewById(R.id.btnScan);
        txtQRCode = (TextView) findViewById(R.id.txtQRCode);
        btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ScanActivity.class);
                if (ContextCompat.checkSelfPermission(MainActivity.this,
                        Manifest.permission.CAMERA)== PackageManager.PERMISSION_GRANTED)
                {
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(MainActivity.this, "lol u got no perms for camera pls enable in settings", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}
