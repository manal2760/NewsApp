package ma.ensaf.newsapp;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NewsRVAdapter extends RecyclerView.Adapter<NewsRVAdapter.ViewHolder>
{
    private ArrayList<Articles> articlesArrayList;
    private Context context;



    public NewsRVAdapter(ArrayList<Articles> articlesArrayList, Context context) {
        this.articlesArrayList = articlesArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public NewsRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.news_rv_items,parent,false);
        return new NewsRVAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsRVAdapter.ViewHolder holder, int position) {
        Articles articles= articlesArrayList.get(position);
        holder.subtitleTV.setText(articles.getDescription());
        holder.titleTV.setText(articles.getTitle());
        Picasso.get().load(articles.getUrlToImage()).into(holder.newsIV);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(context,NewsDetailsActivity.class);
                i.putExtra("title",articles.getTitle());
                i.putExtra("content",articles.getContent());
                i.putExtra("desc",articles.getDescription());
                i.putExtra("image",articles.getUrlToImage());
                i.putExtra("url",articles.getUrl());
                 context.startActivity(i);

            }
        });
        holder.bookmarkbutton.setOnClickListener(new View.OnClickListener() {
           // int check = 1;
            @Override
            public void onClick(View view) {
            //    if (check == 1) {
                    Toast.makeText(context, "article saved", Toast.LENGTH_SHORT).show();
                    holder.bookmarkbutton.setBackgroundResource(R.drawable.ic_baseline_bookmark_24);
                    FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                    String userId = currentUser.getUid();
                    holder.reference = FirebaseDatabase.getInstance().getReference().child("Users").child(userId).child("favoris");
                    favoris fav = new favoris();
                    fav.setTitle(articles.getTitle());
                    fav.setSubtitle(articles.getDescription());
                    fav.setImageUrl(articles.getUrlToImage());
                    fav.setUrl(articles.getUrl());
                    fav.setContent(articles.getContent());
                    holder.reference.push().setValue(fav);
                  //  check=0;
              //  }
               /* else{
                    holder.bookmarkbutton.setBackgroundResource(R.drawable.ic_baseline_bookmark_border_24);
                    check=1;
                }*/
            }
        });

    }

    @Override
    public int getItemCount() {
        return articlesArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        private TextView titleTV,subtitleTV;
        private ImageView newsIV;
        private ImageButton bookmarkbutton;

        DatabaseReference reference;
        FirebaseAuth mAuth;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            titleTV=itemView.findViewById(R.id.idTVNewsHeading);
            subtitleTV=itemView.findViewById(R.id.idTVSubTitle);
            newsIV=itemView.findViewById(R.id.idIVNews);
            bookmarkbutton=itemView.findViewById(R.id.bookmarkButton);

        }
    }
    public void filterList(ArrayList<Articles> filteredList) {
        articlesArrayList = filteredList;
        notifyDataSetChanged();
    }

}
