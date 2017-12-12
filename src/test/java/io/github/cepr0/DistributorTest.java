package io.github.cepr0;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Sergei Poznanski, 2017-12-12
 */
public class DistributorTest {

	private Distributor distributor = new Distributor();

	@Before
	public void setUp() throws Exception {

		distributor.addHandler(TestMessage.class, this::testMessageHandler);
		distributor.addHandler(TestEvent.class, this::testEventHandler);
	}

	@Test
	public void testProcess() {
		distributor.process(TestMessage.of("Test message", 1L), 1);
		distributor.process(TestEvent.of("Test event", 2L), 2);
		distributor.process(1, 2);
	}

	private void testMessageHandler(TestMessage message, Object... objects) {
		assertThat(message).isInstanceOf(TestMessage.class);
		assertThat(objects[0]).isEqualTo(1);
		System.out.println(message);
	}

	private void testEventHandler(TestEvent event, Object... objects) {
		assertThat(event).isInstanceOf(TestEvent.class);
		assertThat(objects[0]).isEqualTo(2);
		System.out.println(event);
	}

}