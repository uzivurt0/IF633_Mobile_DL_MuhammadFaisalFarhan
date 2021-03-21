package com.example.uts_32039;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.MyViewHolder> {
    private Context mContext;
    private ArrayList<FileMusik> mFiles;

    MusicAdapter(Context mContext, ArrayList<FileMusik> mFiles) {
        this.mFiles = mFiles;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_list, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public int getItemCount(){
        return mFiles.size();
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position){
        holder.file_name.setText(mFiles.get(position).getTitle());
        holder.artist_name.setText(mFiles.get(position).getArtist());
        byte[] img = getAlbumArt(mFiles.get(position).getPath());
        if(img != null){
            Glide.with(mContext).asBitmap().load(img).into(holder.art_work);
        }
        else{
            Glide.with(mContext).load(R.drawable.stafaband).into(holder.art_work);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, PlayerActivity.class);
                intent.putExtra("position", position);
                mContext.startActivity(intent);
            }
        });
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView file_name, artist_name;
        ImageView art_work;
        public MyViewHolder(@NonNull View ItemView){
            super(ItemView);
            file_name = ItemView.findViewById(R.id.tvListLagu);
            art_work = ItemView.findViewById(R.id.imageListLagu);
            artist_name = ItemView.findViewById(R.id.tvListArtis);
        }
    }

    private byte[] getAlbumArt(String uri){
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        retriever.setDataSource(uri);
        byte[] art = retriever.getEmbeddedPicture();
        retriever.release();
        return art;
    }
}
