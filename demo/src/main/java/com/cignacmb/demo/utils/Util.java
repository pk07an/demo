package com.cignacmb.demo.utils;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.TimeZone;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletInputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.lang.time.DurationFormatUtils;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

/**
 * 
 * @类名： Utils.java
 * @描述：公共的帮助类
 * @作者： mxyanx
 * @修改日期： 2014年7月4日
 */
public class Util
{

    private static final SmartChineseAnalyzer analyzer = new SmartChineseAnalyzer(Version.LUCENE_47);

    public static final String HTML_TYPE = "text/html;charset=utf-8";

    public static final String JSON_TYPE = "text/json;charset=utf-8";


    /**
     * 
     * 功能描述：订单流水号
     * 
     * @return
     */
    public static String generatorOrderSerialNumber()
    {
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmsssss");
        return dateFormat.format(new Date()) + new DecimalFormat("000").format(new Random().nextInt(100));
    }


    /**
     * 
     * 功能描述：地址合法性校验
     * 
     * @param address
     * @return
     */
    public static boolean isAddressIlegal(String address)
    {
        if (StringUtils.isBlank(address))
            return false;
        String regext = ".*(US|USA|U\\.S\\.A|United State of America|America).*";
        Pattern p = Pattern.compile(regext);
        Matcher matcher = p.matcher(address);
        return matcher.matches();
    }


    /**
     * 
     * 功能描述：是否只包含中文
     * 
     * @param name
     * @return
     */
    public static boolean isOnlyChinese(String name)
    {
        String regextChinese = "^[\u4e00-\u9fa5]{0,}$";
        Pattern p = Pattern.compile(regextChinese);
        Matcher matcher = p.matcher(name);
        return matcher.matches();
    }


    /**
     * 
     * 功能描述：是否只包含中文
     * 
     * @param str
     * @return
     */
    public static boolean isContainChinese(String str)
    {
        if (StringUtils.isBlank(str))
        {
            return false;
        }
        Pattern p = Pattern.compile("[\\u4e00-\\u9fa5]");
        Matcher m = p.matcher(str);
        while (m.find())
        {
            return true;
        }
        return false;
    }


    /**
     * 
     * 功能描述：邮政编码校验
     * 
     * @param youzhengCode
     * @return
     */
    public static boolean isMailCode(String mailCode)
    {
        String regextCode = "^[0-9]\\d{5}(?!d)$";
        Pattern p = Pattern.compile(regextCode);
        Matcher matcher = p.matcher(mailCode);
        return matcher.matches();
    }


    /**
     * @author：j1deng
     * @date：2014-7-9
     * @Description：获得UUID作为主键
     * @return: 返回结果描述
     * @return String: 返回值类型
     * @throws
     */

    public static String getUUID()
    {
        String uuid = UUID.randomUUID().toString();
        return uuid.substring(0, 8) + uuid.substring(9, 13) + uuid.substring(14, 18) + uuid.substring(19, 23) + uuid.substring(24);
    }


