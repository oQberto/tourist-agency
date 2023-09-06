package by.travel.touristagency.controller;

import by.travel.touristagency.dto.UserDto;
import by.travel.touristagency.mapper.UserDtoMapper;
import by.travel.touristagency.mapper.UserMapper;
import by.travel.touristagency.service.UserService;
import by.travel.touristagency.util.HibernateSessionFactoryUtil;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
    private final UserService userService = new UserService(
            HibernateSessionFactoryUtil.getInstance().buildSessionFactory(),
            UserDtoMapper.getInstance(),
            UserMapper.getInstance()
    );

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        UserDto userDto = UserDto.builder()
                .username(req.getParameter("userName"))
                .password(req.getParameter("password"))
                .email(req.getParameter("email"))
                .build();

        userService.createUser(userDto);
        resp.sendRedirect("/travel_by");
    }
}
