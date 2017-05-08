package com.rrmsense.retrofit2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    public static final String BASE_URL = "http://www.rrmelectronics.com/";

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

                UserT userT = new UserT(etUserName.getText().toString(), etPassword.getText().toString());

                Gson gson = new GsonBuilder()
                        .setLenient()
                        .create();

                RequestInterface requestInterface = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .build().create(RequestInterface.class);

                mCompositeDisposable.add(requestInterface.Login(userT.getUserName(),userT.getPassword())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(this::handleResponse,this::handleError));
                break;
        }
    }

    private void handleResponse(String response) {
        Toast.makeText(this, "Success "+response, Toast.LENGTH_SHORT).show();

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
