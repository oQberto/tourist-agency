package by.travel.touristagency.controller;

import by.travel.touristagency.dto.UserDto;
import by.travel.touristagency.entity.Booking;
import by.travel.touristagency.entity.Voucher;
import by.travel.touristagency.service.BookingService;
import by.travel.touristagency.util.JSPHelper;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/user-bookings")
public class ProfileBookingServlet extends HttpServlet {
    private final BookingService bookingService = BookingService.getInstance();
    private List<Voucher> vouchersByUserId;
    private Long userId;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDto user = (UserDto) req.getSession().getAttribute("user");
        userId = user.getId();
        vouchersByUserId = bookingService.getVouchersByUserId(userId);

        req.getSession().setAttribute("user_vouchers", vouchersByUserId);
        req.getRequestDispatcher(JSPHelper.get("user-bookings"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long voucherId = Long.valueOf(req.getParameter("voucherId"));
        Booking booking = bookingService.getBookingByVoucherIdAndUserId(voucherId, userId)
                .orElseThrow(() -> new EntityNotFoundException("Booking didn't find"));

        bookingService.deleteBookingFromUserProfile(booking.getId());
        vouchersByUserId = bookingService.getVouchersByUserId(userId);

        req.getSession().setAttribute("user_vouchers", vouchersByUserId);

        resp.sendRedirect("/user-bookings");
    }
}
