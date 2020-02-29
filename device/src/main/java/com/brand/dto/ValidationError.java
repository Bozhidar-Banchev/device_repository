/**
 *
 */
package com.brand.dto;

import java.io.Serializable;

/**
 * @author Bozhidar
 *
 */
public class ValidationError implements Serializable {

    private static final long serialVersionUID = 5163473185709770312L;

    private String path;
    private String message;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
