package com.cignacmb.demo.xml;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.mapper.MapperWrapper;

/**
 * 
 * @类名： BaseMessageXStream.java 
 * @描述：对XStream对象的扩展，可以做到仅支持解析列出来的节点
 * @作者： mxyanx
 * @修改日期： 2014年11月17日
 */
public class BaseMessageXStream extends XStream {
    @Override
    protected MapperWrapper wrapMapper(MapperWrapper next) {
        return new MapperWrapper(next) {
            @Override
            public boolean shouldSerializeMember(@SuppressWarnings("rawtypes") Class definedIn, String fieldName) {
                //不能识别的节点，掠过。
                if (definedIn == Object.class) {
                    return false;
                }
                //节点名称为fileName的掠过
                if (!fieldName.equalsIgnoreCase("ToUserName")
                        && !fieldName.equalsIgnoreCase("FromUserName")
                        && !fieldName.equalsIgnoreCase("CreateTime")
                        && !fieldName.equalsIgnoreCase("MsgType")
                        && !fieldName.equalsIgnoreCase("Content")
                        && !fieldName.equalsIgnoreCase("MsgId")
                        && !fieldName.equalsIgnoreCase("PicUrl")
                        && !fieldName.equalsIgnoreCase("MediaId")
                        && !fieldName.equalsIgnoreCase("Format")
                        && !fieldName.equalsIgnoreCase("ThumbMediaId")
                        && !fieldName.equalsIgnoreCase("Location_X")
                        && !fieldName.equalsIgnoreCase("Location_Y")
                        && !fieldName.equalsIgnoreCase("Scale")
                        && !fieldName.equalsIgnoreCase("Label")
                        && !fieldName.equalsIgnoreCase("Title")
                        && !fieldName.equalsIgnoreCase("Description")
                        && !fieldName.equalsIgnoreCase("Url")) {
                    return false;
                }
                return super.shouldSerializeMember(definedIn, fieldName);
            }
        };
    }
}
