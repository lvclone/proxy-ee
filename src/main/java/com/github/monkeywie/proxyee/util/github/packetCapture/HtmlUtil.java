package com.github.monkeywie.proxyee.util.github.packetCapture;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.HashMap;
import java.util.Map;

public class HtmlUtil {


    public static Object parseJSDatatoJson(String jsData, String key) {
        jsData = filterIllegalCharacters(jsData);
        ScriptEngine scriptEngine = new ScriptEngineManager().getEngineByName("JavaScript");
        try {
            scriptEngine.eval(jsData);
            return scriptEngine.get(key);
//            return JSONUtils.toJSONObject();
        } catch (ScriptException e) {
            System.err.println("ScriptException: " + e.getMessage());
        }
        return null;
    }

    public void _filter_object() {

    }

    static Map<String,String> illegalCharacters = new HashMap<String,String>(){{
        put("<", "");
        put("window.", "var ");
    }};

    public static String filterIllegalCharacters(String str) {
        for ( String o : illegalCharacters.keySet()) {
            str = str.replace(o, illegalCharacters.get(o));
        }
        return str;
    }
}
