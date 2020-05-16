package com.example.coronatacker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MyCustomAdapter extends ArrayAdapter<CountryModel> {

    private Context context;
    private List<CountryModel> countryModelList;
    private List<CountryModel> countryModelListFiltered;

    public MyCustomAdapter(@NonNull Context context, List<CountryModel> countryModelList) {
        super(context, R.layout.list_item,countryModelList);

        this.context = context;
        this.countryModelList = countryModelList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,null,true);

        TextView tvCountry = view.findViewById(R.id.tvCountryName);
        ImageView imageFlag = view.findViewById(R.id.imageFlag);

        tvCountry.setText(countryModelList.get(position).getCountry());
        Glide.with(context).load(countryModelList.get(position).getFlag()).into(imageFlag);

        return view;
    }

    @Override
    public int getCount() {
        return countryModelList.size();
    }

//    @Nullable
//    @Override
//    public CountryModel getItem(int position) {
//        return countryModelList.get(position);
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }

    public void filteredList(List<CountryModel> filteredList) {

        countryModelList = filteredList;
        AffectedCountries.countryModels=filteredList;
        notifyDataSetChanged();
    }

//    @Override
//    public Filter getFilter() {
//        Filter filter = new Filter() {
//            @Override
//            protected FilterResults performFiltering(CharSequence constraint) {
//
//                FilterResults filterResults = new FilterResults();
//                if(constraint == null || constraint.length() == 0){
//                    filterResults.count = countryModelList.size();
//                    filterResults.values = countryModelList;
//
//                }else{
//                    List<CountryModel> resultsModel = new ArrayList<>();
//                    String searchStr = constraint.toString().toLowerCase();
//
//                    for(CountryModel itemsModel:countryModelList){
//                        if(itemsModel.getCountry().toLowerCase().contains(searchStr)){
//                            resultsModel.add(itemsModel);
//
//                        }
//                        filterResults.count = resultsModel.size();
//                        filterResults.values = resultsModel;
//                    }
//
//
//                }
//
//                return filterResults;
//            }
//
//            @Override
//            protected void publishResults(CharSequence constraint, FilterResults results) {
//
//                countryModelListFiltered = (List<CountryModel>) results.values;
//                AffectedCountries.countryModels = (List<CountryModel>) results.values;
//                notifyDataSetChanged();
//
//            }
//        };
//        return filter;
//    }
}
