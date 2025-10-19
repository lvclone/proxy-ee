package com.github.monkeywie.proxyee.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class KKResult {

    private Object data;

    private Integer code;

    private String message;

    public static KKResult success(Object data) {
        return new KKResult(data, 200, "ok");
    }

    public static KKResult fail(String msg) {
        return new KKResult(null, 400, msg);
    }

}
