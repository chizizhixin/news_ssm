package com.cvtc.news.controller;

import com.cvtc.news.dao.CommentDao;
import com.cvtc.news.dao.impl.CommentDaoImpl;
import com.cvtc.news.model.Comment;
import com.cvtc.news.model.News;
import com.cvtc.news.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class CommentController {

	@RequestMapping("/sendComment/{newsId}")
	public String comment(@PathVariable int newsId, @RequestParam(required = false) Long replyForId, String content, HttpSession session, HttpServletRequest request){

//        Integer newsId =  Integer.valueOf(request.getParameter("newsId"));
//        String replyForId = request.getParameter("replyForId"); //被回复的评论ID，新评论则为null
//        String content = request.getParameter("content");
		User user = (User)session.getAttribute("user"); // 获取当前登陆用户

		Comment comment = new Comment();
		comment.setContent(content);
		News news = new News();
		news.setId(newsId);
		comment.setNews(news);
		comment.setCreator(user);
		comment.setIpAddress(request.getRemoteAddr());  //获取用户IP地址
		if(replyForId != null){
			Comment replyFor = new Comment();
			replyFor.setId(replyForId);
			comment.setReplyFor(replyFor);
		}

		CommentDao commentDao = new CommentDaoImpl();
		commentDao.addComment(comment);

		//页面跳转回当前新闻页
//        response.sendRedirect("news.jsp?id="+newsId);
		return "redirect:/news/"+newsId;
	}

	@RequestMapping("commentLike/{id}/{operate}")
	public @ResponseBody
	int like(@PathVariable int id, @PathVariable String operate){
		CommentDao commentDao = new CommentDaoImpl();
		int resultNum = -1;
		if("like".equals(operate) || "unlike".equals(operate)) {  //点赞或取消点赞
			resultNum = commentDao.updateLikeNum(id, "like".equals(operate));
		} else if("dislike".equals(operate) || "undislike".equals(operate)) { //踩或取消踩
			resultNum = commentDao.updateDislikeNum(id, "dislike".equals(operate));
		}
     return resultNum;
	}
}
