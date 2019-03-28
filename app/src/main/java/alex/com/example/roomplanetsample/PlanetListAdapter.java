package alex.com.example.roomplanetsample;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class PlanetListAdapter extends RecyclerView.Adapter<PlanetListAdapter.PlanetViewHolder>{


    class PlanetViewHolder extends RecyclerView.ViewHolder {
        private final TextView planetItemView;

        private PlanetViewHolder(View itemView) {
            super(itemView);
            planetItemView = itemView.findViewById(R.id.textView);
        }
    }

    private final LayoutInflater mInflater;
    private List<Planet> mPlanets;

    PlanetListAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    @Override
    public PlanetViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new PlanetViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PlanetViewHolder holder, int position) {
        if (mPlanets != null) {
            Planet current = mPlanets.get(position);
            holder.planetItemView.setText(current.getName());
        } else {
            // Covers the case of data not being ready yet.
            holder.planetItemView.setText("No Planet");
        }
    }

    void setPlanets(List<Planet> planets){
        mPlanets = planets;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mPlanets != null)
            return mPlanets.size();
        else return 0;
    }

}
