package ch.uzh.ifi.hase.soprafs23.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ch.uzh.ifi.hase.soprafs23.entity.Game;

@Repository("gameRepository")
public interface GameRepository extends JpaRepository<Game, Long> {
    Optional<Game> findById(String id);
}
