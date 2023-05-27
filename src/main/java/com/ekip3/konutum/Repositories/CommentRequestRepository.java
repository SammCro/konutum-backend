package com.ekip3.konutum.Repositories;

import com.ekip3.konutum.Entities.CommentRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRequestRepository extends JpaRepository<CommentRequest, Long> {
    CommentRequest findByCommentRequestId(Long id);


}
