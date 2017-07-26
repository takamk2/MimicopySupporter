package jp.local.yukichan.mimicopysupporter.note.code;

/**
 * Created by takamk2 on 17/07/13.
 * <p>
 * The Edit Fragment of Base Class.
 */

enum CodeConstituent {
    MAJOR(""),
    MAJOR_SEVEN("M7"),
    MINOR("m"),
    MINOR_SEVEN("m7"),
    SEVEN("7"),
    MINOR_SEVEN_FLAT_FIVE("m7-5");

    private final String mDisplayName;

    CodeConstituent(String displayName) {
        mDisplayName = displayName;
    }

    public String getDisplayName() {
        return mDisplayName;
    }
}
