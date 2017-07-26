package jp.local.yukichan.mimicopysupporter.ui.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import jp.local.yukichan.mimicopysupporter.R;
import jp.local.yukichan.mimicopysupporter.note.code.Code;
import jp.local.yukichan.mimicopysupporter.note.code.CodeDetection;
import timber.log.Timber;

/**
 * 検出したコードの一覧を表示するFragment
 */
public class DetectedCodeListFragment extends Fragment {

    public static final String TAG = DetectedCodeListFragment.class.getSimpleName();

    /** CodeList用Adapter */
    private CodeListAdapter mCodeListAdapter;

    /** Scaleを表示するGridView */
    private GridView mGvCodeList;

    private CodeDetection mCodeDetection;

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
        mCodeDetection = new CodeDetection();
        mCodeListAdapter = new CodeListAdapter(getActivity());
        mCodeListAdapter.setCodes(mCodeDetection.getDetectedCodes());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Timber.i("onCreateView");
        return inflater.inflate(R.layout.fragment_detected_code_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mGvCodeList = (GridView) view.findViewById(R.id.code_list);
        mGvCodeList.setAdapter(mCodeListAdapter);
        mCodeListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDetach() {
        Timber.i("onDetach");
        super.onDetach();
    }

    private class CodeListAdapter extends BaseAdapter {

        private final LayoutInflater mInflater;

        private final List<Code> mCodes = new ArrayList<>();

        public CodeListAdapter(Context context) {
            mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return mCodes.size();
        }

        @Override
        public Code getItem(int position) {
            return mCodes.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;
            if (convertView == null) {
                view = mInflater.inflate(R.layout.item_code_list, parent, false);
            }

            Code code = mCodes.get(position);

            TextView tvName = (TextView) view.findViewById(R.id.tv_name);
            tvName.setText(code.getDisplayName());

            return view;
        }

        public void setCodes(List<Code> codes) {
            mCodes.clear();
            mCodes.addAll(codes);
        }
    }
}
