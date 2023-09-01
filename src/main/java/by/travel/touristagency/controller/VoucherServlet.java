package by.travel.touristagency.controller;

import by.travel.touristagency.service.VoucherService;
import by.travel.touristagency.util.JSPHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/vouchers")
public class VoucherServlet extends HttpServlet {
    private final VoucherService voucherService = VoucherService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long companyId = Long.valueOf(req.getParameter("companyId"));
        req.setAttribute("vouchers", voucherService.getVouchersByCompanyId(companyId));

        req.getRequestDispatcher(JSPHelper.get("vouchers"))
                .forward(req, resp);
    }
}
