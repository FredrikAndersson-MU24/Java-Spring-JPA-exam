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

    public ChannelController(ChannelService channelService) {
        this.channelService = channelService;
    }

    //Create
    @PostMapping
    public ResponseEntity<ChannelDTO> createChannel(@RequestBody @Valid Channel newChannel) {
        return ResponseEntity.ok(channelService.createChannel(newChannel));
    }

    //Read
    @GetMapping
    public ResponseEntity<List<ChannelDTO>> getAllChannels() {
        List<ChannelDTO> channels = channelService.getAllChannels();
        if (!channels.isEmpty()) {
            return ResponseEntity.ok(channels);
        } else return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChannelDTO> getChannelById(@PathVariable long id) {
        ChannelDTO channel = channelService.getChannelById(id);
        if (channel != null) {
            return ResponseEntity.ok(channel);
        } else return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/name/{searchTerm}")
    public ResponseEntity<List<ChannelDTO>> getChannelsByFreeText(@PathVariable String searchTerm) {
        return ResponseEntity.ok(channelService.getChannelsByFreeText(searchTerm));
    }

    @GetMapping("/{channelId}/posts")
    public ResponseEntity<List<PostMinimalDTO>> getPostMinimalDTOsByChannelId(@PathVariable long channelId) {
        List<PostMinimalDTO> posts = channelService.getAllPostMinimalDTOByChannelId(channelId);
        if (posts != null) {
            return ResponseEntity.ok(posts);
        } else return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //Update
    @PutMapping("/{id}")
    public ResponseEntity<ChannelDTO> updateChannel(@RequestBody @Valid Channel channelToUpdate, @PathVariable long id) {
        ChannelDTO channel = channelService.updateChannelByID(channelToUpdate, id);
        if (channel != null) {
            return ResponseEntity.ok(channel);
        } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    //Delete
    @DeleteMapping("/{id}")
    public void deleteChannelById(@PathVariable long id) throws BadRequestException {
        channelService.deleteChannelById(id);
    }

}