    /**
     * 
     * 功能描述：字符串转换时间格式
     * 
     * @param string_DATE
     * @return
     * @throws ParseException
     */
    public static Date getDateByString(String string_DATE) throws ParseException
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.parse(string_DATE);
    }


    /**
     * 
     * 功能描述：时间格式转换成字符串
     * 
     * @param date
     * @return String yyyy-MM-dd HH:mm:ss
     */
    public static String getStringAllByDate(Date date)
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(date);
    }


    /**
     * 
     * 功能描述：时间格式转换成字符串
     * 
     * @param date
     * @return String yyyy-MM-dd
     */
    public static String getTimeByDate(Date date)
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(date);
    }


    /**
     * 
     * 功能描述：时间格式转换成字符串
     * 
     * @param date
     * @return String yyyy-MM-dd
     */
    public static String getStringByDate(Date date)
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(date);
    }


    /***
     * 格式化日期字符串
     * 
     * @param date
     * @param formatStr
     * @return
     */
    public static String formatDate(Date date, String formatStr)
    {
        if (date != null && StringUtils.isNotBlank(formatStr))
        {
            DateFormat dateFormat = new SimpleDateFormat(formatStr);
            return dateFormat.format(date);
        }

        return "";

    }


    /**
     * 
     * 功能描述：证件号码校验（不一定是身份证）
     * 
     * @param
     * @return
     */
    public static boolean isIdNumber(String idNumber)
    {
        if (idNumber != null && idNumber.length() <= 20 && idNumber.length() > 0)
        {
            String regextCode = "^[0-9]\\d{5}(?!d)$";
            Pattern p = Pattern.compile(regextCode);
            Matcher matcher = p.matcher(idNumber);
            return matcher.matches();
        }
        else
        {
            return false;
        }
    }


    /**
     * 从request对象获取到原始的核保或承保的请求文件 功能描述：
     * 
     * @param request
     * @return
     * @throws IOException
     */
    public static String getRequestXmlData(HttpServletRequest request) throws IOException
    {
        ServletInputStream sis = request.getInputStream();
        int size = request.getContentLength() == -1 ? 0 : request.getContentLength();
        byte[] buffer = new byte[size];
        byte[] xmldataByte = new byte[size];
        int count = 0;
        int rbyte = 0;
        while (count < size)
        {
            rbyte = sis.read(buffer);
            for (int i = 0; i < rbyte; i++)
            {
                xmldataByte[count + i] = buffer[i];
            }
            count += rbyte;
        }
        return new String(xmldataByte, "UTF-8");
    }


    /**
     * 
     * 功能描述：获取自出生日到当前日的总天数
     * 
     * @param startTime
     * @param birthday
     * @return
     */
    public static Long getTotalDaysByBirthday(Date birthDate, Date currentDate)
    {
        return NumberUtils.toLong(DurationFormatUtils.formatDuration(currentDate.getTime() - birthDate.getTime(), "d"));
    }


    /**
     * 
     * 功能描述：获取自出生日到当前日的总年龄
     * 
     * @param birthday
     * @return
     */
    public static Long getAgeByBirthDay(Date birthDate, Date currentDate)
    {
        long age = NumberUtils.toLong(DurationFormatUtils.formatPeriod(birthDate.getTime(), currentDate.getTime(), "y"));
        int totalMonths = NumberUtils.toInt(DurationFormatUtils.formatPeriod(birthDate.getTime(), currentDate.getTime(), "M"));
        long monthOffset = totalMonths - age * 12;

        Calendar currentDateCalendar = Calendar.getInstance(TimeZone.getDefault());
        currentDateCalendar.setTime(currentDate);
        int currentDay = currentDateCalendar.get(Calendar.DATE);

        Calendar birthDateCalendar = Calendar.getInstance(TimeZone.getDefault());
        birthDateCalendar.setTime(birthDate);
        int birthDay = birthDateCalendar.get(Calendar.DATE);

        int dateOffset = birthDay - currentDay;
        if (monthOffset < 0)
        {
            age = age - 1;
        }
        else
        {
            // 判断出生日期是否大于当前日日期，大于则减一
            if (dateOffset > 0)
            {
                age = age - 1;
            }
        }
        return age;
    }


    /**
     * 
     * 功能描述：返回异常信息
     * 
     * @param errorMsg
     * @return
     */
    public static String getExceptionMessage(String errorMsg)
    {
        return "<PackageList><Package><exceptionInfo>" + errorMsg + "</exceptionInfo></Package></PackageList>";
    }


    /**
     * 
     * 功能描述：身高转换
     * 
     * @param height
     * @return intHeight
     */
    public static long getHeight(String height)
    {
        Pattern pattern = Pattern.compile("^\\d{1,3}(\\.\\d)?$");
        Matcher matcherH = pattern.matcher(height);
        long intHeight = -1;
        if (matcherH.find())
        {
            if (height.contains("."))
            {
                if (new Integer(height.split("\\.")[0]) < 300)
                {
                    // 合法，单位是毫米
                    intHeight = (int) (new Double(height) * 10);
                    return intHeight;
                }
            }
            else
            {
                if (new Integer(height) < 300)
                {
                    // 合法，单位是毫米
                    intHeight = new Integer(height) * 10;
                    return intHeight;
                }
            }
        }
        return intHeight;
    }


    /**
     * 
     * 功能描述：体重转换
     * 
     * @param weight
     * @return intWeight
     */
    public static long getWeight(String weight)
    {
        Pattern pattern = Pattern.compile("^\\d{1,3}(\\.\\d)?$");
        Matcher matcherW = pattern.matcher(weight);
        long intWeight = -1;
        if (matcherW.find())
        {
            if (weight.contains("."))
            {
                if (new Integer(weight.split("\\.")[0]) < 300)
                {
                    // 合法，单位是克
                    intWeight = (int) (new Double(weight) * 1000);
                    return intWeight;
                }
            }
            else
            {
                if (new Integer(weight) < 300)
                {
                    // 合法，单位是克
                    intWeight = new Integer(weight) * 1000;
                    return intWeight;
                }
            }
        }
        return intWeight;
    }


    /**
     * 
     * 功能描述：根据名称从Cookie中返回值
     * 
     * @param request
     * @param userName
     * @return
     */
    public static String getUserNameFromCookie(HttpServletRequest request, String userName)
    {
        Map<String, Cookie> cookieMap = ReadCookieMap(request);
        if (cookieMap.containsKey(userName))
        {
            Cookie cookie = (Cookie) cookieMap.get(userName);
            return cookie.getValue();
        }
        else
        {
            return null;
        }
    }


    /**
     * 
     * 功能描述：将cookie中的所有对象读取到Map中返回
     * 
     * @param request
     * @return
     */
    private static Map<String, Cookie> ReadCookieMap(HttpServletRequest request)
    {
        Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
        Cookie[] cookies = request.getCookies();
        if (null != cookies)
        {
            for (Cookie cookie : cookies)
            {
                cookieMap.put(cookie.getName(), cookie);
            }
        }
        return cookieMap;
    }


    /**
     * 
     * 功能描述：添加用户名信息到Cookie
     * 
     * @param request
     * @param response
     * @param name
     * @param value
     * @param maxAge
     */
    public static void addUserNameToCookie(HttpServletRequest request, HttpServletResponse response, String name, String value, Integer maxAge)
    {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath(request.getContextPath());
        if (maxAge > 0)
        {
            // 设置cookie过期时间，单位为秒
            cookie.setMaxAge(maxAge);
        }
        response.addCookie(cookie);
    }


    /**
     * 
     * 功能描述：删除Cookie
     * 
     * @param request
     * @param name
     */
    public static void clearCookie(HttpServletRequest request, HttpServletResponse response, String name)
    {
        Cookie[] Cookies = request.getCookies();
        for (Cookie cookie : Cookies)
        {
            cookie.setValue(null);
            cookie.setPath(request.getContextPath());
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        }

    }


    /**
     * 
     * @param indexPath
     *            创建索引的文件夹
     * @param keyValueMap
     *            批量创建索引文件 key value 形式 例如 ("标题1","内容1"),("标题2","内容2")
     * @throws IOException
     */
    public static void createIndex(String indexPath, Map<String, String> keyValueMap) throws IOException
    {

        Directory dir = null;
        File file = new File(indexPath);
        IndexWriter iwriter = null;
        IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_47, analyzer);
        if (keyValueMap == null)
        {
            return;
        }
        if (!file.isDirectory())
        {
            file.mkdirs();
        }
        dir = FSDirectory.open(file);
        if (IndexWriter.isLocked(dir))
        {
            IndexWriter.unlock(dir);
        }
        iwriter = new IndexWriter(dir, config);
        for (String key : keyValueMap.keySet())
        {
            Document doc = new Document();
            doc.add(new Field("id", key, TextField.TYPE_STORED));
            doc.add(new Field("content", keyValueMap.get(key), TextField.TYPE_STORED));
            iwriter.addDocument(doc);
        }
        iwriter.close();
        dir.close();

    }


    /**
     * 
     * 功能描述：返回当前时间
     * 
     * @return
     */
    public static String getCurrentTime()
    {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }


    /**
     * 方法名称: getAnnotation<br>
     * 描述：获取注解类型
     * 
     * @param method
     * @param annotationClass
     * @return
     */
    public static Annotation getAnnotation(Method method, Class annotationClass)
    {
        return method.getAnnotation(annotationClass);
    }


    /**
     * 将长整型数字转换为日期格式的字符串
     * 
     * @param time
     * @param format
     * @return
     */
    public static String convert2String(long time, String format)
    {
        if (time > 0l)
        {
            if (StringUtils.isBlank(format))
            {
                format = "yyyy-MM-dd HH:mm:ss";
            }
            SimpleDateFormat sf = new SimpleDateFormat(format);
            Date date = new Date(time);
            return sf.format(date);
        }
        return "";
    }


    public static Date convert2Date(String dataStr) throws ParseException
    {
        return DateUtils.parseDate(dataStr, new String[] { "yyyy-MM-dd" });
    }


    public static void main(String[] args)
    {
        System.out.println("currentTime:" + convert2String(1411637549000l, null));
        try
        {
            System.out.println(DateUtils.addDays(convert2Date("2014-09-28"), -1));
        }
        catch (ParseException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    /**
     * 返回给浏览器
     * 
     * @param response
     * @return
     */
    public static void render(HttpServletResponse response, String text, String contentType)
    {
        try
        {
            response.setContentType(contentType);
            response.getWriter().write(text);
            response.getWriter().flush();
        }
        catch (IOException e)
        {
            throw new IllegalStateException(e);
        }
    }

}
