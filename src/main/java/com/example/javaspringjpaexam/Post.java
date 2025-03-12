package com.example.javaspringjpaexam;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Entity
@Table(name ="posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    @Size(min = 6, max = 32, message = "The post title must be 6-32 characters.")
    private String title;
    @NotNull
    @Size(min = 4, max = 160, message = "The body of the post must be 4-160 characters.")
    private String body;
    @CreationTimestamp
    private LocalDate posted;
    private LocalDate edited;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "channel_id")
    private Channel channel;

    public Post() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getPosted() {
        return posted;
    }

    public void setPosted(LocalDate posted) {
        this.posted = posted;
    }

    public LocalDate getEdited() {
        return edited;
    }

    public void setEdited(LocalDate edited) {
        this.edited = edited;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }
}
