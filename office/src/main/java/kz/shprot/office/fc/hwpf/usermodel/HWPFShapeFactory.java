/*
 * 文件名称:           HWPFShapeFactory.java
 *  
 * 编译器:             android2.2
 * 时间:               下午4:26:03
 */
package kz.shprot.office.fc.hwpf.usermodel;

import kz.shprot.office.fc.ShapeKit;
import kz.shprot.office.fc.ddf.EscherContainerRecord;
import kz.shprot.office.fc.ddf.EscherProperty;
import kz.shprot.office.fc.ddf.EscherPropertyFactory;
import kz.shprot.office.fc.ddf.EscherRecord;
import kz.shprot.office.fc.ddf.EscherSimpleProperty;
import kz.shprot.office.fc.ddf.EscherSpRecord;

import java.util.List;

/**
 * TODO: 文件注释
 * <p>
 * <p>
 * Read版本:       Read V1.0
 * <p>
 * 作者:           jhy1790
 * <p>
 * 日期:           2013-4-27
 * <p>
 * 负责人:         jhy1790
 * <p>
 * 负责小组:         
 * <p>
 * <p>
 */
public final class HWPFShapeFactory
{
    public static HWPFShape createShape(EscherContainerRecord spContainer, HWPFShape parent)
    {
        if (spContainer.getRecordId() == EscherContainerRecord.SPGR_CONTAINER)
        {
            return createShapeGroup(spContainer, parent);
        }
        return createSimpeShape(spContainer, parent);
    }
    
    public static HWPFShapeGroup createShapeGroup(EscherContainerRecord spContainer, HWPFShape parent)
    {
        HWPFShapeGroup group = null;
        EscherRecord opt = ShapeKit.getEscherChild((EscherContainerRecord)spContainer.getChild(0), (short)0xF122);
        if (opt != null)
        {
            try
            {
                EscherPropertyFactory f = new EscherPropertyFactory();
                List<EscherProperty> props = f.createProperties(opt.serialize(), 8, opt.getInstance());
                EscherSimpleProperty p = (EscherSimpleProperty)props.get(0);
                if (p.getPropertyNumber() == 0x39F && p.getPropertyValue() == 1)
                {
                    
                }
                else
                {
                    group = new HWPFShapeGroup(spContainer, parent);
                }
            }
            catch(Exception e)
            {
                group = new HWPFShapeGroup(spContainer, parent);
            }
        }
        else
        {
            group = new HWPFShapeGroup(spContainer, parent);
        }

        return group;
    }
    
    public static HWPFAutoShape createSimpeShape(EscherContainerRecord spContainer, HWPFShape parent)
    {
        EscherSpRecord spRecord = spContainer.getChildById(EscherSpRecord.RECORD_ID);
        if (spRecord != null)
        {
            return new HWPFAutoShape(spContainer, parent);
        }
        return null;
    }
}
