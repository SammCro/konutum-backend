package com.ekip3.konutum.Entities;

import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "comment_requests")
@Data
public class CommentRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "house_name")
    private String houseName;

    @Column(name = "location_name")
    private String locationName;

    @Column(name = "expert")
    private String expertName;

    @Column(name = "comment")
    private String comment;

}
