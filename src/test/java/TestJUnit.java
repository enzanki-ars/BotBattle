import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class TestJUnit {

	@Test
	public void helloWorld() {
		assertEquals("\"Hello, World!\" should be the same...", "Hello, World!", "Hello, World!");
	}
} 
