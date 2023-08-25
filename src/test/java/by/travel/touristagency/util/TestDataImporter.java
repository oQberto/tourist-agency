package by.travel.touristagency.util;

import by.travel.touristagency.entity.*;
import by.travel.touristagency.entity.enums.Country;
import by.travel.touristagency.entity.enums.Transport;
import by.travel.touristagency.entity.enums.Type;
import lombok.experimental.UtilityClass;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.time.LocalDate;

@UtilityClass
public class TestDataImporter {

    public void importData(SessionFactory sessionFactory) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            Company spainTouristAgency = saveCompany(session, "Spain Tour");
            Company greeceTouristAgency = saveCompany(session, "Greece Tour");
            Company belarusTouristAgency = saveCompany(session, "Belarus Tour");
            Company brazilTouristAgency = saveCompany(session, "Brazil Tour");

            User user = saveUser(session, "UName", "123", "uname@gmail.com", spainTouristAgency);
            User user1 = saveUser(session, "UName1", "1231", "uname1@gmail.com", greeceTouristAgency);
            User user2 = saveUser(session, "UName2", "1232", "uname2@gmail.com", belarusTouristAgency);
            User user3 = saveUser(session, "UName3", "1233", "uname3@gmail.com", brazilTouristAgency);
            User user4 = saveUser(session, "UName4", "1234", "uname4@gmail.com", brazilTouristAgency);
            User user5 = saveUser(session, "UName5", "1235", "uname5@gmail.com", belarusTouristAgency);
            User user6 = saveUser(session, "UName6", "1236", "uname6@gmail.com", greeceTouristAgency);
            User user7 = saveUser(session, "UName7", "1237", "uname7@gmail.com", spainTouristAgency);
            User user8 = saveUser(session, "UName8", "1238", "uname8@gmail.com", brazilTouristAgency);
            User user9 = saveUser(session, "UName9", "1239", "uname9@gmail.com", spainTouristAgency);
            User user10 = saveUser(session, "UName10", "12310", "uname10@gmail.com", greeceTouristAgency);
            User user11 = saveUser(session, "UName11", "12311", "uname11@gmail.com", belarusTouristAgency);
            User user12 = saveUser(session, "UName12", "12312", "uname12@gmail.com", greeceTouristAgency);

            saveProfile(session, user, "FName", "LName", LocalDate.of(2000, 9, 15));
            saveProfile(session, user1, "FName1", "LName1", LocalDate.of(2001, 8, 24));
            saveProfile(session, user2, "FName2", "LName2", LocalDate.of(2002, 4, 13));
            saveProfile(session, user3, "FName3", "LName3", LocalDate.of(2003, 6, 4));
            saveProfile(session, user4, "FName4", "LName4", LocalDate.of(2003, 10, 8));
            saveProfile(session, user5, "FName5", "LName5", LocalDate.of(2002, 9, 16));
            saveProfile(session, user6, "FName6", "LName6", LocalDate.of(2001, 4, 25));
            saveProfile(session, user7, "FName7", "LName7", LocalDate.of(2004, 3, 14));
            saveProfile(session, user8, "FName8", "LName8", LocalDate.of(20001, 1, 5));
            saveProfile(session, user9, "FName9", "LName9", LocalDate.of(2002, 1, 21));
            saveProfile(session, user10, "FName10", "LNam10e", LocalDate.of(2004, 1, 21));
            saveProfile(session, user11, "FName11", "LName11", LocalDate.of(2001, 5, 29));
            saveProfile(session, user12, "FName12", "LName12", LocalDate.of(2000, 2, 28));

