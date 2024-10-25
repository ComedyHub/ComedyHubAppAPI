package com.comedyhub.prot.model;

import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity
@Table(name = "user_meme_interactions")
public class UserMemeInteraction {

    public enum InteractionType {
        LIKED,
        SAVED,
        POSTED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "meme_id", nullable = false)
    private Meme meme;

    @Column(nullable = false)
    private InteractionType type;

    @Column(nullable = false)
    private LocalDateTime interactionTime;

    public UserMemeInteraction() {
        this.interactionTime = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public Meme getMeme() { return meme; }
    public void setMeme(Meme meme) { this.meme = meme; }

    public InteractionType getType() { return type; }
    public void setType(InteractionType type) { this.type = type; }

    public LocalDateTime getInteractionTime() { return interactionTime; }
    public void setInteractionTime(LocalDateTime interactionTime) { this.interactionTime = interactionTime; }
}
