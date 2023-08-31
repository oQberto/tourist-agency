package by.travel.touristagency.controller;

import by.travel.touristagency.dto.ProfileDto;
import by.travel.touristagency.dto.UserDto;
import by.travel.touristagency.service.ProfileService;
import by.travel.touristagency.service.UserService;
import by.travel.touristagency.util.HibernateSessionFactoryUtil;
import by.travel.touristagency.util.JSPHelper;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.SessionFactory;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
    private final ProfileService profileService = ProfileService.getInstance();
    private final UserService userService = UserService.getInstance();
    private SessionFactory sessionFactory;
    private Long userId;
    private Long profileId;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        sessionFactory = HibernateSessionFactoryUtil.getInstance().buildSessionFactory();
    }

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

        userService.updateUser(updatedUserData, sessionFactory);
        profileService.updateProfile(updatedProfileData, profileId, sessionFactory);

        resp.sendRedirect("/profile");
    }
}
