package com.mljr.common.enums;

/**
 * @ClassName GuavaCacheEnum
 * @Description
 * @Author xiangnan.xu
 * @DATE 2017/12/5 17:51
 */
public enum GuavaCacheEnum {
    ACCESSRULECONFIGURATIONCACHE("accessRuleConfigurationCache","中间表规则配置缓存");
    private String beanName;
    private String describe;

    GuavaCacheEnum(String beanName,String describe){
        this.beanName = beanName;
        this.describe = describe;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
