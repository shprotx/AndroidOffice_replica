package kz.shprot.office.common.bg;

import android.graphics.Rect;
import android.graphics.Shader;

import kz.shprot.office.system.IControl;

public abstract class AShader
{
	public Shader getShader() 
	{		
		return shader;
	}

	public Shader createShader(IControl control, int viewIndex, Rect rect)
	{
		return shader;
	}
	
	public int getAlpha()
	{
		return alpha;
	}

	public void setAlpha(int alpha)
	{
		this.alpha = alpha;
	}

	 /**
     * 
     *
     */
    public void dispose()
    {
    	shader = null;
    }
    
	protected int alpha = 255;
	protected Shader shader = null;
}
