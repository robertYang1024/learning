package kafka.model;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author bo.yang
 */
@Data
@Accessors(chain = true)
public class UserEvent {


    private String name;
    private int age;
    private String country;
}
