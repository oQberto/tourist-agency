package by.travel.touristagency.service;

import by.travel.touristagency.entity.Voucher;
import by.travel.touristagency.repository.VoucherRepository;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class VoucherService {
    private static final VoucherService INSTANCE = new VoucherService();
    private final VoucherRepository voucherRepository = new VoucherRepository(null);

    public List<Voucher> getVouchersByCompanyId(Long id, SessionFactory sessionFactory) {
        List<Voucher> vouchers;

        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            voucherRepository.setEntityManager(session);

            vouchers = voucherRepository.getVouchersByCompanyId(id);

            session.getTransaction().commit();
        }

        return vouchers;
    }

    public static VoucherService getInstance() {
        return INSTANCE;
    }
}
