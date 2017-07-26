package jp.local.yukichan.mimicopysupporter.note.code;

import java.util.ArrayList;
import java.util.List;

import jp.local.yukichan.mimicopysupporter.note.RootNote;

/**
 * Created by takamk2 on 17/07/13.
 * <p>
 * The Edit Fragment of Base Class.
 */

public class CodeDetection {

    public List<Code> getDetectedCodes() {
        List<Code> codes = new ArrayList<>();
        codes.add(new Code(RootNote.C, CodeConstituent.MAJOR));
        codes.add(new Code(RootNote.D, CodeConstituent.MINOR_SEVEN));
        codes.add(new Code(RootNote.E, CodeConstituent.MINOR));
        codes.add(new Code(RootNote.F, CodeConstituent.MINOR));
        codes.add(new Code(RootNote.G, CodeConstituent.SEVEN));
        codes.add(new Code(RootNote.A, CodeConstituent.MINOR));
        codes.add(new Code(RootNote.B, CodeConstituent.MINOR_SEVEN_FLAT_FIVE));
        return codes;
    }
}
