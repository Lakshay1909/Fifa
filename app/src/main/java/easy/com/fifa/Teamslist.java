package easy.com.fifa;

/**
 * Created by Hp on 6/8/2018.
 */

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Teamslist {
    @SerializedName("teams")
    ArrayList<Teams> teamsArrayList;

    public Teamslist(ArrayList<Teams> teamsArrayList) {
        this.teamsArrayList = teamsArrayList;
    }

    public ArrayList<Teams> getTeamsArrayList() {
        return teamsArrayList;
    }

    public void setTeamsArrayList(ArrayList<Teams> teamsArrayList) {
        this.teamsArrayList = teamsArrayList;
    }
}
