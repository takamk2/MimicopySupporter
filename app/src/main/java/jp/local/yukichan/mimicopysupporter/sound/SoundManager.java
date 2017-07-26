package jp.local.yukichan.mimicopysupporter.sound;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.media.SoundPool.OnLoadCompleteListener;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import jp.local.yukichan.mimicopysupporter.note.Note;
import timber.log.Timber;

/**
 * SoundManager
 */
public class SoundManager {

    /** Context */
    private final Context mContext;

    /** 読み込み完了通知を受けるためのListener */
    private final OnLoadCompleteListener mOnLoadCompleteListener = new OnLoadCompleteListenerImpl();

    /** SoundPool */
    private SoundPool mSoundPool;

    /** Note - soundId */
    private Map<Note, Integer> mSoundIdMap = new HashMap<>();

    public SoundManager(Context context) {
        mContext = context;
        initialize();
    }

    public void play(Note note) {
        if (note == null) {
            Timber.i("play: note is null");
            return;
        }
        Timber.i("play(note=%s)", note.name());
        if (!mSoundIdMap.containsKey(note)) {
            Timber.d("play: note is not exist");
            return;
        }
        Integer soundId = mSoundIdMap.get(note);
        if (soundId == null) {
            Timber.d("play: soundId is null");
            return;
        }
        Timber.i("play soundId=%d", soundId);
        mSoundPool.play(soundId, 1f, 1f, 1, 0, 1f);
    }

    private void initialize() {
        Timber.i("initialize");

        AudioAttributes audioAttributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .build();

        mSoundPool = new SoundPool.Builder()
                .setAudioAttributes(audioAttributes)
                .setMaxStreams(16)
                .build();
        mSoundPool.setOnLoadCompleteListener(mOnLoadCompleteListener);

        for (Note note : Note.values()) {
            AssetFileDescriptor descriptor = null;
            try {
                descriptor = mContext.getAssets().openFd(note.getFileName());
            } catch (IOException e) {
                Timber.w("IOException occurred", e);
            }
            if (descriptor == null) {
                Timber.w("descriptor is null");
                continue;
            }
            int soundId = mSoundPool.load(descriptor, 1);
            mSoundIdMap.put(note, soundId);
        }
    }

    private class OnLoadCompleteListenerImpl implements OnLoadCompleteListener {

        @Override
        public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
            Timber.i("onLoadComplete sampleId=%d status=%d", sampleId, status);
        }
    }
}
