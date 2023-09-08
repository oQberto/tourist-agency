package by.travel.touristagency.service;

import by.travel.touristagency.entity.Voucher;
import by.travel.touristagency.repository.VoucherRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class VoucherService {
    private final SessionFactory sessionFactory;
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
}
