package com.example.coronatacker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

public class DetailActivity extends AppCompatActivity {

    private int positionCountry;
    TextView tvCountry,tvCases,tvRecovered,tvCritical,tvActive,tvTodayCases,tvTotalDeaths,tvTodayDeaths;
    PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        positionCountry = intent.getIntExtra("position",0);

        getSupportActionBar().setTitle("Details of "+AffectedCountries.countryModels.get(positionCountry).getCountry());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);



        tvCountry = findViewById(R.id.tvDetailCountry);
        tvCases = findViewById(R.id.tvDetailCases);
        tvRecovered = findViewById(R.id.tvDetailRecovered);
        tvCritical = findViewById(R.id.tvDetailCritical);
        tvActive = findViewById(R.id.tvDetailActive);
        tvTodayCases = findViewById(R.id.tvDetailTodayCases);
        tvTotalDeaths = findViewById(R.id.tvDetailDeaths);
        tvTodayDeaths = findViewById(R.id.tvDetailTodayDeaths);

        pieChart = findViewById(R.id.piechart);

        tvCountry.setText(AffectedCountries.countryModels.get(positionCountry).getCountry());
        tvCases.setText(AffectedCountries.countryModels.get(positionCountry).getCases());
        tvRecovered.setText(AffectedCountries.countryModels.get(positionCountry).getRecovered());
        tvCritical.setText(AffectedCountries.countryModels.get(positionCountry).getCritical());
        tvActive.setText(AffectedCountries.countryModels.get(positionCountry).getActive());
        tvTodayCases.setText(AffectedCountries.countryModels.get(positionCountry).getTodayCases());
        tvTotalDeaths.setText(AffectedCountries.countryModels.get(positionCountry).getDeaths());
        tvTodayDeaths.setText(AffectedCountries.countryModels.get(positionCountry).getTodayDeaths());

        pieChart.addPieSlice(new PieModel("Cases",Integer.parseInt(tvCases.getText().toString()), Color.parseColor("#FFA726")));
        pieChart.addPieSlice(new PieModel("Recovered",Integer.parseInt(tvRecovered.getText().toString()), Color.parseColor("#66BB6A")));
        pieChart.addPieSlice(new PieModel("Deaths",Integer.parseInt(tvTotalDeaths.getText().toString()), Color.parseColor("#EF5350")));
        pieChart.addPieSlice(new PieModel("Active",Integer.parseInt(tvActive.getText().toString()), Color.parseColor("#29B6F6")));
        pieChart.startAnimation();



    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}
