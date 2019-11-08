package learning.test.io;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

/**
 * 要想把Java对象转为流，该对象必须实现Serializable接口或者Externalizable接口
 * @author bo.yang
 */
public class SeializableTest {
    public static void main(String[] args){  
                 String path = "e:" + File.separator + "person.txt ";
         try (
                 ObjectOutputStream outputStream =
                      new ObjectOutputStream(new FileOutputStream(path))
         ){
         Person person = new Person("li",23);
             outputStream.writeObject(person);
         }catch (Exception e) {
             e.printStackTrace();
         }

    }
}
