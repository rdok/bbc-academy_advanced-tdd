package dev.rdok;

import org.junit.Assert;
import org.junit.Test;

public class UserTest
{
    @Test
    public void should_have_name()
    {
        User user = new User("Neil Armstrong");

        Assert.assertEquals("Neil Armstrong", user.name());
    }
}
