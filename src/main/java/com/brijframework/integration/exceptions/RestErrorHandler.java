package com.brijframework.integration.exceptions;

import java.io.IOException;

import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResponseErrorHandler;

@Component
public class RestErrorHandler implements ResponseErrorHandler {

    @Override
    public boolean hasError(ClientHttpResponse httpResponse) throws IOException {
        return httpResponse.getStatusCode().is5xxServerError() || 
            httpResponse.getStatusCode().is4xxClientError();
    }

    @Override
    public void handleError(ClientHttpResponse httpResponse) throws IOException {
        if (httpResponse.getStatusCode().is5xxServerError()) {
            throw new HttpClientErrorException(httpResponse.getStatusCode(), httpResponse.getStatusText());
        } else if (httpResponse.getStatusCode().is4xxClientError()) {
            throw new HttpClientErrorException(httpResponse.getStatusCode(), httpResponse.getStatusText());
        }
    }
}