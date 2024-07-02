package com.main.mybabyapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.main.mybabyapp.data.model.ActivityItem;
import com.main.mybabyapp.data.model.BottleFeed;
import com.main.mybabyapp.data.model.BreastFeed;
import com.main.mybabyapp.data.model.Diaper;

import java.util.List;

public class ActivityItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ActivityItem> activityList;
    private static final int VIEW_TYPE_BREAST_FEED = 1;
    private static final int VIEW_TYPE_BOTTLE_FEED = 2;
    private static final int VIEW_TYPE_DIAPER = 3;

    public void CombinedFeedAdapter(List<ActivityItem> feedList) {
        this.activityList = feedList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        switch (viewType) {
            case VIEW_TYPE_BREAST_FEED:
                // TODO: create item_breast_feed layout
                View breastView = inflater.inflate(R.layout.item_breast_feed, parent, false);
                return new BreastFeedViewHolder(breastView);
            case VIEW_TYPE_BOTTLE_FEED:
                // TODO: create item_breast_feed layout
                View bottleView = inflater.inflate(R.layout.item_bottle_feed, parent, false);
                return new BottleFeedViewHolder(bottleView);
            case VIEW_TYPE_DIAPER:
                // TODO: create item_breast_feed layout
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
        private TextView textDate;

        BreastFeedViewHolder(View itemView) {
            super(itemView);
            // TODO: Create textDate view
            textDate = itemView.findViewById(R.id.textDate);
        }

        void bind(BreastFeed breastFeed) {
            // TODO: Bind breast feed data
        }
    }

    private static class BottleFeedViewHolder extends RecyclerView.ViewHolder {
        private TextView textDate;

        BottleFeedViewHolder(View itemView) {
            super(itemView);
            textDate = itemView.findViewById(R.id.textDate);
        }

        void bind(BottleFeed bottleFeed) {
            // TODO: Bind bottle feed data
        }
    }

    private static class DiaperViewHolder extends RecyclerView.ViewHolder {
        private TextView textDate;

        DiaperViewHolder(View itemView) {
            super(itemView);
            textDate = itemView.findViewById(R.id.textDate);
        }

        void bind(Diaper diaper) {
            // TODO: Bind diaper data
        }
    }
}