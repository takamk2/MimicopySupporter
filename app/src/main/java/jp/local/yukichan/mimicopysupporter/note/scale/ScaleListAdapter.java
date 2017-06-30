package jp.local.yukichan.mimicopysupporter.note.scale;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import jp.local.yukichan.mimicopysupporter.R;
import timber.log.Timber;

/**
 * ScaleList用Adapter
 */
public class ScaleListAdapter extends BaseAdapter {

    /** Context */
    private final Context mContext;

    /** レイアウト生成用のInflater */
    private final LayoutInflater mInflater;

    /** ScaleManager */
    private final ScaleManager mScaleManager;

    /** スケールの一覧 */
    private List<Scale> mScales = new CopyOnWriteArrayList<>();

    public ScaleListAdapter(Context context, ScaleManager scaleManager) {
        mContext = context;
        mScaleManager = scaleManager;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        Timber.i("getCount");
        return mScales.size();
    }

    @Override
    public Scale getItem(int position) {
        Timber.i("getItem(position=%d)", position);
        return mScales.get(position);
    }

    @Override
    public long getItemId(int position) {
        Timber.i("getItemId(position=%d)", position);
        // 使用しない、常に0を返す
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Timber.i("getView(position=%d)", position);

        // Viewの生成
        View view = convertView;
        if (view == null) {
            view = mInflater.inflate(R.layout.item_scale_list, parent, false);
        }

        // スケールの取得
        final Scale scale = getItem(position);

        // Viewの取得
        TextView tvName = (TextView) view.findViewById(R.id.name);

        // Scale名のセット
        tvName.setText(scale.getName());

        // 選択済みなら背景色を変える
        Scale selectedScale = mScaleManager.getSelectedScaleProvisional();
        if (selectedScale != null && selectedScale.equals(scale)) {
            view.setBackgroundResource(R.color.colorItemSelected);
        } else {
            view.setBackgroundResource(R.color.colorItemUnselected);
        }

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Timber.i("%s#onClick", getClass().getSimpleName());
                // TODO: 音を鳴らす
            }
        });

        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Timber.i("%s#onLongClick", getClass().getSimpleName());
                mScaleManager.setSelectedScaleProvisional(scale);
                notifyDataSetChanged();
                return true;
            }
        });

        return view;
    }

    public void setScales(List<Scale> scales) {
        Timber.i("setScales");
        mScales.clear();
        mScales.addAll(scales);
    }
}
