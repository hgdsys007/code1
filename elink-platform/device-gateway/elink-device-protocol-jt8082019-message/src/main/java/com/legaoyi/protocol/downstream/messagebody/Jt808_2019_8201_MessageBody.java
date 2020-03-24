package com.legaoyi.protocol.downstream.messagebody;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.legaoyi.protocol.message.MessageBody;

/**
 * 位置信息查询
 * 
 * @author <a href="mailto:shengbo.gao@gmail.com;78772895@qq.com">gaoshengbo</a>
 * @version 1.0.0
 * @since 2019-05-20
 */
@Scope("prototype")
@Component(MessageBody.MESSAGE_BODY_BEAN_PREFIX + "8201_2019" + MessageBody.MESSAGE_BODY_BEAN_SUFFIX)
public class Jt808_2019_8201_MessageBody extends MessageBody {

    private static final long serialVersionUID = -4559747091091307516L;

    public static final String MESSAGE_ID = "8201";

}
