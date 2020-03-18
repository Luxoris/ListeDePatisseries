package com.brunofache.listedepatisseries;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class PatisserieList_Adapter extends ArrayAdapter<Patisserie> implements Filterable {
    private Context context;
    private List<Patisserie> patisserieList;
    private List<Patisserie> patisserieList_Filtered;
    private Resources res;
    private int viewRes;


    static class ViewHolder{
        TextView name;
        ImageView image;
        TextView description;

    }

    public PatisserieList_Adapter(Context context, int textViewResourceId, ArrayList<Patisserie> versions) {
        super(context,textViewResourceId,versions);
        this.patisserieList =versions;
        this.patisserieList_Filtered = versions;
        this.context=context;
        this.res=context.getResources();
        this.viewRes=textViewResourceId;
    }

    @Override //Il faut implémenter getView ici
    public View getView(int position, View ConvertView, ViewGroup parent) {
        View view = ConvertView;
        ViewHolder holder;
        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(viewRes, parent, false);
            //cf. explications effectuée dans cours sur
            holder = new ViewHolder();
            holder.name= (TextView) view.findViewById(R.id.name);
            holder.description = (TextView) view.findViewById(R.id.description);
            holder.image = (ImageView) view.findViewById(R.id.image) ;
            view.setTag(holder);
        }
        else {
            holder= (ViewHolder) view.getTag();
        }
        final Patisserie myPatisserie = patisserieList_Filtered.get(position); //renvoie la position dans la liste des patissserie filtré
        if (myPatisserie != null) {
            final String name = String.format("%s", myPatisserie.getName());
            holder.name.setText(name);
            final String description = String.format("%s : %s",res.getString(R.string.description_title), myPatisserie.getDescription());
            holder.description.setText(description);
            holder.image.setImageResource(myPatisserie.getImage());
        }
        return view;
    }

    @Override
    //Nombre d’éléments de la liste filtrée
    public int getCount() {
        return patisserieList_Filtered.size();
    }

    //implémentation du Filter
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    patisserieList_Filtered = patisserieList;
                } else {
                    List<Patisserie> filteredList = new ArrayList<>();
                    for (Patisserie row : patisserieList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getName().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }

                    patisserieList_Filtered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = patisserieList_Filtered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                patisserieList_Filtered = (ArrayList<Patisserie>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}

