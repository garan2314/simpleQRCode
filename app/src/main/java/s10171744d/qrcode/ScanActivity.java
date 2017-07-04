package s10171744d.qrcode;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ScanActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler{

    private ZXingScannerView mScannerView; //initalise the scanner
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mScannerView = new ZXingScannerView(this);
        setContentView(mScannerView); //show QR scanner instead of the defalut layout
    }

    @Override
    public void handleResult(Result result) {
        MainActivity.txtQRCode.setText(result.getText());
        onBackPressed();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this);
        mScannerView.startCamera(); //start the camera
    }

    @Override
    protected void onPause() {
        super.onPause();
        mScannerView.stopCamera(); //stop the camera
    }
}
