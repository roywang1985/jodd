// Copyright (c) 2003-2013, Jodd Team (jodd.org). All Rights Reserved.

package jodd.typeconverter;

import jodd.typeconverter.impl.ClassArrayConverter;
import org.junit.Test;

import static jodd.typeconverter.TypeConverterTestHelper.arrc;
import static org.junit.Assert.*;

public class ClassArrayConverterTest {

	@Test
	@SuppressWarnings({"unchecked"})
	public void testConversion() {
		ClassArrayConverter classArrayConverter = (ClassArrayConverter) TypeConverterManager.lookup(Class[].class);

		assertNull(classArrayConverter.convert(null));

		assertEq(arrc(String.class), classArrayConverter.convert(String.class));
		assertEq(arrc(String.class, Integer.class), classArrayConverter.convert(arrc(String.class, Integer.class)));
		assertEq(arrc(Integer.class), classArrayConverter.convert("java.lang.Integer"));
		assertEq(arrc(Integer.class, String.class), classArrayConverter.convert("java.lang.Integer,    java.lang.String"));

		try {
			classArrayConverter.convert("foo.Klass");
			fail();
		} catch (TypeConversionException ignore) {
		}
	}

	private void assertEq(Class<String>[] arr1, Class[] arr2) {
		assertEquals(arr1.length, arr2.length);
		for (int i = 0; i < arr1.length; i++) {
			assertEquals(arr1[i], arr2[i]);
		}
	}
}

