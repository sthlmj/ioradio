package se.mookito.ioradio;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridView gridView = (GridView)findViewById(R.id.gridview);
        gridView.setAdapter(new MyAdapter(this));

        /**
         * Clickable
         */

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id)
            {
                // this 'mActivity' parameter is Activity object, you can send the current activity.
                Intent i = new Intent(MainActivity.this, AndroidMediaPlayer.class);
                /**
                 * http://stackoverflow.com/questions/31163202/start-an-activity-non-static-method-cannot-be-referenced-from-a-static-context
                 * This below here works also.
                 * MainActivity.this.startActivity(i);
                 *
                 * Get context from view before calling startActivity()
                 * v.getContext().startActivity(i);
                 */
                startActivity(i);
            }
        });

        /**
         * End of clickable
         */

    }

    /**
     * Begin adapter.
     */
    private class MyAdapter extends BaseAdapter
    {
        private List<Item> items = new ArrayList<Item>();
        private LayoutInflater inflater;

        public MyAdapter(Context context)
        {
            inflater = LayoutInflater.from(context);

            items.add(new Item("Alternative", R.drawable.idobi_radio));
            items.add(new Item("Easy Listening", R.drawable.cool93));
            items.add(new Item("Top 40", R.drawable.brasilhits));
            items.add(new Item("Dancehall", R.drawable.party_vibe));
            items.add(new Item("Talk", R.drawable.alexa_jones));
            items.add(new Item("Pop", R.drawable.antena1));
            items.add(new Item("Pop", R.drawable.lasmas));
            items.add(new Item("News", R.drawable.adomfm));
            items.add(new Item("Public Radio", R.drawable.fusion));
            items.add(new Item("Jazz", R.drawable.abc));
        }

        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public Object getItem(int i)
        {
            return items.get(i);
        }

        @Override
        public long getItemId(int i)
        {
            return items.get(i).drawableId;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup)
        {
            View v = view;
            ImageView picture;
            TextView name;

            if(v == null)
            {
                v = inflater.inflate(R.layout.gridview_item, viewGroup, false);
                v.setTag(R.id.picture, v.findViewById(R.id.picture));
                v.setTag(R.id.text, v.findViewById(R.id.text));
            }

            picture = (ImageView)v.getTag(R.id.picture);
            name = (TextView)v.getTag(R.id.text);

            Item item = (Item)getItem(i);

            picture.setImageResource(item.drawableId);
            name.setText(item.name);

            return v;
        }

        private class Item
        {
            final String name;
            final int drawableId;

            Item(String name, int drawableId)
            {
                this.name = name;
                this.drawableId = drawableId;
            }
        }
    }
}