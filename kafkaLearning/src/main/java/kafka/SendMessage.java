package kafka;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
public class SendMessage {

    private String messageId;

    private String topic;

    private String type;

    private String service;

    private String content; // json message //sdk

    private Date createdAt;

    private String sequenceKey;
    /**
     * 消息的重试发送次数
     * 用于记录监控
     */
    private Integer retries;

    /**
     * 广播 等扩展种类
     */
    private String msgType;
}