package net.lonewolfcode.opensource.springutilities.datamagic.testdata;

import java.util.Objects;

public class b
{
    @Important
    public String string;
    public String notImportant;
    @Other
    public String someOtherValue;
    int value;

    public b(String str, int someInt)
    {
        string = str;
        value = someInt;
    }

    @MethodAnnotation
    public String toString()
    {
        return "b{" +
                "string='" + string + '\'' +
                ", notImportant='" + notImportant + '\'' +
                ", someOtherValue='" + someOtherValue + '\'' +
                ", value=" + value +
                '}';
    }

    @Other
    @MethodAnnotation
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        b b = (b) o;
        return value == b.value &&
                Objects.equals(string, b.string) &&
                Objects.equals(notImportant, b.notImportant) &&
                Objects.equals(someOtherValue, b.someOtherValue);
    }

    public int hashCode()
    {
        return Objects.hash(string, notImportant, someOtherValue, value);
    }
}