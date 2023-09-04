package by.travel.touristagency.repository;

import by.travel.touristagency.dto.VoucherFilter;
import by.travel.touristagency.entity.Voucher;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberTemplate;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.Generated;
import org.hibernate.Session;
import org.hibernate.query.SortDirection;

import java.util.List;

import static by.travel.touristagency.entity.QVoucher.voucher;

public class VoucherRepository extends BaseRepository<Long, Voucher> {

    @Generated
    public VoucherRepository(Session session) {
        super(Voucher.class, session);
    }

    public List<Voucher> getFilteredVoucher(VoucherFilter filter) {
        Predicate predicate = buildPredicate(filter);

        return new JPAQuery<Voucher>(getSession())
                .select(voucher)
                .from(voucher)
                .where(predicate)
                .fetch();
    }

    public List<Voucher> getVouchersByCompanyId(Long companyId) {
        return new JPAQuery<Voucher>(getSession())
                .select(voucher)
                .from(voucher)
                .where(voucher.company.id.eq(companyId))
                .fetch();
    }

    public List<Voucher> getVouchersByAmountOfDays(Integer amountOfDays) {
        NumberTemplate<Integer> expression = Expressions.numberTemplate(
                Integer.class,
                "timestampdiff(day, {0}, {1})",
                voucher.info.startAt,
                voucher.info.endAt
        );

        return new JPAQuery<Voucher>(getSession())
                .select(voucher)
                .from(voucher)
                .where(expression.goe(amountOfDays))
                .fetch();
    }

    public List<Voucher> getVouchersSortedByPrice(SortDirection sortDirection, int limit) {
        JPAQuery<Voucher> query = new JPAQuery<>(getSession())
                .select(voucher)
                .from(voucher);

        if (sortDirection == SortDirection.ASCENDING) {
            query.orderBy(voucher.price.asc());
        } else if (sortDirection == SortDirection.DESCENDING) {
            query.orderBy(voucher.price.desc());
        }
        return query
                .limit(limit)
                .fetch();
    }

    private static Predicate buildPredicate(VoucherFilter filter) {
        return CustomPredicate.builder()
                .add(filter.getName(), voucher.name::eq)
                .add(filter.getPriceFrom(), voucher.price::goe)
                .add(filter.getPriceTo(), voucher.price::loe)
                .add(filter.getType(), voucher.type::eq)
                .add(filter.getCountry(), voucher.info.country::eq)
                .add(filter.getTransport(), voucher.info.transport::eq)
                .add(filter.getFood(), voucher.info.food::eq)
                .buildAnd();
    }
}
