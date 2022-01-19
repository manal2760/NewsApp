package ma.ensaf.newsapp;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class FavsRVAdapter extends RecyclerView.Adapter<FavsRVAdapter.ViewHolder>
{
    private ArrayList<Articles> articlesArrayList;
    private Context context;

    public FavsRVAdapter(ArrayList<Articles> articlesArrayList, Context context) {
        this.articlesArrayList = articlesArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public FavsRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.news_rv_items2,parent,false);
        return new FavsRVAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavsRVAdapter.ViewHolder holder, int position) {
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
        holder.removebookmarkbutton.setOnClickListener(new View.OnClickListener() {
            int check = 0;
            @Override
            public void onClick(View view) {
                if (check == 1) {
                    holder.removebookmarkbutton.setBackgroundResource(R.drawable.ic_baseline_bookmark_24);
                    Toast.makeText(context, "article removed", Toast.LENGTH_SHORT).show();
                    FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                    String userId = currentUser.getUid();
                    holder.reference = FirebaseDatabase.getInstance().getReference().child("Users").child(userId).child("favoris");
                    Query queryRef = holder.reference.orderByChild("title").equalTo(articles.getTitle());
                    queryRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for (DataSnapshot appleSnapshot : dataSnapshot.getChildren()) {
                                appleSnapshot.getRef().removeValue();
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            // Log.e(TAG, "onCancelled", databaseError.toException());
                        }
                    });
                    check=0;
                }
                else{
                    holder.removebookmarkbutton.setBackgroundResource(R.drawable.ic_baseline_bookmark_border_24);

                    check=1;
                }
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
        private ImageButton removebookmarkbutton;

        DatabaseReference reference;
        FirebaseAuth mAuth;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            titleTV=itemView.findViewById(R.id.idTVNewsHeading);
            subtitleTV=itemView.findViewById(R.id.idTVSubTitle);
            newsIV=itemView.findViewById(R.id.idIVNews);
            removebookmarkbutton=itemView.findViewById(R.id.RemoveBookmarkButton);

        }
    }
}
