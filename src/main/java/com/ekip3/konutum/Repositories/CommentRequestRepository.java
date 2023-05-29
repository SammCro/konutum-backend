package com.ekip3.konutum.Repositories;

import com.ekip3.konutum.Entities.CommentRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRequestRepository extends JpaRepository<CommentRequest, Long> {
    List<CommentRequest> findByHouseName(String houseName);

    @Query("SELECT c FROM CommentRequest c WHERE c.status = 'pending'")
    List<CommentRequest> findPendingCommentRequests();

    @Query("SELECT c FROM CommentRequest c WHERE c.houseName = ?1 AND c.status = 'accepted'")
    List<CommentRequest> findAcceptedCommentRequests(String houseName);
}
