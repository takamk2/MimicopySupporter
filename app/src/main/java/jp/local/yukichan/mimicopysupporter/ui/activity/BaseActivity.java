package jp.local.yukichan.mimicopysupporter.ui.activity;

import android.app.Activity;
import android.os.Bundle;

import jp.local.yukichan.mimicopysupporter.application.MCSPApplication;
import jp.local.yukichan.mimicopysupporter.application.MCSPApplication.AppListener;
import timber.log.Timber;

/**
 * Class of Base for Activity
 */
public abstract class BaseActivity extends Activity {

    private final MCSPApplication.AppListener mAppListener = new AppListenerImpl();

    private MCSPApplication mApplication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Timber.i("[%s]onCreate called", getClass().getSimpleName());
        super.onCreate(savedInstanceState);

        mApplication = (MCSPApplication) getApplication();
        mApplication.registerAppListener(mAppListener);
    }

    @Override
    protected void onDestroy() {
        Timber.i("[%s]onDestroy called", getClass().getSimpleName());
        mApplication.unregisterAppListener(mAppListener);
        super.onDestroy();
    }

    /**
     * 初期化完了通知
     */
    public void onInitialized() {
        Timber.i("[%s]onInitialized called", getClass().getSimpleName());
    }

    /**
     * 初期化が完了しているかどうか
     *
     * @return 初期化が完了していればtrue、そうでなければfalse
     */
    public boolean isInitialized() {
        return mApplication.isInitialized();
    }

    private class AppListenerImpl implements AppListener {

        @Override
        public void onInitialized() {
            BaseActivity.this.onInitialized();
        }
    }
}
