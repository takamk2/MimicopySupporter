package jp.local.yukichan.mimicopysupporter.ui.activity;

import android.os.Bundle;

import jp.local.yukichan.mimicopysupporter.R;
import timber.log.Timber;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Timber.i("onCreate called");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    @Override
    protected void onResume() {
        Timber.i("onResume called");
        super.onResume();

        if (isInitialized()) {
            HomeActivity.startActivity(this);
            finish();
        }
    }

    @Override
    public void onInitialized() {
        HomeActivity.startActivity(this);
        finish();
    }

    @Override
    protected void onDestroy() {
        Timber.i("onDestroy called");
        super.onDestroy();
    }
}
