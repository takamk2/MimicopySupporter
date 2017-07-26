package jp.local.yukichan.mimicopysupporter.application;

import android.app.Application;
import android.os.Handler;
import android.os.HandlerThread;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import jp.local.yukichan.mimicopysupporter.note.scale.ScaleManager;
import jp.local.yukichan.mimicopysupporter.sound.SoundManager;
import timber.log.Timber;

/**
 * Application class
 */
public class MCSPApplication extends Application {

    /** スケール保存や取得を行うためのクラス */
    private ScaleManager mScaleManager;

    /** 音声の読み込みと再生を行うためのクラス */
    private SoundManager mSoundManager;

    /** Initializeが完了しているかどうかフラグ */
    private boolean mIsInitialized = false;

    /** WorkerThread */
    private HandlerThread mWorkerThread = null;
    private Handler mWorkerHandler = null;

    /** このクラスからの通知を受けるためのListener */
    private List<AppListener> mAppListeners = new CopyOnWriteArrayList<>();

    /**
     * 初期化完了通知を受けるためのListener
     */
    public interface AppListener {
        void onInitialized();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        // Timberの有効化
        Timber.plant(new Timber.DebugTree());

        // WorkerThreadの開始
        mWorkerThread = new HandlerThread(MCSPApplication.class.getSimpleName());
        mWorkerThread.start();
        mWorkerHandler = new Handler(mWorkerThread.getLooper());

        initialize();
    }

    @Override
    public void onTerminate() {
        Timber.i("onTerminate called");
        mWorkerThread.quitSafely();
        super.onTerminate();
    }

    /**
     * 初期化が完了しているかどうか
     *
     * @return 初期化完了していればtrue、そうでなければfalse
     */
    public boolean isInitialized() {
        return mIsInitialized;
    }

    /**
     * ScaleManagerの取得
     *
     * @return ScaleManager
     */
    public ScaleManager getScaleManager() {
        return mScaleManager;
    }

    /**
     * SoundManagerの取得
     *
     * @return SoundManager
     */
    public SoundManager getSoundManager() {
        return mSoundManager;
    }

    /**
     * Listenerの登録
     *
     * @param appListener
     */
    public void registerAppListener(AppListener appListener) {
        Timber.i("registerAppListener");
        mAppListeners.add(appListener);
    }

    /**
     * Listenerの登録解除
     *
     * @param appListener
     */
    public void unregisterAppListener(AppListener appListener) {
        Timber.i("unregisterAppListener");
        mAppListeners.remove(appListener);
    }

    /**
     * 初期化処理
     */
    private void initialize() {
        mWorkerHandler.post(new Runnable() {
            @Override
            public void run() {
                mScaleManager = new ScaleManager(getApplicationContext());
                mSoundManager = new SoundManager(getApplicationContext());
                mIsInitialized = true;
                notifyInitialized();
            }
        });
    }

    /**
     * 初期化完了をListenerへ通知
     */
    private void notifyInitialized() {
        Timber.i("notifyInitialized");
        for (AppListener listener : mAppListeners) {
            listener.onInitialized();
        }
    }
}
