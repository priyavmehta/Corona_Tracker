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

public class DetailState extends AppCompatActivity {

    private int positionCountry;
    private TextView tvStateName,tvStateConfirmed,tvStateDeaths,tvStateActive,tvStateRecovered,tvStateLastUpdated;
    PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_state);

        Intent intent = getIntent();
        positionCountry = intent.getIntExtra("position",0);

        getSupportActionBar().setTitle("Details of "+IndianStates.stateModels.get(positionCountry).getStateName());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        tvStateName = findViewById(R.id.tvStateName);
        tvStateConfirmed = findViewById(R.id.tvDetailStateCases);
        tvStateActive = findViewById(R.id.tvDetailStateActive);
        tvStateRecovered = findViewById(R.id.tvDetailStateRecovered);
        tvStateDeaths = findViewById(R.id.tvDetailStateDeaths);
        tvStateLastUpdated = findViewById(R.id.tvDetailLastUpdated);

        pieChart = findViewById(R.id.piechart);

        tvStateName.setText(IndianStates.stateModels.get(positionCountry).getStateName());
        tvStateConfirmed.setText(IndianStates.stateModels.get(positionCountry).getConfirmed());
        tvStateDeaths.setText(IndianStates.stateModels.get(positionCountry).getDeaths());
        tvStateActive.setText(IndianStates.stateModels.get(positionCountry).getActive());
        tvStateRecovered.setText(IndianStates.stateModels.get(positionCountry).getRecovered());
        tvStateLastUpdated.setText(IndianStates.stateModels.get(positionCountry).getLastUpdated());

        pieChart.addPieSlice(new PieModel("Cases",Integer.parseInt(tvStateConfirmed.getText().toString()), Color.parseColor("#FFA726")));
        pieChart.addPieSlice(new PieModel("Recovered",Integer.parseInt(tvStateRecovered.getText().toString()), Color.parseColor("#66BB6A")));
        pieChart.addPieSlice(new PieModel("Deaths",Integer.parseInt(tvStateDeaths.getText().toString()), Color.parseColor("#EF5350")));
        pieChart.addPieSlice(new PieModel("Active",Integer.parseInt(tvStateActive.getText().toString()), Color.parseColor("#29B6F6")));
        pieChart.startAnimation();


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}
