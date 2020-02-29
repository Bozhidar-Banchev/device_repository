/**
 *
 */
package com.brand.dto;

import java.io.Serializable;

import org.springframework.http.HttpStatus;

/**
 * @author Bozhidar
 *
 */
public class CustomResponse implements Serializable {

    private static final long serialVersionUID = -5644338445289000670L;

    private HttpStatus code;

    private String message;

    /**
     * default constructor
     */
    public CustomResponse() {

    }

    /**
     * @param code
     * @param message
     */
    public CustomResponse(HttpStatus code, String message) {
        super();
        this.code = code;
        this.message = message;
    }

    public HttpStatus getCode() {
        return code;
    }

    public void setCode(HttpStatus code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
