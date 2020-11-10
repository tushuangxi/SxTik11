package com.tushuangxi.smart.tv.lding.other;

/**
 *  desc  全局常量
 */
public class AppGlobalConsts {
    //注意必须是0
    public static final int GLOBAL_ZERO = 0;
    //请求成功 必须是200
    public static final int HTTP_SUCCESS = 200;

    //该设备已存在开始的活动  必须是10011055
    public static final int HTTP_EXISTINGSTART = 10011055;

    //该设备活动已经结束  必须是10011048
    public static final int HTTP_EXISTINGOVER = 10011048;

    //token  已经登录
    public static final String Token  = "token";
    //token  nologin 未登录
    public static final String Token_NoLogin  = "Token_NoLogin";


    //是否过期状态
    public static final String DEVICE_AUTH_STATUS  = "deviceAuthStatus";
    //(10011047, "已过期"),必须是10011047
    public static final int HTTP_OUTDATE = 10011047;
    //(10011049, "设备未认证"),必须是10011049
    public static final int HTTP_UNAUTHORIZED = 10011049;
    //(10011050, "设备已认证"),必须是10011050
    public static final int HTTP_AUTHENTICATED = 10011050;


    //活动记录
    public static final String FloatId  = "FloatId";
    public static final String FloatQrCodeUrl  = "FloatQrCodeUrl";
    public static final String FloatOrgName  = "FloatOrgName";
    public static final String FloatDuration  = "FloatDuration";

    //认证公司名称
    public static final String ORGANIZATION_NAME  = "OrganizationName";
    //保存的可编辑公司名称
    public static final String EDIT_ORGANIZATION_NAME  = "EditOrganizationName";
    //保存的可编辑组织名称
    public static final String ValueOrgName  = "valueOrgName";


    //有效期
    public static final String StartTime  = "startTime";
    public static final String EndTime  = "endTime";


    //帮助里的信息
    public static final String COMPANY_NAME  = "companyName";
    public static final String COMPANY_PHONE  = "companyPhone";
    public static final String COMPANY_EMAIL  = "email";
    public static final String VIDE_OURL  = "videoUrl";

    //设备mac
    public static final String MacAddr  = "deviceIdMac";

}
