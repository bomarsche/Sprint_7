import org.apache.commons.lang3.RandomStringUtils;

public class CourierGenerator {
    public static Courier getRandomFullData(){
        final String login = RandomStringUtils.randomAlphabetic(7);
        final String password = RandomStringUtils.randomAlphanumeric(7);
        final String firstName = RandomStringUtils.randomAlphanumeric(7);
        return new Courier(login, password, firstName);
    }

    public static Courier getRandomWithoutLogin(){
        final String password = RandomStringUtils.randomAlphanumeric(7);
        final String firstName = RandomStringUtils.randomAlphanumeric(7);
        return new Courier(null, password, firstName);
    }

    public static Courier getRandomWithoutPassword(){
        final String login = RandomStringUtils.randomAlphanumeric(7);
        final String firstName = RandomStringUtils.randomAlphanumeric(7);
        return new Courier(login, null, firstName);
    }

    public static Courier getRandomEmpty(){
        return new Courier(null, null, null);
    }

}
