package jp.local.yukichan.mimicopysupporter.note.scale;

import android.content.Context;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Scaleを管理するためのクラス
 */
public class ScaleManager {

    private final Context mContext;

    private List<Scale> mHoleScaleList;

    /** 選択中のScale */
    private Scale mSelectedScale = null;

    /** 選択中のスケール(仮) */
    private Scale mSelectedScaleProvisional = null;

    /** ScaleManagerからの通知を受け取る受け取るためのManager */
    private List<Listener> mListeners = new CopyOnWriteArrayList<>();

    /** Synchronized用　*/
    private Object mLock = new Object();

    /**
     * 変更通知用Listener
     */
    public interface Listener {
        /**
         * 選択済みScaleが変わったことの通知
         *
         * @param scale
         */
        void onSelectedScaleChanged(Scale scale);

        /**
         * 選択済みScale(仮)が変わったことの通知
         * @param scale
         */
        void onSelectedScaleProvisionalChanged(Scale scale);
    }

    public ScaleManager(Context context) {
        mContext = context;
        ScaleDetection scaleDetection = new ScaleDetection();
        mHoleScaleList = scaleDetection.detect();
    }

    /**
     * 選択済みScaleを取得する
     *
     * @return
     */
    public Scale getSelectedScale() {
        return mSelectedScale;
    }

    /**
     * 選択済みScale(仮)をセットする
     *
     * @param scale
     */
    public void setSelectedScaleProvisional(Scale scale) {
        synchronized (mLock) {
            mSelectedScaleProvisional = scale;
            notifySelectedScaleProvisionalChanged(scale);
        }
    }

    /**
     * 選択済みScale(仮)を取得する
     *
     * @return
     */
    public Scale getSelectedScaleProvisional() {
        return mSelectedScaleProvisional;
    }

    /**
     * 選択済みScaleを確定する
     */
    public void commit() {
        synchronized (mLock) {
            mSelectedScale = mSelectedScaleProvisional;
            notifySelectedScaleChanged(mSelectedScale);
            // TODO: データを永続化する
        }
    }

    /**
     * 選択済みScale(仮)を取り消す
     */
    public void abandon() {
        synchronized (mLock) {
            mSelectedScaleProvisional = mSelectedScale;
        }
    }

    /**
     * ScaleManagerListenerを登録する
     *
     * @param listener
     */
    public void registerListener(Listener listener) {
        mListeners.add(listener);
    }

    /**
     * ScaleManagerListenerを解除する
     *
     * @param listener
     */
    public void unregisterListener(Listener listener) {
        mListeners.remove(listener);
    }

    /**
     * 選択済みScaleが変わったことを通知する
     *
     * @param scale
     */
    private void notifySelectedScaleChanged(Scale scale) {
        for (Listener listener : mListeners) {
            listener.onSelectedScaleChanged(scale);
        }
    }

    /**
     * 選択済みScale(仮)が変わったことを通知する
     *
     * @param scale
     */
    private void notifySelectedScaleProvisionalChanged(Scale scale) {
        for (Listener listener : mListeners) {
            listener.onSelectedScaleProvisionalChanged(scale);
        }
    }
}
