package easy.com.fifa;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.TreeMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by Hp on 11/4/2017.
 */

public class Genres_Fragment extends Fragment {
    RecyclerView recyclerView;
    FifaInterface fifaInterface;
    Fixtureadapter fixtureadapter;
    ArrayList<Fixturelist> teamsArrayList;
    private Call<Fixtures> teamslistCall;
    ArrayList<ListItem> l1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.genres_fragment_list, container, false);
        //teamsArrayList=new ArrayList<>();
        recyclerView = (RecyclerView) v.findViewById(R.id.recy);
        l1=new ArrayList<>();
        fixtureadapter=new Fixtureadapter(getContext(), l1, new Fixtureadapter.FixtureListner() {
            @Override
            public void Click(View v, int position) {
                Toast.makeText(getContext(),"hyy",Toast.LENGTH_LONG).show();
            }
        });
        recyclerView.setAdapter(fixtureadapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        fifaInterface = RetrofitInstance.getRetrofit();
        teamslistCall = fifaInterface.getfixtures();
        teamslistCall.enqueue(new Callback<Fixtures>() {
            @Override
            public void onResponse(Call<Fixtures> call, Response<Fixtures> response) {
                Log.i("Code", response.code() + "");
                final Fixtures f = response.body();
                ArrayList<Fixturelist> fixturelists = f.getFixturelists();
                Log.i("Data", fixturelists.get(0).getDate());
                setDate(fixturelists);
            }

            @Override
            public void onFailure(Call<Fixtures> call, Throwable t) {

            }
        });


        return v;
    }

    private void setDate(ArrayList<Fixturelist> fixturelists) {
        for (Fixturelist fixturelist : fixturelists) {
            String[] s = fixturelist.getDate().split("T");
            String s1 = s[0];
            String s2 = s[1];
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date newDate = null;
            try {
                newDate = format.parse(s1);

                format = new SimpleDateFormat("dd-MM-yyyy");
                String s3 = format.format(newDate);
                String s4 = s2.substring(0, 5);
                Log.i("Date", s3 + "\t" + s4);
                String s5 = s3 + "-" + s4;
                fixturelist.setDate(s5);
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }
        Gson gson=new Gson();
        String s=gson.toJson(fixturelists);
        Log.i("Array",s);
         HashMap<String, ArrayList<Fixturelist>> f=groupDataIntoHashMap(fixturelists);
      //  TreeMap<String,ArrayList<Fixturelist>> sorted=new TreeMap<>();
       // sorted.putAll(f);
        ArrayList<String> s3=new ArrayList<>();
        for(String s2:f.keySet())
        {
            s3.add(s2);
        }
        Collections.sort(s3, new Comparator<String>() {
            DateFormat f1 = new SimpleDateFormat("dd-MM-yyyy");
            @Override
            public int compare(String o1, String o2) {
                try {
                    return f1.parse(o1).compareTo(f1.parse(o2));
                } catch (ParseException e) {
                    throw new IllegalArgumentException(e);
                }
            }
        });
        for (String date : s3) {
            DateItem dateItem = new DateItem();
            dateItem.setDate(date);
            l1.add(dateItem);


            for (Fixturelist pojoOfJsonArray : f.get(date)) {
                GeneralItem generalItem = new GeneralItem();
                generalItem.setFixturelist(pojoOfJsonArray);
                l1.add(generalItem);
            }
        }
        fixtureadapter.notifyDataSetChanged();

    }

//    private void setFixtures(ArrayList<Fixturelist> fixturelists) {
//        f = new HashMap<>();
//        ArrayList<Fixturelist> f1 = new ArrayList<>();
//
//        for (Fixturelist fixturelist : fixturelists) {
//            String date = fixturelist.getDate().substring(0, 10);
//            if (f.containsKey(date)) {
//                f.get(date).add(fixturelist);
//            } else {
//                f1.add(fixturelist);
//                f.put(date, f1);
//            }
//        }
//        for(String s:f.keySet())
//        {
//            String key=s.toString();
//            ArrayList<Fixturelist> f5=f.get(s);
//            Gson gson=new Gson();
//           String g=gson.toJson(f5);
//           Log.i("Hashmap",key+"\t"+g);
//        }
//        ArrayList<ListItem> l = new ArrayList<>();
//        l.clear();
//        for (String date : f.keySet()) {
//            DateItem dateItem = new DateItem();
//            dateItem.setDate(date);
//            l.add(dateItem);
//            for (Fixturelist pojoOfJsonArray : f.get(date)) {
//                GeneralItem generalItem = new GeneralItem();
//                generalItem.setFixturelist(pojoOfJsonArray);
//                l.add(generalItem);
//            }
//        }
//        l1.clear();
//        l1.addAll(l);
//        fixtureadapter.notifyDataSetChanged();
//
//
//    }
    private HashMap<String, ArrayList<Fixturelist>> groupDataIntoHashMap(ArrayList<Fixturelist> listOfPojosOfJsonArray) {

        HashMap<String, ArrayList<Fixturelist>> groupedHashMap = new HashMap<>();

        for (Fixturelist pojoOfJsonArray : listOfPojosOfJsonArray) {

            String hashMapKey = pojoOfJsonArray.getDate().substring(0,10);

            if (groupedHashMap.containsKey(hashMapKey)) {
                // The key is already in the HashMap; add the pojo object
                // against the existing key.
                ArrayList<Fixturelist> z=groupedHashMap.get(hashMapKey);
                z.add(pojoOfJsonArray);
                groupedHashMap.put(hashMapKey,z);
            } else {
                // The key is not there in the HashMap; create a new key-value pair
                ArrayList<Fixturelist> list = new ArrayList<>();
                list.add(pojoOfJsonArray);
                groupedHashMap.put(hashMapKey, list);
            }
        }


        return groupedHashMap;
    }

}
