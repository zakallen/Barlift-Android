package com.barliftapp.barlift;

import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;

import com.viewpagerindicator.CirclePageIndicator;
import com.viewpagerindicator.UnderlinePageIndicator;


public class AboutActivity extends FragmentActivity {

    // Declare Variables
    ViewPager viewPager;
    PagerAdapter adapter;
    String[] about;
    int[] pic;
    CirclePageIndicator mIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        // Generate sample data
        about = new String[] { "Sample Text 1", "Sample Text 2", "Sample Text 3", "Sample Text 4", "Sample Text 5", "Sample Text 6", "Sample Text 7", "Sample Text 8", "Sample Text 9", "Sample Text 10" };

        pic = new int[] { R.drawable.phone, R.drawable.phone,
                R.drawable.phone, R.drawable.phone,
                R.drawable.phone, R.drawable.phone, R.drawable.phone,
                R.drawable.phone, R.drawable.phone, R.drawable.phone };

        // Locate the ViewPager in viewpager_main.xml
        viewPager = (ViewPager) findViewById(R.id.pager);
        // Pass results to ViewPagerAdapter Class
        adapter = new ViewPagerAdapter(AboutActivity.this, about, pic);
        // Binds the Adapter to the ViewPager
        viewPager.setAdapter(adapter);

        // ViewPager Indicator
        mIndicator = (CirclePageIndicator) findViewById(R.id.indicator);
        mIndicator.setSnap(true);
        mIndicator.setViewPager(viewPager);
    }

    @Override
    public void onBackPressed() {
//        if (viewPager.getCurrentItem() == 0) {
//            // If the user is currently looking at the first step, allow the system to handle the
//            // Back button. This calls finish() on this activity and pops the back stack.
//            super.onBackPressed();
//        } else {
//            // Otherwise, select the previous step.
//            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
//        }
          super.onBackPressed();
    }

}
