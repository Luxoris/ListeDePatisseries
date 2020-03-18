package com.brunofache.listedepatisseries;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SearchView searchView = findViewById(R.id.mySearchView);
        ArrayList<Patisserie> patisserieList = new ArrayList<>();
        initList(patisserieList);
        final TextView emptyListMessage = findViewById(R.id.EmptyList);
        final PatisserieList_Adapter adapter = new PatisserieList_Adapter(this,R.layout.patisserie,patisserieList);
        final ListView list = findViewById(R.id.myListView);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
                Patisserie selectedItem = (Patisserie) adapter.getItemAtPosition(position);
                Log.v("ListPersonnalisée", "Element selectionné : " + selectedItem.getName());
                /*Toast toaster = Toast.makeText(getApplicationContext(),"Element selectionné : " + selectedItem.getName(),Toast.LENGTH_SHORT);
                toaster.show();*/
                Intent i = new Intent(getBaseContext(), PatisseriePage.class);
                i.putExtra("Name", selectedItem.getName());
                i.putExtra("Description",selectedItem.getDescription());
                i.putExtra("Image",Integer.toString(selectedItem.getImage()));
                startActivity(i);
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // filter recycler view when query submitted
                adapter.getFilter().filter(query);
                //Affiche un message si la liste est vide
                if(adapter.getCount()==0){
                    emptyListMessage.setVisibility(View.VISIBLE);
                }else{
                    emptyListMessage.setVisibility(View.GONE);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                // filter recycler view when text is changed
                adapter.getFilter().filter(query);
                //Affiche un message si la liste est vide
                if(adapter.getCount()==0){
                    emptyListMessage.setVisibility(View.VISIBLE);
                }else{
                    emptyListMessage.setVisibility(View.GONE);
                }
                return false;
            }
        });
    }
    private void initList(ArrayList<Patisserie> patisserieList){
        Patisserie p = new Patisserie(getString(R.string.paris_brest),R.drawable.paris_brest,getString(R.string.paris_brest_description));
        patisserieList.add(p);

        p = new Patisserie(getString(R.string.tropezienne),R.drawable.tropezienne,getString(R.string.tropezienne_description));
        patisserieList.add(p);

        p = new Patisserie(getString(R.string.profiteroles),R.drawable.profiteroles,getString(R.string.profiteroles_description));
        patisserieList.add(p);

        p = new Patisserie(getString(R.string.saint_honore),R.drawable.saint_honore,getString(R.string.saint_honore_description));
        patisserieList.add(p);

        p = new Patisserie(getString(R.string.mille_feuille),R.drawable.mille_feuille,getString(R.string.mille_feuille_description));
        patisserieList.add(p);

        p = new Patisserie(getString(R.string.gateauBasque),R.drawable.gateau_basque,getString(R.string.gateauBasque_description));
        patisserieList.add(p);

        p = new Patisserie(getString(R.string.opera),R.drawable.opera,getString(R.string.opera_description));
        patisserieList.add(p);

        p = new Patisserie(getString(R.string.babaAuRhum),R.drawable.baba_au_rhum,getString(R.string.babaAuRhum_description));
        patisserieList.add(p);

        p = new Patisserie(getString(R.string.kouign_amann),R.drawable.kouign_amann,getString(R.string.kouign_amann_description));
        patisserieList.add(p);

        p = new Patisserie(getString(R.string.tarteTatin),R.drawable.tarte_tatin,getString(R.string.tarteTatin_description));
        patisserieList.add(p);

        p = new Patisserie(getString(R.string.eclair),R.drawable.eclair,getString(R.string.eclair_description));
        patisserieList.add(p);

        p = new Patisserie(getString(R.string.fraisier),R.drawable.fraisier,getString(R.string.fraisier_description));
        patisserieList.add(p);

    }
}
