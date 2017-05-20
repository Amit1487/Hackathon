package bhartiairtel.themehackathon.echeck;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import bhartiairtel.themehackathon.R;

/**
 * Created by B0089798 on 21 / May / 2017 , 3:12 AM.
 * bhartiairtel.themehackathon.echeck
 * Hackathon
 */

public class CustomListAdapter extends BaseAdapter {
    private Context context; //context
    private ArrayList<ChequeItem> items; //data source of the list adapter

    //public constructor
    public CustomListAdapter(Context context, ArrayList<ChequeItem> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size(); //returns total of items in the list
    }

    @Override
    public Object getItem(int position) {
        return items.get(position); //returns list item at the specified position
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // inflate the layout for each list row
        if (convertView == null) {
            convertView = LayoutInflater.from(context).
                    inflate(R.layout.layout_list_view_row_items, parent, false);
        }

        // get current item to be displayed
        ChequeItem currentItem = (ChequeItem) getItem(position);

        // get the TextView for item name and item description
        TextView textViewItemName = (TextView)
                convertView.findViewById(R.id.tv_cheque_no);
        TextView textViewItemDescription = (TextView)
                convertView.findViewById(R.id.tv_id_no);

        //sets the text for item name and item description from the current item object
        textViewItemName.setText(currentItem.getChequenumber());
        textViewItemDescription.setText(currentItem.getIdnumber());

        // returns the view for the current row
        return convertView;
    }
}