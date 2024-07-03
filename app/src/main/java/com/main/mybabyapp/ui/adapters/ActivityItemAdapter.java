package com.main.mybabyapp.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.main.mybabyapp.R;
import com.main.mybabyapp.data.model.ActivityItem;
import com.main.mybabyapp.data.model.BottleFeed;
import com.main.mybabyapp.data.model.BreastFeed;
import com.main.mybabyapp.data.model.Diaper;

import java.text.SimpleDateFormat;
import java.util.List;

public class ActivityItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ActivityItem> activityList;
    private static final SimpleDateFormat daytimeFormat = new SimpleDateFormat("HH:mm");
    private static final int VIEW_TYPE_BREAST_FEED = 1;
    private static final int VIEW_TYPE_BOTTLE_FEED = 2;
    private static final int VIEW_TYPE_DIAPER = 3;

    public ActivityItemAdapter(List<ActivityItem> activityItemList) {
        this.activityList = activityItemList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        switch (viewType) {
            case VIEW_TYPE_BREAST_FEED:
                View breastView = inflater.inflate(R.layout.item_breast_feed, parent, false);
                return new BreastFeedViewHolder(breastView);
            case VIEW_TYPE_BOTTLE_FEED:
                View bottleView = inflater.inflate(R.layout.item_bottle_feed, parent, false);
                return new BottleFeedViewHolder(bottleView);
            case VIEW_TYPE_DIAPER:
                View diaperView = inflater.inflate(R.layout.item_diaper, parent, false);
                return new DiaperViewHolder(diaperView);
            default:
                throw new IllegalArgumentException("Unsupported view type");
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ActivityItem item = activityList.get(position);

        switch (holder.getItemViewType()) {
            case VIEW_TYPE_BREAST_FEED:
                ((BreastFeedViewHolder) holder).bind((BreastFeed) item);
                break;
            case VIEW_TYPE_BOTTLE_FEED:
                ((BottleFeedViewHolder) holder).bind((BottleFeed) item);
                break;
            case VIEW_TYPE_DIAPER:
                ((DiaperViewHolder) holder).bind((Diaper) item);
                break;
            default:
                throw new IllegalArgumentException("Unsupported view type");
        }
    }

    @Override
    public int getItemCount() {
        return activityList.size();
    }

    @Override
    public int getItemViewType(int position) {
        ActivityItem item = activityList.get(position);
        if (item instanceof BreastFeed) {
            return VIEW_TYPE_BREAST_FEED;
        } else if (item instanceof BottleFeed) {
            return VIEW_TYPE_BOTTLE_FEED;
        } else if (item instanceof Diaper) {
            return VIEW_TYPE_DIAPER;
        }
        return super.getItemViewType(position);
    }

    private static class BreastFeedViewHolder extends RecyclerView.ViewHolder {
        private TextView breastFeedLength, breastFeedTime;

        BreastFeedViewHolder(View itemView) {
            super(itemView);
            breastFeedLength = itemView.findViewById(R.id.breastFeedLength);
            breastFeedTime = itemView.findViewById(R.id.breastFeedTime);
        }

        void bind(BreastFeed breastFeed) {
            String formattedBreastFeedTime = daytimeFormat.format(breastFeed.getTime());
            breastFeedTime.setText(formattedBreastFeedTime);
            breastFeedLength.setText(String.valueOf(breastFeed.getLength()));
        }
    }

    private static class BottleFeedViewHolder extends RecyclerView.ViewHolder {
        private TextView bottleFeedQuantity, bottleFeedTime;

        BottleFeedViewHolder(View itemView) {
            super(itemView);
            bottleFeedQuantity = itemView.findViewById(R.id.bottleFeedQuantity);
            bottleFeedTime = itemView.findViewById(R.id.bottleFeedTime);
        }

        void bind(BottleFeed bottleFeed) {
            bottleFeedQuantity.setText(String.valueOf(bottleFeed.getQuantity()));
            bottleFeedTime.setText(daytimeFormat.format(bottleFeed.getTime()));
        }
    }

    private static class DiaperViewHolder extends RecyclerView.ViewHolder {
        private TextView diaperTime;
        private ImageView peeImage, poopImage;

        DiaperViewHolder(View itemView) {
            super(itemView);
            diaperTime = itemView.findViewById(R.id.diaperTime);
            peeImage = itemView.findViewById(R.id.peeImage);
            poopImage = itemView.findViewById(R.id.poopImage);
        }

        void bind(Diaper diaper) {
            diaperTime.setText(daytimeFormat.format(diaper.getTime()));
            if (diaper.getPee()) {
                peeImage.setVisibility(View.VISIBLE);
            }
            if (diaper.getPoop()) {
                poopImage.setVisibility(View.VISIBLE);
            }
        }
    }
}