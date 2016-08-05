package com.cignacmb.demo.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

/**
 * 
 * @类名： ApiClient.java 
 * @描述：调外部接口的类对象
 * @作者： mxyanx
 * @修改日期： 2014年11月14日
 */
@Repository
public class ApiClient
{
    private static final Logger logger = Logger.getLogger(ApiClient.class);

    private String responseStr;


    public String getResponseStr()
    {
        return responseStr;
    }


    public void setResponseStr(String responseStr)
    {
        this.responseStr = responseStr;
    }


    /**
     * 
     * 功能描述：调外部接口方法，不区分post和get
     * @param urlStr：外部接口url
     * @param params：URL后缀参数
     * @param content：post方法时的参数
     * @return
     * @throws IOException
     */
    public boolean invokeApi(String urlStr, Map<String, Object> params, String content) throws IOException
    {
        String paramsStr = "";
        boolean flag = false;
        for (String key : params.keySet())
        {
            if (flag)
            {
                paramsStr = paramsStr + "&";
            }
            paramsStr += key + "=" + params.get(key);
            flag = true;
        }
        if (StringUtils.isNotEmpty(paramsStr))
        {
            urlStr += "?" + paramsStr;
        }
        URL url = new URL(urlStr);

        URLConnection conn = url.openConnection();
        if (StringUtils.isNotEmpty(content))
        {
            conn.setDoOutput(true);
            OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
            out.write(content);
            out.flush();
            out.close();
        }

        InputStream in = conn.getInputStream();
        BufferedReader bin = new BufferedReader(new InputStreamReader(in, "UTF-8"));
        String rs = null;
        while ((rs = bin.readLine()) != null)
        {
            this.responseStr = rs;
        }
        bin.close();
        in.close();
        return true;
    }
}
