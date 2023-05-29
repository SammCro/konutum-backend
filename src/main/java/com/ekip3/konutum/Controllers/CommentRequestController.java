package com.ekip3.konutum.Controllers;

import com.ekip3.konutum.Services.CommentRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/commentRequest")
public class CommentRequestController {
    @Autowired
    private CommentRequestService commentRequestService;
}
