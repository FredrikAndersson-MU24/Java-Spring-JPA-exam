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

    private final ChannelService channelService;

    public ChannelController(ChannelService channelService, UserService userService) {
        this.channelService = channelService;
    }

    @GetMapping
    public ResponseEntity<?> getAllChannels() {
        List<Channel> channels = channelService.getAllChannels();
        if (!channels.isEmpty()) {
            return ResponseEntity.ok(channels);
        } else return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Channel> getChannelById(@PathVariable long id) {
        Channel channel = channelService.getChannelById(id);
        if (channel != null) {
            return ResponseEntity.ok(channel);
        } else return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/name/{searchTerm}")
    public ResponseEntity<List<Channel>> getChannelsByFreeText(@PathVariable String searchTerm) {
        return ResponseEntity.ok(channelService.getChannelsByFreeText(searchTerm));
    }

    @PostMapping
    public ResponseEntity<Channel> createChannel(@RequestBody @Valid Channel newChannel) {
        return ResponseEntity.ok(channelService.createChannel(newChannel));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Channel> updateChannel(@RequestBody @Valid Channel channelToUpdate, @PathVariable long id) {
        Channel channel = channelService.updateChannelByID(channelToUpdate, id);
        if (channel != null) {
            return ResponseEntity.ok(channel);
        } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{id}")
    public void deleteChannelById(@PathVariable long id) throws BadRequestException {
        channelService.deleteChannelById(id);
    }

}
