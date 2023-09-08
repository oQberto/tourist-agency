package by.travel.touristagency.controller;

import by.travel.touristagency.dto.BookingDto;
import by.travel.touristagency.dto.UserDto;
import by.travel.touristagency.mapper.BookingMapper;
import by.travel.touristagency.mapper.UserDtoMapper;
import by.travel.touristagency.mapper.UserMapper;
import by.travel.touristagency.service.BookingService;
import by.travel.touristagency.service.UserService;
import by.travel.touristagency.service.VoucherService;
import by.travel.touristagency.util.HibernateSessionFactoryUtil;
import by.travel.touristagency.util.JSPHelper;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/booking")
public class BookingServlet extends HttpServlet {
    private final BookingService bookingService = BookingService.getInstance();
    private final BookingMapper bookingMapper = BookingMapper.getInstance();
    private final VoucherService voucherService = new VoucherService(
            HibernateSessionFactoryUtil.getInstance().buildSessionFactory()
    );
    private final UserService userService = new UserService(
            HibernateSessionFactoryUtil.getInstance().buildSessionFactory(),
            UserDtoMapper.getInstance(),
            UserMapper.getInstance()
    );

    private Integer numberOfPersons;
    private Long voucherId;
    private UserDto user;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        user = (UserDto) req.getSession().getAttribute("user");
        voucherId = Long.valueOf(req.getParameter("voucherId"));

        req.getRequestDispatcher(JSPHelper.get("booking"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        numberOfPersons = Integer.valueOf(req.getParameter("persons"));

        if (user == null) {
            resp.sendRedirect("/login");
        } else {
            BookingDto bookingDto = buildBookingDto();
            bookingService.createBooking(bookingMapper.map(bookingDto));
        }
    }

    private BookingDto buildBookingDto() {

        return BookingDto.builder()
                .user(userService.getUserById(user.getId())
                        .orElseThrow(() -> new EntityNotFoundException("User didn't find"))
                )
                .voucher(voucherService.getVoucherById(voucherId)
                        .orElseThrow(() -> new EntityNotFoundException("Voucher didn't find"))
                )
                .numberOfPersons(numberOfPersons)
                .build();
    }
}