            Voucher voucher = saveVoucher(session, spainTouristAgency, user, "Voucher", 123.95, Type.THERAPY, null);
            Voucher voucher1 = saveVoucher(session, spainTouristAgency, user1, "Voucher1", 136.75, Type.SHOPPING, null);
            Voucher voucher2 = saveVoucher(session, spainTouristAgency, user4, "Voucher2", 143.59, Type.THERAPY, null);
            Voucher voucher3 = saveVoucher(session, spainTouristAgency, user2, "Voucher3", 153.29, Type.SHOPPING, null);
            Voucher voucher4 = saveVoucher(session, spainTouristAgency, user11, "Voucher4", 167.49, Type.EXCURSION, null);
            Voucher voucher5 = saveVoucher(session, spainTouristAgency, user10, "Voucher5", 173.46, Type.CRUISE, null);
            Voucher voucher6 = saveVoucher(session, spainTouristAgency, user5, "Voucher6", 183.15, Type.REST, null);
            Voucher voucher7 = saveVoucher(session, spainTouristAgency, user4, "Voucher7", 199.25, Type.THERAPY, null);
            Voucher voucher8 = saveVoucher(session, spainTouristAgency, user3, "Voucher8", 131.35, Type.EXCURSION, null);
            Voucher voucher9 = saveVoucher(session, spainTouristAgency, user12, "Voucher9", 143.55, Type.THERAPY, null);
            Voucher voucher10 = saveVoucher(session, spainTouristAgency, user, "Voucher10", 123.45, Type.REST, null);

            saveVoucherInfo(session, voucher, Country.PL, LocalDate.of(2023, 8, 22), LocalDate.of(2023, 8, 30), Transport.BUS, false);
            saveVoucherInfo(session, voucher1, Country.BY, LocalDate.of(2023, 9, 22), LocalDate.of(2023, 9, 30), Transport.PLANE, true);
            saveVoucherInfo(session, voucher2, Country.UK, LocalDate.of(2023, 9, 10), LocalDate.of(2023, 9, 16), Transport.CRUISE, false);
            saveVoucherInfo(session, voucher3, Country.USA, LocalDate.of(2023, 9, 22), LocalDate.of(2023, 10, 5), Transport.BUS, true);
            saveVoucherInfo(session, voucher4, Country.BY, LocalDate.of(2023, 10, 10), LocalDate.of(2023, 10, 20), Transport.BUS, false);
            saveVoucherInfo(session, voucher5, Country.UK, LocalDate.of(2023, 11, 1), LocalDate.of(2023, 11, 8), Transport.BUS, true);
            saveVoucherInfo(session, voucher6, Country.USA, LocalDate.of(2024, 1, 22), LocalDate.of(2024, 1, 30), Transport.PLANE, true);
            saveVoucherInfo(session, voucher7, Country.PL, LocalDate.of(2024, 2, 22), LocalDate.of(2024, 2, 28), Transport.BUS, true);
            saveVoucherInfo(session, voucher8, Country.PL, LocalDate.of(2024, 3, 10), LocalDate.of(2024, 3, 16), Transport.CRUISE, true);
            saveVoucherInfo(session, voucher9, Country.BY, LocalDate.of(2024, 4, 15), LocalDate.of(2024, 4, 30), Transport.BUS, true);
            saveVoucherInfo(session, voucher10, Country.USA, LocalDate.of(2024, 6, 20), LocalDate.of(2024, 6, 28), Transport.PLANE, true);

            session.getTransaction().commit();
        }
    }

    private Company saveCompany(Session session,
                                String name) {
        Company company = Company.builder()
                .name(name)
                .build();

        session.persist(company);

        return company;
    }

    private void saveProfile(Session session,
                             User user,
                             String firstName,
                             String lastName,
                             LocalDate birthday) {
        Profile profile = Profile.builder()
                .user(user)
                .firstName(firstName)
                .lastName(lastName)
                .birthday(birthday)
                .build();

        session.persist(profile);
    }

    private User saveUser(Session session,
                          String username,
                          String password,
                          String email,
                          Company company) {
        User user = User.builder()
                .username(username)
                .password(password)
                .email(email)
                .company(company)
                .build();

        session.persist(user);

        return user;
    }

    private Voucher saveVoucher(Session session,
                                Company company,
                                User user,
                                String name,
                                Double price,
                                Type type,
                                String description) {
        Voucher voucher = Voucher.builder()
                .company(company)
                .user(user)
                .name(name)
                .price(price)
                .type(type)
                .description(description)
                .build();

        session.persist(voucher);

        return voucher;
    }

    private void saveVoucherInfo(Session session,
                                 Voucher voucher,
                                 Country country,
                                 LocalDate startOn,
                                 LocalDate endOn,
                                 Transport transport,
                                 boolean food) {
        VoucherInfo voucherInfo = VoucherInfo.builder()
                .voucher(voucher)
                .country(country)
                .startOn(startOn)
                .endOn(endOn)
                .transport(transport)
                .isFoodInclude(food)
                .build();

        session.persist(voucherInfo);
    }
}
