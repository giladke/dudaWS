package com.duda.controllers;

import com.duda.services.FriendshipService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FriendshipController implements BasicController {

    private FriendshipService friendshipService;

    public FriendshipController(FriendshipService friendshipService)
    {
        this.friendshipService = friendshipService;
    }

    @PostMapping("/setFriendShip/{id}/{secondId}")
    public void setFriendShip(@PathVariable long id, @PathVariable long secondId){
        friendshipService.addFriendShip(id, secondId);
    }


}
