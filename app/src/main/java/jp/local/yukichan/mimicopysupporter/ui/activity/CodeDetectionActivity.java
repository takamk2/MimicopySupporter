package jp.local.yukichan.mimicopysupporter.ui.activity;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toolbar;

import jp.local.yukichan.mimicopysupporter.R;
import jp.local.yukichan.mimicopysupporter.ui.fragment.DetectedCodeListFragment;
import jp.local.yukichan.mimicopysupporter.ui.fragment.DisplaySelectedCodeFragment;
import jp.local.yukichan.mimicopysupporter.ui.fragment.DisplaySelectedScaleFragment;
import jp.local.yukichan.mimicopysupporter.ui.fragment.DisplaySelectedScaleFragment.DisplayType;
import jp.local.yukichan.mimicopysupporter.ui.fragment.KeyboardFragment;
import timber.log.Timber;

/**
 * コードを検出する画面
 */
public class CodeDetectionActivity extends BaseActivity {

    /** スケールを表示するFragment */
    private DisplaySelectedScaleFragment mDisplaySelectedScaleFragment;

    /** 選択したコードを表示するFragment */
    private DisplaySelectedCodeFragment mDisplaySelectedCodeFragment;

    /** 候補コード一覧を表示するFragment */
    private DetectedCodeListFragment mDetectedCodeListFragment;

    /** キーボードFragment　*/
    private KeyboardFragment mKeyboardFragment;

    /**
     * Activityの起動
     *
     * @param context Context
     */
    public static void startActivity(Context context) {
        Timber.i("startActivity called");
        Intent intent = new Intent(context, CodeDetectionActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Timber.i("startActivity called");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code_detection);

        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setActionBar(toolbar);

        initializeFragments();
    }

    @Override
    protected void onResume() {
        Timber.i("onResume called");
        super.onResume();
    }

    /**
     * 各種Fragmentの初期化
     */
    private void initializeFragments() {
        Timber.i("initializeFragments");
        mDisplaySelectedScaleFragment =
                DisplaySelectedScaleFragment.newInstance(DisplayType.DISPLAY);
        mDisplaySelectedCodeFragment = DisplaySelectedCodeFragment.newInstance();
        mDetectedCodeListFragment = DetectedCodeListFragment.newInstance();
        mKeyboardFragment = KeyboardFragment.newInstance();

        FragmentTransaction transaction;
        transaction = getFragmentManager().beginTransaction();
        transaction.add(R.id.fragment_display_selected_scale, mDisplaySelectedScaleFragment,
                DisplaySelectedScaleFragment.TAG);
        transaction.add(R.id.fragment_display_selected_code, mDisplaySelectedCodeFragment,
                DisplaySelectedCodeFragment.TAG);
        transaction.add(R.id.fragment_detected_code_list, mDetectedCodeListFragment,
                DetectedCodeListFragment.TAG);
        transaction.add(R.id.fragment_keyboard, mKeyboardFragment, KeyboardFragment.TAG);
        transaction.commit();
    }
}
