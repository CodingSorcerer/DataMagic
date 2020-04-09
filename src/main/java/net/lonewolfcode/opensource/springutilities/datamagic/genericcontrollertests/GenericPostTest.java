package net.lonewolfcode.opensource.springutilities.datamagic.genericcontrollertests;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.Queue;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class GenericPostTest
{
    private String postUrl;
    private static Queue<Method> testingQueue = new LinkedList<>();
    @Autowired
    private MockMvc mvc;

    public GenericPostTest()
    {
        if (testingQueue.isEmpty()) throw new IllegalStateException("There are no endpoints to test.");
        Method method = testingQueue.remove();
        PostMapping postMapping = method.getAnnotation(PostMapping.class);
        if (postMapping == null) throw new IllegalArgumentException("This must be a post method: " + method.getName());
        postUrl = createUrl(postMapping.value(), method.getDeclaringClass());
    }

    public static void addToQueue(Method method)
    {
        testingQueue.add(method);
    }

    private String createUrl(String[] postMappingValue, Class<?> controllerClass)
    {
        String postPath = controllerClass.getAnnotation(RestController.class).value();
        postPath += postMappingValue == null || postMappingValue.length < 1 ? "" : postMappingValue[0];
        if (postPath.isEmpty()) postPath = "/";
        return postPath;
    }

    @Test
    public void postWithNullBodyThrows400() throws Exception
    {
        mvc.perform(post(postUrl).contentType(MediaType.APPLICATION_JSON).content("")).andExpect(status().isBadRequest());
    }
}
