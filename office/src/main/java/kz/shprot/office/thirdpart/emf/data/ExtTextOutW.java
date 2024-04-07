// Copyright 2002-2007, FreeHEP.
package kz.shprot.office.thirdpart.emf.data;

import kz.shprot.office.java.awt.Rectangle;
import kz.shprot.office.thirdpart.emf.EMFConstants;
import kz.shprot.office.thirdpart.emf.EMFInputStream;
import kz.shprot.office.thirdpart.emf.EMFTag;

import java.io.IOException;

/**
 * ExtTextOutW TAG.
 * 
 * @author Mark Donszelmann
 * @version $Id: ExtTextOutW.java 10525 2007-02-12 04:08:30Z duns $
 */
public class ExtTextOutW extends AbstractExtTextOut implements EMFConstants {

    private TextW text;

    public ExtTextOutW() {
        super(84, 1, null, 0, 1, 1);
    }

    public ExtTextOutW(
        Rectangle bounds,
        int mode,
        float xScale,
        float yScale,
        TextW text) {

        super(84, 1, bounds, mode, xScale, yScale);
        this.text = text;
    }

    public EMFTag read(int tagID, EMFInputStream emf, int len)
            throws IOException {

        return new ExtTextOutW(
            emf.readRECTL(),
            emf.readDWORD(),
            emf.readFLOAT(),
            emf.readFLOAT(),
            TextW.read(emf));
    }

    public Text getText() {
        return text;
    }
}
