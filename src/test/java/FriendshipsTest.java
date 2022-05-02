import org.assertj.core.api.Assert;
import org.example.Friendships;
import org.junit.jupiter.api.*;

//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.Matchers.*;

import static org.assertj.core.api.Assertions.*;


import java.util.Arrays;
import java.util.List;

public class FriendshipsTest {
    Friendships friendships;

    @BeforeAll
    public static void beforeAll() {
        // Este método é executado antes de todos os testes e apenas 1 vez
        System.out.println("BEFORE ALL TESTS...");
    }

    @BeforeEach
    public void before() {
        // Este método é executado a cada método de teste
        friendships = new Friendships();
        friendships.makeFriends("Joe", "Audrey");
        friendships.makeFriends("Joe", "Peter");
        friendships.makeFriends("Joe", "Michael");
        friendships.makeFriends("Joe", "Britney");
        friendships.makeFriends("Joe", "Paul");
    }

    @AfterAll
    public static void afterAll() {
        // Este método é executado quando todos os testes tiverem terminado
        System.out.println("ALL TESTS TERMINATED!");
    }

    @AfterEach
    public void after() {
        // Este método é executado quando cada teste for terminado
        System.out.println("A TEST TERMINATED!");
    }

    @Test
    public void alexDoesNotHaveFriends() {
        Assertions.assertTrue(
                friendships.getFriendList("Alex").isEmpty(), "Alex does not have friends"
        );
    }

    @Test
    public void joeHas5Friends() {
        // Usando JUnit puro
        Assertions.assertEquals(5, friendships.getFriendList("Joe").size(), "Joe has 5 friends");

        // Usando Hamcrest
//        assertThat(friendships.getFriendList("Joe"), hasSize(5));

        // Usando AssertJ : semelhante a Hamcrest, mas concatena metodos
        assertThat(friendships.getFriendList("Joe"))
                .hasSize(5)
                .containsOnly("Audrey", "Peter", "Michael", "Britney", "Paul");

    }

    @Test
    public void joeIsFriendWithEveryone() {
        List<String> friendOfJoe = Arrays.asList("Audrey", "Peter", "Michael", "Britney", "Paul");
        Assertions.assertTrue(friendships.getFriendList("Joe").containsAll(friendOfJoe));

        // Podemos usar mais expressividade usando Hamcrest evitando boilerplate
//        assertThat(
//                friendships.getFriendList("Joe"),
//                containsInAnyOrder("Audrey", "Peter", "Michael", "Britney", "Paul")
//        );
    }
}
