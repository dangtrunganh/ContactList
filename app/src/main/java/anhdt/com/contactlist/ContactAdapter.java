package anhdt.com.contactlist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sylversky.indexablelistview.scroller.Indexer;
import com.sylversky.indexablelistview.section.AlphabetSection;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by admin on 8/10/2017.
 */

public class ContactAdapter extends BaseAdapter implements Indexer {
    private ArrayList<Contact> arrContact;
    private ArrayList<Contact> tempArrayContact;
    private Context mContext;
    private AlphabetSection alphabetSection;


    public ContactAdapter(ArrayList<Contact> arrContact, Context mContext) {
        this.arrContact = arrContact;
        this.tempArrayContact = (ArrayList<Contact>) arrContact.clone();
//        tempArrayContact = new ArrayList<>();
//        this.tempArrayContact.addAll(arrContact);
        this.mContext = mContext;
        this.alphabetSection = new AlphabetSection(this);
    }

    @Override
    public int getCount() {
        return arrContact.size();
    }

    @Override
    public Contact getItem(int position) {
        return arrContact.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_contact, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.setData(getItem(position).getFirstName() + " " + getItem(position).getLastName());

        return convertView;
    }

    @Override
    public String getComponentName(int position) {
        return arrContact.get(position).getFirstName();
    }

    @Override
    public Object[] getSections() {
        return alphabetSection.getArraySections();
    }

    @Override
    public int getPositionForSection(int sectionIndex) {
        return alphabetSection.getPositionForSection(sectionIndex, getCount());
    }

    @Override
    public int getSectionForPosition(int position) {
        return 0;
    }

    static class ViewHolder {
        TextView tvName;

        public ViewHolder(View itemView) {
            tvName = (TextView) itemView.findViewById(R.id.tv_item_display_name);
        }

        public void setData(String name) {
            tvName.setText(name);
        }
    }

    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        arrContact.clear();
        if (charText.length() == 0) {
            arrContact.addAll(tempArrayContact);
        } else {
            for (Contact contact : tempArrayContact) {
                if ((contact.getFirstName() + " " + contact.getLastName()).toLowerCase(Locale.getDefault()).contains(charText)) {
                    arrContact.add(contact);
                }
            }
        }
        notifyDataSetChanged();
    }


}
