package jp.local.yukichan.mimicopysupporter.ui.activity;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import jp.local.yukichan.mimicopysupporter.R;
import jp.local.yukichan.mimicopysupporter.application.MCSPApplication;
import jp.local.yukichan.mimicopysupporter.note.scale.ScaleManager;
import jp.local.yukichan.mimicopysupporter.ui.fragment.DetectedScaleListFragment;
import jp.local.yukichan.mimicopysupporter.ui.fragment.DisplaySelectedScaleFragment;
import jp.local.yukichan.mimicopysupporter.ui.fragment.DisplaySelectedScaleFragment.DisplayType;
import timber.log.Timber;

/**
 * Scaleを検出する画面
 */
public class ScaleDetectionActivity extends Activity {

    /** 選択したScaleを表示するFragment */
    private DisplaySelectedScaleFragment mDisplaySelectedScaleFragment;

    /** 検出したスケールの一覧を表示するFragment */
    private DetectedScaleListFragment mDetectionScaleListFragment;

    /** ScaleManager */
    private ScaleManager mScaleManager;

    /**
     * Activityの起動
     *
     * @param context
     */
    public static void startActivity(Context context) {
        Timber.i("startActivity called");
        Intent intent = new Intent(context, ScaleDetectionActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Timber.i("onCreate called");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scale_detection);

        mScaleManager = ((MCSPApplication)getApplication()).getScaleManager();

        initializeFragments();
    }

    /**
     * Fragmentの初期化
     */
    private void initializeFragments() {
        Timber.i("initializeFragments");
        mDisplaySelectedScaleFragment =
                DisplaySelectedScaleFragment.newInstance(DisplayType.EDIT);
        mDetectionScaleListFragment = DetectedScaleListFragment.newInstance();

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.add(R.id.fragment_display_selected_scale, mDisplaySelectedScaleFragment,
                DisplaySelectedScaleFragment.TAG);
        transaction.add(R.id.fragment_detected_scale_list, mDetectionScaleListFragment,
                DetectedScaleListFragment.TAG);
        transaction.commit();
    }
}
