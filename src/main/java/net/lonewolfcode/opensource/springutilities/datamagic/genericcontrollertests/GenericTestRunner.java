package net.lonewolfcode.opensource.springutilities.datamagic.genericcontrollertests;

import org.junit.runner.Description;
import org.junit.runner.Runner;
import org.junit.runners.Suite;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

public class GenericTestRunner extends Suite
{
    public GenericTestRunner(Class<?> clazz) throws Throwable
    {
        this(clazz, new RunnersFactory(clazz, getControllerClasses(clazz)));
    }

    private GenericTestRunner(Class<?> clazz, RunnersFactory runnersFactory) throws Exception
    {
        super(clazz, runnersFactory.createRunners());
    }

    private static List<Class<?>> getControllerClasses(Class<?> clazz) throws InitializationError
    {
        Suite.SuiteClasses annotation = clazz.getAnnotation(Suite.SuiteClasses.class);
        if (annotation == null)
            throw new InitializationError(String.format("class '%s' must have a SuiteClasses annotation", clazz.getName()));
        return List.of(annotation.value());
    }

    private static class RunnersFactory
    {
        private Class<?> clazz;

        private RunnersFactory(Class<?> clazz, List<Class<?>> controllerClasses)
        {
            this.clazz = clazz;
        }

        private List<Runner> createRunners() throws Exception
        {
            List<Runner> runners = new ArrayList<>();


            return runners;
        }
    }

    private static class GenericRunner extends SpringJUnit4ClassRunner
    {
        String name;

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
            return Description.createTestDescription("", method.getName());
        }
    }
}
