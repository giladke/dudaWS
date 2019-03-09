package com.duda.services.impl;

import com.duda.model.Friendship;
import com.duda.model.Pupil;
import com.duda.repositories.FriendshipRepository;
import com.duda.repositories.PupilRepository;
import com.duda.services.FriendshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class FriendshipServiceImpl implements FriendshipService {

    private FriendshipRepository friendshipRepository;
    private PupilRepository pupilRepository;

    @Autowired
    public FriendshipServiceImpl(PupilRepository pupilRepository, FriendshipRepository friendshipRepository) {
        this.pupilRepository = pupilRepository;
        this.friendshipRepository = friendshipRepository;
    }

    @Override
    public void addFriendShip(long pupilId, long friendId) {
        Pupil pupil = getPupil(pupilId);
        Pupil friend = getPupil(friendId);
        addFriend(pupil, friend);
        addFriend(friend, pupil);

        Friendship friendship = new Friendship(pupilId, friendId);
        friendshipRepository.save(friendship);
    }

    private Pupil getPupil(long pupilId) {  //does not handle bad id
        return pupilRepository.findById(pupilId).get();
    }

    public Collection<Long> getFriendsIds(long pupilId){
        Predicate<Long> isNotMe = id -> id != pupilId;

        return getPupil(pupilId).getFriendsList().stream()
                .map(FriendshipServiceImpl::getParticipants)
                .flatMap(Collection::stream)
                .filter(isNotMe)
                .collect(Collectors.toSet());
    }

    private static Collection<Long> getParticipants(Friendship friendship){
        return Arrays.asList(friendship.getPupilId(), friendship.getOtherPupilId());
    }

    private void addFriend(Pupil pupil, Pupil friend){
        pupil.addFriend(friend);
        pupilRepository.save(pupil);
    }
}
