package easy.com.fifa;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import easy.com.fifa.Genres_Fragment;
import easy.com.fifa.Moviefragment;
import easy.com.fifa.Recommended_Movies_Knn;

/**
 * Created by Hp on 11/2/2017.
 */

public class RecommendedPageradapter extends FragmentStatePagerAdapter {
        private Context mContext;
        public RecommendedPageradapter(FragmentManager fm, Context context) {
            super(fm);
            mContext=context;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new Moviefragment();
                case 1:
                    return new Genres_Fragment();
                case 2:
                    return new Recommended_Movies_Knn();
                case 3:
                    return new Webviewfragment();
            }
            return null;
        }

        @Override
        public int getCount() {
            return 4;
        }
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                return "Teams";

                case 1:
                    return "Schedule";
                case 2:
                return "LeaderBoard";
                case 3:
                    return "Form";

            }
            return null;
        }
    }


