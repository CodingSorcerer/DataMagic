package net.lonewolfcode.opensource.springutilities.datamagic;

import net.lonewolfcode.opensource.springutilities.datamagic.testdata.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestDataMagic
{
    a actual;

    @Before
    public void setup() throws Exception
    {
        actual = DataMagic.createDefaultObject(a.class);
    }

    @Test
    public void testCreateDefaultObjectSetsPrivateString()
    {
        Assert.assertEquals(DataConstants.getConstant(String.class), actual.getString());
    }

    @Test
    public void testCreateDefaultObjectSetsByte()
    {
        Assert.assertEquals((byte) DataConstants.getConstant(byte.class), actual.byte_primitive);
    }

    @Test
    public void testCreateDefaultObjectSetsShort()
    {
        Assert.assertEquals((short) DataConstants.getConstant(short.class), actual.short_primitive);
    }

    @Test
    public void testCreateDefaultObjectSetsInteger()
    {
        Assert.assertEquals((int) DataConstants.getConstant(int.class), actual.integer_primitive);
    }

    @Test
    public void testCreateDefaultObjectSetsLong()
    {
        Assert.assertEquals((long) DataConstants.getConstant(long.class), actual.long_primitive);
    }

    @Test
    public void testCreateDefaultObjectSetsFloat()
    {
        Assert.assertEquals(DataConstants.getConstant(float.class), actual.float_primitive, 0);
    }

    @Test
    public void testCreateDefaultObjectSetsDouble()
    {
        Assert.assertEquals(DataConstants.getConstant(double.class), actual.double_primitive, 0);
    }

    @Test
    public void testCreateDefaultObjectSetsBoolean()
    {
        Assert.assertEquals(DataConstants.getConstant(boolean.class), actual.boolean_primitive);
    }

    @Test
    public void testCreateDefaultObjectSetsChar()
    {
        Assert.assertEquals((char) DataConstants.getConstant(char.class), actual.char_primitive);
    }

    @Test
    public void testCreateDefaultObjectSetsByteWrapper()
    {
        Assert.assertEquals(DataConstants.getConstant(Byte.class), actual.byte_wrapper);
    }

    @Test
    public void testCreateDefaultObjectSetsShortWrapper()
    {
        Assert.assertEquals(DataConstants.getConstant(Short.class), actual.short_wrapper);
    }

    @Test
    public void testCreateDefaultObjectSetsIntegerWrapper()
    {
        Assert.assertEquals(DataConstants.getConstant(Integer.class), actual.integer_wrapper);
    }

    @Test
    public void testCreateDefaultObjectSetsLongWrapper()
    {
        Assert.assertEquals(DataConstants.getConstant(Long.class), actual.long_wrapper);
    }

    @Test
    public void testCreateDefaultObjectSetsFloatWrapper()
    {
        Assert.assertEquals(DataConstants.getConstant(Float.class), actual.float_wrapper, 0);
    }

    @Test
    public void testCreateDefaultObjectSetsDoubleWrapper()
    {
        Assert.assertEquals(DataConstants.getConstant(Double.class), actual.double_wrapper, 0);
    }

    @Test
    public void testCreateDefaultObjectSetsBooleanWrapper()
    {
        Assert.assertEquals(DataConstants.getConstant(Boolean.class), actual.boolean_wrapper);
    }

    @Test
    public void testCreateDefaultObjectSetsCharWrapper()
    {
        Assert.assertEquals(DataConstants.getConstant(Character.class), actual.character_wrapper);
    }

    @Test
    public void testCreateDefaultObjectSetsCustomObject() throws Exception
    {
        Assert.assertEquals(DataMagic.createDefaultObject(b.class), actual.class_b);
    }

    @Test
    public void testCreateDefaultObjectSetsStringList()
    {
        Assert.assertEquals(List.of(DataConstants.getConstant(String.class)), actual.string_list);
    }

    @Test
    public void testCreateDefaultObjectSetsByteList()
    {
        Assert.assertEquals(List.of(DataConstants.getConstant(Byte.class)), actual.byte_list);
    }

    @Test
    public void testCreateDefaultObjectSetsShortList()
    {
        Assert.assertEquals(List.of(DataConstants.getConstant(Short.class)), actual.short_list);
    }

    @Test
    public void testCreateDefaultObjectSetsIntegerList()
    {
        Assert.assertEquals(List.of(DataConstants.getConstant(Integer.class)), actual.integer_list);
    }

    @Test
    public void testCreateDefaultObjectSetsLongList()
    {
        Assert.assertEquals(List.of(DataConstants.getConstant(Long.class)), actual.long_list);
    }

    @Test
    public void testCreateDefaultObjectSetsFloatList()
    {
        Assert.assertEquals(List.of(DataConstants.getConstant(Float.class)), actual.float_list);
    }

    @Test
    public void testCreateDefaultObjectSetsDoubleList()
    {
        Assert.assertEquals(List.of(DataConstants.getConstant(Double.class)), actual.double_list);
    }

    @Test
    public void testCreateDefaultObjectSetsBooleanList()
    {
        Assert.assertEquals(List.of(DataConstants.getConstant(Boolean.class)), actual.boolean_list);
    }

    @Test
    public void testCreateDefaultObjectSetsCharacterList()
    {
        Assert.assertEquals(List.of(DataConstants.getConstant(Character.class)), actual.character_list);
    }

    @Test
    public void testCreateDefaultObjectSetsCustomObjectList() throws Exception
    {
        Assert.assertEquals(List.of(DataMagic.createDefaultObject(b.class)), actual.b_list);
    }

    @Test
    public void testCreateDefaultList() throws Exception
    {
        List<a> actualList = DataMagic.createDefaultObjectList(a.class);
        Assert.assertEquals(List.of(DataMagic.createDefaultObject(a.class)), actualList);
    }

    @Test
    public void testCreateDefaultListWithMultipleItems() throws Exception
    {
        List<a> expected = new ArrayList<>();
        List<a> actualList = DataMagic.createDefaultObjectList(a.class, 12);
        for (int x = 0; x < 12; x++) expected.add(DataMagic.createDefaultObject(a.class));

        Assert.assertEquals(expected, actualList);
    }

    @Test
    public void testCreateDefaultListWithImportantTag() throws Exception
    {
        List<b> actualList = DataMagic.createDefaultObjectList(b.class, Important.class);
        Assert.assertEquals(List.of(DataMagic.createDefaultObject(b.class, Important.class)), actualList);
    }

    @Test
    public void testCreateDefaultListWithImportantFlagAndMultipleItems() throws Exception
    {
        List<b> expected = new ArrayList<>();
        List<b> actualList = DataMagic.createDefaultObjectList(b.class, 12, Important.class);
        for (int x = 0; x < 12; x++) expected.add(DataMagic.createDefaultObject(b.class, Important.class));

        Assert.assertEquals(expected, actualList);
    }

    @Test
    public void testCreateDefaultObjectSetsOnlyImportantFields() throws Exception
    {
        b importantOnly = DataMagic.createDefaultObject(b.class, Important.class);
        b manuallyCreated = new b();
        manuallyCreated.string = DataConstants.getConstant(String.class);
        manuallyCreated.notImportant = null;

        Assert.assertEquals(manuallyCreated, importantOnly);
    }

    @Test
    public void testGetAnnotatedMethods()
    {
        Method[] methods = b.class.getMethods();
        Map<Class<? extends Annotation>, List<Method>> expected = new HashMap<>();
        Map<Class<? extends Annotation>, List<Method>> actual = DataMagic.getMethodsSortedByAnnotation(b.class);
        for (Method method : methods)
        {
            Annotation[] annotations = method.getAnnotations();
            if (annotations.length > 0)
            {
                for (Annotation annotation : annotations)
                {
                    if (! expected.containsKey(annotation.annotationType())) expected.put(annotation.annotationType(), new ArrayList<>());
                    expected.get(annotation.annotationType()).add(method);
                }
            }
            else
            {
                if (!expected.containsKey(null)) expected.put(null, new ArrayList<>());
                expected.get(null).add(method);
            }
        }

        Assert.assertEquals(expected, actual);
        Assert.assertEquals(2, actual.get(MethodAnnotation.class).size());
        Assert.assertEquals(1, actual.get(Other.class).size());
    }
}
