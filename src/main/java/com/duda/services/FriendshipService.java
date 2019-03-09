package com.duda.services;

import java.util.Collection;

public interface FriendshipService {
    void addFriendShip(long pupilId, long friendId);
    Collection<Long> getFriendsIds(long pupilId);
}
