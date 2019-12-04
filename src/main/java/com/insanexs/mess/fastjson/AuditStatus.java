package com.insanexs.mess.fastjson;

/**
 * @Author: insaneXs
 * @Description: Article Audit Status
 * @Date: Create at 2019-12-03
 */
public enum AuditStatus {
    /**
     * 审核中
     */
    AUDITING(1),
    /**
     * 通过
     */
    PASSED(2),
    /**
     * 失败
     */
    FAILED(3);

    private int code;

    AuditStatus(int code){
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static AuditStatus convert(int code){
        AuditStatus[] enums = AuditStatus.values();
        for(AuditStatus e : enums){
            if(e.code == code){
                return e;
            }
        }
        return null;
    }
}
