package com.zdh.controller.content;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.zdh.dto.CommentDto;
import com.zdh.service.CommentService;

@Controller
@RequestMapping("/comments")
public class CommentsController {
	
	@Autowired
	private CommentService commentService;
	
	@RequestMapping
	public String init(Model model) {
		List<CommentDto> result = commentService.getCommentList();
		model.addAttribute("commentList", result);
		/*model.addAttribute("searchParam", commentDto);*/
		return "/content/commentList";
	}
}
