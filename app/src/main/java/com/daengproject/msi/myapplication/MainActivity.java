package com.daengproject.msi.myapplication;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.daengproject.msi.myapplication.Activity.Main2Activity;

public class MainActivity extends AppCompatActivity {


    private EditText edt_send;
    private Button btn_send, btn_rv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edt_send = (EditText) findViewById(R.id.txt_send);
        btn_send = (Button) findViewById(R.id.btn_send);
        btn_rv = (Button) findViewById(R.id.btn_rv);

        btn_rv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Main2Activity.class);
                startActivity(i);
            }
        });

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = edt_send.getText().toString();
                String number= "+6285398420385";
                number = number.replace("+","").replace(" ","");
                if(text.length()!=0){
                    PackageManager pm = getPackageManager();
                    try {
                        Intent wa = new Intent(Intent.ACTION_SEND);

                        PackageInfo info = pm.getPackageInfo("com.whatsapp", PackageManager.GET_META_DATA);
                        wa.putExtra(Intent.EXTRA_TEXT,text);
                        wa.putExtra("jid", PhoneNumberUtils.stripSeparators(number)+"@s.whatsapp.net");
                        wa.setPackage("com.whatsapp");
                        wa.setType("text/plain");
                        startActivity(Intent.createChooser(wa,"share with"));
                    } catch (PackageManager.NameNotFoundException e) {
                        Uri uri = Uri.parse("market://details?id=com.whatsapp");
                        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
                        Toast.makeText(getApplicationContext(), "WhatsApp not Installed",
                                Toast.LENGTH_SHORT).show();
                        startActivity(goToMarket);
                    }
                }else{
                    edt_send.setError("harap isi");
                }
            }
        });
    }
}
