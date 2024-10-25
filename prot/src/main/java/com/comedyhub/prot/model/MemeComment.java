package com.comedyhub.prot.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "meme_comments")
public class MemeComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "meme_id", nullable = false)
    private Meme meme;

    @Column(nullable = false, length = 500)
    private String content;

    @Column(nullable = false)
    private LocalDateTime commentedAt;

    public MemeComment() {
        this.commentedAt = LocalDateTime.now(); // Data de criação do comentário
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public Meme getMeme() { return meme; }
    public void setMeme(Meme meme) { this.meme = meme; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public LocalDateTime getCommentedAt() { return commentedAt; }
    public void setCommentedAt(LocalDateTime commentedAt) { this.commentedAt = commentedAt; }
}
