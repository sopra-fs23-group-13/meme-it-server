package ch.uzh.ifi.hase.soprafs23.service;

// import java.time.Duration;
// import java.time.Instant;

// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.stereotype.Service;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;

// import com.auth0.jwt.JWT;
// import com.auth0.jwt.JWTVerifier;
// import com.auth0.jwt.algorithms.Algorithm;
// import com.auth0.jwt.exceptions.JWTVerificationException;

// import ch.uzh.ifi.hase.soprafs23.entity.User;

/**
 * JwtToken Service
 * This class is the "worker" and responsible for all functionality related to
 * the jwt token, It creates and verifies the token.
 */
// @Service
// public class JwtTokenService {
// private final Logger log = LoggerFactory.getLogger(JwtTokenService.class);

// private static final Duration JWT_TOKEN_VALIDITY = Duration.ofMinutes(20);

// private final Algorithm hmac512;
// private final JWTVerifier verifier;

// @Value("${jwt.secret}")
// final String secret = "";

// public JwtTokenService() {
// this.hmac512 = Algorithm.HMAC512(secret);
// this.verifier = JWT.require(this.hmac512).build();
// }

// public String generateToken(final User userDetails) {
// final Instant now = Instant.now();
// return JWT.create()
// .withSubject(userDetails.getUuid())
// .withIssuer("https://sopra-fs-21-group-13.google.com")
// .withIssuedAt(now)
// .withExpiresAt(now.plusMillis(JWT_TOKEN_VALIDITY.toMillis()))
// .sign(this.hmac512);
// }

// public String validateTokenAndGetUUID(final String token) {
// try {
// return verifier.verify(token).getSubject();
// } catch (final JWTVerificationException verificationEx) {
// log.warn("token invalid: {}", verificationEx.getMessage());
// return null;
// }
// }

// }
