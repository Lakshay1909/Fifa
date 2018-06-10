package easy.com.fifa;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Hp on 6/9/2018.
 */

public class Fixtureadapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private ArrayList<ListItem> l;
    private FixtureListner fixtureListner;

    public Fixtureadapter(Context context, ArrayList<ListItem> l, FixtureListner fixtureListner) {
        this.context = context;
        this.l = l;
        this.fixtureListner = fixtureListner;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {

            case ListItem.TYPE_GENERAL:
                View v1 = inflater.inflate(R.layout.fixtures_list, parent,
                        false);
                viewHolder = new GeneralViewHolder(v1,fixtureListner);
                break;

            case ListItem.TYPE_DATE:
                View v2 = inflater.inflate(R.layout.date_layout, parent, false);
                viewHolder = new DateViewHolder(v2);
                break;
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {

            case ListItem.TYPE_GENERAL:
                GeneralItem generalItem
                        = (GeneralItem) l.get(position);
                GeneralViewHolder generalViewHolder
                        = (GeneralViewHolder) holder;
                generalViewHolder.t2.setText(generalItem.getFixturelist().getHomeTeamName());
                generalViewHolder.t4.setText(generalItem.getFixturelist().getAwayTeamName());
                generalViewHolder.t3.setText(generalItem.getFixturelist().getDate().substring(11, 16));
                break;

            case ListItem.TYPE_DATE:
                DateItem dateItem
                        = (DateItem) l.get(position);
                DateViewHolder dateViewHolder
                        = (DateViewHolder) holder;

                dateViewHolder.t1.setText(dateItem.getDate().substring(0, 10));
                break;
        }
    }


    @Override
    public int getItemCount() {
        return l.size();
    }

    @Override
    public int getItemViewType(int position) {
        return l.get(position).getType();
    }
    class DateViewHolder extends RecyclerView.ViewHolder {
            TextView t1;
        public DateViewHolder(View v) {
            super(v);
            t1=(TextView)v.findViewById(R.id.textView2);


        }
    }

    class GeneralViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            TextView t2,t3,t4;
            FixtureListner f;
        public GeneralViewHolder(View v,FixtureListner fixtureListner) {
            super(v);
            f=fixtureListner;
            v.setOnClickListener(this);
            t2=(TextView)v.findViewById(R.id.textView11);
            t3=(TextView)v.findViewById(R.id.textView14);

            t4=(TextView)v.findViewById(R.id.textView15);


        }

        @Override
        public void onClick(View v) {
            int position=getAdapterPosition();
            if(position!=RecyclerView.NO_POSITION)
            {
                fixtureListner.Click(v,position);
            }
        }
    }
    public interface FixtureListner
    {
         void Click(View v,int position);
    }
}
