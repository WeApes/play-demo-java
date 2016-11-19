package util;

import play.mvc.Controller;

/**
 * Created by lawrence on 16/6/11.
 */
public class Utils {
    private Utils() {}

    public static void addCORS() {
        Controller.response()
                .setHeader("Access-Control-Allow-Origin", "*");
        Controller.response()
                .setHeader("Access-Control-Allow-Methods", "ALL");
    }
}
