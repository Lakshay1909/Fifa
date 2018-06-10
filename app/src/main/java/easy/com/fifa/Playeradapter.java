package easy.com.fifa;

/**
 * Created by Hp on 6/8/2018.
 */

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ahmadrosid.svgloader.SvgLoader;

import java.util.ArrayList;

public class Playeradapter extends RecyclerView.Adapter<Playeradapter.Play> {
    private ArrayList<Playerlist> teamsArrayList;
    private Context mContext;
    private Playerclicklistner teamclicklistner;

    public Playeradapter(ArrayList<Playerlist> teamsArrayList, Context mContext, Playerclicklistner teamclicklistner) {
        this.teamsArrayList = teamsArrayList;
        this.mContext = mContext;
        this.teamclicklistner = teamclicklistner;
    }

    @NonNull
    @Override
    public Play onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(mContext).inflate(R.layout.players_list,parent,false);

        return new Play(v,teamclicklistner);
    }

    @Override
    public void onBindViewHolder(@NonNull Play holder, int position) {
        Playerlist t=teamsArrayList.get(position);
        holder.t1.setText(t.getName());
        holder.t2.setText(t.getPosition());
        holder.t3.setText(t.getJerseyNumber()+"");
    }

    @Override
    public int getItemCount() {
        return teamsArrayList.size();
    }
    public interface Playerclicklistner
    {
        void Click(View v, int position);
    }
    public class Play extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        TextView t1,t2,t3;
        Playerclicklistner teamclicklistner;
        public Play(View itemView,Playerclicklistner listner) {
            super(itemView);
            teamclicklistner=listner;
            itemView.setOnClickListener(this);
            t1=itemView.findViewById(R.id.textViewplayer);
            t2=itemView.findViewById(R.id.textViewplayer1);
            t3=itemView.findViewById(R.id.textViewplayer2);

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
