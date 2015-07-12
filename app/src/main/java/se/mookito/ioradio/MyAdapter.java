package se.mookito.ioradio;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

/**
 * Begin adapter.
 */
public class MyAdapter extends BaseAdapter {
    public List<Item> items = new ArrayList<Item>();
    private LayoutInflater inflater;

    public MyAdapter(Context context) {
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
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return items.get(i).drawableId;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = view;
        ImageView picture;
        TextView name;

        if (v == null) {
            v = inflater.inflate(R.layout.gridview_item, viewGroup, false);
            v.setTag(R.id.picture, v.findViewById(R.id.picture));
            v.setTag(R.id.text, v.findViewById(R.id.text));
        }

        picture = (ImageView) v.getTag(R.id.picture);
        name = (TextView) v.getTag(R.id.text);

        Item item = (Item) getItem(i);

        picture.setImageResource(item.drawableId);
        name.setText(item.name);

        return v;
    }

    public class Item {
        final String name;
        final int drawableId;

        Item(String name, int drawableId) {
            this.name = name;
            this.drawableId = drawableId;
        }
    }
}