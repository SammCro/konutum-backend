package com.ekip3.konutum.Services;


import com.ekip3.konutum.Entities.CommentRequest;
import com.ekip3.konutum.Repositories.CommentRequestRepository;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CommentRequestService {
    @Autowired
    private CommentRequestRepository repo;

    public void saveMyCommentRequest(CommentRequest commentRequest){
        repo.save(commentRequest);
    }

    public Iterable<CommentRequest> showAllCommentRequests() {
        return repo.findAll();
    }

    public Iterable<CommentRequest> deleteCommentRequestById(Long id) {
        repo.deleteById(id);
        return repo.findAll();
    }

    public CommentRequest findByCommentRequestId(Long id) {
        return repo.findById(id).orElse(null);
    }


}
