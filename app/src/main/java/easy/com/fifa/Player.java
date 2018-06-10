package easy.com.fifa;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Hp on 6/9/2018.
 */

public class Player {
    @SerializedName("href")
    String href;

    public Player(String href) {
        this.href = href;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }
}
