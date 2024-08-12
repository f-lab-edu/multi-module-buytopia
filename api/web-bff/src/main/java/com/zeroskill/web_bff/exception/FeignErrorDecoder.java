package com.zeroskill.web_bff.exception;

import com.zeroskill.common.exception.BuytopiaException;
import com.zeroskill.common.exception.ErrorType;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FeignErrorDecoder implements ErrorDecoder {
    private static final Logger logger = LogManager.getLogger(FeignErrorDecoder.class);

    @Override
    public Exception decode(String methodKey, Response response) {

        return switch (response.status()) {
            case 400 -> new BuytopiaException(ErrorType.DATA_NOT_FOUND, logger::error);
            case 500 -> new BuytopiaException(ErrorType.INTERNAL_SERVER_ERROR_EXCEPTION, logger::error);
            default -> new Exception(response.reason());
        };
    }
}
