package com.cignacmb.demo.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.apache.commons.net.ftp.FTPClient;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

/**
 * 
 * @类名： FileUtil.java
 * @描述：文件上传工具类
 * @作者： mxyanx
 * @修改日期： 2014年9月17日
 */
public class FileUtil
{

    /**
     * 连接sftp服务器
     * 
     * @param host
     *            主机
     * @param port
     *            端口
     * @param username
     *            用户名
     * @param password
     *            密码
     * @return
     * @throws JSchException 
     */
    public static ChannelSftp connect(String host, int port, String username, String password) throws JSchException
    {
        ChannelSftp sftp = null;
        JSch jsch = new JSch();
        jsch.getSession(username, host, port);
        Session sshSession = jsch.getSession(username, host, port);
        sshSession.setPassword(password);
        Properties sshConfig = new Properties();
        sshConfig.put("StrictHostKeyChecking", "no");
        sshSession.setConfig(sshConfig);
        sshSession.connect();
        Channel channel = sshSession.openChannel("sftp");
        channel.connect();
        sftp = (ChannelSftp) channel;
        return sftp;
    }


    /**
     * 上传文件
     * 
     * @param directory
     *            上传的目录
     * @param uploadFile
     *            要上传的文件
     * @param sftp
     * @throws SftpException
     * @throws FileNotFoundException
     * @throws JSchException 
     */
    public static void sftpUploadImg(String host, int port, String userName, String password, String directory, String uploadFile) throws FileNotFoundException, SftpException, JSchException
    {
        ChannelSftp sftp = connect(host, port, userName, password);
        sftp.cd(directory);
        File file = new File(uploadFile);
        sftp.put(new FileInputStream(file), file.getName());
    }


    /**
     * 下载文件
     * 
     * @param directory
     *            下载目录
     * @param downloadFile
     *            下载的文件
     * @param saveFile
     *            存在本地的路径
     * @param sftp
     */
    public void download(String directory, String downloadFile, String saveFile, ChannelSftp sftp)
    {
        try
        {
            sftp.cd(directory);
            File file = new File(saveFile);
            sftp.get(downloadFile, new FileOutputStream(file));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    /**
     * 删除文件
     * 
     * @param directory
     *            要删除文件所在目录
     * @param deleteFile
     *            要删除的文件
     * @param sftp
     */
    public void delete(String directory, String deleteFile, ChannelSftp sftp)
    {
        try
        {
            sftp.cd(directory);
            sftp.rm(deleteFile);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    
    /**
     * 
     * 功能描述：ftp上传
     * @param host
     * @param port
     * @param userName
     * @param password
     * @param directory
     * @param uploadFile
     */
    public static void ftpUploadImg(String host, int port, String userName, String password, String directory,String orginalFileName, String uploadFile) { 
        FTPClient ftpClient = new FTPClient(); 
        FileInputStream fis = null; 
        try { 
            ftpClient.connect(host); 
            ftpClient.login(userName, password); 

            File srcFile = new File(uploadFile); 
            fis = new FileInputStream(srcFile); 
            //设置上传目录 
            ftpClient.changeWorkingDirectory(directory); 
            ftpClient.setBufferSize(1024); 
            ftpClient.setControlEncoding("UTF-8"); 
            //设置文件类型（二进制） 
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE); 
            ftpClient.storeFile(orginalFileName, fis); 
        } catch (IOException e) { 
            e.printStackTrace(); 
            throw new RuntimeException("FTP客户端出错！", e); 
        } finally { 
            IOUtils.closeQuietly(fis); 
            try { 
                ftpClient.disconnect(); 
            } catch (IOException e) { 
                e.printStackTrace(); 
                throw new RuntimeException("关闭FTP连接发生异常！", e); 
            } 
        } 
    } 

}
