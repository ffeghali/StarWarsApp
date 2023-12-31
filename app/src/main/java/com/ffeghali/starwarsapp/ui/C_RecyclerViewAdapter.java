package com.ffeghali.starwarsapp.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ffeghali.starwarsapp.R;
import com.ffeghali.starwarsapp.model.CharacterModel;

import java.util.ArrayList;

public class C_RecyclerViewAdapter extends RecyclerView.Adapter<C_RecyclerViewAdapter.MyViewHolder> {
    private final RecyclerViewInterface recyclerViewInterface;
    Context context;
    ArrayList<CharacterModel> characterModels;

    public C_RecyclerViewAdapter(RecyclerViewInterface recyclerViewInterface, Context context, ArrayList<CharacterModel> characterModels) {
        this.recyclerViewInterface = recyclerViewInterface;
        this.context = context;
        this.characterModels = characterModels;
    }

    public void setSearchedList(ArrayList<CharacterModel> searchedList){
        this.characterModels = searchedList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public C_RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // This is where you inflate the layout which gives a look to each of our rows in the list
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_view_row, parent, false);

        return new C_RecyclerViewAdapter.MyViewHolder(view, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull C_RecyclerViewAdapter.MyViewHolder holder, int position) {
        // Assigns values (or changing the data) to each recycler_view_row
        // Based on the position of the recycler view (it sorts what is coming in and out)
        holder.tvName.setText(characterModels.get(position).getName());

        if (characterModels.get(position).isFavorite()) {
            holder.ivFavorite.setImageResource(R.drawable.baseline_favorite_24);
        }
        else {
            holder.ivFavorite.setImageResource(R.drawable.baseline_favorite_border_24);
        }
    }

    @Override
    public int getItemCount() {
        // Recycler view needs to know how many items we have in total

        return characterModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        // Grabs all the different views from recycler_view_row layout and assigns them to variables
        // Similar to an onCreate method

        TextView tvName;
        ImageView ivFavorite;
        public MyViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);

            // Name Button
            tvName = itemView.findViewById(R.id.cardName);
            tvName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (recyclerViewInterface != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            recyclerViewInterface.onNameClick(position);
                        }
                    }
                }
            });

            // Favorite Button
            ivFavorite = itemView.findViewById(R.id.cardFav);
            ivFavorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (recyclerViewInterface != null) {
                        int position = getAdapterPosition();

                        if (position != RecyclerView.NO_POSITION) {
                            recyclerViewInterface.onFavClick(position);
                        }
                    }
                }
            });
        }
    }
}
