package net.lonewolfcode.opensource.springutilities.datamagic.constants;

public class InternalConstants
{
    public static class InternalErrorMessageTemplates
    {
        public final static String CONTROLLER_REST_ANNOTATION_NOT_FOUND_MESSAGE = "Class '%s' must be  a rest endpoint. Please expose this endpoint using @RestController";
        public final static String TEST_CLASS_SUITE_CLASSES_ANNOTATION_NOT_FOUND = "class '%s' must have a SuiteClasses annotation";
        public final static String TEST_CLASS_SUITE_CLASSES_ANNOTATION_HAS_NO_CONTROLLERS = "No controller classes were provided in SuiteClasses annotation";
    }
}
