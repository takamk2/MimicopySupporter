package jp.local.yukichan.mimicopysupporter.ui.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import jp.local.yukichan.mimicopysupporter.R;
import jp.local.yukichan.mimicopysupporter.application.MCSPApplication;
import jp.local.yukichan.mimicopysupporter.note.scale.ScaleListAdapter;
import jp.local.yukichan.mimicopysupporter.note.scale.ScaleManager;
import timber.log.Timber;

/**
 * 検出したScaleの一覧を表示するFragment
 */
public class DetectedScaleListFragment extends Fragment {

    /** TAG */
    public static final String TAG = DetectedScaleListFragment.class.getSimpleName();

    /** Context */
    private Context mContext;

    /** ScaleManager */
    private ScaleManager mScaleManager;

    /** Scaleを表示するGridView */
    private GridView mGvScaleList;

    /** ScaleList用Adapter */
    private ScaleListAdapter mScaleListAdapter;

    /**
     * インスタンスの生成
     *
     * @return
     */
    public static DetectedScaleListFragment newInstance() {
        Timber.i("newInstance called");
        DetectedScaleListFragment fragment = new DetectedScaleListFragment();
        return fragment;
    }

    public DetectedScaleListFragment() {
        // NOP
    }

    @Override
    public void onAttach(Context context) {
        Timber.i("onAttach called");
        super.onAttach(context);
        mContext = context;
        mScaleManager =
                ((MCSPApplication) mContext.getApplicationContext()).getScaleManager();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Timber.i("onCreate called");
        super.onCreate(savedInstanceState);

        mScaleListAdapter = new ScaleListAdapter(getActivity(), mScaleManager);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Timber.i("onCreateView called");
        return inflater.inflate(R.layout.fragment_detected_scale_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Timber.i("onViewCreated called");
        super.onViewCreated(view, savedInstanceState);
        initializeView();
    }

    @Override
    public void onDestroy() {
        Timber.i("onDestroy called");
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        Timber.i("onDetach called");
        super.onDetach();
    }

    /**
     * Viewの初期化
     */
    private void initializeView() {
        Timber.i("initializeView");
        mGvScaleList = (GridView) getView().findViewById(R.id.scale_list);
        mGvScaleList.setAdapter(mScaleListAdapter);
        mScaleListAdapter.notifyDataSetChanged();
    }
}
