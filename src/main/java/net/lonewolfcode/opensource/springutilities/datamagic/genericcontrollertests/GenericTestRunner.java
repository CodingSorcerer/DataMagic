package net.lonewolfcode.opensource.springutilities.datamagic.genericcontrollertests;

import net.lonewolfcode.opensource.springutilities.datamagic.DataMagic;
import org.junit.runner.Description;
import org.junit.runner.Runner;
import org.junit.runners.Suite;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static net.lonewolfcode.opensource.springutilities.datamagic.constants.InternalConstants.InternalErrorMessageTemplates.*;

public class GenericTestRunner extends Suite
{
    public GenericTestRunner(Class<?> clazz) throws Throwable
    {
        this(clazz, new RunnersFactory(getControllerClasses(clazz)));
    }

    private GenericTestRunner(Class<?> clazz, RunnersFactory runnersFactory) throws Exception
    {
        super(clazz, runnersFactory.createRunners());
    }

    private static List<Class<?>> getControllerClasses(Class<?> clazz) throws InitializationError
    {
        Suite.SuiteClasses annotation = clazz.getAnnotation(Suite.SuiteClasses.class);
        if (annotation == null)
            throw new InitializationError(String.format(TEST_CLASS_SUITE_CLASSES_ANNOTATION_NOT_FOUND, clazz.getName()));
        if (annotation.value().length < 1)
            throw new InitializationError(TEST_CLASS_SUITE_CLASSES_ANNOTATION_HAS_NO_CONTROLLERS);
        List<Class<?>> classes = List.of(annotation.value());
        for (Class<?> controllerClass : classes)
        {
            if (controllerClass.getAnnotation(RestController.class) == null)
                throw new InitializationError(String.format(CONTROLLER_REST_ANNOTATION_NOT_FOUND_MESSAGE, controllerClass.getName()));
        }
        return classes;
    }

    private static class RunnersFactory
    {
        private List<Class<?>> controllerClasses;

        private RunnersFactory(List<Class<?>> controllerClasses)
        {
            this.controllerClasses = controllerClasses;
        }

        private List<Runner> createRunners() throws InitializationError
        {
            List<Runner> runners = new ArrayList<>();

            for (Class<?> ctrlClazz : controllerClasses)
            {
                String classUrl = ctrlClazz.getAnnotation(RestController.class).value();
                Map<Class<? extends Annotation>, List<Method>> methods = DataMagic.getMethodsSortedByAnnotation(ctrlClazz);
                for (Method method : methods.get(PostMapping.class))
                {
                    GenericPostTest.addToQueue(method);
                    runners.add(new GenericRunner(GenericPostTest.class, String.format("Endpoint: %s%s", classUrl, getUrl(method.getAnnotation(PostMapping.class).value()))));
                }
            }
            return runners;
        }

        private String getUrl(String[] value)
        {
            return value == null || value.length < 1 ? "" : value[0];
        }
    }

    private static class GenericRunner extends SpringJUnit4ClassRunner
    {
        private String name;

        public GenericRunner(Class<?> clazz, String name) throws InitializationError
        {
            super(clazz);
            this.name = name;
        }

        public Description getDescription()
        {
            Description description = Description.createSuiteDescription(name);
            getChildren().forEach(child -> description.addChild(describeChild(child)));
            return description;
        }

        protected Description describeChild(FrameworkMethod method)
        {
            return Description.createTestDescription(getTestClass().getName(), method.getName());
        }
    }
}
