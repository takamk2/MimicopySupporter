package jp.local.yukichan.mimicopysupporter.note.scale;

/**
 * スケールの構成音
 */

public enum  ScaleConstituent {
    MAJOR_SCALE("メジャースケール", new Integer[] {0, 2, 4, 5, 7, 9, 11});
//    MINOR_SCALE("マイナースケール", new Integer[] {0, 2, 3, 5, 7, 8, 10}); // TODO: マイナーは難しいので後回し

    private final String mDisplayName;
    private final Integer[] mInterval;

    ScaleConstituent(String displayName, Integer[] interval) {
        mDisplayName = displayName;
        mInterval = interval;
    }

    /**
     * 表示用名称を取得
     *
     * @return
     */
    public String getDisplayName() {
        return mDisplayName;
    }

    /**
     * 構成音のインターバルを取得
     *
     * @return
     */
    public Integer[] getInterval() {
        return mInterval;
    }
}
