// Copyright 2002, FreeHEP.

package kz.shprot.office.thirdpart.emf.data;

import kz.shprot.office.java.awt.Dimension;
import kz.shprot.office.thirdpart.emf.EMFInputStream;
import kz.shprot.office.thirdpart.emf.EMFRenderer;
import kz.shprot.office.thirdpart.emf.EMFTag;

import java.io.IOException;

/**
 * SetWindowExtEx TAG.
 * 
 * @author Mark Donszelmann
 * @version $Id: SetWindowExtEx.java 10367 2007-01-22 19:26:48Z duns $
 */
public class SetWindowExtEx extends EMFTag
{

    private Dimension size;

    public SetWindowExtEx()
    {
        super(9, 1);
    }

    public SetWindowExtEx(Dimension size)
    {
        this();
        this.size = size;
    }

    public EMFTag read(int tagID, EMFInputStream emf, int len) throws IOException
    {

        return new SetWindowExtEx(emf.readSIZEL());
    }

    public String toString()
    {
        return super.toString() + "\n  size: " + size;
    }

    /**
     * displays the tag using the renderer
     *
     * @param renderer EMFRenderer storing the drawing session data
     */
    public void render(EMFRenderer renderer)
    {
        renderer.setWindowSize(size);
    }
}
