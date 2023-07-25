package fr.johann.android.androidweatherapp.activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import fr.johann.android.androidweatherapp.R;
import fr.johann.android.androidweatherapp.adapters.FavoriteAdapter;
import fr.johann.android.androidweatherapp.databinding.ActivityFavoriteBinding;
import fr.johann.android.androidweatherapp.models.City;

import java.util.ArrayList;

import static androidx.core.view.accessibility.AccessibilityEventCompat.setAction;

public class FavoriteActivity extends AppCompatActivity {

    private ActivityFavoriteBinding binding;
    private ArrayList<City> mCities;
    //private RecyclerView mRecyclerView;
    private FavoriteAdapter mAdapter;
    private Context mContext = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityFavoriteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent = getIntent();
        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = binding.toolbarLayout;
        toolBarLayout.setTitle(getTitle());

        FloatingActionButton fab = binding.fab;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                // Récupérez le layout personnalisé
                View customLayout = getLayoutInflater().inflate(R.layout.dialog_add_favorite, null);
                final EditText editTextCity = (EditText) customLayout.findViewById(R.id.edit_text_dialog_city);
                builder.setView(customLayout);
                builder.setTitle("Ajouter une ville");
                builder.setMessage("Ajouter une ville");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        final EditText editTextCity = (EditText) customLayout.findViewById(R.id.edit_text_dialog_city);
                        Toast.makeText(mContext, editTextCity.getText(), Toast.LENGTH_SHORT).show();
                        String cityName = editTextCity.getText().toString();
                        City city = new City(cityName,"Légères pluies", "22°C", R.drawable.weather_rainy_grey);
                        mCities.add(city);
                        mAdapter.notifyDataSetChanged();
                        mAdapter.notifyItemInserted(mCities.size()-1);

                    }
                })
                        .setNegativeButton("ANNULER", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).show();
            }
        });
        Log.d("TAG", "FavoriteActivity: onCreate()");

        mCities = new ArrayList<>();
        City city1 = new City("Montréal", "Légères pluies", "22°C", R.drawable.weather_rainy_grey);
        City city2 = new City("New York", "Ensoleillé", "22°C", R.drawable.weather_sunny_grey);
        City city3 = new City("Paris", "Nuageux", "24°C", R.drawable.weather_foggy_grey);
        City city4 = new City("Toulouse", "Pluies modérées", "20°C", R.drawable.weather_rainy_grey);
        City city5 = new City("Montréal", "Légères pluies", "22°C", R.drawable.weather_rainy_grey);
        City city6 = new City("New York", "Ensoleillé", "22°C", R.drawable.weather_sunny_grey);
        City city7 = new City("Paris", "Nuageux", "24°C", R.drawable.weather_foggy_grey);
        City city8 = new City("Toulouse", "Pluies modérées", "20°C", R.drawable.weather_rainy_grey);
        City city9 = new City("Montréal", "Légères pluies", "22°C", R.drawable.weather_rainy_grey);
        City city10 = new City("New York", "Ensoleillé", "22°C", R.drawable.weather_sunny_grey);
        City city11 = new City("Paris", "Nuageux", "24°C", R.drawable.weather_foggy_grey);
        City city12 = new City("Toulouse", "Pluies modérées", "20°C", R.drawable.weather_rainy_grey);
        City city13 = new City("Montréal", "Légères pluies", "22°C", R.drawable.weather_rainy_grey);
        City city14 = new City("New York", "Ensoleillé", "22°C", R.drawable.weather_sunny_grey);
        City city15 = new City("Paris", "Nuageux", "24°C", R.drawable.weather_foggy_grey);
        City city16 = new City("Toulouse", "Pluies modérées", "20°C", R.drawable.weather_rainy_grey);
        City city17 = new City("Montréal", "Légères pluies", "22°C", R.drawable.weather_rainy_grey);
        City city18 = new City("New York", "Ensoleillé", "22°C", R.drawable.weather_sunny_grey);
        City city19 = new City("Paris", "Nuageux", "24°C", R.drawable.weather_foggy_grey);
        City city20 = new City("Toulouse", "Pluies modérées", "20°C", R.drawable.weather_rainy_grey);
        mCities.add(city1);
        mCities.add(city2);
        mCities.add(city3);
        mCities.add(city4);
        mCities.add(city5);
        mCities.add(city6);
        mCities.add(city7);
        mCities.add(city8);
        mCities.add(city9);
        mCities.add(city10);
        mCities.add(city11);
        mCities.add(city12);
        mCities.add(city13);
        mCities.add(city14);
        mCities.add(city15);
        mCities.add(city16);
        mCities.add(city17);
        mCities.add(city18);
        mCities.add(city19);
        mCities.add(city20);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        binding.mInclude.myRecyclerView.setLayoutManager(layoutManager);

        mAdapter = new FavoriteAdapter(this, mCities);
        binding.mInclude.myRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("TAG", "FavoriteActivity: onDestroy()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("TAG", "FavoriteActivity: onStart()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("TAG", "FavoriteActivity: onRestart()");
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.d("TAG", "FavoriteActivity: onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("TAG", "FavoriteActivity: onPause()");
    }
    @Override
    protected void onStop() {
        super.onStop();
        Log.d("TAG", "FavoriteActivity: onStop()");
    }
}