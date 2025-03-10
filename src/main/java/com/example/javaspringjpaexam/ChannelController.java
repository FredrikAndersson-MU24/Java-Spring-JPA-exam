package com.example.javaspringjpaexam;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/channels")
public class ChannelController {

    private ChannelService channelService;

    public ChannelController(ChannelService channelService) {
        this.channelService = channelService;
    }



    @PostMapping
    public ResponseEntity<Channel> createChannel(@RequestBody @Valid Channel newChannel) {
        return ResponseEntity.ok(channelService.createChannel(newChannel));
    }

}
