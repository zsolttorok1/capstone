package utilities;
import javax.servlet.http.*;

/**
 * Murach.util - CookieUtil
 */
public class CookieUtil {

    /**
     * Gets the cookie value from the cookie name
     * @param cookies contains the whole cookie array
     * @param cookieName the cookie name to get
     * @return the cookie value String
     */
    public static String getCookieValue(
            Cookie[] cookies, String cookieName) {
        
        String cookieValue = "";
        if (cookies != null) {
            for (Cookie cookie: cookies) {
                if (cookieName.equals(cookie.getName())) {
                    cookieValue = cookie.getValue();
                }
            }
        }
        return cookieValue;
    }
}
