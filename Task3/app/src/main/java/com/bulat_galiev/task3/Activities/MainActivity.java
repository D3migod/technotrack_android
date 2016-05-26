package com.bulat_galiev.task3.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.bulat_galiev.task3.Fragments.AddChatFragment;
import com.bulat_galiev.task3.Fragments.ChangeAboutMeFragment;
import com.bulat_galiev.task3.Fragments.LoginFragment;
import com.bulat_galiev.task3.Fragments.SettingsFragment;
import com.bulat_galiev.task3.Models.FragmentChanger;
import com.bulat_galiev.task3.Models.SocketSingleton;
import com.bulat_galiev.task3.R;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by BulatGaliev on 12.05.16.
 */
public class MainActivity extends AppCompatActivity {
    FragmentChanger fragmentChanger;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        LoginFragment fragment = new LoginFragment();
        fragmentChanger= new FragmentChanger(this);
        fragmentChanger.changeFragment(fragment);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.action_settings:
                SettingsFragment settingsFragment = new SettingsFragment();
                fragmentChanger.changeFragment(settingsFragment);
                return true;

            case R.id.action_plus:
                AddChatFragment addChatFragment = new AddChatFragment();
                fragmentChanger.changeFragment(addChatFragment);
                return true;
            case android.R.id.home:
                fragmentChanger.popFragment();
                return true;
            case R.id.action_about_me:
                ChangeAboutMeFragment changeAboutMeFragment = new ChangeAboutMeFragment();
                fragmentChanger.changeFragment(changeAboutMeFragment);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public void onPause(){
        super.onPause();
        if (SocketSingleton.getSocket() != null && !SocketSingleton.getSocket().isClosed()) {
            try {
                SocketSingleton.getSocket().close();
            } catch (IOException e) {
                Log.e("Exception", "MainActivity: " + e.toString());
            }
        }
    }

}
