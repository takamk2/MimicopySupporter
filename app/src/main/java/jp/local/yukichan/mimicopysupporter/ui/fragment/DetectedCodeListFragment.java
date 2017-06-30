package jp.local.yukichan.mimicopysupporter.ui.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import jp.local.yukichan.mimicopysupporter.R;
import timber.log.Timber;

/**
 * 検出したコードの一覧を表示するFragment
 */
public class DetectedCodeListFragment extends Fragment {

    /**
     * インスタンスの生成
     *
     * @return
     */
    public static DetectedCodeListFragment newInstance() {
        Timber.i("newInstance called");
        DetectedCodeListFragment fragment = new DetectedCodeListFragment();
        return fragment;
    }

    public DetectedCodeListFragment() {
        // NOP
    }

    @Override
    public void onAttach(Context context) {
        Timber.i("onAttach");
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Timber.i("onCreate");
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Timber.i("onCreateView");
        return inflater.inflate(R.layout.fragment_detected_code_list, container, false);
    }

    @Override
    public void onDetach() {
        Timber.i("onDetach");
        super.onDetach();
    }
}
