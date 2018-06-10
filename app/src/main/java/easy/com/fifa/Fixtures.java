package easy.com.fifa;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Hp on 6/9/2018.
 */

public class Fixtures {
    @SerializedName("fixtures")
    ArrayList<Fixturelist> fixturelists;

    public ArrayList<Fixturelist> getFixturelists() {
        return fixturelists;
    }

    public void setFixturelists(ArrayList<Fixturelist> fixturelists) {
        this.fixturelists = fixturelists;
    }
}
