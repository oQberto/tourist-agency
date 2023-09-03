package by.travel.touristagency.service;

import by.travel.touristagency.entity.Voucher;
import by.travel.touristagency.repository.VoucherRepository;
import by.travel.touristagency.util.HibernateSessionFactoryUtil;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class VoucherService {
    private static volatile VoucherService instance;
    private final SessionFactory sessionFactory = HibernateSessionFactoryUtil.getInstance().buildSessionFactory();
    private VoucherRepository voucherRepository;

    public List<Voucher> getVouchersByCompanyId(Long id) {
        List<Voucher> vouchers;

        try (Session session = sessionFactory.openSession()) {
            voucherRepository = new VoucherRepository(session);
            session.beginTransaction();

            vouchers = voucherRepository.getVouchersByCompanyId(id);

            session.getTransaction().commit();
        }

        return vouchers;
    }

    public Optional<Voucher> getVoucherById(Long id) {
        Optional<Voucher> voucher;

        try (Session session = sessionFactory.openSession()) {
            voucherRepository = new VoucherRepository(session);
            session.beginTransaction();

            voucher = voucherRepository.findById(id);

            session.getTransaction().commit();
        }

        return voucher;
    }

    public static VoucherService getInstance() {
        VoucherService result = instance;
        if (result != null) {
            return result;
        }

        synchronized (VoucherService.class) {
            if (instance == null) {
                instance = new VoucherService();
            }
            return instance;
        }
    }
}
