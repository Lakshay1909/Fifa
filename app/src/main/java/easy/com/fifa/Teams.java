package easy.com.fifa;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Hp on 6/8/2018.
 */

public class Teams {
    String name;
    String code;
    String crestUrl;
    @SerializedName("_links")
    Link links;

    public Teams(String name, String code, String crestUrl, Link links) {
        this.name = name;
        this.code = code;
        this.crestUrl = crestUrl;
        this.links = links;
    }

    public Teams(String name, String code, String crestUrl) {

        this.name = name;
        this.code = code;
        this.crestUrl = crestUrl;
    }

    public Link getLinks() {
        return links;
    }

    public void setLinks(Link links) {
        this.links = links;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCrestUrl() {
        return crestUrl;
    }

    public void setCrestUrl(String crestUrl) {
        this.crestUrl = crestUrl;
    }

    public class Link {
        @SerializedName("players")
        Player player;

        public Player getPlayer() {
            return player;
        }

        public void setPlayer(Player player) {
            this.player = player;
        }
    }
}
