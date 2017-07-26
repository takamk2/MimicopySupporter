package jp.local.yukichan.mimicopysupporter.note.code;

import jp.local.yukichan.mimicopysupporter.note.RootNote;

/**
 * Created by takamk2 on 17/07/13.
 * <p>
 * The Edit Fragment of Base Class.
 */

public class Code {

    private final RootNote mRootNote;
    private final CodeConstituent mCodeConstituent;

    public Code(RootNote rootNote, CodeConstituent codeConstituent) {
        mRootNote = rootNote;
        mCodeConstituent = codeConstituent;
    }

    public String getDisplayName() {
        return mRootNote.getDisplayName() + mCodeConstituent.getDisplayName();
    }
}
