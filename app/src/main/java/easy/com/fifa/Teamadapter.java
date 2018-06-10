package easy.com.fifa;

/**
 * Created by Hp on 6/8/2018.
 */

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.PictureDrawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.ahmadrosid.svgloader.SvgLoader;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class Teamadapter extends RecyclerView.Adapter<Teamadapter.Team> {
    private ArrayList<Teams> teamsArrayList;
    private Context mContext;
    private Teamclicklistner teamclicklistner;

    public Teamadapter(ArrayList<Teams> teamsArrayList, Context mContext, Teamclicklistner teamclicklistner) {
        this.teamsArrayList = teamsArrayList;
        this.mContext = mContext;
        this.teamclicklistner = teamclicklistner;
    }

    @NonNull
    @Override
    public Team onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(mContext).inflate(R.layout.teams_list_content,parent,false);

        return new Team(v,teamclicklistner);
    }

    @Override
    public void onBindViewHolder(@NonNull Team holder, int position) {
        Teams t=teamsArrayList.get(position);
        SvgLoader.pluck()
                .with((Activity)mContext)
                .setPlaceHolder(R.mipmap.ic_launcher, R.mipmap.ic_launcher)
                .load(t.crestUrl, holder.circleImageView);
        Log.i("Tag",t.getCrestUrl());
        holder.t1.setText(t.getName());
        holder.t2.setText(t.getCode());
    }

    @Override
    public int getItemCount() {
        return teamsArrayList.size();
    }
    public interface Teamclicklistner
    {
        void Click(View v,int position);
    }
    public class Team extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        ImageView circleImageView;
        TextView t1,t2;
        Teamclicklistner teamclicklistner;
        public Team(View itemView,Teamclicklistner listner) {
            super(itemView);
            teamclicklistner=listner;
            itemView.setOnClickListener(this);
            circleImageView=itemView.findViewById(R.id.profile_image);
            t1=itemView.findViewById(R.id.textView);
            t2=itemView.findViewById(R.id.textView1);
        }

        @Override
        public void onClick(View v) {
            int position=getAdapterPosition();
            if(position!=RecyclerView.NO_POSITION)
            {
                teamclicklistner.Click(v,position);
            }

        }
    }
}
