package net.lonewolfcode.opensource.springutilities.datamagic.testdata;

import java.util.Objects;

public class b
{
    @Important
    public String string;
    public String notImportant;

    public String toString()
    {
        return "b{" +
                "string='" + string + '\'' +
                ", notImportant='" + notImportant + '\'' +
                '}';
    }

    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        b b = (b) o;
        return Objects.equals(string, b.string) &&
                Objects.equals(notImportant, b.notImportant);
    }

    public int hashCode()
    {
        return Objects.hash(string, notImportant);
    }
}