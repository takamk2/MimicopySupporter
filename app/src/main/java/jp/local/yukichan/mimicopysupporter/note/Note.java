package jp.local.yukichan.mimicopysupporter.note;

import timber.log.Timber;

import static jp.local.yukichan.mimicopysupporter.note.RootNote.A;
import static jp.local.yukichan.mimicopysupporter.note.RootNote.AS;
import static jp.local.yukichan.mimicopysupporter.note.RootNote.B;
import static jp.local.yukichan.mimicopysupporter.note.RootNote.C;
import static jp.local.yukichan.mimicopysupporter.note.RootNote.CS;
import static jp.local.yukichan.mimicopysupporter.note.RootNote.D;
import static jp.local.yukichan.mimicopysupporter.note.RootNote.DS;
import static jp.local.yukichan.mimicopysupporter.note.RootNote.E;
import static jp.local.yukichan.mimicopysupporter.note.RootNote.F;
import static jp.local.yukichan.mimicopysupporter.note.RootNote.FS;
import static jp.local.yukichan.mimicopysupporter.note.RootNote.G;
import static jp.local.yukichan.mimicopysupporter.note.RootNote.GS;

/**
 * Note
 *
 * 鍵盤の数だけ音が存在する
 */
public enum Note {
    C0(0, C), CS0(1, CS), D0(2, D), DS0(3, DS), E0(4, E), F0(5, F), FS0(6, FS),
    G0(7, G), GS0(8, GS), A0(9, A), AS0(10, AS), B0(11, B),
    C1(12, C), CS1(13, CS), D1(14, D), DS1(15, DS), E1(16, E), F1(17, F), FS1(18, FS),
    G1(19, G), GS1(20, GS), A1(21, A), AS1(22, AS), B1(23, B),
    C2(24, C), CS2(25, CS), D2(26, D), DS2(27, DS), E2(28, E), F2(29, F), FS2(30, FS),
    G2(31, G), GS2(32, GS), A2(33, A), AS2(34, AS), B2(35, B),
    C3(36, C), CS3(37, CS), D3(38, D), DS3(39, DS), E3(40, E), F3(41, F), FS3(42, FS),
    G3(43, G), GS3(44, GS), A3(45, A), AS3(46, AS), B3(47, B),
    C4(48, C), CS4(49, CS), D4(50, D), DS4(51, DS), E4(52, E), F4(53, F), FS4(54, FS),
    G4(55, G), GS4(56, GS), A4(57, A), AS4(58, AS), B4(59, B);

    private static final int OCTAVE_INTERVAL = 12;

    private final Integer mNo;
    private final RootNote mRootNote;
    private final String mFileName;

    Note(Integer no, RootNote rootNote) {
        mNo = no;
        mRootNote = rootNote;
        mFileName = name().toLowerCase() + ".wav";
    }

    public static Note getNote(Integer noteNo) {
        Timber.i("getNote(noteNo=%s)", noteNo);

        for (Note note : Note.values()) {
            if (note.mNo.equals(noteNo)) {
                return note;
            }
        }

        return null;
    }

    public static Note getNote(int octave, int keyNo) {
        int noteNo = octave * OCTAVE_INTERVAL + keyNo;
        Timber.i("getNote(octave=%d, keyNote=%d) noteNo=%d", octave, keyNo, noteNo);
        return getNote(noteNo);
    }

    public RootNote getRootNote() {
        return mRootNote;
    }

    public String getFileName() {
        return mFileName;
    }
}
