package anhdt.com.contactlist;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.SearchView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.sylversky.indexablelistview.widget.IndexableListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, SearchView.OnQueryTextListener, AdapterView.OnItemClickListener {
    public static final String PHONE_NUMBER = "PHONE_NUMBER";
    public static final String FIRST_NAME = "FIRST_NAME";
    public static final String LAST_NAME = "LAST_NAME";
    public static final String EMAIL = "EMAIL";


    private IndexableListView lvContact;
    private Button btnAdd;
    private ArrayList<Contact> arrContacts;
    private TextView tvTest;
    private ContactAdapter contactAdapter;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initviews();
        readAllContact();
        setData();
    }

    private void setData() {
        contactAdapter = new ContactAdapter(arrContacts, this);
        lvContact.setAdapter(contactAdapter);
        contactAdapter.notifyDataSetChanged();
    }


    private void initviews() {
        btnAdd = (Button) findViewById(R.id.btn_add);
        lvContact = (IndexableListView) findViewById(R.id.lv_contact_indexlv);
        lvContact.setOnItemClickListener(this);
        searchView = (SearchView) findViewById(R.id.sv_contact);
        searchView.setOnQueryTextListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add:
//                startActivity(new Intent(MainActivity.this, DemoReadContactAct.class));
//                finish();
                break;
            default:
                break;
        }
    }

    private void readAllContact() {
        arrContacts = new ArrayList<>();
        ContentResolver cr = this.getContentResolver(); //Activity/Application android.content.Context
        Cursor cursor = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);


        if (cursor.moveToFirst()) {
            do {
                String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));

                if (Integer.parseInt(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {
                    Cursor pCur = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?", new String[]{id}, null);
                    Cursor eCur = cr.query(ContactsContract.CommonDataKinds.Email.CONTENT_URI, null, ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = ?", new String[]{id}, null);

                    String whereName = ContactsContract.Data.MIMETYPE + " = ? AND " + ContactsContract.CommonDataKinds.StructuredName.CONTACT_ID + " = ?";
                    String[] whereNameParams = new String[] { ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE, id };
                    Cursor nCur = cr.query(ContactsContract.Data.CONTENT_URI, null, whereName, whereNameParams, ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME);

                    while (pCur.moveToNext() && eCur.moveToNext() && nCur.moveToNext()) {
                        String phoneNumber = pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DATA));
//
                        String email = eCur.getString(eCur.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));

//                        String phoneNumber = "";
//                        String email = "";

                        String firstName = nCur.getString(nCur.getColumnIndex(ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME));
                        String lastName = nCur.getString(nCur.getColumnIndex(ContactsContract.CommonDataKinds.StructuredName.FAMILY_NAME));
//                        String displayName = nCur.getString(nCur.getColumnIndex(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME));

                        Contact contact = new Contact(firstName, lastName, "", email, phoneNumber);
                        arrContacts.add(contact);
                        break;
                    }
                    pCur.close();
                    eCur.close();
                    nCur.close();
                }

            } while (cursor.moveToNext());
        }
        Collections.sort(arrContacts, new Comparator<Contact>() {
            @Override
            public int compare(Contact o1, Contact o2) {
                return o1.getFirstName().compareToIgnoreCase(o2.getFirstName());
            }
        });
    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        String text = newText;
        contactAdapter.filter(text);
        return false;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(MainActivity.this, DetailAct.class);
        intent.putExtra(PHONE_NUMBER, arrContacts.get(position).getPhoneNumber());
        intent.putExtra(FIRST_NAME, arrContacts.get(position).getFirstName());
        intent.putExtra(LAST_NAME, arrContacts.get(position).getLastName());
        intent.putExtra(EMAIL, arrContacts.get(position).getEmail());
        startActivity(intent);
    }
}
