package jp.local.yukichan.mimicopysupporter.note;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import timber.log.Timber;

/**
 * Noteをまとめるクラス
 */

public class Notes {

    Set<Note> mNoteSet = new CopyOnWriteArraySet<>();

    public Notes() {
    }

    public void addNote(Note note) {
        Timber.i("addNote(note=%s)", note.name());
        mNoteSet.add(note);
    }

    public void removeNote(Note note) {
        Timber.i("removeNote(note=%s)", note.name());
        mNoteSet.remove(note);
    }
}
