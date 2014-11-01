package com.example.root.birdnest;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;


public class Main extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.deleteDatabase(MySQLite.TABLE_NAME);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }

        // Database Test RW
        MySQLite db = new MySQLite(this);


        //db.onUpgrade(,0,1);
        db.addBird(new Bird("BirdA","MY","Image1","Sound1"));
        db.addBird(new Bird("BirdB","MY","Image2","Sound2"));
        db.addBird(new Bird("BirdC","SG","Image3","Sound3"));

        // Database Modules Test
        List<Bird> list1 = db.getAllBird();
        List <Bird> list2 = db.getBird("Image2",MySQLite.BIRD_IMAGES);
        List <Bird> list3 = db.getBird("Image20", MySQLite.BIRD_IMAGES);

        // Clear Test DB
        db.cleanDB();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }
}
