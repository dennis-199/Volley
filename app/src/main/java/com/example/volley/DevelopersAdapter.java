package com.example.volley;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class DevelopersAdapter extends RecyclerView.Adapter<DevelopersAdapter.ViewHolder> {

    private List<DeveloperList> developerList;

    private Context mContext;
    // keys for our intents
    public static final String KEY_NAME = "name";
    public static final String KEY_IMAGE = "image";
    public static final String KEY_URL = "url";
    public DevelopersAdapter(List<DeveloperList>developerList,Context context){
        this.developerList=developerList;
        this.mContext=context;
    }


    @NonNull
    @Override
    public DevelopersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.developers_list,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull DevelopersAdapter.ViewHolder holder, int position) {
        // create a variable that get the current instance of the developers in the list
        final DeveloperList currentDeveloper=developerList.get(position);
        // populate the text views and image with data
        holder.login.setText(currentDeveloper.getLogin());
        holder.html_url.setText(currentDeveloper.getHtml_url());
        // use the library Picasso to load images to prevent crashing ... loading images resource intensive
        Picasso.with(mContext)
                .load(currentDeveloper.getAvatar_url())
                .into(holder.avatar_url);
        // set on click listener to handler click events
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeveloperList developerList1 = developerList.get(position);
                //create an instance of developers list and initialize it
                Intent skipIntent = new Intent(v.getContext(), ProfileActivity.class);
                // use intent Extra
                skipIntent.putExtra(KEY_NAME, developerList1.getLogin());
                skipIntent.putExtra(KEY_URL, developerList1.getHtml_url());
                skipIntent.putExtra(KEY_IMAGE, developerList1.getAvatar_url());
                v.getContext().startActivity(skipIntent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return developerList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView login;
        public ImageView avatar_url;
        public TextView html_url;
        public LinearLayout linearLayout;

        public ViewHolder(View itemView){
            super(itemView);
            login=itemView.findViewById(R.id.username);
            avatar_url=itemView.findViewById(R.id.imageView);
            html_url=itemView.findViewById(R.id.html_url);
            linearLayout=itemView.findViewById(R.id.linearLayout);
        }

    }

}
