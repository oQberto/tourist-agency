package by.travel.touristagency.repository;

import by.travel.touristagency.entity.Voucher;
import jakarta.persistence.EntityManager;

public class VoucherRepository extends BaseRepository<Long, Voucher> {

    public VoucherRepository(EntityManager entityManager) {
        super(Voucher.class, entityManager);
    }
}
