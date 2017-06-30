package jp.local.yukichan.mimicopysupporter.ui.fragment;

import android.app.Fragment;

import timber.log.Timber;

/**
 * Created by takamk2 on 17/06/29.
 * <p>
 * The Edit Fragment of Base Class.
 */

public class DisplaySelectedCodeFragment extends Fragment {

    public static final String TAG = DisplaySelectedScaleFragment.class.getSimpleName();

    public static DisplaySelectedCodeFragment newInstance() {
        Timber.i("DisplaySelectedCodeFragment called");
        DisplaySelectedCodeFragment fragment = new DisplaySelectedCodeFragment();
        return fragment;
    }
}
