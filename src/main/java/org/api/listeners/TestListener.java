package org.api.listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

/**
 * TestListener is a custom implementation of the ITestListener interface.
 * It provides logging for various test execution events such as start, success, failure, and skip.
 */
public class TestListener implements ITestListener {

    // Logger instance for logging test execution details
    private static final Logger logger = LogManager.getLogger(TestListener.class);

    /**
     * Invoked before the test suite starts.
     *
     * @param context the test context containing information about the test suite
     */
    @Override
    public void onStart(ITestContext context) {
        logger.info("Test Suite Started!!!");
    }

    /**
     * Invoked after the test suite finishes.
     *
     * @param context the test context containing information about the test suite
     */
    @Override
    public void onFinish(ITestContext context) {
        logger.info("Test Suite Completed!!!");
    }

    /**
     * Invoked when an individual test starts.
     *
     * @param result the test result containing information about the test method
     */
    @Override
    public void onTestStart(ITestResult result) {
        logger.info("************************************************************************");
        logger.info("{} is Started!!!", result.getMethod().getMethodName());
        logger.info("Description: {}", result.getMethod().getDescription());
    }

    /**
     * Invoked when an individual test passes successfully.
     *
     * @param result the test result containing information about the test method
     */
    @Override
    public void onTestSuccess(ITestResult result) {
        logger.info("{} is Passed!!!", result.getMethod().getMethodName());
    }

    /**
     * Invoked when an individual test fails.
     *
     * @param result the test result containing information about the test method
     */
    @Override
    public void onTestFailure(ITestResult result) {
        logger.error("{} is Failed!!!", result.getMethod().getMethodName());
    }

    /**
     * Invoked when an individual test is skipped.
     *
     * @param result the test result containing information about the test method
     */
    @Override
    public void onTestSkipped(ITestResult result) {
        logger.info("{} is Skipped!!!", result.getMethod().getMethodName());
    }
}