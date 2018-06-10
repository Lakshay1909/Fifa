package easy.com.fifa;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ogaclejapan.smarttablayout.SmartTabLayout;

/**
 * Created by Hp on 11/2/2017.
 */

public class Recommendedfragment extends Fragment {
    private SmartTabLayout mSmartTabLayout;
    private ViewPager mViewPager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.movie_recommended_fragment, container, false);

        mSmartTabLayout = (SmartTabLayout) view.findViewById(R.id.tab_view_pager_movie_recommended);
        mViewPager = (ViewPager) view.findViewById(R.id.view_pager_movie_recommended);
        mViewPager.setAdapter(new RecommendedPageradapter(getChildFragmentManager(), getContext()));
        mSmartTabLayout.setViewPager(mViewPager);

        return view;
    }
}
