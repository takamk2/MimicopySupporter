package jp.local.yukichan.mimicopysupporter.note.scale;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import jp.local.yukichan.mimicopysupporter.note.Note;
import jp.local.yukichan.mimicopysupporter.note.Notes;
import jp.local.yukichan.mimicopysupporter.note.RootNote;
import timber.log.Timber;

/**
 * スケールを求めるためのクラス
 */
public class ScaleDetection {

    /** Notes */
    private Notes mNotes = new Notes();

    public ScaleDetection() {
        // NOP
    }

    /**
     * Noteを追加
     *
     * @param note
     */
    public void addNote(Note note) {
        Timber.i("addNote(note=%s)", note.name());
        mNotes.addNote(note);
    }

    /**
     * Noteを削除
     *
     * @param note
     */
    public void removeNote(Note note) {
        Timber.i("removeNote(note=%s)", note.name());
        mNotes.removeNote(note);
    }

    /**
     * Scaleを検出する
     *
     * @return 検出したScale一覧
     */
    public List<Scale> detect() {
        Timber.i("detect");

        // TODO: NotesにヒットするScaleだけを返すようにする
        List<Scale> scales = new ArrayList<>();
        for (ScaleConstituent scaleConstituent : ScaleConstituent.values()) {
            for (RootNote rootNote : RootNote.values()) {
                Scale scale = new Scale.Builder()
                        .setRootNote(rootNote)
                        .setScaleConstituent(scaleConstituent)
                        .build();
                if (scale != null) {
                    scales.add(scale);
                }
            }
        }

        return scales;
    }
}
