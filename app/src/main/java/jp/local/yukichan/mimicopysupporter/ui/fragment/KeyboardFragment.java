package jp.local.yukichan.mimicopysupporter.ui.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import jp.local.yukichan.mimicopysupporter.R;
import jp.local.yukichan.mimicopysupporter.application.MCSPApplication;
import jp.local.yukichan.mimicopysupporter.note.Note;
import jp.local.yukichan.mimicopysupporter.sound.SoundManager;
import jp.local.yukichan.mimicopysupporter.ui.view.KeyboardView;
import timber.log.Timber;

/**
 * キーボードを表示するFragment
 */
public class KeyboardFragment extends Fragment {

    /** TAG */
    public static final String TAG = KeyboardFragment.class.getSimpleName();

    /** KeyboardView */
    private KeyboardView mKeyboardView;

    /** KeyboardViewの通知を受けるListener */
    private KeyboardView.OnKeyListener mOnKeyListener = new OnKeyListenerImpl();

    /** Fragmentからの通知を受け取るためのListener */
    private List<Listener> mListeners = new CopyOnWriteArrayList<>();

    private SoundManager mSoundManager;

    /**
     * インスタンスの生成
     *
     * @return
     */
    public static KeyboardFragment newInstance() {
        Timber.i("newInstance called");
        KeyboardFragment fragment = new KeyboardFragment();
        return fragment;
    }

    public interface Listener extends KeyboardView.OnKeyListener {}

    @Override
    public void onAttach(Context context) {
        Timber.i("onAttach called");
        super.onAttach(context);
        MCSPApplication application = (MCSPApplication) getActivity().getApplication();
        mSoundManager = application.getSoundManager();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Timber.i("onCreate called");
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Timber.i("onCreateView called");
        return inflater.inflate(R.layout.fragment_keyboard, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Timber.i("onViewCreated called");
        super.onViewCreated(view, savedInstanceState);
        initializeView(view);
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

    public void registerListener(Listener listener) {
        mListeners.add(listener);
    }

    public void unregisterListener(Listener listener) {
        mListeners.remove(listener);
    }

    public boolean addSelectedKeyNo(int octave, int keyNo) {
        Timber.i("addSelectedKeyNo: octave=%d keyNo=%d", octave, keyNo);
        return mKeyboardView.addSelectedKeyNo(octave, keyNo);
    }

    public boolean removeSelectedKeyNo(int octave, int keyNo) {
        Timber.i("removeSelectedKeyNo: octave=%d keyNo=%d", octave, keyNo);
        return mKeyboardView.removeSelectedKeyNo(octave, keyNo);
    }

    public boolean isSelectedKeyNo(int octave, int keyNo) {
        Timber.i("isSelectedKeyNo: octave=%d keyNo=%d", octave, keyNo);
        return mKeyboardView.isSelectedKeyNo(octave, keyNo);
    }

    private void initializeView(View view) {
        Timber.i("initializeView");
        mKeyboardView = (KeyboardView) view.findViewById(R.id.keyboard);

        mKeyboardView.setOnKeyListener(mOnKeyListener);
    }

    private class OnKeyListenerImpl implements KeyboardView.OnKeyListener {

        @Override
        public void onKeyPressed(int octave, int keyNo) {
            Timber.i("onKeyPressed(octave=%d, keyNo=%d)", octave, keyNo);
            mSoundManager.play(Note.getNote(octave, keyNo));
            for (Listener listener : mListeners) {
                listener.onKeyPressed(octave, keyNo);
            }
        }

        @Override
        public void onKeyLongPressed(int octave, int keyNo) {
            Timber.i("onKeyLongPressed(octave=%d, keyNo=%d)", octave, keyNo);
            for (Listener listener : mListeners) {
                listener.onKeyLongPressed(octave, keyNo);
            }
        }

        @Override
        public void onKeyReleased(int octave, int keyNo) {
            Timber.i("onKeyReleased(octave=%d, keyNo=%d)", octave, keyNo);
            for (Listener listener : mListeners) {
                listener.onKeyReleased(octave, keyNo);
            }
        }
    }
}
