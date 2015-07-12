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

                //send intent to Media Player activity
                Intent i = new Intent(getApplicationContext(), AndroidMediaPlayer.class);

                //pass index number
                i.putExtra("id", position);
                startActivity(i);
            }
        });



        /**
         * End of clickable
         */
    }
}