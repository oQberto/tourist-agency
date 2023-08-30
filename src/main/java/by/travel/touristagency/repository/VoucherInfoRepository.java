package by.travel.touristagency.repository;

import by.travel.touristagency.entity.VoucherInfo;
import org.hibernate.Session;

public class VoucherInfoRepository extends BaseRepository<Long, VoucherInfo> {

    public VoucherInfoRepository(Session session) {
        super(VoucherInfo.class, session);
    }
}
