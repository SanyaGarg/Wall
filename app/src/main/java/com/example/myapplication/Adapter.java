package com.example.myapplication;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.example.myapplication.Post;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    private List<Post> posts;
    private Context context;
    private List<String> img;
    private OnItemClickListener onItemClickListener;

    public Adapter(List<Post> posts,Context context){
        this.posts = posts;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();          //
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        View view = LayoutInflater.from(context).inflate(R.layout.post,parent,false);
        return new MyViewHolder(view,onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holders, int position) {
        final MyViewHolder holder = holders;
        Post model = posts.get(position);
        List<HorizontalPost> imgs = model.getMedia();

        HorizontalRecyclerViewAdapter horizontalRecyclerViewAdapter = new HorizontalRecyclerViewAdapter(context,imgs);
        holder.recyclerView.setHasFixedSize(true);
        holder.recyclerView.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));

        holder.recyclerView.setAdapter(horizontalRecyclerViewAdapter);


        RequestOptions requestOptions = new RequestOptions();
        requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL);
        requestOptions.centerCrop();



        holder.author.setText(model.getUserId());
        holder.likes.setText(model.getLikes());
        holder.text.setText(model.getText());
        holder.date.setText(model.getDate());
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener{
        void onItemClick(View view, int position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView author,likes,comments,text,date;
        ImageView imageView;
       // ProgressBar progressBar;
        OnItemClickListener onItemClickListener;

        RecyclerView recyclerView;

        public MyViewHolder(@NonNull View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);

            itemView.setOnClickListener(this);
            author = itemView.findViewById(R.id.user);
            likes = itemView.findViewById(R.id.likes);
            //comments = itemView.findViewById(R.id.comments);
            text = itemView.findViewById(R.id.text);
            imageView = itemView.findViewById(R.id.img);
            date = itemView.findViewById(R.id.date);


            this.onItemClickListener = onItemClickListener;

            recyclerView = (RecyclerView)itemView.findViewById(R.id.recyclerView);
        }

        @Override
        public void onClick(View v) {
            onItemClickListener.onItemClick(v,getAdapterPosition());
        }
    }
}
