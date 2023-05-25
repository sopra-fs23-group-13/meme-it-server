package ch.uzh.ifi.hase.soprafs23.job;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.lang.reflect.Field;
import java.time.Instant;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;

import ch.uzh.ifi.hase.soprafs23.EntityMother;
import ch.uzh.ifi.hase.soprafs23.entity.Game;
import ch.uzh.ifi.hase.soprafs23.entity.GameState;

/**
 * Tests for the GameJob class
 */
class GameJobTest {

    @Mock
    private SessionFactory sessionFactory;

    @Mock
    private Session session;

    @Mock
    private Transaction transaction;

    @Mock
    private Logger logger;

    private GameJob gameJob;

    private static final String FORMATTED_DATE = "2023-04-01T01:00:00.000+00:00";
    private static final Date DATE = Date.from(Instant.parse(FORMATTED_DATE));

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        gameJob = new GameJob(sessionFactory);
        when(sessionFactory.openSession()).thenReturn(session);
        when(session.beginTransaction()).thenReturn(transaction);
        when(logger.isDebugEnabled()).thenReturn(true);
    }

    @Test
    void testRun_GameNotFound() {
        // Arrange
        String gameId = "invalid-game-id";
        when(session.get(Game.class, gameId)).thenReturn(null);

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> gameJob.run(gameId));
        verify(transaction, never()).commit();
        verify(session, never()).save(any());
        verify(session, never()).delete(any());
    }

    @Test
    void testRun_GameFinished_1round() {
        // Arrange
        String gameId = "valid-game-id";
        Game game = EntityMother.buildFullGame(DATE, "templateId", 1);

        try {
            Field idField = Game.class.getDeclaredField("id");
            idField.setAccessible(true); // Make the private field accessible
            idField.set(game, gameId);
        } catch (Exception e) {
            fail("Could not set id field");
        }

        assertEquals(GameState.CREATION, game.getState());

        when(session.get(Game.class, gameId)).thenReturn(game);

        // Act
        gameJob.run(gameId);

        // Assert
        assertEquals(GameState.GAME_RESULTS, game.getState());
        verify(transaction, times(13)).commit();
        verify(session, times(5)).save(game);
        // verify(session).delete(game);
        verify(session).close();
    }

    @Test
    void testRun_GameFinished_2rounds() {
        // Arrange
        String gameId = "valid-game-id";
        Game game = EntityMother.buildFullGame(DATE, "templateId", 2);

        try {
            Field idField = Game.class.getDeclaredField("id");
            idField.setAccessible(true); // Make the private field accessible
            idField.set(game, gameId);
        } catch (Exception e) {
            fail("Could not set id field");
        }

        assertEquals(GameState.CREATION, game.getState());

        when(session.get(Game.class, gameId)).thenReturn(game);

        // Act
        gameJob.run(gameId);

        // Assert
        assertEquals(GameState.GAME_RESULTS, game.getState());
        verify(transaction, times(5)).commit();
        verify(session, times(5)).save(game);
        // verify(session).delete(game);
        verify(session).close();
    }
}
