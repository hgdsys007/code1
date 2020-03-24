package com.legaoyi.protocol.up.messagebody;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.legaoyi.protocol.message.MessageBody;

/**
 * @author <a href="mailto:shengbo.gao@gmail.com;78772895@qq.com">gaoshengbo</a>
 * @version 1.0.0
 * @since 2015-01-30
 */
@Scope("prototype")
@Component(MessageBody.MESSAGE_BODY_BEAN_PREFIX + "0702_2011" + MessageBody.MESSAGE_BODY_BEAN_SUFFIX)
public class JTT808_0702_MessageBody extends MessageBody {

    private static final long serialVersionUID = 7700806649269928953L;

    public static final String MESSAGE_ID = "0702";

    /** 驾驶员姓名 **/
    @JsonProperty("driverName")
    private String driverName;

    /** IC卡号 **/
    @JsonProperty("idCard")
    private String idCard;

    /** 从业资格证书 **/
    @JsonProperty("qualification")
    private String qualification;

    /** 发证机构 **/
    @JsonProperty("certifyauth")
    private String certifyauth;

    public final String getDriverName() {
        return driverName;
    }

    public final void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public final String getIdCard() {
        return idCard;
    }

    public final void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public final String getQualification() {
        return qualification;
    }

    public final void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public final String getCertifyauth() {
        return certifyauth;
    }

    public final void setCertifyauth(String certifyauth) {
        this.certifyauth = certifyauth;
    }

}
