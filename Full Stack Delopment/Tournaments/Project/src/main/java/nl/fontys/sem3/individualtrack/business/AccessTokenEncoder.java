package nl.fontys.sem3.individualtrack.business;

import nl.fontys.sem3.individualtrack.domain.AccessToken;

public interface AccessTokenEncoder {
    String encode(AccessToken accessToken);
}
