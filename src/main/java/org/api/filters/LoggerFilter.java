package org.api.filters;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * LoggerFilter is a custom implementation of the Rest Assured Filter interface.
 * It logs HTTP request and response details using Log4j2.
 */
public class LoggerFilter implements Filter {

    // Logger instance for logging request and response details
    private static final Logger logger = LogManager.getLogger(LoggerFilter.class);

    /**
     * Filters the HTTP request and response.
     * Logs the request details before sending it and the response details after receiving it.
     *
     * @param requestSpec  the HTTP request specification
     * @param responseSpec the HTTP response specification
     * @param ctx          the filter context for processing the request and response
     * @return the HTTP response
     */
    @Override
    public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec, FilterContext ctx) {
        logRequest(requestSpec); // Log the request details
        Response response = ctx.next(requestSpec, responseSpec); // Proceed with the request
        logResponse(response); // Log the response details
        return response;
    }

    /**
     * Logs the details of the HTTP request.
     *
     * @param requestSpec the HTTP request specification
     */
    public void logRequest(FilterableRequestSpecification requestSpec) {
        logger.info("Request Method:{}", requestSpec.getMethod());
        logger.info("Request URI:{}", requestSpec.getURI());
        logger.info("Request Header:{}", requestSpec.getHeaders());
        logger.info("Request PayLoad:{}", requestSpec.getBody() != null ? requestSpec.getBody().toString() : "No Body");
    }

    /**
     * Logs the details of the HTTP response.
     *
     * @param response the HTTP response
     */
    public void logResponse(Response response) {
        logger.info("STATUS CODE:{}", response.getStatusCode());
        logger.info("Response Header :{}", response.headers());
        logger.info("Response Body:{}", response.getBody().prettyPrint());
        logger.info("Response Time:{}ms", response.getTime());
        logger.info("Response Status Line:{}", response.getStatusLine());
        logger.info("Response Content Type:{}", response.getContentType());
    }
}