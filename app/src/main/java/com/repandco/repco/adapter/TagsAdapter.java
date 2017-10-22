package com.repandco.repco.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.repandco.repco.ManagerActivity;
import com.repandco.repco.R;
import com.repandco.repco.constants.Keys;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.repandco.repco.FirebaseConfig.mAuth;
import static com.repandco.repco.constants.Values.SIZES.TAG_SIZES;


public class TagsAdapter extends RecyclerView.Adapter<TagsAdapter.TagHolder> {

    private ArrayList<String> tags_List;
    private ManagerActivity manager;
    private boolean create;
    private CardView tags_card;
    private Activity act;

    public void setTags_card(CardView tags_card) {
        this.tags_card = tags_card;
    }

    public TagsAdapter(ManagerActivity manager,Map<String, Boolean> tags) {
        tags_List = new ArrayList<>(tags.keySet());
        this.manager = manager;
        this.create = false;
    }

    public TagsAdapter(ManagerActivity manager) {
        tags_List = new ArrayList<>();
        this.manager = manager;
        this.create = false;
    }

    public TagsAdapter(ManagerActivity manager,boolean create) {
        tags_List = new ArrayList<>();
        this.manager = manager;
        this.create = create;
    }

    public TagsAdapter(Activity act,Map<String, Boolean> tags) {
        tags_List = new ArrayList<>(tags.keySet());
        this.manager = null;
        this.act = act;
        this.create = false;
    }

    public static class TagHolder extends RecyclerView.ViewHolder {

        public TextView tag;
        public CardView card;
        public TagHolder(View itemView) {
            super(itemView);

            card = (CardView) itemView.findViewById(R.id.card);
            tag = (TextView) itemView.findViewById(R.id.tag);
        }
    }

    @Override
    public TagHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v1 = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_tag,parent,false);
        TagHolder vh = new TagHolder(v1);
        return vh;
    }

    @Override
    public void onBindViewHolder(final TagHolder holder, final int position) {
        final String tagName = tags_List.get(position);
            if (tagName != null) {
                if (holder.tag != null) holder.tag.setText(tagName);
                if (holder.card != null) {
                    holder.card.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (!create){
                                if(manager!=null) manager.openSearh(tagName);
                                else{
                                    Intent intent = new Intent(act, ManagerActivity.class);
                                    intent.putExtra(Keys.TAG, tagName);
                                    act.startActivity(intent);
                                }
                            }
                            else {
                                tags_List.remove(position);
                                if(tags_List.size()==0) tags_card.setVisibility(View.GONE);
                                notifyDataSetChanged();
                            }
                        }
                    });
                }
            }
//        }
    }

    @Override
    public int getItemCount() {
        return tags_List.size();
    }

    public void addTag(String tag){
        if(tag.length()>0) {
            if (tags_List.size() == 0) tags_card.setVisibility(View.VISIBLE);
            if (tags_List.size() < TAG_SIZES) {
                if (!tags_List.contains(tag)) tags_List.add(tag);
                notifyDataSetChanged();
            }
        }
    }

    public Map<String,Boolean> getTags() {
        Map<String,Boolean> map = new HashMap<>();
        for (String tag : tags_List) map.put(tag,true);
        return map;
    }
}
