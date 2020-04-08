package net.lonewolfcode.opensource.springutilities.datamagic.genericcontrollertests;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.web.bind.annotation.PostMapping;

import java.lang.reflect.Method;
import java.util.Stack;

public class GenericPostTest
{
    private String postUrl;
    private static Stack<Method> testingQueue = new Stack<>();

    public GenericPostTest()
    {
        if (testingQueue.empty()) throw new IllegalStateException("There are no endpoints to test.");
        Method method = testingQueue.pop();
        PostMapping postMapping = method.getAnnotation(PostMapping.class);
        if (postMapping == null) throw new IllegalArgumentException("This must be a post method: " + method.getName());
        postUrl = postMapping.name();
    }

    public static void pushToQueue(Method method)
    {
        testingQueue.push(method);
    }

    @Test
    public void runTest()
    {
        Assert.assertEquals("", postUrl);
    }
}
