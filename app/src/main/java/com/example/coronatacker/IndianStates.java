package com.example.coronatacker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.leo.simplearcloader.SimpleArcLoader;

import org.eazegraph.lib.models.PieModel;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class IndianStates extends AppCompatActivity {

    SimpleArcLoader simpleArcLoader;
    ListView listView;
    EditText edtSearch;

    public static List<StateModel> stateModels = new ArrayList<>();
    public static List<StateModel> stateModelsFiltered = new ArrayList<>();
    StateModel stateModel;
    StateAdapter stateAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indian_states);

        edtSearch = findViewById(R.id.edtSearch);
        listView = findViewById(R.id.listView);
        simpleArcLoader = findViewById(R.id.loader);

        getSupportActionBar().setTitle("Affected States ");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setBackgroundDrawable(
                new ColorDrawable(Color.parseColor("#330867")));


        fetchData();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                startActivity(new Intent(IndianStates.this,DetailState.class).putExtra("position",i));
            }
        });

        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

//                stateAdapter.getFilter().filter(charSequence);
//                stateAdapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable editable) {
                search(editable.toString());
            }
        });
    }

    private void search(String search) {
        ArrayList<StateModel> filteredList = new ArrayList<>();

        for (StateModel model:stateModelsFiltered){
            if (model.getStateName().toLowerCase().contains(search.toLowerCase())){
                filteredList.add(model);
            }
        }

        stateAdapter.filteredList(filteredList);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            stateModels.clear();
            stateModelsFiltered.clear();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK ) {

            stateModels.clear();
            stateModelsFiltered.clear();

            finish();


        }
        return super.onKeyDown(keyCode, event);
    }

    private void fetchData() {

        String url = "https://api.covid19india.org/data.json";
        simpleArcLoader.start();

        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        try {

                            JSONObject jsonObject = new JSONObject(response.toString());
                            JSONArray jsonArray = jsonObject.getJSONArray("statewise");


                            for (int i = 0; i < jsonArray.length(); i++){
                                JSONObject object = jsonArray.getJSONObject(i);

                                String stateName = object.getString("state");
                                String active = object.getString("active");
                                String deaths = object.getString("deaths");
                                String recovered = object.getString("recovered");
                                String confirmed = object.getString("confirmed");
                                String lastUpdated = object.getString("lastupdatedtime");

                                stateModel = new StateModel(active,confirmed,deaths,stateName,recovered,lastUpdated);
                                stateModels.add(stateModel);
                            }

                            stateAdapter = new StateAdapter(IndianStates.this,stateModels);
                            stateModelsFiltered = stateModels;
                            listView.setAdapter(stateAdapter);
                            simpleArcLoader.stop();
                            simpleArcLoader.setVisibility(View.GONE);

                            //Toast.makeText(IndianStates.this,Integer.toString(count),Toast.LENGTH_LONG).show();

                        } catch (JSONException e) {
                            e.printStackTrace();
                            simpleArcLoader.stop();
                            simpleArcLoader.setVisibility(View.GONE);
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                simpleArcLoader.stop();
                simpleArcLoader.setVisibility(View.GONE);
                Toast.makeText(IndianStates.this, error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }
}
