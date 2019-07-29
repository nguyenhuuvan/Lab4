package com.poly.lab4.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.poly.lab4.R;
import com.poly.lab4.modelCategory.Category;
import com.poly.lab4.modelPost.Post;

import java.util.List;

public class PostAdapter extends BaseAdapter {
    private Context mContext;
    private List<Post> postList;

    public PostAdapter(Context mContext, List<Post> postList) {
        this.mContext = mContext;
        this.postList = postList;
    }

    @Override
    public int getCount() {
        return postList.size();
    }
    public void changeDataset(List<Post> items) {
        this.postList = items;
        notifyDataSetChanged();
    }
    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View grid;
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (view == null) {
            grid = new View(mContext);
            grid = inflater.inflate(R.layout.item_post, null);
            TextView textView = (TextView) grid.findViewById(R.id.tvNamePost);
            ImageView imageView = (ImageView) grid.findViewById(R.id.imgPost);
            textView.setText(postList.get(i).getTitle().getRendered());
            try {
                Glide
                        .with(mContext)
                        .load(postList.get(i).getContent().getRendered().substring(postList.get(i).getContent().getRendered().lastIndexOf("http"), postList.get(i).getContent().getRendered().lastIndexOf(".jpg") + 4))
                        .centerCrop()
                        .into(imageView);
            } catch (Exception e) {

            }

        } else {
            grid = (View) view;
        }
        return grid;
    }
}
