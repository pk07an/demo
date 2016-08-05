package com.cignacmb.demo.utils;
import java.util.HashMap;  
import java.util.Hashtable;  
import java.util.Iterator;  
import java.util.Map;  
import java.util.Map.Entry;  
  
import com.thoughtworks.xstream.converters.MarshallingContext;  
import com.thoughtworks.xstream.converters.UnmarshallingContext;  
import com.thoughtworks.xstream.converters.collections.AbstractCollectionConverter;  
import com.thoughtworks.xstream.io.ExtendedHierarchicalStreamWriterHelper;  
import com.thoughtworks.xstream.io.HierarchicalStreamReader;  
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;  
import com.thoughtworks.xstream.mapper.Mapper;  

/**
 * 
 * @类名： MapCustomConverterUtils.java 
 * @描述：XML解析转化工具，主要是支持将Map转化为特定的格式，如：<node key="xxx">xxx</node>
 * @作者： mxyanx
 * @修改日期： 2014年7月1日
 */
public class MapCustomConverterUtils extends AbstractCollectionConverter {  
  
    public MapCustomConverterUtils(Mapper mapper) {
        super(mapper);  
    }  
  
    /**
     * 是否支持的转换Map类型
     */
    public boolean canConvert(Class type) {  
        return type.equals(HashMap.class)  
                || type.equals(Hashtable.class)
                || type.getName().equals("java.util.LinkedHashMap")  
                || type.getName().equals("sun.font.AttributeMap");
    }  
  
    /**
     * 生成xml文件时的处理方法，将key值set到属性中，将value值set到节点中
     */
    public void marshal(Object source, HierarchicalStreamWriter writer, MarshallingContext context) {  
        Map map = (Map) source;  
        for (Iterator iterator = map.entrySet().iterator(); iterator.hasNext();) {  
            Entry entry = (Entry) iterator.next();  
            ExtendedHierarchicalStreamWriterHelper.startNode(writer, "Custom", Entry.class);  
            writer.addAttribute("key",  entry.getKey().toString());  
            writer.setValue(entry.getValue().toString());
            writer.endNode();  
        }  
    }  
    
    /**
     * 解析xml文件时的处理方法
     */
    public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {  
        Map map = (Map) createCollection(context.getRequiredType());  
        populateMap(reader, context, map);  
        return map;  
    }  
  
    /**
     * 
     * 功能描述：由xml文件的节点计算Map的key和value，返回map结构
     * @param reader
     * @param context
     * @param map
     */
    protected void populateMap(HierarchicalStreamReader reader, UnmarshallingContext context, Map map) {  
        while (reader.hasMoreChildren()) {  
            reader.moveDown();  
            Object key = reader.getAttribute("key");  
            Object value = reader.getValue();
            map.put(key, value);  
            reader.moveUp();  
        }  
    }  
}