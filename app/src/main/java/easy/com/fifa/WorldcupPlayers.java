package easy.com.fifa;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Hp on 6/9/2018.
 */

public class WorldcupPlayers {
    @SerializedName("players")
    ArrayList<Playerlist> playerlistArrayList;

    public ArrayList<Playerlist> getPlayerlistArrayList() {
        return playerlistArrayList;
    }

    public void setPlayerlistArrayList(ArrayList<Playerlist> playerlistArrayList) {
        this.playerlistArrayList = playerlistArrayList;
    }
}
