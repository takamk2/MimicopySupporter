package jp.local.yukichan.mimicopysupporter.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import jp.local.yukichan.mimicopysupporter.R;
import timber.log.Timber;

/**
 * Home画面
 */
public class HomeActivity extends Activity {

    /** Startボタン */
    private Button mBtStart;

    /** Aboutボタン */
    private Button mBtToAbout;

    /** ClickListener */
    private View.OnClickListener mOnClickListener = new OnClickListenerImpl();

    /**
     * Activityの起動
     *
     * @param context
     */
    public static void startActivity(Context context) {
        Timber.i("startActivity called");
        Intent intent = new Intent(context, HomeActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Timber.i("onCreate called");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initializeView();
    }

    /**
     * Viewの初期化
     */
    private void initializeView() {
        Timber.i("initializeView");
        mBtStart = (Button) findViewById(R.id.start);
        mBtToAbout = (Button) findViewById(R.id.to_about);

        mBtStart.setOnClickListener(mOnClickListener);
        mBtToAbout.setOnClickListener(mOnClickListener);
    }

    private class OnClickListenerImpl implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            Timber.i("onClick called");
            switch (view.getId()) {
                case R.id.start:
                    CodeDetectionActivity.startActivity(HomeActivity.this);
                    break;
                case R.id.to_about:
                    AboutActivity.startActivity(HomeActivity.this);
                    break;
                default:
                    break;
            }
        }
    }
}
