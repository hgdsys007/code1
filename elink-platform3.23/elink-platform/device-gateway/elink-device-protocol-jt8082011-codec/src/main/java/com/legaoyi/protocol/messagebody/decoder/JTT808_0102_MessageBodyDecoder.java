package com.legaoyi.protocol.messagebody.decoder;

import org.springframework.stereotype.Component;

import com.legaoyi.protocol.exception.IllegalMessageException;
import com.legaoyi.protocol.message.MessageBody;
import com.legaoyi.protocol.message.decoder.MessageBodyDecoder;
import com.legaoyi.protocol.up.messagebody.JTT808_0102_MessageBody;
import com.legaoyi.protocol.util.ByteUtils;

/**
 * @author <a href="mailto:shengbo.gao@gmail.com;78772895@qq.com">gaoshengbo</a>
 * @version 1.0.0
 * @since 2015-01-30
 */
@Component(MessageBodyDecoder.MESSAGE_BODY_DECODER_BEAN_PREFIX + "0102_2011" + MessageBodyDecoder.MESSAGE_BODY_DECODER_BEAN_SUFFIX)
public class JTT808_0102_MessageBodyDecoder implements MessageBodyDecoder {

    @Override
    public MessageBody decode(byte[] messageBody) throws IllegalMessageException {
        if (messageBody == null || messageBody.length == 0) {
            throw new IllegalMessageException();
        }
        JTT808_0102_MessageBody message = new JTT808_0102_MessageBody();
        try {
            message.setAuthCode(ByteUtils.bytes2gbk(messageBody));
            return message;
        } catch (Exception e) {
            throw new IllegalMessageException(e);
        }
    }
}
