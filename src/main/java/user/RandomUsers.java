package user;

import com.github.javafaker.Faker;
import org.apache.commons.codec.digest.DigestUtils;
import java.time.ZoneId;
import java.util.Locale;

public class RandomUsers {
    private Faker faker;
    public RandomUsers(Locale locale) {
        faker = new Faker(locale);
    }

    public User generate()
    {
        User user = User.builder().username(faker.name().username())
                .password(DigestUtils.md2Hex(faker.internet().password()))
                .name(faker.name().fullName())
                .email(faker.internet().emailAddress())
                .gender(faker.options().option(User.Gender.MALE,User.Gender.FEMALE))
                .birthDate(faker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
                .enabled(faker.bool().bool())
                .build();
        return user;
    }
}
