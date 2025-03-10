package com.example.javaspringjpaexam;

import jakarta.validation.Valid;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/channels")
public class ChannelController {

    private ChannelService channelService;

    public ChannelController(ChannelService channelService) {
        this.channelService = channelService;
    }

    @GetMapping
    public ResponseEntity<?> getAllChannels() {
        List<Channel> channels = channelService.getAllChannels();
        if (!channels.isEmpty()) {
            return ResponseEntity.ok(channels);
        } else return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping
    public ResponseEntity<Channel> createChannel(@RequestBody @Valid Channel newChannel) {
        return ResponseEntity.ok(channelService.createChannel(newChannel));
    }

    @DeleteMapping("/{id}")
    public void deleteChannelById(@PathVariable long id) throws BadRequestException {
        channelService.deleteChannelById(id);
    }

}
