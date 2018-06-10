package easy.com.fifa;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by Hp on 8/22/2017.
 */

public class Moviefragment extends Fragment {


    RecyclerView recyclerView;
    FifaInterface fifaInterface;
    Teamadapter teamadapter;
    ArrayList<Teams> teamsArrayList;
    private Call<Teamslist> teamslistCall;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.movie_fragment, container, false);
        teamsArrayList=new ArrayList<>();
        recyclerView=(RecyclerView)v.findViewById(R.id.recycler1);
        teamadapter=new Teamadapter(teamsArrayList, getContext(), new Teamadapter.Teamclicklistner() {
            @Override
            public void Click(View v, int position) {
                Teams t=teamsArrayList.get(position);
               // Toast.makeText(getContext(),t.getLinks().getPlayer().getHref(),Toast.LENGTH_LONG).show();
                Intent i=new Intent(getContext(),PlayerActivity.class);
                i.putExtra("Url",t.getLinks().getPlayer().getHref());
                startActivity(i);
            }
        });
        recyclerView.setAdapter(teamadapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        fifaInterface= RetrofitInstance.getRetrofit();
        teamslistCall=fifaInterface.getTeams();
        teamslistCall.enqueue(new Callback<Teamslist>() {
            @Override
            public void onResponse(Call<Teamslist> call, Response<Teamslist> response) {
                final Teamslist teamslist=response.body();
                ArrayList<Teams> t= teamslist.getTeamsArrayList();
                setTeams(t);
            }

            @Override
            public void onFailure(Call<Teamslist> call, Throwable t) {

            }
        });







        return v;
    }
    private void setTeams(ArrayList<Teams> t) {
        teamsArrayList.clear();
        teamsArrayList.addAll(t);
        teamadapter.notifyDataSetChanged();
    }
}