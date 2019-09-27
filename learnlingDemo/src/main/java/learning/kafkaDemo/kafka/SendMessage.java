package learning.kafkaDemo.kafka;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
public class SendMessage {

    private String id;
    private String jsonMessageType;

    private String primaryKey;
    /**
     * 广播 等扩展种类
     */
    private String messageType;

    private String service;

    private String content; // json message //sdk

    private Date createdAt;

    /**
     * 消息的重试发送次数
     * 用于记录监控
     */
    private Integer retries;

}