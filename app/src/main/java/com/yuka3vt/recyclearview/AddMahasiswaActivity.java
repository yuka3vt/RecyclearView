package com.yuka3vt.recyclearview;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;

import cz.msebera.android.httpclient.Header;
import kotlin.jvm.internal.PropertyReference0Impl;

public class AddMahasiswaActivity extends AppCompatActivity {

    private Button _saveButton;
    private EditText _alamatEditText,_namaEditText,_nimEditText,_tahunMasukEditText,_tanggalLahirEditText;
    private EditText _tempatLahirEditText;
    private Spinner _jenisKelaminSpinner,_jpSpinner,_statusNikahSipnner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_mahasiswa);

        initInputs();
        initSaveButton();
    }

    private void initSaveButton(){
        _saveButton = findViewById(R.id.saveButton);

        _saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String alamat = _alamatEditText.getText().toString();
                String jenisKelamin = _jenisKelaminSpinner.getSelectedItem().toString();
                String jp = _jpSpinner.getSelectedItem().toString();
                String nama = _namaEditText.getText().toString();
                String nim = _nimEditText.getText().toString();
                String statusPernikahan = _statusNikahSipnner.getSelectedItem().toString();
                String tahunMasuk = _tahunMasukEditText.getText().toString();
                String tanggalLahir = _tanggalLahirEditText.getText().toString();
                String tempatLahir = _tempatLahirEditText.getText().toString();

                try {
                    alamat = URLEncoder.encode(alamat,"utf-8");
                    jenisKelamin = URLEncoder.encode(jenisKelamin,"utf-8");
                    jp = URLEncoder.encode(jp,"utf-8");
                    nama = URLEncoder.encode(nama,"utf-8");
                    nim = URLEncoder.encode(nim,"utf-8");
                    statusPernikahan = URLEncoder.encode(statusPernikahan,"utf-8");
                    tanggalLahir = URLEncoder.encode(tanggalLahir,"utf-8");
                    tempatLahir = URLEncoder.encode(tempatLahir,"utf-8");
                }catch (UnsupportedEncodingException e){
                    e.printStackTrace();
                }

                String url = "https://stmikpontianak.net/011100862/tambahMahasiswa.php"+
                        "?nim=" + nim +
                        "&nama=" + nama +
                        "&jenisKelamin=" + jenisKelamin +
                        "&tempatLahir=" + tempatLahir +
                        "&tanggalLahir=" + tanggalLahir +
                        "&alamat=" + alamat +
                        "&jp=" + jp +
                        "&statusPernikahan=" + statusPernikahan +
                        "&tahunMasuk=" + tahunMasuk;

                AsyncHttpClient ahc = new AsyncHttpClient();

                ahc.get(url, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        Log.d("*tw*", new String(responseBody));

                        new AlertDialog.Builder(AddMahasiswaActivity.this)
                                .setTitle("Berhasil")
                                .setMessage("Record berhasil disimpan")
                                .show();
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }

    private void initInputs(){
        _alamatEditText = findViewById(R.id.alamatEditText);
        _jenisKelaminSpinner = findViewById(R.id.jenisKelaminSpinner);
        _jpSpinner = findViewById(R.id.jpSpinner);
        _namaEditText = findViewById(R.id.namaEditText);
        _nimEditText = findViewById(R.id.nimEditText);
        _statusNikahSipnner = findViewById(R.id.statusNikahSpinner);
        _tahunMasukEditText = findViewById(R.id.tahunMasukEditText);
        _tanggalLahirEditText = findViewById(R.id.tanggalLahirEditText);
        _tempatLahirEditText = findViewById(R.id.tempatLahirEditText);
    }
}