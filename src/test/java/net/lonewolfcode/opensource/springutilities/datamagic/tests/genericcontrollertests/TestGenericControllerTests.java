package net.lonewolfcode.opensource.springutilities.datamagic.tests.genericcontrollertests;

import net.lonewolfcode.opensource.springutilities.datamagic.genericcontrollertests.GenericTestRunner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.model.InitializationError;
import org.mockito.junit.MockitoJUnitRunner;

import static net.lonewolfcode.opensource.springutilities.datamagic.constants.InternalConstants.InternalErrorMessageTemplates.*;

@RunWith(MockitoJUnitRunner.class)
public class TestGenericControllerTests
{
    //the exception checker rule does not seem compatible with what i'm doing. Going old-school here.
    @Test
    public void genericTestRunnerThrowsExceptionIfNonRestControllerIsAdded()
    {
        initializationErrorFailureTest(InvalidControllersTest.class, String.format(CONTROLLER_REST_ANNOTATION_NOT_FOUND_MESSAGE, TestGenericControllerTests.class.getName()));
    }

    @Test
    public void genericTestRunnerThrowsExceptionIfNoSuiteClassesAnnotationIsFound()
    {
        initializationErrorFailureTest(TestGenericControllerTests.class, String.format(TEST_CLASS_SUITE_CLASSES_ANNOTATION_NOT_FOUND, TestGenericControllerTests.class.getName()));
    }

    @Test
    public void genericTestRunnerThrowsExceptionIfSuiteClassesAnnotationHasNoTestClassesListed()
    {
        initializationErrorFailureTest(EmptySuiteClassAnnotationTest.class, TEST_CLASS_SUITE_CLASSES_ANNOTATION_HAS_NO_CONTROLLERS);
    }

    private void initializationErrorFailureTest(Class<?> testingClass, String expectedCausalMessage)
    {
        try
        {
            new GenericTestRunner(testingClass);
            Assert.fail("No exception was thrown.");
        } catch (Throwable e)
        {
            Assert.assertEquals(InitializationError.class, e.getClass());
            InitializationError error = (InitializationError) e;
            Assert.assertEquals(1, error.getCauses().size());
            Assert.assertEquals(expectedCausalMessage, error.getCauses().get(0).getMessage());
        }
    }

    @Suite.SuiteClasses({TestGenericControllerTests.class})
    private class InvalidControllersTest
    {

    }

    @Suite.SuiteClasses({})
    private class EmptySuiteClassAnnotationTest
    {

    }
}