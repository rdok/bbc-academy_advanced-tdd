package test.dev.rdok;

import dev.rdok.User;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest
{
    @Test
    public void should_have_name()
    {
        User user = new User("Neil Armstrong");

        assertEquals("Neil Armstrong", user.name());
    }

}
