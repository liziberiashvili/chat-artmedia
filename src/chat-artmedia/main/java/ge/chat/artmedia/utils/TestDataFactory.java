package ge.chat.artmedia.utils;

import net.datafaker.Faker;

public class TestDataFactory {
    static Faker faker = new Faker();

    public static String randomName(){
        return faker.name().firstName();
    }

    public static String randomEmail() {
        return faker.internet().emailAddress();
    }

    public static String randomMessage() {
        return faker.lorem().sentence();
    }

    public static String randomFullName() {
        return faker.name().fullName();
    }
}
