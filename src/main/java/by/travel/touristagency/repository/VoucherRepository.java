package by.travel.touristagency.repository;

import by.travel.touristagency.dto.VoucherFilter;
import by.travel.touristagency.entity.Voucher;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberTemplate;
import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;

import java.util.List;

import static by.travel.touristagency.entity.QVoucher.voucher;

public class VoucherRepository extends BaseRepository<Long, Voucher> {

    public VoucherRepository(EntityManager entityManager) {
        super(Voucher.class, entityManager);
    }

    public List<Voucher> getFilteredVoucher(VoucherFilter filter) {
        Predicate predicate = buildPredicate(filter);

        return new JPAQuery<Voucher>(getEntityManager())
                .select(voucher)
                .from(voucher)
                .where(predicate)
                .fetch();
    }

    public List<Voucher> getVoucherByAmountOfDays(Integer amountOfDays) {
        NumberTemplate<Integer> expression = Expressions.numberTemplate(
                Integer.class,
                "function('dateDiff', {0}, {1}",
                voucher.info.endOn,
                voucher.info.startOn
        );

        return new JPAQuery<Voucher>(getEntityManager())
                .select(voucher)
                .from(voucher)
                .where(expression.goe(amountOfDays))
                .fetch();
    }

    public List<Voucher> getVouchersSortedByPriceASC() {
        return new JPAQuery<Voucher>(getEntityManager())
                .select(voucher)
                .from(voucher)
                .orderBy(voucher.price.asc())
                .fetch();
    }

    public List<Voucher> getVouchersSortedByPriceDESC() {

        return new JPAQuery<Voucher>(getEntityManager())
                .select(voucher)
                .from(voucher)
                .orderBy(voucher.price.desc())
                .fetch();
    }

    public List<Voucher> getVouchersSortedByFoodASC() {
        return new JPAQuery<Voucher>(getEntityManager())
                .select(voucher)
                .from(voucher)
                .orderBy(voucher.info.isFoodInclude.asc())
                .fetch();
    }

    public List<Voucher> getVouchersSortedByFoodDESC() {

        return new JPAQuery<Voucher>(getEntityManager())
                .select(voucher)
                .from(voucher)
                .orderBy(voucher.info.isFoodInclude.desc())
                .fetch();
    }

    private static Predicate buildPredicate(VoucherFilter filter) {
        return CustomPredicate.builder()
                .add(filter.getName(), voucher.name::eq)
                .add(filter.getPriceFrom(), voucher.price::goe)
                .add(filter.getPriceTo(), voucher.price::loe)
                .add(filter.getType(), voucher.type::eq)
                .add((filter.getCountry()), voucher.info.country::eq)
                .add((filter.getTransport()), voucher.info.transport::eq)
                .add(filter.getFood(), voucher.info.isFoodInclude::eq)
                .buildAnd();
    }
}
