package io.github.cepr0;

import lombok.Data;

/**
 * @author Sergei Poznanski, 2017-12-12
 */
@Data(staticConstructor = "of")
public class TestEvent {
	private final String strValue;
	private final Long longValue;

	public static Object toObject() {
		return new TestEvent("", 0L);
	}
}
