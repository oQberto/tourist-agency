package by.travel.touristagency.controller;

import by.travel.touristagency.service.CompanyService;
import by.travel.touristagency.util.HibernateSessionFactoryUtil;
import by.travel.touristagency.util.JSPHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/travel_by")
public class MainServlet extends HttpServlet {
    private final CompanyService companyService = new CompanyService(
            HibernateSessionFactoryUtil.getInstance().buildSessionFactory()
    );

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("companies", companyService.getAllCompanies());
        req.getRequestDispatcher(JSPHelper.get("home_page"))
                .forward(req, resp);
    }
}