package net.lonewolfcode.opensource.springutilities.datamagic.genericcontrollertests;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.web.bind.annotation.PostMapping;

import java.lang.reflect.Method;

public class GenericPostTest
{
    String postUrl;

    GenericPostTest(Method method)
    {
        PostMapping postMapping = method.getAnnotation(PostMapping.class);
        if (postMapping == null) throw new IllegalArgumentException("This must be a post method: " + method.getName());
        postUrl = postMapping.name();
    }

    @Test
    public void runTest()
    {
        Assert.assertEquals("", postUrl);
    }
}
