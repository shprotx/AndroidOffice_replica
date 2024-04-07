// Copyright 2002, FreeHEP.

package kz.shprot.office.thirdpart.emf.data;

import kz.shprot.office.thirdpart.emf.EMFConstants;
import kz.shprot.office.thirdpart.emf.EMFInputStream;
import kz.shprot.office.thirdpart.emf.EMFRenderer;
import kz.shprot.office.thirdpart.emf.EMFTag;

import java.io.IOException;

/**
 * SelectClipPath TAG.
 * 
 * @author Mark Donszelmann
 * @version $Id: SelectClipPath.java 10516 2007-02-06 21:11:19Z duns $
 */
public class SelectClipPath extends AbstractClipPath
{

    public SelectClipPath()
    {
        super(67, 1, EMFConstants.RGN_AND);
    }

    public SelectClipPath(int mode)
    {
        super(67, 1, mode);
    }

    public EMFTag read(int tagID, EMFInputStream emf, int len) throws IOException
    {

        return new SelectClipPath(emf.readDWORD());
    }

    /**
     * displays the tag using the renderer
     *
     * @param renderer EMFRenderer storing the drawing session data
     */
    public void render(EMFRenderer renderer)
    {
        render(renderer, renderer.getPath());
    }
}
