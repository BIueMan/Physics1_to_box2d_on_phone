package com.mygdx.game;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    EditText e1;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //e1 = (EditText)findViewById(R.id.edMassege);

        button = (Button) findViewById(R.id.start_server);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String file_name = "/Download/outputExp.json";
                openActivity_box2d(file_name);
            }
        });
    }

    public void send(View v)
    {
        MassageSender massagesender = new MassageSender();
        massagesender.execute(e1.getText().toString());
    }

    public void send_image(View v)
    {
        String image_location = "/Download/egypt_kitty_mobile.jpg"; //"/Download/cat_in_egypt.jpeg";
        ImageSender imagesender = new ImageSender();
        imagesender.execute(image_location);
    }

    /*public void launch_box2d(View view) {
        openActivity_box2d();
    }*/

    public void openActivity_box2d(String file_name){
        String filepath = Environment.getExternalStorageDirectory() + file_name;
        // TODO: save this file into a default file that MyGdxGame can easly read, (data cant be send to this aplication :( )

        // --- load json file ---
        // String json_text = load_json(file_name);

        Intent intent = new Intent(this, AndroidLauncher.class);
        // intent.putExtra("json_file", filepath);
        startActivity(intent);
    }

    // TODO: change load_json to MyGdxGane... cant send data between main and there :(
    public String load_json(String file_name){
        File filepath = Environment.getExternalStorageDirectory();
        //File f = new File(filepath + file_name);

        FileInputStream fis = null;
        String json_text = "";
        try {
            fis = new FileInputStream(new File(filepath + file_name));
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String text;

            while ((text = br.readLine()) != null){
                sb.append(text).append("\n");
            }


            json_text = sb.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }  finally {
            if(fis != null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return json_text;
    }

    public void select_image(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 3);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && data != null){
            Uri selectedImage = data.getData();
            ImageView imageView = findViewById(R.id.imageView);
            imageView.setImageURI(selectedImage);
        }
    }

    /*public void launch_box2d(View view) {
        String file_name = "/Download/outputExp.json";
        openActivity_box2d(file_name);
    }*/
}