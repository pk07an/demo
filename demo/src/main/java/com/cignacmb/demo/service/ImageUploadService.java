package com.cignacmb.demo.service;

import java.io.File;
import java.io.FileNotFoundException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.cignacmb.demo.utils.FileUtil;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;

/**
 * 
 * @类名： ImageUploadService.java 
 * @描述：图片上传的Service
 * @作者： mxyanx
 * @修改日期： 2014年11月14日
 */
@Service
public class ImageUploadService
{
    
    @Value("${img.server.ip}")
    private String imgServerIp;

    @Value("${img.server.port}")
    private int imgServerPort;

    @Value("${img.server.userName}")
    private String imgServerUserName;

    @Value("${img.server.password}")
    private String imgServerPassword;

    @Value("${img.server.ftpPath}")
    private String imgServerFtpPath;
    
    public void uploadImage(String fileName,String filePath) throws FileNotFoundException, SftpException, JSchException{
        String osName = System.getProperty("os.name");
        if (osName.startsWith("Windows"))
        {
            FileUtil.sftpUploadImg(imgServerIp, imgServerPort, imgServerUserName, imgServerPassword, imgServerFtpPath, filePath);
        }
        else
        {
            FileUtil.ftpUploadImg(imgServerIp, imgServerPort, imgServerUserName, imgServerPassword, imgServerFtpPath, fileName, filePath);
        }
    }
    
    
    /**
     * 
     * 功能描述：返回File对象
     * @param request
     * @param fileName
     * @return
     */
    public File getTargetFile(String path,String fileName){
        File targetFile = new File(path, fileName);
        if (!targetFile.exists())
        {
            targetFile.mkdirs();
        }
        return targetFile;
    }

}
