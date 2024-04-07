/*
 * 文件名称:          PDFReader.java
 *  
 * 编译器:            android2.2
 * 时间:              下午4:30:30
 */
package kz.shprot.office.fc.pdf;

import kz.shprot.office.constant.EventConstant;
import kz.shprot.office.system.AbstractReader;
import kz.shprot.office.system.IControl;

/**
 * reader PDF document 
 * <p>
 * <p>
 * Read版本:        Read V1.0
 * <p>
 * 作者:            ljj8494
 * <p>
 * 日期:            2012-9-19
 * <p>
 * 负责人:          ljj8494
 * <p>
 * 负责小组:         
 * <p>
 * <p>
 */
public class PDFReader extends AbstractReader
{
    /**
     * 
     * @param filePath
     */
    public PDFReader(IControl control, String filePath) throws Exception
    {
        this.control = control;
        this.filePath = filePath;
    }
     
    /**
     *
     */
    public Object getModel() throws Exception
    {        
        control.actionEvent(EventConstant.SYS_SET_PROGRESS_BAR_ID, false);
        //lib = new PDFLib(filePath);
        lib = PDFLib.getPDFLib();
        lib.openFileSync(filePath);
        return lib;
    }

    /**
     * 
     *
     */
    public void dispose()
    {
        super.dispose();
        lib = null;
        control = null;
    }
    
    //
    private String filePath;
    //
    private PDFLib lib;
    //
    //private AlertDialog.Builder alertBuilder;
    //
    //private EditText pwView;

}
