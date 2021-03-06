package com.legaoyi.protocol.messagebody.decoder;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

import com.legaoyi.protocol.exception.IllegalMessageException;
import com.legaoyi.protocol.message.MessageBody;
import com.legaoyi.protocol.message.decoder.MessageBodyDecoder;
import com.legaoyi.protocol.up.messagebody.JTT808_0700_11H_MessageBody;
import com.legaoyi.protocol.util.ByteUtils;
import com.legaoyi.protocol.util.DateUtils;

/**
 * 采集最近 2个 日历 天 内的同一 驾驶 员连续 驾驶 时间超过 3小时 的所 有记录数据
 * 
 * @author <a href="mailto:shengbo.gao@gmail.com;78772895@qq.com">gaoshengbo</a>
 * @version 1.0.0
 * @since 2015-01-30
 */
@Component(MessageBodyDecoder.MESSAGE_BODY_DECODER_BEAN_PREFIX + "0700_11H_2011" + MessageBodyDecoder.MESSAGE_BODY_DECODER_BEAN_SUFFIX)
public class JTT808_0700_11H_MessageBodyDecoder extends JTT808_0700_MessageBodyDecoder {

    @Override
    public MessageBody decode(byte[] messageBody) throws IllegalMessageException {
        JTT808_0700_11H_MessageBody message = new JTT808_0700_11H_MessageBody();
        try {
            int offset = 9;

            if (messageBody.length - offset > 0) {
                byte[] arr = new byte[18];
                System.arraycopy(messageBody, offset, arr, 0, arr.length);
                // ASCII码字符
                message.setDriverLicense(ByteUtils.bytes2ascii(arr));
                offset += arr.length;

                List<String> dataList = new ArrayList<String>();
                int count = (int) Math.floor((messageBody.length - 1 - offset) / 10);
                StringBuilder sb = null;
                for (int i = 0; i < count; i++) {
                    arr = new byte[5];
                    System.arraycopy(messageBody, offset, arr, 0, arr.length);
                    String startTime = DateUtils.bcd2dateTime(ByteUtils.bytes2bcd(arr));
                    offset += arr.length;

                    System.arraycopy(messageBody, offset, arr, 0, arr.length);
                    String endTime = DateUtils.bcd2dateTime(ByteUtils.bytes2bcd(arr));
                    offset += arr.length;

                    sb = new StringBuilder();
                    sb.append(startTime).append(",").append(endTime);
                    dataList.add(sb.toString());
                }
                message.setDataList(dataList);
            }
            return message;
        } catch (Exception e) {
            throw new IllegalMessageException(e);
        }
    }
}
