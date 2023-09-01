package by.travel.touristagency.controller;

import by.travel.touristagency.dto.UserDto;
import by.travel.touristagency.entity.Profile;
import by.travel.touristagency.service.ProfileService;
import by.travel.touristagency.service.UserService;
import by.travel.touristagency.util.JSPHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private final UserService userService = UserService.getInstance();
    private final ProfileService profileService = ProfileService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(JSPHelper.get("login"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        userService.login(email, password)
                .ifPresentOrElse(
                        user -> onLoginSuccess(user, req, resp),
                        () -> onLoginFail(req, resp)
                );
    }

    @SneakyThrows
    private void onLoginFail(HttpServletRequest req, HttpServletResponse resp) {
        resp.sendRedirect("login?error&email=" + req.getParameter("email"));
    }

    @SneakyThrows
    private void onLoginSuccess(UserDto user, HttpServletRequest req, HttpServletResponse resp) {
        Profile profile = profileService.getByUserId(user.getId());

        req.getSession().setAttribute("user", user);
        req.getSession().setAttribute("profile", profile);
        req.getSession().setAttribute("userId", user.getId());
        req.getSession().setAttribute("profileId", profile.getId());

        resp.sendRedirect("/travel_by");
    }
}
