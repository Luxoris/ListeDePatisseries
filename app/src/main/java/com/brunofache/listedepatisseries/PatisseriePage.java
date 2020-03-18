package com.brunofache.listedepatisseries;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class PatisseriePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patisserie_page);
        final TextView name = findViewById(R.id.name2);
        final TextView description = findViewById(R.id.description2);
        final ImageView image = findViewById(R.id.image2);
        Bundle extras = getIntent().getExtras();
        if(extras !=null)
        {
            final Patisserie p = new Patisserie(extras.getString("Name"), Integer.parseInt(extras.getString("Image")),extras.getString("Description"));
            name.setText(p.getName());
            description.setText(p.getDescription());
            image.setImageResource(p.getImage());
        }
    }
}
