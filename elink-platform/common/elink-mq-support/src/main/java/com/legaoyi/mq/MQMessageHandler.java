package com.legaoyi.mq;

/**
 * @author <a href="mailto:shengbo.gao@gmail.com;78772895@qq.com">gaoshengbo</a>
 * @version 1.0.0
 * @since 2015-01-30
 */
public interface MQMessageHandler {

    public void handle(String message) throws Exception;

    public void handle(byte[] bytes) throws Exception;

}