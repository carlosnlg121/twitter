package com.totvs.financeiro.twitter.infra.exception.advisor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.totvs.financeiro.twitter.infra.exception.BusinessException;
import com.totvs.financeiro.twitter.infra.exception.ServiceError;
import com.totvs.financeiro.twitter.infra.exception.NotFoundException;

@ControllerAdvice
public class ServiceExceptionAdvisor {
	
	private final Logger LOG = LoggerFactory.getLogger(ServiceExceptionAdvisor.class);

    @ResponseBody
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ServiceError runtime(RuntimeException ex) {
        LOG.error("Requisiton ERROR:", ex);
        return ServiceError.Builder
                .instance("UNK-ERR", String.format("Erro de Requisição: %s", ex.getMessage()))
                    .build();
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ServiceError exception(Exception ex) {
        LOG.error("Server ERROR:", ex);
        return ServiceError.Builder
                .instance("UNK-ERR",  String.format("Erro interno: %s", ex.getMessage()))
                    .build();
    }

    @ResponseBody
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ServiceError> businessException(BusinessException ex) {
        LOG.error("EXCEPTION LAUNCHED:", ex);
        return new ResponseEntity<>(ex.getError(), HttpStatus.valueOf(ex.getHttpStatusCode()));
    }

    @ResponseBody
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ServiceError notFound(NotFoundException ex) {
        return ex.getError();
    }

}
