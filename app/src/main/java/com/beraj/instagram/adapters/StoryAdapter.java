package com.beraj.instagram.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.beraj.instagram.R;
import com.beraj.instagram.models.StoryModel;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class StoryAdapter extends RecyclerView.Adapter<StoryAdapter.StoryViewHolder> {

    private List<StoryModel> listStories;
    private Context mContext;

    public StoryAdapter(List<StoryModel> listStories, Context mContext) {
        this.listStories = listStories;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public StoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.insta_story_card, parent, false);

        return new StoryViewHolder(view, mContext, listStories);
    }

    @Override
    public void onBindViewHolder(@NonNull StoryViewHolder holder, int position) {

        StoryModel myStory = listStories.get(0);
        int getMy = holder.getAdapterPosition();
        if(getMy == 0){
            holder.name.setText("Your Story");
            holder.story.setImageResource(myStory.getDailyPhoto());
            holder.addStory.setVisibility(View.VISIBLE);
        }
        else {
            StoryModel story = listStories.get(position);
            holder.story.setImageResource(story.getDailyPhoto());
            holder.name.setText(story.getName());
            holder.addStory.setVisibility(View.INVISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return listStories.size();
    }

    public class StoryViewHolder extends RecyclerView.ViewHolder{

        CircleImageView story, addStory;
        TextView name;
        Context mContext;
        List<StoryModel> list;

        public StoryViewHolder(@NonNull View itemView, final Context mContext, final List<StoryModel> list) {
            super(itemView);
            story = itemView.findViewById(R.id.imgInstaStory);
            name = itemView.findViewById(R.id.tvStoryBy);
            addStory = itemView.findViewById(R.id.imgInstaStoryAdd);
            this.mContext = mContext ;
            this.list = list;

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    StoryModel story = list.get(getAdapterPosition());
                    Toast.makeText(mContext, "" + story.getName(), Toast.LENGTH_SHORT).show();

                }
            });

        }

    }
}
