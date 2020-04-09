package net.lonewolfcode.opensource.springutilities.datamagic.genericcontrollertests;

import net.lonewolfcode.opensource.springutilities.datamagic.constants.InternalConstants;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.model.InitializationError;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TestGenericControllerTests
{
    @Rule
    public ExpectedException exceptionChecker = ExpectedException.none();

    @Test
    public void genericTestRunnerThrowsExceptionIfNonRestControllerIsAdded() throws Throwable
    {
        exceptionChecker.expect(InitializationError.class);
        exceptionChecker.expectMessage(String.format(InternalConstants.CONTROLLER_REST_ANNOTATION_NOT_FOUND_MESSAGE, TestGenericControllerTests.class.getName()));
        new GenericTestRunner(InvalidControllersTest.class);
    }

    @Suite.SuiteClasses({TestGenericControllerTests.class})
    private class InvalidControllersTest
    {

    }
}