package com.org.util;


import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.KeyGenerator;
import java.io.*;
import java.security.Key;
import java.security.SecureRandom;
/**
* @Author: WangLin
* @Description: 根据密码文件加解密
* @Date: 2018/11/28 16:20
*/
public class FileDes {

    private static Logger logger = LoggerFactory.getLogger(FileDes.class);

    private static final String DES = "DES";

    private Key key;

    private FileDes(){

    }

    public FileDes(String key){
        setKey(key);
    }

    /**
     * 根据参数生成KEY
     */
    private void setKey(String password) {
        try {
            KeyGenerator _generator = KeyGenerator.getInstance(DES);
            _generator.init(new SecureRandom(password.getBytes()));
            this.key =_generator.generateKey();
        } catch (Exception e) {
            throw new RuntimeException("Error initializing SqlMap class. Cause: " + e);
        }
    }

    private boolean isExists(String filePath){
        File file = new File(filePath);
        if(!file.exists()){
            return false;
        }
        return true;
    }

    /**
     * 如果不存在目标文件,根据源文件生成目标文件在当前目录
     * @param srcFilePath
     * @return
     */
    private String generateSpecifiedFileName(String srcFilePath) {
        File file = new File(srcFilePath);
        String fileName = file.getName();
        String fileSuffix = fileName.substring(fileName.lastIndexOf("."),fileName.length());
        String destFilePath = file.getParentFile().getAbsolutePath().concat(UUIDUtils.generatorUUID().concat(fileSuffix));
        logger.info("Create a new file, path："+destFilePath);
        return destFilePath;
    }

    /**
     * 加密or解密
     * @param srcFilePath
     * @param destFilePath
     * @param encrypt_mode
     * @throws Exception
     */
    private String encryptOrDecrypt(String srcFilePath, String destFilePath,final int encrypt_mode) throws Exception {
        if(!isExists(srcFilePath)){
            logger.error("Source file not exixt, filePath: "+srcFilePath);
            return srcFilePath;
        }
        if(StringUtils.isBlank(destFilePath)){
            destFilePath = generateSpecifiedFileName(srcFilePath);
        }
        InputStream is = new FileInputStream(srcFilePath);
        OutputStream out = new FileOutputStream(destFilePath);
        Cipher cipher = Cipher.getInstance(DES);
        cipher.init(encrypt_mode, this.key);
        CipherInputStream cis = new CipherInputStream(is, cipher);
        byte[] buffer = new byte[1024];
        int r;
        while ((r = cis.read(buffer)) > 0) {
            out.write(buffer, 0, r);
        }
        cis.close();
        is.close();
        out.close();
        return destFilePath;
    }

    /**
     * 加密并保存目标文件
     * @param srcFilePath 要加密的文件
     * @param destFilePath 加密后存放的文件
     * @throws Exception
     */
    public void encryptFile(String srcFilePath, String destFilePath) throws Exception {
         if(StringUtils.isBlank(destFilePath)) {
             logger.error("Target file is not null, filePath: "+destFilePath);
             return;
         }
        encryptOrDecrypt(srcFilePath, destFilePath, Cipher.ENCRYPT_MODE);
    }

    /**
     * 解密文件
     * @param srcFilePath 已加密的文件
     * @param destFilePath 解密后的文件
     * @throws Exception
     */
    public void decryptFile(String srcFilePath, String destFilePath) throws Exception {
        if(StringUtils.isBlank(destFilePath)) {
            logger.error("Target file is not null, filePath: ",destFilePath);
            return;
        }
        encryptOrDecrypt(srcFilePath, destFilePath, Cipher.DECRYPT_MODE);
    }

    /**
     * 根据源文件获取加密后的文件
     * @param srcFilePath
     * @return
     * @throws Exception
     */
    public File getEncryptedFile(String srcFilePath) throws Exception{
        return new File(encryptOrDecrypt(srcFilePath, null,Cipher.ENCRYPT_MODE));
    }

    /**
     * 根据加密的文件获取解密后的文件
     * @param srcFilePath
     * @return
     * @throws Exception
     */
    public File getDecryptFile(String srcFilePath) throws Exception{
        return new File(encryptOrDecrypt(srcFilePath, null,Cipher.DECRYPT_MODE));
    }

    public static void main(String[] args) throws Exception {
        FileDes fileDes = new FileDes("12345");
        //加密
        fileDes.encryptFile("E:\\abc.txt", "E:\\aaa1.tif");
            //解密
        fileDes.decryptFile("E:\\aaa1.tif", "E:\\aaa2.bmp");

        /*fileDes.encryptFile("E:\\abc.jpg", "E:\\abc1.tif");
        //解密
        fileDes.decryptFile("E:\\abc1.tif", "E:\\abc2.bmp");

        File file = fileDes.getEncryptedFile("E:\\abc.txt");
        fileDes.getDecryptFile(file.getAbsolutePath());
        file = fileDes.getEncryptedFile("E:\\abc.jpg");
        fileDes.getDecryptFile(file.getAbsolutePath());*/
    }
}
