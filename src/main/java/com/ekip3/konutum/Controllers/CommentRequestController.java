package com.ekip3.konutum.Controllers;

import com.ekip3.konutum.Entities.CommentRequest;
import com.ekip3.konutum.Services.CommentRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@CrossOrigin
@Controller
@RequestMapping("/commentRequest")
public class CommentRequestController {
    @Autowired
    private CommentRequestService commentRequestService;

    @PostMapping("/addCommentRequest")
    public ResponseEntity<?> addCommentRequest(@Valid @RequestBody CommentRequest commentRequest){
        commentRequestService.saveCommentRequest(commentRequest);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/getAllCommentRequests")
    public ResponseEntity<?> getAllCommentRequests(){
        return ResponseEntity.ok(commentRequestService.showAllCommentRequests());
    }

    @GetMapping("/acceptCommentRequest/{id}")
    public ResponseEntity<?> acceptCommentRequest(@PathVariable Long id){
        commentRequestService.acceptCommentRequest(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/rejectCommentRequest/{id}")
    public ResponseEntity<?> deleteCommentRequest(@PathVariable Long id){
        commentRequestService.deleteCommentRequestById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/generateReport/{houseName}")
    public ResponseEntity<?> generatePdf(@PathVariable String houseName) {
        return commentRequestService.generateReport(houseName);
    }

    @GetMapping("/getPendingCommentRequests")
    public ResponseEntity<?> getPendingCommentRequests(){
        return ResponseEntity.ok(commentRequestService.findPendingCommentRequests());
    }

    @GetMapping("/getAcceptedCommentRequests/{houseName}")
    public ResponseEntity<?> getAcceptedCommentRequests(@PathVariable String houseName){
        return ResponseEntity.ok(commentRequestService.findAcceptedCommentRequests(houseName));
    }
}
