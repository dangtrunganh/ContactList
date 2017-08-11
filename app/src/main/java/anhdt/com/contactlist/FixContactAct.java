package anhdt.com.contactlist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

import static anhdt.com.contactlist.DetailAct.EMAIL_DETAIL;
import static anhdt.com.contactlist.DetailAct.FIRST_NAME_DETAIL;
import static anhdt.com.contactlist.DetailAct.LAST_NAME_DETAIL;
import static anhdt.com.contactlist.DetailAct.PHONE_NUMBER_DETAIL;
import static anhdt.com.contactlist.MainActivity.EMAIL;
import static anhdt.com.contactlist.MainActivity.FIRST_NAME;
import static anhdt.com.contactlist.MainActivity.LAST_NAME;
import static anhdt.com.contactlist.MainActivity.PHONE_NUMBER;

public class FixContactAct extends AppCompatActivity implements View.OnClickListener {
    private Button mBtnOK;
    private TextView mTvCancel, mTvDelete;
    private EditText mEdtFirstName, mEdtLastName, mEdtPhoneNumber, mEdtEmail;
    private de.hdodenhof.circleimageview.CircleImageView mCircleImageView;
    private Contact mContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fix_contact);
        initViews();
        setData();
    }

    private void setData() {
        Intent intentReceived = getIntent();

        mContact = new Contact(intentReceived.getStringExtra(FIRST_NAME_DETAIL),
                intentReceived.getStringExtra(LAST_NAME_DETAIL),
                "",
                intentReceived.getStringExtra(EMAIL_DETAIL),
                intentReceived.getStringExtra(PHONE_NUMBER_DETAIL));
        mEdtPhoneNumber.setText(mContact.getPhoneNumber());
        mEdtFirstName.setText(mContact.getFirstName());
        mEdtLastName.setText(mContact.getLastName());
        mEdtEmail.setText(mContact.getEmail());
    }

    private void initViews() {
        mBtnOK = (Button) findViewById(R.id.btn_ok_fix_contact);
        mTvCancel = (TextView) findViewById(R.id.tv_cancel_fix);
        mTvDelete = (TextView) findViewById(R.id.tv_delete_contact);
        mEdtFirstName = (EditText) findViewById(R.id.edt_fix_first_name);
        mEdtLastName = (EditText) findViewById(R.id.edt_fix_last_name);
        mEdtPhoneNumber = (EditText) findViewById(R.id.edt_fix_phone_number);
        mEdtEmail = (EditText) findViewById(R.id.edt_fix_email);
        mCircleImageView = (CircleImageView) findViewById(R.id.civ_fix_image_contact);
        mTvCancel.setOnClickListener(this);
        mBtnOK.setOnClickListener(this);
        mTvDelete.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_cancel_fix:
                finish();
                break;
            case R.id.tv_delete_contact:
                break;
            case R.id.btn_ok_fix_contact:
                break;
            default:
                break;
        }
    }
}
