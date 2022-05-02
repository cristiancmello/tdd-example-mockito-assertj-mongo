
import org.example.FriendsCollection;
import org.example.FriendshipsMongo;
import org.example.Person;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;


@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
public class FriendshipsMongoTestAssertJTest {
    @InjectMocks
    FriendshipsMongo friendships;

    @Mock
    FriendsCollection friends;

    @Test
    public void mockingWorksAsExpected() {
        Person joe = new Person("joe");
        doReturn(joe)
            .when(friends)
            .findByName("Joe");

        assertThat(friends.findByName("Joe"))
            .isEqualTo(joe);
    }

    @Test
    public void alexDoesNotHaveFriends() {
        assertThat(friendships.getFriendsList("Alex"))
            .isEmpty();
    }

    @Test
    public void joeHas5Friends() {
        List<String> expected = Arrays.asList("Audrey", "Peter", "Michael", "Britney", "Paul");

        Person joe = spy(new Person("Joe"));
        doReturn(joe)
            .when(friends)
            .findByName("Joe");

        doReturn(expected)
            .when(joe)
            .getFriends();

        assertThat(friendships.getFriendsList("Joe"))
            .hasSize(5)
            .containsOnly("Audrey", "Peter", "Michael", "Britney", "Paul");
    }
}
