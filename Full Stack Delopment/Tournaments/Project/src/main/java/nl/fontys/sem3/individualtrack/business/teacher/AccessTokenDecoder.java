package nl.fontys.sem3.individualtrack.business.teacher;

import nl.fontys.sem3.individualtrack.domain.AccessToken;

public interface AccessTokenDecoder {
    AccessToken decode(String accessTokenEncoded);
}
