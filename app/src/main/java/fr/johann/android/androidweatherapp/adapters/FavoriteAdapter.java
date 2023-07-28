package fr.johann.android.androidweatherapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import fr.johann.android.androidweatherapp.R;
import fr.johann.android.androidweatherapp.models.City;

import java.util.ArrayList;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<City> mCities;

    // Constructor
    public FavoriteAdapter(Context mContext, ArrayList<City> mCities) {
        this.mContext = mContext;
        this.mCities = mCities;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_favorite_city, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        City city = mCities.get(position);
        holder.mTextViewCityName.setText(city.mName);
        holder.mTextViewCityTemp.setText(city.mTemperature);
        holder.mTextViewCityWeather.setText(city.mDescription);
    }


    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return mCities.size();
    }

    // Classe holder qui contient la vue d’un item
    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView mTextViewCityName;
        private final TextView mTextViewCityTemp;
        private final TextView mTextViewCityWeather;

        public ViewHolder(View view) {
            super(view);
            mTextViewCityName = (TextView) view.findViewById(R.id.textViewItemCityName);
            mTextViewCityTemp = (TextView) view.findViewById(R.id.textViewItemDescription);
            mTextViewCityWeather = (TextView) view.findViewById(R.id.textViewItemTemperature);
            view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    mCities.remove(getBindingAdapterPosition());
                    notifyItemRemoved(getBindingAdapterPosition());
                    return true;
                }
            });
        }
    }
}
