package com.example.nathapong.app52_masterjsoninandroid_connecttoitunewebservice;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nathapong.app52_masterjsoninandroid_connecttoitunewebservice.Model.ItunesStuff;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView txtType;
    TextView txtArtistName;
    TextView txtCollectionName;
    TextView txtTrackName;
    TextView txtKind;
    ImageView imgArt;
    Button btnGetData;

    String imgURL;
    Bitmap bitmap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        txtType = (TextView)findViewById(R.id.txtType);
        txtArtistName = (TextView)findViewById(R.id.txtArtistName);
        txtCollectionName = (TextView)findViewById(R.id.txtCollectionName);
        txtTrackName = (TextView)findViewById(R.id.txtTrackName);
        txtKind = (TextView)findViewById(R.id.txtKind);
        imgArt = (ImageView)findViewById(R.id.imgArt);
        btnGetData = (Button)findViewById(R.id.btnGetData);

        btnGetData.setOnClickListener(MainActivity.this);


    }

    @Override
    public void onClick(View view) {

        JSONItunesStuffTask jsonItunesStuffTask = new JSONItunesStuffTask(MainActivity.this);
        jsonItunesStuffTask.execute();

    }

    private class JSONItunesStuffTask extends AsyncTask<String, Void, ItunesStuff>{

        Context context;
        ProgressDialog progressDialog;

        public JSONItunesStuffTask (Context context){

            this.context = context;
            progressDialog = new ProgressDialog(context);
        }


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog.setTitle("Downloading Info From iTunes.....Please Wait");
            progressDialog.show();
        }



        @Override
        protected ItunesStuff doInBackground(String... strings) {

            ItunesStuff itunesStuff = new ItunesStuff();

            ItunesHTTPClient itunesHTTPClient = new ItunesHTTPClient(); // ควรสร้าง Object แล้ว reuse ใช้งาน

            String data = (itunesHTTPClient.getItunesStuffData());

            try {
                itunesStuff = JsonItunesParser.getItunesStuff(data);
                imgURL = itunesStuff.getArtistViewURL();
                bitmap = (itunesHTTPClient.getBitmapFromURL(imgURL));

            }catch (Throwable t){
                t.printStackTrace();
            }

            return itunesStuff;
        }



        @Override
        protected void onPostExecute(ItunesStuff itunesStuff) {
            super.onPostExecute(itunesStuff);

            txtType.setText(itunesStuff.getType());
            txtArtistName.setText(itunesStuff.getArtistName());
            txtCollectionName.setText(itunesStuff.getCollectionName());
            txtTrackName.setText(itunesStuff.getTrackName());
            txtKind.setText(itunesStuff.getKind());
            imgArt.setImageBitmap(bitmap);

            if (progressDialog.isShowing()){
                progressDialog.dismiss();
            }
        }
    }
}
