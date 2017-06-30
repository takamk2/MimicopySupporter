package jp.local.yukichan.mimicopysupporter.note;

/**
 * 1オクターブ分のルートノート
 */
public enum RootNote {
    // 基本ノート
    C {
        @Override
        void initializeParameters() {
            this.mId = 0;
            this.mNoteNo = 0;
            this.mDisplayName = "C";
            this.mCanUseToScaleName = true;
            this.mIsShapeScale = true;
            this.mHasShapeSymbol = false;
            this.mHasFlatSymbol = false;
        }

        @Override
        public RootNote getEnharmonicNote() {
            return BS;
        }
    },
    CS {
        @Override
        void initializeParameters() {
            this.mId = 1;
            this.mNoteNo = 1;
            this.mDisplayName = "C♯";
            this.mCanUseToScaleName = true;
            this.mIsShapeScale = true;
            this.mHasShapeSymbol = true;
            this.mHasFlatSymbol = false;
        }

        @Override
        public RootNote getEnharmonicNote() {
            return DF;
        }
    },
    D {
        @Override
        void initializeParameters() {
            this.mId = 2;
            this.mNoteNo = 2;
            this.mDisplayName = "D";
            this.mCanUseToScaleName = true;
            this.mIsShapeScale = true;
            this.mHasShapeSymbol = false;
            this.mHasFlatSymbol = false;
        }

        @Override
        public RootNote getEnharmonicNote() {
            return null;
        }
    },
    DS {
        @Override
        void initializeParameters() {
            this.mId = 3;
            this.mNoteNo = 3;
            this.mDisplayName = "D♯";
            this.mCanUseToScaleName = false;
            this.mIsShapeScale = true;
            this.mHasShapeSymbol = true;
            this.mHasFlatSymbol = false;
        }

        @Override
        public RootNote getEnharmonicNote() {
            return EF;
        }
    },
    E {
        @Override
        void initializeParameters() {
            this.mId = 4;
            this.mNoteNo = 4;
            this.mDisplayName = "E";
            this.mCanUseToScaleName = true;
            this.mIsShapeScale = true;
            this.mHasShapeSymbol = false;
            this.mHasFlatSymbol = false;
        }

        @Override
        public RootNote getEnharmonicNote() {
            return FF;
        }
    },
    F {
        @Override
        void initializeParameters() {
            this.mId = 5;
            this.mNoteNo = 5;
            this.mDisplayName = "F";
            this.mCanUseToScaleName = true;
            this.mIsShapeScale = false;
            this.mHasShapeSymbol = false;
            this.mHasFlatSymbol = false;
        }

        @Override
        public RootNote getEnharmonicNote() {
            return ES;
        }
    },
    FS {
        @Override
        void initializeParameters() {
            this.mId = 6;
            this.mNoteNo = 6;
            this.mDisplayName = "F♯";
            this.mCanUseToScaleName = true;
            this.mIsShapeScale = true;
            this.mHasShapeSymbol = true;
            this.mHasFlatSymbol = false;
        }

        @Override
        public RootNote getEnharmonicNote() {
            return GF;
        }
    },
    G {
        @Override
        void initializeParameters() {
            this.mId = 7;
            this.mNoteNo = 7;
            this.mDisplayName = "G";
            this.mCanUseToScaleName = true;
            this.mHasFlatSymbol = false;
            this.mIsShapeScale = true;
            this.mHasShapeSymbol = false;
            this.mHasFlatSymbol = false;
        }

        @Override
        public RootNote getEnharmonicNote() {
            return null;
        }
    },
    GS {
        @Override
        void initializeParameters() {
            this.mId = 8;
            this.mNoteNo = 8;
            this.mDisplayName = "G♯";
            this.mCanUseToScaleName = false;
            this.mIsShapeScale = true;
            this.mHasShapeSymbol = true;
            this.mHasFlatSymbol = false;
        }

        @Override
        public RootNote getEnharmonicNote() {
            return AF;
        }
    },
    A {
        @Override
        void initializeParameters() {
            this.mId = 9;
            this.mNoteNo = 9;
            this.mDisplayName = "A";
            this.mCanUseToScaleName = true;
            this.mIsShapeScale = true;
            this.mHasShapeSymbol = false;
            this.mHasFlatSymbol = false;
        }

        @Override
        public RootNote getEnharmonicNote() {
            return null;
        }
    },
    AS {
        @Override
        void initializeParameters() {
            this.mId = 10;
            this.mNoteNo = 10;
            this.mDisplayName = "A♯";
            this.mCanUseToScaleName = false;
            this.mIsShapeScale = true;
            this.mHasShapeSymbol = true;
            this.mHasFlatSymbol = false;
        }

        @Override
        public RootNote getEnharmonicNote() {
            return BF;
        }
    },
    B {
        @Override
        void initializeParameters() {
            this.mId = 11;
            this.mNoteNo = 11;
            this.mDisplayName = "B";
            this.mCanUseToScaleName = true;
            this.mIsShapeScale = true;
            this.mHasShapeSymbol = false;
            this.mHasFlatSymbol = false;
        }

        @Override
        public RootNote getEnharmonicNote() {
            return CF;
        }
    },

