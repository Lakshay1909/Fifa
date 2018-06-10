package easy.com.fifa;

/**
 * Created by Hp on 6/8/2018.
 */

import easy.com.fifa.Teamslist;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface FifaInterface {
    @GET("competitions/467/teams")
    Call<Teamslist> getTeams();
    @GET
    Call<WorldcupPlayers> getplayers(@Url String url);
    @GET("competitions/467/fixtures")
    Call<Fixtures> getfixtures();
}

