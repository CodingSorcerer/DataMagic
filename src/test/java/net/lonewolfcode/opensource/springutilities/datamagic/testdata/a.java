package net.lonewolfcode.opensource.springutilities.datamagic.testdata;

import java.util.List;

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
}
