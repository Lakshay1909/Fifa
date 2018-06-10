package easy.com.fifa;

/**
 * Created by Hp on 6/8/2018.
 */
        import android.content.Intent;
        import android.os.Bundle;
        import android.os.Handler;
        import android.support.annotation.Nullable;
        import android.support.v7.app.AppCompatActivity;
        import android.view.Window;
        import android.view.WindowManager;

public class Splashscreen extends AppCompatActivity{

    public static int timespan=4000;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splash_screen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i=new Intent(Splashscreen.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        },timespan);
    }
}
