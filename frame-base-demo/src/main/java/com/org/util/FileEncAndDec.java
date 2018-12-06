package com.org.util;

import java.io.*;

public class FileEncAndDec {
        //加密解密秘钥
        private static final int numOfEncAndDec = 0x99999;

        //文件字节内容
        private static int dataOfFile = 0;

        public static void main(String[] args) {
            System.out.println(numOfEncAndDec);
            //初始文件
            File srcFile = new File("E:\\abc.jpg");
            //加密文件
            File encFile = new File("E:\\encFile1.jpg");
            //解密文件
            File decFile = new File("E:\\decFile1.jpg");

            try {
                //加密操作
                decFile(encFile, decFile);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private static void encFile(File srcFile, File encFile) throws Exception {
            if(!srcFile.exists()){
                System.out.println("source file not exixt");
                return;
            }

            if(!encFile.exists()){
                System.out.println("encrypt file created");
                encFile.createNewFile();
            }
            InputStream fis  = new FileInputStream(srcFile);
            OutputStream fos = new FileOutputStream(encFile);

            while ((dataOfFile = fis.read()) > -1) {
                fos.write(dataOfFile^numOfEncAndDec);
            }

            fis.close();
            fos.flush();
            fos.close();
        }

    private static void decFile(File srcFile, File dncFile) throws Exception {
        if(!srcFile.exists()){
            System.out.println("source file not exixt");
            return;
        }

        if(!dncFile.exists()){
            System.out.println("decrypt file created");
            dncFile.createNewFile();
        }
        InputStream fis  = new FileInputStream(srcFile);
        OutputStream fos = new FileOutputStream(dncFile);

        while ((dataOfFile = fis.read()) > -1) {
            fos.write(dataOfFile^numOfEncAndDec);
        }

        fis.close();
        fos.flush();
        fos.close();
    }
}
