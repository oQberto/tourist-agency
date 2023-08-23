package by.travel.touristagency.repository;

import by.travel.touristagency.entity.VoucherInfo;
import jakarta.persistence.EntityManager;

public class VoucherInfoRepository extends BaseRepository<Long, VoucherInfo> {

    public VoucherInfoRepository(EntityManager entityManager) {
        super(VoucherInfo.class, entityManager);
    }
}
