package com.rrmsense.retrofit2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {



    @BindView(R.id.et_user_name)
    EditText etUserName;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.button_login)
    Button buttonLogin;

    private CompositeDisposable mCompositeDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mCompositeDisposable = new CompositeDisposable();
    }

    @OnClick({ R.id.button_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button_login:
                User user = User.builder().userName(etUserName.getText().toString()).password(etPassword.getText().toString()).build();

                RequestInterface requestInterface = RetrofitApiClient.getClient().create(RequestInterface.class);

                mCompositeDisposable.add(requestInterface.Login(user)
                //mCompositeDisposable.add(requestInterface.Login(user.userName(),user.password())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(this::handleResponse,this::handleError));
                break;
        }
    }

    private void handleResponse(User response) {
        Toast.makeText(this, "Success "+response.toString(), Toast.LENGTH_SHORT).show();

    }

    private void handleError(Throwable error) {

        Toast.makeText(this, "Error "+error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mCompositeDisposable.clear();
    }
}
