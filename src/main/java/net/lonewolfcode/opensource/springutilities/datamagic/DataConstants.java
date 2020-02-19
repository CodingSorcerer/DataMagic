package net.lonewolfcode.opensource.springutilities.datamagic;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DataConstants
{
    static final Map<Class<?>, Object> defaultMap = Stream.of(new Object[][]{
            {byte.class, (byte)1},
            {short.class, (short)1},
            {int.class, 1},
            {long.class, 1L},
            {float.class, (float) 1},
            {double.class, (double) 1},
            {boolean.class, true},
            {char.class, 'c'},
            {String.class, "Test"},
            {Byte.class, (byte)1},
            {Short.class, (short)1},
            {Integer.class, 1},
            {Long.class, 1L},
            {Float.class, (float) 1},
            {Double.class, (double) 1},
            {Boolean.class, true},
            {Character.class, 'c'}
    }).collect(Collectors.toMap(data -> (Class<?>) data[0], data -> data[1]));

    public static <T> T getConstant(Class<T> clazz)
    {
        return (T) defaultMap.get(clazz);
    }
}
