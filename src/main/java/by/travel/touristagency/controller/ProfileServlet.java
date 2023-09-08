package by.travel.touristagency.controller;

import by.travel.touristagency.dto.ProfileDto;
import by.travel.touristagency.dto.UserDto;
import by.travel.touristagency.mapper.UserDtoMapper;
import by.travel.touristagency.mapper.UserMapper;
import by.travel.touristagency.service.ProfileService;
import by.travel.touristagency.service.UserService;
import by.travel.touristagency.util.HibernateSessionFactoryUtil;
import by.travel.touristagency.util.JSPHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
    private final ProfileService profileService = new ProfileService(
            HibernateSessionFactoryUtil.getInstance().buildSessionFactory()
    );
    private final UserService userService = new UserService(
            HibernateSessionFactoryUtil.getInstance().buildSessionFactory(),
            UserDtoMapper.getInstance(),
            UserMapper.getInstance()
    );

    private Long userId;
    private Long profileId;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        userId = (Long) req.getSession().getAttribute("userId");
        profileId = (Long) req.getSession().getAttribute("profileId");

        req.getRequestDispatcher(JSPHelper.get("profile"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        UserDto updatedUserData = UserDto.builder()
                .id(userId)
                .password(req.getParameter("password"))
                .username(req.getParameter("username"))
                .email(req.getParameter("email"))
                .build();

        ProfileDto updatedProfileData = ProfileDto.builder()
                .firstName(req.getParameter("firstName"))
                .lastName(req.getParameter("lastName"))
                .birthday(LocalDate.parse(req.getParameter("birthday")))
                .build();

        userService.updateUser(updatedUserData);
        profileService.updateProfile(updatedProfileData, profileId);

        req.getSession().setAttribute("user", updatedUserData);
        req.getSession().setAttribute("profile", updatedProfileData);

        resp.sendRedirect("/profile");
    }
}
