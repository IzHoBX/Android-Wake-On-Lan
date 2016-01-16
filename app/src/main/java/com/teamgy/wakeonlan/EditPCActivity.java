package com.teamgy.wakeonlan;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

/**
 * Created by Jakov on 01/11/2015.
 */
public class EditPCActivity extends AppCompatActivity {

    private EditText editMac;
    private EditText editSSID;
    private PCInfo pcinfo;
    private onPCInfoAddedListener listener;
    private boolean editMode;
    private int mode;
    private int positon;

    private AppCompatActivity activity;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_pc);
        Toolbar toolbar =(Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_check_white_24dp);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        Intent i = getIntent();
        Bundle bundle = i.getExtras();
        mode = bundle.getInt("mode");
        String macAdress = bundle.getString("macAdress");
        String ssid = bundle.getString("ssid");
        pcinfo = new PCInfo(macAdress,ssid);

        editMac = (EditText)findViewById(R.id.edit_mac);
        editSSID = (EditText)findViewById(R.id.edit_ssid);
        //Log.d("d", "meme");

        if(mode == MainActivity.REQUEST_ADD){

            getSupportActionBar().setTitle("Add New PC");
            //we are creating a new pc then
            //layout is fine since we have hints there
            editMode = false;
        }
        else{
            //its edit
            getSupportActionBar().setTitle("Edit PC");

            editMac.setText(pcinfo.getMacAdress());
            editSSID.setText(pcinfo.getPcName());
            positon = bundle.getInt("position");
            editMode = true;

        }

        editMac.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

               reformatMac();


            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    public void addOnPCInfoAddedListener(onPCInfoAddedListener lst){
        this.listener = lst;
    }

    @Override
    public void onBackPressed() {
        Intent data = new Intent();
        setResult(RESULT_CANCELED, data);
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d("edit", "called it");
        if(item.getItemId() == android.R.id.home){
            reformatMac();
            applyResult();
            finish();//very sketch, why do you do this android, just call the method in main :((
            return true;
        }
        if(item.getItemId() == R.id.menu_pc_trash){
            Intent data = new Intent();
            data.putExtra("position", positon);
            setResult(MainActivity.RESULT_DELETE, data);
            finish();

        }
        return super.onOptionsItemSelected(item);
    }

    private void applyResult(){
        reformatMac();
        Intent data = new Intent();
        Log.d("",editMac.getText().toString());
        data.putExtra("macAdress",editMac.getText().toString());
        data.putExtra("ssid",editSSID.getText().toString());
        data.putExtra("position", positon); //TODO PLEASE CHANGE THIS ITS DUMB
        setResult(RESULT_OK, data);

    }

    public interface onPCInfoAddedListener{

        void onPcInfoAdded(PCInfo pcInfo,boolean editMode);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_pc_activity, menu);
        return true;
    }
    private void reformatMac(){

        String inputText = editMac.getText().toString();
        String formatedText = Tools.reformatMACInput(inputText);
        if(!inputText.equals(formatedText)){
            Log.d("debug", "input: " + inputText + " format: " + formatedText);
            editMac.setText(Tools.reformatMACInput(editMac.getText().toString()));
            Snackbar.make(findViewById(R.id.edit_pc_view),"Reformatted MAC to application format",Snackbar.LENGTH_SHORT).show();

        }

    }


}
