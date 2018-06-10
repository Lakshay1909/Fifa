package easy.com.fifa.Database;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import easy.com.fifa.Playerlist;
import easy.com.fifa.R;

/**
 * Created by Hp on 6/10/2018.
 */

public class Leaderboardadapter extends RecyclerView.Adapter<Leaderboardadapter.Leaderboard> {
    private List<Formresponse> teamsArrayList;
    private Context mContext;
    private Leaderclick teamclicklistner;

    public Leaderboardadapter(List<Formresponse> teamsArrayList, Context mContext,Leaderclick teamclicklistner) {
        this.teamsArrayList = teamsArrayList;
        this.mContext = mContext;
        this.teamclicklistner = teamclicklistner;
    }

    @NonNull
    @Override
    public Leaderboard onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(mContext).inflate(R.layout.leaderboard,parent,false);

        return new Leaderboard(v,teamclicklistner);
    }

    @Override
    public void onBindViewHolder(@NonNull Leaderboard holder, int position) {
        Formresponse t=teamsArrayList.get(position);
        holder.t1.setText(t.getPosition()+"1");
        holder.t2.setText(t.getName());
        holder.t3.setText(t.getScore()+"10");
        holder.t4.setText(t.getName().substring(0,1));
    }

    @Override
    public int getItemCount() {
        return teamsArrayList.size();
    }
    public interface Leaderclick
    {
        void Click(View v, int position);
    }
    public class Leaderboard extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        TextView t1,t2,t3,t4;
        Leaderclick teamclicklistner;
        public Leaderboard(View itemView,Leaderclick listner) {
            super(itemView);
            teamclicklistner=listner;
            itemView.setOnClickListener(this);
            t1=itemView.findViewById(R.id.topUsersLeaderboardItem_position);
            t2=itemView.findViewById(R.id.topUsersLeaderboardItem_username);
            t3=itemView.findViewById(R.id.topUsersLeaderboardItem_score);
            t4=itemView.findViewById(R.id.topUsersLeaderboardItem_userInitial);

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

