package learning.test.io;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Externalizable;
import java.io.Serializable;

/**
 * @author bo.yang
 */
@Data
@AllArgsConstructor
public class Person implements Serializable {
    private String name;
    private int age;


}
