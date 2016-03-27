package com.primedroid.primeaccount;

import android.accounts.Account;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.UUID;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by sastagi on 3/27/16.
 */
public class LoginActivity extends Activity {

    @Bind(R.id.login_email_address)
    EditText mEmailField;

    @Bind(R.id.login_password)
    EditText mPasswordField;

    @Bind(R.id.login)
    Button login;

    @Bind(R.id.invalidcredentials)
    TextView mInvalidCredentials;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.login)
    protected void addAccount(){
        //This is where you make the server call and get the authorization tokens. Adding dummy stuff here.
        String username = mEmailField.getText().toString();
        String password = mPasswordField.getText().toString();
        String token = UUID.randomUUID()+"-prime";

        if(TextUtils.isEmpty(username)||TextUtils.isEmpty(password)){
            mInvalidCredentials.setVisibility(View.GONE);
        } else {
            final Account account = new Account(username, AccountInfo.ACCOUNT_TYPE);
            MainActivity.mAccountManager.addAccountExplicitly(account, password, null);
            MainActivity.mAccountManager.setAuthToken(account, AccountInfo.AUTHTOKENTYPE, token);
            mInvalidCredentials.setVisibility(View.GONE);
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }

}
