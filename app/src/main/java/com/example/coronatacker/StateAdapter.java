package com.example.coronatacker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class StateAdapter extends ArrayAdapter<StateModel> {

    private List<StateModel> stateModelList;
    private List<StateModel> stateModelFilteredList;
    private Context context;


    public StateAdapter(@NonNull Context context, List<StateModel> stateModelList) {
        super(context, R.layout.state_item_list, stateModelList);

        this.context = context;
        this.stateModelList = stateModelList;
        this.stateModelFilteredList = stateModelList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.state_item_list,null,true);

        TextView tvStateName = view.findViewById(R.id.tvStateName);
        TextView tvStateCases = view.findViewById(R.id.tvStateCases);

        tvStateName.setText(stateModelList.get(position).getStateName());
        tvStateCases.setText(stateModelList.get(position).getConfirmed());

        return view;
    }

    @Override
    public int getCount() {
        return stateModelList.size();
    }

    @Nullable
    @Override
    public StateModel getItem(int position) {
        return stateModelList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void filteredList(ArrayList<StateModel> filteredList) {
        stateModelList = filteredList;
        IndianStates.stateModels = filteredList;
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
//                    filterResults.count = stateModelList.size();
//                    filterResults.values = stateModelList;
//
//                }else{
//                    List<StateModel> resultsModel = new ArrayList<>();
//                    String searchStr = constraint.toString().toLowerCase();
//
//                    for(StateModel itemsModel:stateModelList){
//                        if(itemsModel.getStateName().toLowerCase().contains(searchStr)){
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
//                stateModelFilteredList = (List<StateModel>) results.values;
//                IndianStates.stateModels = (List<StateModel>) results.values;
//                notifyDataSetChanged();
//
//            }
//        };
//        return filter;
//    }
}
