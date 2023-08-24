package by.travel.touristagency.repository;

import by.travel.touristagency.dto.VoucherFilter;
import by.travel.touristagency.entity.Voucher;
import by.travel.touristagency.entity.enums.Country;
import by.travel.touristagency.entity.enums.Transport;
import by.travel.touristagency.entity.enums.Type;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;

import java.util.List;

import static by.travel.touristagency.entity.QVoucher.voucher;

public class VoucherRepository extends BaseRepository<Long, Voucher> {

    public VoucherRepository(EntityManager entityManager) {
        super(Voucher.class, entityManager);
    }

    public List<Voucher> getFilteredVoucher(VoucherFilter filter) {
        Predicate predicate = CustomPredicate.builder()
                .add(filter.getName(), voucher.name::eq)
                .add(filter.getPriceFrom(), voucher.price::goe)
                .add(filter.getPriceTo(), voucher.price::loe)
                .add(Type.valueOf(filter.getType().name()), voucher.type::eq)
                .add(Country.valueOf(filter.getCountry().name()), voucher.info.country::eq)
                .add(Transport.valueOf(filter.getTransport().name()), voucher.info.transport::eq)
                .add(filter.isFood(), voucher.info.isFoodInclude::eq)
                .buildAnd();

        return new JPAQuery<Voucher>(getEntityManager())
                .select(voucher)
                .from(voucher)
                .where(predicate)
                .fetch();
    }
}
