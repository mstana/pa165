package cz.muni.fi.pa165.bookingmanager.controllers;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: Ondřej Pavelka <pavelka@cesnet.cz>
 */
public class AuthorizationHelper {
    public static final String ROLE_USER = "ROLE_USER";
    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String ROLE_REST = "ROLE_REST";

    public static String getLayoutUrlPrefix(HttpServletRequest request)
    {
        return request.isUserInRole(ROLE_ADMIN) ? "admin/" : "";
    }
}
