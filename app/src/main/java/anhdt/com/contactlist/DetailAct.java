package anhdt.com.contactlist;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import static anhdt.com.contactlist.MainActivity.EMAIL;
import static anhdt.com.contactlist.MainActivity.FIRST_NAME;
import static anhdt.com.contactlist.MainActivity.LAST_NAME;
import static anhdt.com.contactlist.MainActivity.PHONE_NUMBER;

public class DetailAct extends AppCompatActivity implements View.OnClickListener {
    public static final String FIRST_NAME_DETAIL = "FIRST_NAME_DETAIL";
    public static final String LAST_NAME_DETAIL = "LAST_NAME_DETAIL";
    public static final String EMAIL_DETAIL= "EMAIL_DETAIL";
    public static final String PHONE_NUMBER_DETAIL = "PHONE_NUMBER_DETAIL";

    private TextView mTvPhoneNumber;
    private TextView mTvFirstName;
    private TextView mTvLastName;
    private TextView mTvEmail;
    private FloatingActionButton mFabFix;
    private Contact mContact;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        initViews();
        setData();
    }

    private void setData() {
        Intent intenReceived = getIntent();
        mContact = new Contact(intenReceived.getStringExtra(FIRST_NAME),
                intenReceived.getStringExtra(LAST_NAME),
                "",
                intenReceived.getStringExtra(EMAIL),
                intenReceived.getStringExtra(PHONE_NUMBER));
        mTvPhoneNumber.setText(mContact.getPhoneNumber());
        mTvFirstName.setText(mContact.getFirstName());
        mTvLastName.setText(mContact.getLastName());
        mTvEmail.setText(mContact.getEmail());
    }

    private void initViews() {
        mTvPhoneNumber = (TextView) findViewById(R.id.tv_detail_phone_number);
        mTvFirstName = (TextView) findViewById(R.id.tv_detail_first_name);
        mTvLastName = (TextView) findViewById(R.id.tv_detail_last_name);
        mTvEmail = (TextView) findViewById(R.id.tv_detail_email);
        mFabFix = (FloatingActionButton) findViewById(R.id.fab_fix_detail);

        mFabFix.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(DetailAct.this, FixContactAct.class);
        intent.putExtra(FIRST_NAME_DETAIL, mContact.getFirstName());
        intent.putExtra(LAST_NAME_DETAIL, mContact.getLastName());
        intent.putExtra(EMAIL_DETAIL, mContact.getEmail());
        intent.putExtra(PHONE_NUMBER_DETAIL, mContact.getPhoneNumber());
        startActivity(intent);
    }
}
