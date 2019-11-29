package com.sport.AlkaicerTV.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.sport.AlkaicerTV.DetailsActivity;
import com.sport.AlkaicerTV.R;
import com.sport.AlkaicerTV.models.EpiModel;

import java.util.ArrayList;
import java.util.List;

public class DirectorApater extends RecyclerView.Adapter<DirectorApater.OriginalViewHolder> {

    final DirectorApater.OriginalViewHolder[] viewHolderArray = {null};
    DirectorApater.OriginalViewHolder viewHolder;
    private List<EpiModel> items = new ArrayList<>();
    private Context ctx;
    private DirectorApater.OnItemClickListener mOnItemClickListener;

    public DirectorApater(Context context, List<EpiModel> items, String name) {
        ArrayList<EpiModel> arrayList = new ArrayList<>();
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getSeson().equals(name)) {
                arrayList.add(items.get(i));
            }
        }

        this.items = arrayList;
        ctx = context;
    }

    public void setOnItemClickListener(OnItemClickListener mItemClickListener) {
        this.mOnItemClickListener = mItemClickListener;
    }

    @Override
    public DirectorApater.OriginalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        DirectorApater.OriginalViewHolder vh;
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_director_name, parent, false);
        vh = new DirectorApater.OriginalViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final DirectorApater.OriginalViewHolder holder, final int position) {

        final EpiModel obj = items.get(position);
        holder.name.setText("Episode : " + obj.getEpi());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new DetailsActivity().iniMoviePlayer(obj.getStreamURL(), obj.getServerType(), ctx);

                chanColor(viewHolderArray[0], position);
                holder.name.setTextColor(ctx.getResources().getColor(R.color.colorPrimary));
                viewHolderArray[0] = holder;


            }
        });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    private void chanColor(DirectorApater.OriginalViewHolder holder, int pos) {

        if (holder != null) {
            holder.name.setTextColor(ctx.getResources().getColor(R.color.grey_60));
        }


    }

    public interface OnItemClickListener {
        void onItemClick(View view, EpiModel obj, int position, OriginalViewHolder holder);
    }

    public class OriginalViewHolder extends RecyclerView.ViewHolder {

        public TextView name;
        public CardView cardView;


        public OriginalViewHolder(View v) {
            super(v);
            name = v.findViewById(R.id.name);
            cardView = v.findViewById(R.id.card_view_home);
        }
    }

}