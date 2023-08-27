package by.travel.touristagency.servlet;

import by.travel.touristagency.service.VoucherService;
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

@WebServlet("/vouchers")
public class VoucherServlet extends HttpServlet {
    private final VoucherService voucherService = VoucherService.getInstance();
    private SessionFactory sessionFactory;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        sessionFactory = HibernateSessionFactoryUtil.getInstance().buildSessionFactory();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long companyId = Long.valueOf(req.getParameter("companyId"));
        req.setAttribute("vouchers", voucherService.getVouchersByCompanyId(companyId, sessionFactory));

        req.getRequestDispatcher(JSPHelper.get("vouchers"))
                .forward(req, resp);
    }
}
