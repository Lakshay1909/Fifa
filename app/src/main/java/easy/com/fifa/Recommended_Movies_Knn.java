package easy.com.fifa;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.ogaclejapan.smarttablayout.SmartTabLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import easy.com.fifa.Database.DatabaseAccess;
import easy.com.fifa.Database.Formresponse;
import easy.com.fifa.Database.Leaderboardadapter;


/**
 * Created by Hp on 11/2/2017.
 */

public class Recommended_Movies_Knn extends Fragment {
    RecyclerView recyclerView;
    Leaderboardadapter leaderboardadapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recommended_fragment, container, false);
        recyclerView=(RecyclerView)view.findViewById(R.id.recyclerView2);
        DatabaseAccess databaseAccess=DatabaseAccess.getInstance(getContext());
        databaseAccess.open();
        List<Formresponse> formresponses=databaseAccess.getformresponse();
        databaseAccess.close();
        leaderboardadapter=new Leaderboardadapter(formresponses, getContext(), new Leaderboardadapter.Leaderclick() {
            @Override
            public void Click(View v, int position) {

            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(leaderboardadapter);
        leaderboardadapter.notifyDataSetChanged();
        setLeaderboard(formresponses);
        return view;

    }

    private void setLeaderboard(List<Formresponse> formresponses) {
        ArrayList<String> s=new ArrayList<>();
        for(Formresponse f:formresponses)
        {
            s.add(f.getPrediction());
        }
        ArrayList<String> s3=new ArrayList<>();
        ArrayList<String> s4=new ArrayList<>();
        for(String s1:s)
        {
            String[] s2=s1.split("-");
            s3.add(s2[0]);
            s4.add(s2[1]);
        }
        ArrayList<Integer> result = new ArrayList<Integer>();
        for(String stringValue : s3) {
            try {
                //Convert String to Integer, and store it into integer array list.
                result.add(Integer.parseInt(stringValue));
            } catch(NumberFormatException nfe) {
                //System.out.println("Could not parse " + nfe);
                Log.w("NumberFormat", "Parsing failed! " + stringValue + " can not be an integer");
            }
        }
        ArrayList<Integer> result1 = new ArrayList<Integer>();
        for(String stringValue : s4) {
            try {
                //Convert String to Integer, and store it into integer array list.
                result1.add(Integer.parseInt(stringValue));
            } catch(NumberFormatException nfe) {
                //System.out.println("Could not parse " + nfe);
                Log.w("NumberFormat", "Parsing failed! " + stringValue + " can not be an integer");
            }
        }
        Gson gson=new Gson();
       String string= gson.toJson(result1);
       Log.i("Predicted",string);
       //Assuming Actual score 2-0
        calculatepoints(result,result1);

    }

    private void calculatepoints(ArrayList<Integer> result, ArrayList<Integer> result1) {
        int points=0;
        ArrayList<Integer> i=new ArrayList<>();
        Iterator<Integer> it1=result.iterator();
        Iterator<Integer> it2=result1.iterator();
        while (it1.hasNext() && it2.hasNext())
        {
            int a=it1.next();
            int b=it2.next();
            if(Math.abs(a-2)+Math.abs(b)==0)
            {
                points=100;
            }
            else if(Math.abs(a-2)+Math.abs(b)==1)
            {
                points=75;
            }
            else if(Math.abs(it1.next()-2)+Math.abs(it2.next())==2)
            {
                points=50;
            }
            else if(Math.abs(a-2)+Math.abs(b)==3)
            {
                points=25;
            }
            else
                points=0;
        }
    }
}
