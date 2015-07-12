package se.mookito.ioradio;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridView gridView = (GridView) findViewById(R.id.gridview);
        gridView.setAdapter(new MyAdapter(this));

        /**
         * Clickable
         */
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                // this 'mActivity' parameter is Activity object, you can send the current activity.
                Intent i = new Intent(MainActivity.this, AndroidMediaPlayer.class);
                startActivity(i);
            }
        });

        /**
         * End of clickable
         */
    }
}