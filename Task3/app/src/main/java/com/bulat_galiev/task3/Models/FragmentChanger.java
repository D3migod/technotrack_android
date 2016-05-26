package com.bulat_galiev.task3.Models;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.bulat_galiev.task3.R;

/**
 * Created by BulatGaliev on 13.05.16.
 */
public class FragmentChanger {
    FragmentActivity context;
    FragmentManager fragmentManager;
    public FragmentChanger(FragmentActivity context) {
        this.context = context;
    }
    public void changeFragment(Fragment fragment) {
        fragmentManager = context.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContent, fragment, Integer.toString(fragmentManager.getBackStackEntryCount()));
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
    public void popFragment() {
        fragmentManager.popBackStack();
        /*if (fragmentManager.getBackStackEntryCount() - 1 > 0) {
            changeFragment(getFragmentAt(fragmentManager.getBackStackEntryCount() - 2));
        }*/
    }

    private Fragment getFragmentAt(int index) {
        return fragmentManager.getBackStackEntryCount() > 0 ? fragmentManager.findFragmentByTag(Integer.toString(index)) : null;
    }
}
