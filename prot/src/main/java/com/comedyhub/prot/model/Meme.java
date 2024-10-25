package com.comedyhub.prot.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "memes")
public class Meme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(length = 500)
    private String description;

    @Column(nullable = false)
    private String memeUrl;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private LocalDateTime datePost;

    @ManyToOne
    @JoinColumn(name = "posted_by", nullable = false)
    private User postedBy;

    @OneToMany(mappedBy = "meme", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<MemeComment> comments = new HashSet<>();

    public Meme() {
        this.datePost = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getMemeUrl() { return memeUrl; }
    public void setMemeUrl(String memeUrl) { this.memeUrl = memeUrl; }

    public LocalDateTime getDatePost() { return datePost; }
    public void setDatePost(LocalDateTime datePost) { this.datePost = datePost; }

    public User getPostedBy() { return postedBy; }
    public void setPostedBy(User postedBy) { this.postedBy = postedBy; }

    public Set<MemeComment> getComments() { return comments; }
    public void setComments(Set<MemeComment> comments) { this.comments = comments; }
}
