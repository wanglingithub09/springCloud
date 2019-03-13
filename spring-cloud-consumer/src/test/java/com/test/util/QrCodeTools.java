package com.test.util;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;
import cn.hutool.extra.qrcode.QrConfig;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 二维码生成工具
 */
public class QrCodeTools {

    /**
     * 生成二维码
     * @param jsonStr
     * @return
     */
    public static boolean generate(String jsonStr){
        if(!JSONUtil.isJsonObj(jsonStr)){
            return false;
        }
        Map map = JSONUtil.toBean(jsonStr,Map.class);
        String path = null!=map.get("path")?map.get("path").toString():null;
        String url = null!=map.get("url")?map.get("url").toString():null;
        if(StrUtil.hasBlank(path) || StrUtil.hasBlank(url)){
            return false;
        }
        try {
            QrConfig config = new QrConfig(300, 300);
            //调整纠错级别L、M、Q、H几个参数，由低到高。低级别的像素块更大
            config.setErrorCorrection(ErrorCorrectionLevel.H);
            setQrConfig(config, map.get("qrConfigBean"));
            // 生成二维码到文件，也可以到流
            QrCodeUtil.generate(url, config, FileUtil.file(path));
        }catch (Exception e){
            return false;
        }
        return true;
    }


    /**
     * 识别二维码
     * @return
     */
    public static String decode(String filePath){
        try {
            File file = FileUtil.file(filePath);
            if (file.isFile() && file.exists()) {
                return QrCodeUtil.decode(file);
            }
        }catch (Exception e){
            return null;
        }
        return null;
    }
    private static int a = 0;
    /**
     * 设置二维码配置
     * @param config
     * @param object
     */
    private static void setQrConfig(QrConfig config,Object object){
        if(null == object){
            return;
        }
        QrConfigBean qrConfigBean = JSONObject.parseObject(object.toString(),QrConfigBean.class);
        /*if(!JSONUtil.isJsonObj(object.toString())){
            return;
        }*/
        //QrConfigBean qrConfigBean = JSONUtil.toBean(object.toString(),QrConfigBean.class);
        if(qrConfigBean.getWidth()>0){
            config.setWidth(qrConfigBean.getWidth());
        }
        if(qrConfigBean.getHeight()>0){
            config.setHeight(qrConfigBean.getHeight());
        }
        if(qrConfigBean.getRatio()>0) {
            config.setRatio(qrConfigBean.getRatio());
        }
        if(null != qrConfigBean.getCharset()) {
            config.setCharset(qrConfigBean.getCharset());
        }
        if(!StrUtil.hasBlank(qrConfigBean.getImgPath())){
            config.setImg(qrConfigBean.getImgPath());
        }
        if(null != qrConfigBean.getImgFile()){
            File file = qrConfigBean.getImgFile();
            config.setImg(file);
        }
        if (qrConfigBean.getBackColor() > 0) {
            config.setBackColor(qrConfigBean.getBackColor());
        }
        if(qrConfigBean.getForeColor() > 0) {
            config.setForeColor(qrConfigBean.getForeColor());
        }
        if(qrConfigBean.getMargin() > 0) {
            config.setMargin(qrConfigBean.getMargin());
        }
        if(qrConfigBean.getRatio() > 0) {
            config.setRatio(qrConfigBean.getRatio());
        }
        if(null != qrConfigBean.getErrorCorrection()){
            config.setErrorCorrection(qrConfigBean.getErrorCorrection());
        }
    }

    public static void main(String[] args) {
        Map map = new LinkedHashMap();
        map.put("url","https://www.baidu.com/");
        map.put("path","D:/qrcodeCustom.jpg");
        QrConfigBean bean = new QrConfigBean();
        bean.setImgPath("D:/upload/image/aaa.jpg");
        bean.setImgFile(new File("D:/upload/image/aaa.jpg"));
        map.put("qrConfigBean",bean);

        System.out.println(generate(JSONUtil.toJsonStr(map)));

        System.out.println(decode(map.get("path").toString()));
    }
}
