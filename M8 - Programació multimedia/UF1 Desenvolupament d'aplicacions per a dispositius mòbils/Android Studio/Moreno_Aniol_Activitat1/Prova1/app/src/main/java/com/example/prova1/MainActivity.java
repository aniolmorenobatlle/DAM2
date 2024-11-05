package com.example.prova1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // Generals
    private TextView gestio_alumnes;

    private TextView nom_titol;
    private TextView cognoms_titol;
    private TextView edat_titol;
    private TextView poblacio_titol;

    private TextView nom;
    private TextView cognoms;
    private TextView edat;
    private TextView poblacio;



    // Alumne 1
    private TextView alumneView1;
    private Button alumne1;
    private ImageView alumneImage1;

    // Alumne 2
    private TextView alumneView2;
    private Button alumne2;
    private ImageView alumneImage2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        // General
        gestio_alumnes = findViewById(R.id.textView2);

        nom_titol = findViewById(R.id.textView3);
        cognoms_titol = findViewById(R.id.textView7);
        edat_titol = findViewById(R.id.textView9);
        poblacio_titol = findViewById(R.id.textView11);

        nom = findViewById(R.id.textView4);
        cognoms = findViewById(R.id.textView8);
        edat = findViewById(R.id.textView10);
        poblacio = findViewById(R.id.textView14);


        // Alumne 1
        alumneView1 = findViewById(R.id.textView2);
        alumne1 = findViewById(R.id.button);
        alumneImage1 = findViewById(R.id.imageView);


        alumne1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nomAlumne = getString(R.string.nom_alumne1);
                String cognomsAlumne = getString(R.string.cognoms_alumne1);
                String gestioAlumnes = nomAlumne + " " + cognomsAlumne;

                gestio_alumnes.setText(gestioAlumnes);

                alumneImage1.setImageResource(R.drawable.foto1);

                nom_titol.setText(getString(R.string.nom_titol));
                cognoms_titol.setText(getString(R.string.cognoms_titol));
                edat_titol.setText(getString(R.string.edat_titol));
                poblacio_titol.setText(getString(R.string.poblacio_titol));

                nom.setText(getString(R.string.nom_alumne1));
                cognoms.setText(getString(R.string.cognoms_alumne1));
                edat.setText(getString(R.string.edat_alumne1));
                poblacio.setText(getString(R.string.poblacio_alumne1));
            }
        });


        // Alumne 2
        alumneView2 = findViewById(R.id.textView2);
        alumne2 = findViewById(R.id.button2);
        alumneImage2 = findViewById(R.id.imageView);

        alumne2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nomAlumne = getString(R.string.nom_alumne2);
                String cognomsAlumne = getString(R.string.cognoms_alumne2);
                String gestioAlumnes = nomAlumne + " " + cognomsAlumne;

                gestio_alumnes.setText(gestioAlumnes);

                alumneImage2.setImageResource(R.drawable.foto2);

                nom_titol.setText(getString(R.string.nom_titol));
                cognoms_titol.setText(getString(R.string.cognoms_titol));
                edat_titol.setText(getString(R.string.edat_titol));
                poblacio_titol.setText(getString(R.string.poblacio_titol));

                nom.setText(getString(R.string.nom_alumne2));
                cognoms.setText(getString(R.string.cognoms_alumne2));
                edat.setText(getString(R.string.edat_alumne2));
                poblacio.setText(getString(R.string.poblacio_alumne2));
            }
        });

    }
}