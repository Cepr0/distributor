package io.github.cepr0;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiConsumer;

/**
 * @author Sergei Poznanski, 2017-12-12
 */
@Slf4j
public class Distributor {

	private final Map<Class<?>, BiConsumer<?, Object[]>> handlers = new ConcurrentHashMap<>();

	public <T> void addHandler(Class<T> cls, BiConsumer<T, Object[]> handler) {
		handlers.put(cls, handler);
	}

	private <T> BiConsumer<T, Object[]> getHandler(Class<?> cls) {
		BiConsumer<?, Object[]> handler = handlers.get(cls);
		if (handler != null) {
			//noinspection unchecked
			return (BiConsumer<T, Object[]>) handler;
		} else {
			return null;
		}
	}

	public <T> void process(T object, Object... objects) {
		Class<?> cls = object.getClass();
		BiConsumer<Object, Object[]> handler = getHandler(cls);
		if (handler != null) {
			handler.accept(object, objects);
		} else {
			log.warn("Handler not found for class type '{}'", cls.getName());
		}
	}
}
