package com.org.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
* @Author: WangLin
* @Description:
* @Date: 2018/11/16 9:55
*/
@Data
public class HrpMenuBlock implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 区块编码 */
    private String crcHrpBlockCode;

    /** 类型（待定） */
    private String crcHrpBlockType;

    /** 名称 */
    private String crcHrpBlockName;

    /** 关联菜单 */
    private String crcHrpMenuCode;

    /** 父级节点，顶层默认为-1 */
    private String crcParBlockCode = "-1";

    /** 附件名称 */
    private String fileName1;

    /** 附件唯一名称 */
    private String attachsysfilename;

    /** 描述 */
    private String descr254;

    /** 序号 */
    private Integer crcHrpOrderNum;

    /** 是否启用 */
    private String enabled = "Y";

    /**
     * 后端是否权限拦截，默认Y
     */
    private String crcAuthorityFlag = "Y";

    /** 添加时间 */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    private Date hrsRowAddDttm;

    /** 添加用户 */
    private String hrsRowAddOprid;

    /** 最后修改时间 */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    private Date hrsRowUpdDttm;

    /** 最后修改用户 */
    private String hrsRowUpdOprid;

    /*** 图标路径*/
    private String crcImagePath;
}
