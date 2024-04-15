/*
 * 文件名称:          ResManager.java
 *  
 * 编译器:            android2.2
 * 时间:              上午9:39:36
 */
package kz.shprot.office.ss.sheetbar;

import android.content.Context;
import android.graphics.drawable.Drawable;

import androidx.core.content.ContextCompat;

import kz.shprot.office.R;

/**
 * TODO: 文件注释
 * <p>
 * <p>
 * Read版本:        Read V1.0
 * <p>
 * 作者:            jqin
 * <p>
 * 日期:            2012-8-27
 * <p>
 * 负责人:           jqin
 * <p>
 * 负责小组:           
 * <p>
 * <p>
 */
public class SheetbarResManager
{
    public SheetbarResManager(Context context)
    {
        this.context = context;
        
        ClassLoader loader = context.getClassLoader();
        sheetbarBG = ContextCompat.getDrawable(context, R.drawable.ss_sheetbar_bg);
        sheetbarLeftShadow = ContextCompat.getDrawable(context, R.drawable.ss_sheetbar_shadow_left);
        sheetbarRightShadow = ContextCompat.getDrawable(context, R.drawable.ss_sheetbar_shadow_right);
        hSeparator = ContextCompat.getDrawable(context, R.drawable.ss_sheetbar_separated_horizontal);
        normalLeft = ContextCompat.getDrawable(context, R.drawable.ss_sheetbar_button_normal_left);
        normalRight = ContextCompat.getDrawable(context, R.drawable.ss_sheetbar_button_normal_right);
        normalMiddle = ContextCompat.getDrawable(context, R.drawable.ss_sheetbar_button_normal_middle);
        pushLeft = ContextCompat.getDrawable(context, R.drawable.ss_sheetbar_button_push_left);
        pushMiddle = ContextCompat.getDrawable(context, R.drawable.ss_sheetbar_button_push_middle);
        pushRight = ContextCompat.getDrawable(context, R.drawable.ss_sheetbar_button_push_right);
        focusLeft = ContextCompat.getDrawable(context, R.drawable.ss_sheetbar_button_focus_left);
        focusMiddle = ContextCompat.getDrawable(context, R.drawable.ss_sheetbar_button_focus_middle);
        focusRight = ContextCompat.getDrawable(context, R.drawable.ss_sheetbar_button_focus_right);

    }
    
    public Drawable getDrawable(short resID)
    {
        switch(resID)
        {
            case SheetbarResConstant.RESID_SHEETBAR_BG:
                return sheetbarBG;
                
            case SheetbarResConstant.RESID_SHEETBAR_SHADOW_LEFT:
                return sheetbarLeftShadow;
                
            case SheetbarResConstant.RESID_SHEETBAR_SHADOW_RIGHT:
                return sheetbarRightShadow;
                
            case SheetbarResConstant.RESID_SHEETBAR_SEPARATOR_H:
                return hSeparator;
                
            case SheetbarResConstant.RESID_SHEETBUTTON_NORMAL_LEFT:
                return normalLeft;
                
            case SheetbarResConstant.RESID_SHEETBUTTON_NORMAL_MIDDLE:
                return Drawable.createFromResourceStream(context.getResources(), null, 
                    context.getClassLoader().getResourceAsStream(SheetbarResConstant.RESNAME_SHEETBUTTON_NORMAL_MIDDLE), 
                    SheetbarResConstant.RESNAME_SHEETBUTTON_NORMAL_MIDDLE);
                
            case SheetbarResConstant.RESID_SHEETBUTTON_NORMAL_RIGHT:
                return normalRight;
                
            case SheetbarResConstant.RESID_SHEETBUTTON_PUSH_LEFT:
                return pushLeft;
                
            case SheetbarResConstant.RESID_SHEETBUTTON_PUSH_MIDDLE:
                return pushMiddle;
                
            case SheetbarResConstant.RESID_SHEETBUTTON_PUSH_RIGHT:
                return pushRight;
                
            case SheetbarResConstant.RESID_SHEETBUTTON_FOCUS_LEFT:
                return focusLeft;
                
            case SheetbarResConstant.RESID_SHEETBUTTON_FOCUS_MIDDLE:
                return focusMiddle;
                
            case SheetbarResConstant.RESID_SHEETBUTTON_FOCUS_RIGHT:
                return focusRight;
        }
        
        return null;
    }
    
    public void dispose()
    {
        sheetbarBG = null;
        
        sheetbarLeftShadow = null;
        sheetbarRightShadow = null;
        
        hSeparator = null;
        
        normalLeft = null;
        normalMiddle = null;
        normalRight = null;
        
        pushLeft = null;
        pushMiddle =  null;
        pushRight = null;
        
        focusLeft = null;
        focusMiddle = null;
        focusRight = null;
    }
    
    private Context context;
    private Drawable sheetbarBG;
    
    private Drawable sheetbarLeftShadow, sheetbarRightShadow;
    
    private Drawable hSeparator;
    //left
    private Drawable normalLeft;
    private Drawable pushLeft;
    private Drawable focusLeft;
    
    //middle
    private Drawable normalMiddle;
    private Drawable pushMiddle;
    private Drawable focusMiddle;
    
    //right
    private Drawable normalRight;
    private Drawable pushRight;
    private Drawable focusRight;
}
