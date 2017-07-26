package jp.local.yukichan.mimicopysupporter.note.scale;

import android.provider.DocumentsContract;

import java.util.ArrayList;
import java.util.List;

import jp.local.yukichan.mimicopysupporter.note.RootNote;
import timber.log.Timber;

import static jp.local.yukichan.mimicopysupporter.note.RootNote.A;
import static jp.local.yukichan.mimicopysupporter.note.RootNote.B;
import static jp.local.yukichan.mimicopysupporter.note.RootNote.C;
import static jp.local.yukichan.mimicopysupporter.note.RootNote.D;
import static jp.local.yukichan.mimicopysupporter.note.RootNote.E;
import static jp.local.yukichan.mimicopysupporter.note.RootNote.F;
import static jp.local.yukichan.mimicopysupporter.note.RootNote.G;

/**
 * スケールを表すクラス
 */
public class Scale {

    /** RootNote */ // TODO: Json
    private final RootNote mRootNote;

    /** Root音からのインターバル */ // TODO: Json
    private final ScaleConstituent mScaleConstituent;

    /** スケール名 */
    private final String mName;

    /** スケールの構成音 */
    private final RootNote[] mIntervalIndex;

    private Scale(RootNote rootNote, ScaleConstituent scaleConstituent) {
        mRootNote = rootNote;
        mScaleConstituent = scaleConstituent;
        mName = mRootNote.getDisplayName() + " " + mScaleConstituent.getDisplayName();
        mIntervalIndex = new RootNote[]{C, D, E, F, G, A, B}; // 白鍵上の音
    }

    @Override
    public String toString() {
        Timber.i("toString");

        String a = mRootNote.name().substring(0, 1);
        int index = 0;
        for (int i = 0; i < mIntervalIndex.length; i++) {
            RootNote n = mIntervalIndex[i];
            String b = n.name();
            if (a.equals(b)) break;
            index++;
        }

        StringBuffer sb = new StringBuffer();
        for (Integer i : mScaleConstituent.getInterval()) {
            Integer noteNo = mRootNote.getNoteNo() + i;
            RootNote note = RootNote.getNote(noteNo);

            String c = mIntervalIndex[index].name();
            if (!note.name().startsWith(c)) {
                note = note.getEnharmonicNote();
            }

            if (sb.length() > 0) sb.append(",");
            sb.append(note.name());
            if (index < mIntervalIndex.length - 1) {
                index++;
            } else {
                index = 0;
            }
        }
        sb.insert(0, mRootNote + ": ");
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        Timber.i("equals");

        if (obj == null) {
            Timber.i("equals: obj is null");
            return false;
        }

        if (!(obj instanceof Scale)) {
            Timber.i("equals: Instance of obj is not Scale. %s", obj.getClass().getSimpleName());
            return false;
        }

        Scale target = (Scale) obj;
        if (!target.getName().equals(getName())) {
            Timber.i("equals: Target and this are not the same. %s:%s",
                    target.getName(), this.getName());
            return false;
        }

        Timber.i("equals: Target and this are the same! %s:%s",
                target.getName(), this.getName());
        return true;
    }

    public String getName() {
        return mName;
    }

    public List<RootNote> getRootNotes() {

        List<RootNote> rooteNotes = new ArrayList<>();

        for (Integer i : mScaleConstituent.getInterval()) {
            Integer noteNo = mRootNote.getNoteNo() + i;
            rooteNotes.add(RootNote.getNote(noteNo));
        }

        return rooteNotes;
    }

    public static class Builder {

        private RootNote mmRootNote = null;
        private ScaleConstituent mmScaleConstituent = null;

        public Scale build() {
            if (mmRootNote == null) {
                return null;
            }

            if (mmScaleConstituent == null) {
                return null;
            }

            if (!mmRootNote.canUseToScaleName()) {
                return null;
            }

            return new Scale(mmRootNote, mmScaleConstituent);
        }

        public Builder setRootNote(RootNote rootNote) {
            mmRootNote = rootNote;
            return this;
        }

        public Builder setScaleConstituent(ScaleConstituent scaleConstituent) {
            mmScaleConstituent = scaleConstituent;
            return this;
        }
    }
}
