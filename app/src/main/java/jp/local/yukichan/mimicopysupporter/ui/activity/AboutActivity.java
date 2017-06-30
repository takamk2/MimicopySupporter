package jp.local.yukichan.mimicopysupporter.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import jp.local.yukichan.mimicopysupporter.R;
import timber.log.Timber;

/**
 * About画面
 */
public class AboutActivity extends Activity {

    public static void startActivity(Context context) {
        Timber.i("startActivity called");
        Intent intent = new Intent(context, AboutActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Timber.i("onCreate called");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
    }
}
