package jp.local.yukichan.mimicopysupporter.ui.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import jp.local.yukichan.mimicopysupporter.R;
import jp.local.yukichan.mimicopysupporter.application.MCSPApplication;
import jp.local.yukichan.mimicopysupporter.note.scale.Scale;
import jp.local.yukichan.mimicopysupporter.note.scale.ScaleManager;
import jp.local.yukichan.mimicopysupporter.ui.activity.ScaleDetectionActivity;
import timber.log.Timber;

/**
 * 選択済みScaleを表示するFragment
 */
public class DisplaySelectedScaleFragment extends Fragment {

    /** TAG */
    public static final String TAG = DisplaySelectedScaleFragment.class.getSimpleName();

    /** 画面タイプ */
    public static final String EXTRA_DISPLAY_TYPE = "extra_display_type";

    /** Scale名を表示するView */
    private TextView mScaleName;

    /** 変更ボタン */
    private TextView mTvModify;

    /** OKボタン */
    private TextView mTvOk;

    /** Cancelボタン */
    private TextView mTvCancel;

    /** ScaleManager */
    private ScaleManager mScaleManager;

    /** 表示する画面のタイプ */
    private DisplayType mDisplayType = DisplayType.DISPLAY;

    /** ScaleManagerからの通知を受け取るListener */
    private ScaleManager.Listener mScaleManagerListener = new ScaleManagerListenerImpl();

    /** ボタンクリックイベントのListener */
    private View.OnClickListener mOnClickListener = new OnClickListenerImpl();

    /** 表示する画面のタイプ */
    public enum DisplayType {
        /** 変更ボタン */
        DISPLAY,
        /** OKボタン, Cancelボタン */
        EDIT
    }

    /**
     * インスタンスの生成
     *
     * @param displayType
     * @return
     */
    public static DisplaySelectedScaleFragment newInstance(DisplayType displayType) {
        Timber.i("newInstance(displayType=%s) called", displayType.name());
        DisplaySelectedScaleFragment fragment = new DisplaySelectedScaleFragment();
        Bundle args = new Bundle();
        args.putString(EXTRA_DISPLAY_TYPE, displayType.name());
        fragment.setArguments(args);
        return fragment;
    }

    public DisplaySelectedScaleFragment() {
        // NOP
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Timber.i("onCreate called");
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            mDisplayType = convertDisplayType(args.getString(EXTRA_DISPLAY_TYPE, null));
        }

        mScaleManager = ((MCSPApplication) getActivity().getApplication()).getScaleManager();
        mScaleManager.registerListener(mScaleManagerListener);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Timber.i("onCreateView called");
        return inflater.inflate(R.layout.fragment_display_selected_scale, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Timber.i("onViewCreated called");
        super.onViewCreated(view, savedInstanceState);
        initializeView(view);
    }

    @Override
    public void onDestroy() {
        mScaleManager.unregisterListener(mScaleManagerListener);
        Timber.i("onViewCreated called");
        super.onDestroy();
    }

    /**
     * 画面のタイプの文字列をEnumに変換
     *
     * @param displayTypeStr
     * @return
     */
    private DisplayType convertDisplayType(String displayTypeStr) {
        Timber.i("convertDisplayType(displayTypeStr=%s) called", displayTypeStr);
        super.onDestroy();

        if (displayTypeStr != null) {
            for (DisplayType type : DisplayType.values()) {
                if (type.name().equals(displayTypeStr)) {
                    return type;
                }
            }
        }

        return DisplayType.DISPLAY;
    }

    /**
     * Viewの初期化
     *
     * @param view
     */
    private void initializeView(View view) {
        Timber.i("initializeView");
        mScaleName = (TextView) view.findViewById(R.id.scale_name);
        mTvModify = (TextView) view.findViewById(R.id.modify);
        mTvOk = (TextView) view.findViewById(R.id.ok);
        mTvCancel = (TextView) view.findViewById(R.id.cancel);

        Scale selectedScale;
        if (mDisplayType == DisplayType.EDIT) {
            selectedScale = mScaleManager.getSelectedScaleProvisional();
        } else {
            selectedScale = mScaleManager.getSelectedScale();
        }
        if (selectedScale != null) {
            mScaleName.setText(selectedScale.getName());
        }

        mTvModify.setOnClickListener(mOnClickListener);
        mTvOk.setOnClickListener(mOnClickListener);
        mTvCancel.setOnClickListener(mOnClickListener);

        if (mDisplayType == DisplayType.EDIT) {
            mTvModify.setVisibility(View.GONE);
            mTvOk.setVisibility(View.VISIBLE);
            mTvCancel.setVisibility(View.VISIBLE);
        } else {
            mTvModify.setVisibility(View.VISIBLE);
            mTvOk.setVisibility(View.GONE);
            mTvCancel.setVisibility(View.GONE);
        }
    }

    /**
     * Activityごと終了
     */
    private void finish() {
        Timber.i("finish");
        if (!getActivity().isFinishing()) {
            getActivity().finish();
        }
    }

    /**
     * ScaleManagerListenerの実装
     */
    private class ScaleManagerListenerImpl implements ScaleManager.Listener {

        /**
         * 選択済みScaleの変更通知
         *
         * @param scale
         */
        @Override
        public void onSelectedScaleChanged(Scale scale) {
            Timber.i("onSelectedScaleChanged(scale=%s)", scale.getName());
            if (mDisplayType == DisplayType.DISPLAY) {
                if (mScaleName != null) {
                    if (scale != null) {
                        mScaleName.setText(scale.getName());
                    } else {
                        mScaleName.setText("-----");
                    }
                }
            }
        }

        /**
         * 選択済み仮Scaleの変更通知
         * @param scale
         */
        @Override
        public void onSelectedScaleProvisionalChanged(Scale scale) {
            Timber.i("onSelectedScaleProvisionalChanged(scale=%s)", scale.getName());
            if (mDisplayType == DisplayType.EDIT) {
                if (mScaleName != null) {
                    if (scale != null) {
                        mScaleName.setText(scale.getName());
                    } else {
                        mScaleName.setText("-----");
                    }
                }
            }
        }
    }

    /**
     * OnClickListenerの実装
     */
    private class OnClickListenerImpl implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            Timber.i("onClick");
            switch (view.getId()) {
                case R.id.modify: {
                    // 変更ボタン
                    Timber.i("modify clicked");
                    ScaleDetectionActivity.startActivity(getActivity());
                    break;
                }
                case R.id.ok: {
                    // OKボタン
                    Timber.i("ok clicked");
                    mScaleManager.commit();
                    finish();
                    break;
                }
                case R.id.cancel: {
                    // Cancelボタン
                    Timber.i("cancel clicked");
                    mScaleManager.abandon();
                    getActivity().finish();
                    finish();
                    break;
                }
                default:
                    break;
            }
        }
    }
}