    // 異名同音(♯)
    ES {
        @Override
        void initializeParameters() {
            this.mId = 12;
            this.mNoteNo = F.mNoteNo;
            this.mDisplayName = "E♯";
            this.mCanUseToScaleName = false;
            this.mIsShapeScale = true;
            this.mHasShapeSymbol = true;
            this.mHasFlatSymbol = false;
        }

        @Override
        public RootNote getEnharmonicNote() {
            return F;
        }
    },
    BS {
        @Override
        void initializeParameters() {
            this.mId = 13;
            this.mNoteNo = C.mNoteNo;
            this.mDisplayName = "B♯";
            this.mCanUseToScaleName = false;
            this.mIsShapeScale = true;
            this.mHasShapeSymbol = true;
            this.mHasFlatSymbol = false;
        }

        @Override
        public RootNote getEnharmonicNote() {
            return C;
        }
    },

    // 異名同音(♭)
    CF {
        @Override
        void initializeParameters() {
            this.mId = 14;
            this.mNoteNo = B.mNoteNo;
            this.mDisplayName = "C♭";
            this.mCanUseToScaleName = true;
            this.mIsShapeScale = false;
            this.mHasShapeSymbol = false;
            this.mHasFlatSymbol = true;
        }

        @Override
        public RootNote getEnharmonicNote() {
            return B;
        }
    },
    DF {
        @Override
        void initializeParameters() {
            this.mId = 15;
            this.mNoteNo = CS.mNoteNo;
            this.mDisplayName = "D♭";
            this.mCanUseToScaleName = true;
            this.mIsShapeScale = false;
            this.mHasShapeSymbol = false;
            this.mHasFlatSymbol = true;
        }

        @Override
        public RootNote getEnharmonicNote() {
            return CS;
        }
    },
    EF {
        @Override
        void initializeParameters() {
            this.mId = 16;
            this.mNoteNo = DS.mNoteNo;
            this.mDisplayName = "E♭";
            this.mCanUseToScaleName = true;
            this.mIsShapeScale = false;
            this.mHasShapeSymbol = false;
            this.mHasFlatSymbol = true;
        }

        @Override
        public RootNote getEnharmonicNote() {
            return DS;
        }
    },
    FF {
        @Override
        void initializeParameters() {
            this.mId = 17;
            this.mNoteNo = E.mNoteNo;
            this.mDisplayName = "F♭";
            this.mCanUseToScaleName = false;
            this.mIsShapeScale = false;
            this.mHasShapeSymbol = false;
            this.mHasFlatSymbol = true;
        }

        @Override
        public RootNote getEnharmonicNote() {
            return E;
        }
    },
    GF {
        @Override
        void initializeParameters() {
            this.mId = 18;
            this.mNoteNo = FS.mNoteNo;
            this.mDisplayName = "G♭";
            this.mCanUseToScaleName = true;
            this.mIsShapeScale = false;
            this.mHasShapeSymbol = false;
            this.mHasFlatSymbol = true;
        }

        @Override
        public RootNote getEnharmonicNote() {
            return FS;
        }
    },
    AF {
        @Override
        void initializeParameters() {
            this.mId = 19;
            this.mNoteNo = GS.mNoteNo;
            this.mDisplayName = "A♭";
            this.mCanUseToScaleName = true;
            this.mIsShapeScale = false;
            this.mHasShapeSymbol = false;
            this.mHasFlatSymbol = true;
        }

        @Override
        public RootNote getEnharmonicNote() {
            return GS;
        }
    },
    BF {
        @Override
        void initializeParameters() {
            this.mId = 20;
            this.mNoteNo = AS.mNoteNo;
            this.mDisplayName = "B♭";
            this.mCanUseToScaleName = true;
            this.mIsShapeScale = false;
            this.mHasShapeSymbol = false;
            this.mHasFlatSymbol = true;
        }

        @Override
        public RootNote getEnharmonicNote() {
            return AS;
        }
    };

    Integer mId = -1;
    Integer mNoteNo = -1;
    String mDisplayName = "";
    boolean mCanUseToScaleName = false;
    boolean mIsShapeScale = true;
    boolean mHasShapeSymbol = false;
    boolean mHasFlatSymbol = false;

    /**
     * noteNoに対応する基本ノートを返す
     *
     * @param noteNo
     * @return
     */
    public static RootNote getNote(final Integer noteNo) {
        Integer no = (noteNo + 12) % 12;
        for (RootNote note : RootNote.values()) {
            if (note.getNoteNo().equals(no)) {
                return note;
            }
        }

        return null;
    }

    RootNote() {
        initializeParameters();
    }

    public abstract RootNote getEnharmonicNote();

    /**
     * Parameterの初期化
     */
    abstract void initializeParameters();

    /**
     * NoteNoを取得
     *
     * @return NoteNo
     */
    public Integer getNoteNo() {
        return mNoteNo;
    }

    /**
     * 表示用の名称
     *
     * @return
     */
    public String getDisplayName() {
        return mDisplayName;
    }

    /**
     * スケールに使用できるかどうか // TODO: Scale側にもつべきか
     *
     * @return
     */
    public boolean canUseToScaleName() {
        return mCanUseToScaleName;
    }

    /**
     * Shapeが付与するスケールか // TODO: Scale側にもつべきか
     *
     * @return
     */
    public boolean isShapeScale() {
        return mIsShapeScale;
    }

    /**
     * Shapeがつくどうか
     *
     * @return
     */
    public boolean hasShapeSymbol() {
        return mHasShapeSymbol;
    }

    /**
     * Flatがつくかどうか
     *
     * @return
     */
    public boolean hasFlatSymbol() {
        return mHasFlatSymbol;
    }
}
