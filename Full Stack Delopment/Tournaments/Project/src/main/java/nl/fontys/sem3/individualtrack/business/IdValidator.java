package nl.fontys.sem3.individualtrack.business;

import nl.fontys.sem3.individualtrack.business.exception.InvalidException;

public interface IdValidator<T> {
    void ValidateId(Long targetId) throws InvalidException;
}
