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

        logger.error("Feign error occurred. MethodKey: {}, Response: {}", methodKey, response);

        try {
            return switch (response.status()) {
                case 400 -> {
                    yield new BuytopiaException(ErrorType.DATA_NOT_FOUND, logger::error);
                }
                case 500 -> {
                    yield new BuytopiaException(ErrorType.INTERNAL_SERVER_ERROR_EXCEPTION, logger::error);
                }
                default -> {
                    yield new Exception("Unexpected error: " + response.reason());
                }
            };
        } catch (Exception e) {
            logger.error("Exception occurred during Feign client error decoding", e);
            throw e;  // 예외를 다시 던져서 호출 측에서 처리할 수 있도록 함
        }
    }
}
