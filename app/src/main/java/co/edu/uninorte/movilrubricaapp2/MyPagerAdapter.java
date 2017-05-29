package co.edu.uninorte.movilrubricaapp2;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by fdjvf on 4/14/2017.
 */

public class MyPagerAdapter extends FragmentPagerAdapter {

    private static int NUM_ITEMS = 2;
    public ItemFragment Cursos;
    public ItemFragment Rubricas;

    public MyPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    // Returns total number of pages
    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

    // Returns the fragment to display for that page
    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0: // Fragment # 0 - This will show FirstFragment

                Cursos = ItemFragment.newInstance(1);
                return Cursos;
            case 1: // Fragment # 0 - This will show FirstFragment different title
                Rubricas = ItemFragment.newInstance(2);
                return Rubricas;
            default:
                return null;
        }
    }

    // Returns the page title for the top indicator
    @Override
    public CharSequence getPageTitle(int position) {
        return "Page " + position;
    }

}


