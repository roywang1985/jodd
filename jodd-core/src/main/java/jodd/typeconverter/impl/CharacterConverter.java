// Copyright (c) 2003-2013, Jodd Team (jodd.org). All Rights Reserved.

package jodd.typeconverter.impl;

import jodd.typeconverter.TypeConversionException;
import jodd.typeconverter.TypeConverter;

/**
 * Converts given object to <code>Character</code>.
 * Conversion rules:
 * <ul>
 * <li><code>null</code> value is returned as <code>null</code></li>
 * <li>object of destination type is simply casted</li>
 * <li><code>Number</code> is converted to <code>char</code> value</li>
 * <li>finally, <code>toString()</code> value of length 1 is converted to <code>char</code></li>
 * </ul>
 */
public class CharacterConverter implements TypeConverter<Character> {

	public Character convert(Object value) {
		if (value == null) {
			return null;
		}
		if (value.getClass() == Character.class) {
			return (Character) value;
		}
		if (value instanceof Number) {
			char c = (char) ((Number) value).intValue();
			return Character.valueOf(c);
		}
		try {
			String s = value.toString();
			if (s.length() != 1) {
				throw new TypeConversionException(value);
			}
			return Character.valueOf(s.charAt(0));
		} catch (IndexOutOfBoundsException ioobex) {
			throw new TypeConversionException(value, ioobex);
		}
	}

}