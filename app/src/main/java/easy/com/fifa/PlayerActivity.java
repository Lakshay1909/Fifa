package easy.com.fifa;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Hp on 6/9/2018.
 */

public class PlayerActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    FifaInterface fifaInterface;
    Playeradapter teamadapter;
    ArrayList<Playerlist> teamsArrayList;
    private Call<WorldcupPlayers> teamslistCall;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_activity);
        teamsArrayList=new ArrayList<>();
        recyclerView=(RecyclerView)findViewById(R.id.recyclerView);
        teamadapter=new Playeradapter(teamsArrayList, this, new Playeradapter.Playerclicklistner() {
            @Override
            public void Click(View v, int position) {

            }
        });
        recyclerView.setAdapter(teamadapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        fifaInterface= RetrofitInstance.getRetrofit();
        teamslistCall=fifaInterface.getplayers(getIntent().getStringExtra("Url"));
        teamslistCall.enqueue(new Callback<WorldcupPlayers>() {
            @Override
            public void onResponse(Call<WorldcupPlayers> call, Response<WorldcupPlayers> response) {
                final WorldcupPlayers w=response.body();
               ArrayList<Playerlist> p= w.getPlayerlistArrayList();
               setTeams(p);
            }

            @Override
            public void onFailure(Call<WorldcupPlayers> call, Throwable t) {

            }
        });




    }
    private void setTeams(ArrayList<Playerlist> t) {
        teamsArrayList.clear();
        teamsArrayList.addAll(t);
        teamadapter.notifyDataSetChanged();}
}
