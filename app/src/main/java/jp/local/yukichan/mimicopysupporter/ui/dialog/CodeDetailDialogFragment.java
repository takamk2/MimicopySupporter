package jp.local.yukichan.mimicopysupporter.ui.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import jp.local.yukichan.mimicopysupporter.R;
import timber.log.Timber;

/**
 * コード詳細画面
 */
public class CodeDetailDialogFragment extends DialogFragment {

    public static final String TAG = CodeDetailDialogFragment.class.getSimpleName();

    /**
     * Dialogの表示
     *
     * @param activity
     */
    public static void showDialog(Activity activity) {
        Timber.i("showDialog called");
        FragmentManager fm = activity.getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        Fragment prevDialogFragment = fm.findFragmentByTag(TAG);
        if (prevDialogFragment != null) {
            ft.remove(prevDialogFragment);
        }
        ft.addToBackStack(null);

        CodeDetailDialogFragment dialogFragment = CodeDetailDialogFragment.newInstance();
        dialogFragment.show(ft, TAG);
    }

    /**
     * インスタンスの取得
     *
     * @return
     */
    private static CodeDetailDialogFragment newInstance() {
        CodeDetailDialogFragment dialogFragment = new CodeDetailDialogFragment();
        return dialogFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_code_detail, container, false);
    }
}
