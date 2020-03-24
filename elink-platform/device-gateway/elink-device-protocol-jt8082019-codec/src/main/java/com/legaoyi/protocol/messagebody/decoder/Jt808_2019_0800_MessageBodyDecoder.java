package com.legaoyi.protocol.messagebody.decoder;

import org.springframework.stereotype.Component;

import com.legaoyi.protocol.exception.IllegalMessageException;
import com.legaoyi.protocol.message.MessageBody;
import com.legaoyi.protocol.message.decoder.MessageBodyDecoder;
import com.legaoyi.protocol.upstream.messagebody.Jt808_2019_0800_MessageBody;
import com.legaoyi.protocol.util.ByteUtils;

/**
 * @author <a href="mailto:shengbo.gao@gmail.com;78772895@qq.com">gaoshengbo</a>
 * @version 1.0.0
 * @since 2019-05-20
 */
@Component(MessageBodyDecoder.MESSAGE_BODY_DECODER_BEAN_PREFIX + "0800_2019" + MessageBodyDecoder.MESSAGE_BODY_DECODER_BEAN_SUFFIX)
public class Jt808_2019_0800_MessageBodyDecoder implements MessageBodyDecoder {

    @Override
    public MessageBody decode(byte[] bytes) throws IllegalMessageException {
        Jt808_2019_0800_MessageBody messageBody = new Jt808_2019_0800_MessageBody();
        try {
            int offset = 0;

            byte[] arr = new byte[4];
            System.arraycopy(bytes, offset, arr, 0, arr.length);
            messageBody.setMediaDataId(ByteUtils.dword2long(arr));
            offset += arr.length;

            messageBody.setMediaType(ByteUtils.byte2int(bytes[offset++]));
            messageBody.setMediaFormatCode(ByteUtils.byte2int(bytes[offset++]));
            messageBody.setEventCode(ByteUtils.byte2int(bytes[offset++]));
            messageBody.setChannelId(ByteUtils.byte2int(bytes[offset++]));
            return messageBody;
        } catch (Exception e) {
            throw new IllegalMessageException(e);
        }
    }

    protected void copy(MessageBody src, Jt808_2019_0800_MessageBody desc) throws IllegalMessageException {
        Jt808_2019_0800_MessageBody src1 = (Jt808_2019_0800_MessageBody) src;
        desc.setChannelId(src1.getChannelId());
        desc.setEventCode(src1.getEventCode());
        desc.setMediaDataId(src1.getMediaDataId());
        desc.setMediaFormatCode(src1.getMediaFormatCode());
        desc.setMediaType(src1.getMediaType());
    }
}
