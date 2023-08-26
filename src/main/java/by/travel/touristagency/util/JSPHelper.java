package by.travel.touristagency.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class JSPHelper {
    private static final String JSP_FORMAT = "WEB-INF/template/%s.jsp";

    public static String get(String jspPage) {
        return String.format(JSP_FORMAT, jspPage);
    }
}
