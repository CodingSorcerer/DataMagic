package net.lonewolfcode.opensource.springutilities.datamagic;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.*;
import java.util.stream.Collectors;

public class DataMagic
{
    public static <T> List<T> createDefaultObjectList(Class<T> clazz) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException
    {
        return createDefaultObjectList(clazz, 1, null);
    }

    public static <T> List<T> createDefaultObjectList(Class<T> clazz, int entries) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException
    {
        return createDefaultObjectList(clazz, entries, null);
    }

    public static <T> List<T> createDefaultObjectList(Class<T> clazz, Class<? extends Annotation> importantMarker) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException
    {
        return createDefaultObjectList(clazz, 1, importantMarker);
    }

    public static <T> List<T> createDefaultObjectList(Class<T> clazz, int entries, Class<? extends Annotation> importantMarker) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException
    {
        ArrayList<T> output = new ArrayList<>();
        for (int x = 0; x < entries; x++) output.add(createDefaultObject(clazz, importantMarker));
        return output;
    }

    public static <T> T createDefaultObject(Class<T> clazz) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException
    {
        return createDefaultObject(clazz, null);
    }

    @SuppressWarnings({"unchecked"})
    public static <T> T createDefaultObject(Class<T> clazz, Class<? extends Annotation> importantMarker) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException
    {
        T output;

        if (DataConstants.defaultMap.containsKey(clazz))
        {
            output = (T) DataConstants.defaultMap.get(clazz);
        }
        else
        {
            output = instanciateObject(clazz);
            Field[] fields = clazz.getDeclaredFields();

            for (Field field : fields)
            {
                if (importantMarker == null || field.getDeclaredAnnotation(importantMarker) != null)
                {
                    field.setAccessible(true);
                    setField(field, output, importantMarker);
                }
            }
        }

        return output;
    }

    public static Map<Class<? extends Annotation>, List<Method>> getMethodsSortedByAnnotation(Class<?> clazz)
    {
        Map<Class<? extends Annotation>, List<Method>> annotatedMethods = new HashMap<>();

        for (Method method : clazz.getMethods())
        {
            Annotation[] annotations = method.getAnnotations();
            if (annotations.length > 0)
            {
                for (Annotation annotation : annotations)
                {
                    Class<? extends Annotation> annotationClass = annotation.annotationType();
                    if (!annotatedMethods.containsKey(annotationClass))
                        annotatedMethods.put(annotationClass, new ArrayList<>());
                    annotatedMethods.get(annotationClass).add(method);
                }
            }
            else
            {
                if (!annotatedMethods.containsKey(null)) annotatedMethods.put(null, new ArrayList<>());
                annotatedMethods.get(null).add(method);
            }
        }

        return annotatedMethods;
    }

    private static void setField(Field field, Object object, Class<? extends Annotation> importantMarker) throws IllegalAccessException, NoSuchMethodException, InstantiationException, InvocationTargetException
    {
        Type fieldType = field.getType();

        if (DataConstants.defaultMap.containsKey(fieldType))
        {
            field.set(object, DataConstants.defaultMap.get(fieldType));
        }
        else if (fieldType == List.class)
        {
            field.set(object, createDefaultObjectList((Class<?>) ((ParameterizedType) field.getGenericType()).getActualTypeArguments()[0], 1, importantMarker));
        }
        else
        {
            field.set(object, createDefaultObject((Class<?>) fieldType));
        }
    }

    @SuppressWarnings({"unchecked"})
    private static <T> T instanciateObject(Class<T> clazz) throws InstantiationException, IllegalAccessException, NoSuchMethodException
    {
        T output = null;
        List<Constructor<?>> constructorList = List.of(clazz.getDeclaredConstructors()).stream().sorted(Comparator.comparingInt(Constructor::getParameterCount)).collect(Collectors.toList());
        Iterator<Constructor<?>> constructorIterator = constructorList.iterator();

        while (constructorIterator.hasNext() && output == null)
        {
            try
            {
                Constructor<?> constructor = constructorIterator.next();
                Class<?>[] parameterTypes = constructor.getParameterTypes();
                Object[] parameters = new Object[parameterTypes.length];
                for (int x = 0; x < parameterTypes.length; x++) parameters[x] = createDefaultObject(parameterTypes[x]);
                constructor.setAccessible(true);
                output = (T)constructor.newInstance(parameters);
            }
            catch (InvocationTargetException e)
            {
                output = null;
            }
        }

        if (output == null) throw new InstantiationException(String.format("Could not construct object from given visible constructors: %s", constructorList.toString()));

        return output;
    }
}
