package learning.kafkaDemo.kafka;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
public class RegisterMessage {

    private String id;

    private String topic;

    private String type;

    private String service;

    private String content; // json message //sdk

    private Date createAt;
}