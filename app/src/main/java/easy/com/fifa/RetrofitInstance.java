package easy.com.fifa;

/**
 * Created by Hp on 6/8/2018.
 */
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
    private static Retrofit retrofit;
    private static  FifaInterface fifaInterface;
    public static FifaInterface getRetrofit()
    {
        if(retrofit==null)
        {
            Retrofit retrofit=new Retrofit.Builder().baseUrl("http://api.football-data.org/v1/")
                    .addConverterFactory(GsonConverterFactory.create()).build();
            fifaInterface=retrofit.create(FifaInterface.class);
            return fifaInterface;

        }

        return fifaInterface;
    }
}

