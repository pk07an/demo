package com.cignacmb.demo.utils;


/**
 * xml解析工具类，运用XStream包解析，减少解析代码
 * @类名： XmlParserUtils.java 
 * @描述：列出了解析简单xml、复杂xml、生成xml文件的方法（支持CDATA格式的数据）
 * @作者： mxyanx
 * @修改日期： 2014年7月2日
 */
public class XmlUtils
{
    
    /**
     * 
     * 功能描述：生成XML字符串的例子，支持CDATA数据的生成，前提是在对应类的节点上加上XStreamCDATA注解
     * 
     * @param content
     * @return
     */
//    public static String getResTextMessage()
//    {
//        ResTextMessage resTextMessage = new ResTextMessage();
//        resTextMessage.setToUserName("ToUserName");
//        resTextMessage.setFromUserName("FromUserName");
//        resTextMessage.setCreateTime("" + System.currentTimeMillis() / 1000);
//        resTextMessage.setMsgType("text");
//        resTextMessage.setContent("Content");
//        XStream xStream = CDATAXppDriver.createXstream();
//        xStream.processAnnotations(ResTextMessage.class);
//        String resTextMessageStr = xStream.toXML(resTextMessage);
//        return resTextMessageStr;
//    }
    
    /**
     * 
     * 功能描述：解析复杂XML对象为类的例子，支持Map结构的数据解析
     * @param requestXmlData
     * @return
     * @throws FileNotFoundException
     */
//    public static HBPackageList parserHbRequestXmlData(String requestXmlData) throws FileNotFoundException,StreamException{
//        XStream hbXStream = new XStream(new DomDriver());  
//        HBPackageList packageList = new HBPackageList();
//        InputStream input = new   ByteArrayInputStream(requestXmlData.getBytes());
//        hbXStream.processAnnotations(HBPackageList.class);
//        hbXStream.registerConverter(new MapCustomConverterUtils(new DefaultMapper(HBPackageList.class.getClassLoader()))); 
//        hbXStream.fromXML(input, packageList); 
//        return packageList;
//    }
    
    
    
    /**
     * 
     * 功能描述：解析简单XML对象的例子，支持对CDATA数据的解析，也支持对节点的过滤（用到了BaseMessageXStream对象，如果不过滤，则用XStream对象即可）
     * 
     * @param requestXmlData
     * @return
     * @throws FileNotFoundException
     */
//    public static BaseMessage getBaseMessage(String requestXmlData) throws FileNotFoundException, StreamException
//    {
//        BaseMessageXStream baseMessageXStream = new BaseMessageXStream();
//        BaseMessage baseMessage = new BaseMessage();
//        InputStream input = new ByteArrayInputStream(requestXmlData.getBytes());
//        baseMessageXStream.alias("xml", BaseMessage.class);
//        baseMessageXStream.fromXML(input, baseMessage);
//        return baseMessage;
//    }

}
