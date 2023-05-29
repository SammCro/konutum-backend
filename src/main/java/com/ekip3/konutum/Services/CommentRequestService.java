package com.ekip3.konutum.Services;


import com.ekip3.konutum.Entities.CommentRequest;
import com.ekip3.konutum.Repositories.CommentRequestRepository;
import javax.transaction.Transactional;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
@Transactional
public class CommentRequestService {
    @Autowired
    private CommentRequestRepository repo;

    public void saveCommentRequest(CommentRequest commentRequest){
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

    public List<CommentRequest> findByHouseName(String houseName) {
        return repo.findByHouseName(houseName);
    }

    public List<CommentRequest> findPendingCommentRequests() {
        return repo.findPendingCommentRequests();
    }

    public List<CommentRequest> findAcceptedCommentRequests(String houseName) {
        return repo.findAcceptedCommentRequests(houseName);
    }

    public ResponseEntity<?> generateReport(String houseName) {
        // find all comment requests by house name
        List<CommentRequest> commentRequests = findAcceptedCommentRequests(houseName);
        try {
            PDDocument document = new PDDocument();
            PDPage page = new PDPage();
            document.addPage(page);

            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
            float yPosition = page.getMediaBox().getHeight() - 50; // Start y-position

            for (CommentRequest commentRequest : commentRequests) {
                contentStream.beginText();
                contentStream.newLineAtOffset(50, yPosition);

                contentStream.showText("Expert: " + commentRequest.getExpertName());
                yPosition -= 20; // Move down by 20 points
                contentStream.newLineAtOffset(0, -20);

                contentStream.showText("House Name: " + commentRequest.getHouseName());
                yPosition -= 20;
                contentStream.newLineAtOffset(0, -20);

                contentStream.showText("Comment: " + commentRequest.getComment());
                yPosition -= 20;
                contentStream.newLineAtOffset(0, -20);

                contentStream.endText();
                yPosition -= 20; // Add some space between entries
            }

            contentStream.close();

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            document.save(outputStream);
            document.close();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.add("Content-Disposition", "attachment; filename=example.pdf");

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentLength(outputStream.size())
                    .body(outputStream.toByteArray());
        } catch (IOException e) {
            // Handle PDF generation exception
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


}
