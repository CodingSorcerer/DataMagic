package net.lonewolfcode.opensource.springutilities.datamagic.tests.testdata;

import java.util.List;
import java.util.Objects;

public class a
{
    public a()
    {

    }

    //primitives
    public byte byte_primitive;
    public short short_primitive;
    public int integer_primitive;
    public long long_primitive;
    public float float_primitive;
    public double double_primitive;
    public boolean boolean_primitive;
    public char char_primitive;

    //wrappers
    private String string;
    public Byte byte_wrapper;
    public Short short_wrapper;
    public Integer integer_wrapper;
    public Long long_wrapper;
    public Float float_wrapper;
    public Double double_wrapper;
    public Boolean boolean_wrapper;
    public Character character_wrapper;

    //custom objects
    public b class_b;

    //lists
    public List<String> string_list;
    public List<Byte> byte_list;
    public List<Short> short_list;
    public List<Integer> integer_list;
    public List<Long> long_list;
    public List<Float> float_list;
    public List<Double> double_list;
    public List<Boolean> boolean_list;
    public List<Character> character_list;
    public List<b> b_list;

    public String getString()
    {
        return string;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        a a = (a) o;
        return byte_primitive == a.byte_primitive &&
                short_primitive == a.short_primitive &&
                integer_primitive == a.integer_primitive &&
                long_primitive == a.long_primitive &&
                Float.compare(a.float_primitive, float_primitive) == 0 &&
                Double.compare(a.double_primitive, double_primitive) == 0 &&
                boolean_primitive == a.boolean_primitive &&
                char_primitive == a.char_primitive &&
                Objects.equals(string, a.string) &&
                Objects.equals(byte_wrapper, a.byte_wrapper) &&
                Objects.equals(short_wrapper, a.short_wrapper) &&
                Objects.equals(integer_wrapper, a.integer_wrapper) &&
                Objects.equals(long_wrapper, a.long_wrapper) &&
                Objects.equals(float_wrapper, a.float_wrapper) &&
                Objects.equals(double_wrapper, a.double_wrapper) &&
                Objects.equals(boolean_wrapper, a.boolean_wrapper) &&
                Objects.equals(character_wrapper, a.character_wrapper) &&
                Objects.equals(class_b, a.class_b) &&
                Objects.equals(string_list, a.string_list) &&
                Objects.equals(byte_list, a.byte_list) &&
                Objects.equals(short_list, a.short_list) &&
                Objects.equals(integer_list, a.integer_list) &&
                Objects.equals(long_list, a.long_list) &&
                Objects.equals(float_list, a.float_list) &&
                Objects.equals(double_list, a.double_list) &&
                Objects.equals(boolean_list, a.boolean_list) &&
                Objects.equals(character_list, a.character_list) &&
                Objects.equals(b_list, a.b_list);
    }

    public int hashCode()
    {
        return Objects.hash(byte_primitive, short_primitive, integer_primitive, long_primitive, float_primitive, double_primitive, boolean_primitive, char_primitive, string, byte_wrapper, short_wrapper, integer_wrapper, long_wrapper, float_wrapper, double_wrapper, boolean_wrapper, character_wrapper, class_b, string_list, byte_list, short_list, integer_list, long_list, float_list, double_list, boolean_list, character_list, b_list);
    }
}
