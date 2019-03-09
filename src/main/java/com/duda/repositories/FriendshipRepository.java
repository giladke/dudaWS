package com.duda.repositories;

import com.duda.model.Friendship;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface FriendshipRepository extends CrudRepository<Friendship, Long> {
    Collection<Friendship> findByPupilIdOrOtherPupilId(long pupilId, long otherPupilId);
}
