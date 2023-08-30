package by.travel.touristagency.servlet;

import by.travel.touristagency.dto.CreateUserDto;
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

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
    private SessionFactory sessionFactory;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        sessionFactory = HibernateSessionFactoryUtil.getInstance().buildSessionFactory();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(JSPHelper.get("profile"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CreateUserDto updatedUserData = CreateUserDto.builder()
                .password(req.getParameter("password"))
                .username(req.getParameter("username"))
                .email(req.getParameter("email"))
                .build();
    }
}
