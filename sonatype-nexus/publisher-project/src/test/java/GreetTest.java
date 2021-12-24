
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GreetTest {

    @Test
    public void HelloTest() {
        Greet greet = new Greet();
        String hello = greet.Hello("World");
        assertEquals("Hello World!", hello);
    }
}
