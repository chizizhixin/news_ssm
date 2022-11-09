package com.cdvtc.news.controller;

import com.cdvtc.news.dao.CommentDao;
import com.cdvtc.news.dao.NewsDao;
import com.cdvtc.news.dao.TagDao;
import com.cdvtc.news.dao.impl.CommentDaoImpl;
import com.cdvtc.news.dao.impl.NewsDaoImpl;
import com.cdvtc.news.dao.impl.TagDaoImpl;
import com.cdvtc.news.model.Comment;
import com.cdvtc.news.model.News;
import com.cdvtc.news.model.Tag;
import com.cdvtc.news.util.CommentUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;
import java.util.Map;

@Controller
public class newsController {

    @RequestMapping("/news/{id}")  // {id}作为路径参数
    public String news(@PathVariable Integer id, Model model){
//        int id = Integer.parseInt(request.getParameter("id"));
        NewsDao newsDao = new NewsDaoImpl();
        News news = newsDao.getNewsById(id);
        model.addAttribute("news", news);

        CommentDao commentDao = new CommentDaoImpl();
        List<Comment> comments = commentDao.getCommentsByNewsId(id);
//        pageContext.setAttribute("comments", comments); //存入页面作用域，EL表达式才能获取值
        model.addAttribute("comments", comments);

//        request.setAttribute("commentMap", CommentUtil.toMap(comments)); //保存评论Map，解决jsp:param不能传对象的问题（改为只传Id）
        model.addAttribute("commentMap", CommentUtil.toMap(comments));


        TagDao tagDao = new TagDaoImpl();
        Set<Tag> tagSet = tagDao.getTagsByNewsId(id);
        model.addAttribute("tagSet", tagSet);

        List<News> recommendedNews = newsDao.getRecommendedNews(id);
        model.addAttribute("recommendedNews", recommendedNews);

        // 更新点击计数
        newsDao.updateClickCount(id);

        return "news";
    }

    @RequestMapping("/comment/{id}")
    public String comment(@PathVariable Long id, Model model, HttpServletRequest request) {
//        Long commentId = Long.valueOf(request.getParameter("commentId"));
//        CommentDao commentDao = new CommentDaoImpl();
//        Comment comment = commentDao.getCommentById(id);

        Comment comment = ((Map<Long, Comment>) request.getAttribute("commentMap")).get(id);
//        pageContext.setAttribute("comment", comment);
        model.addAttribute("comment", comment);

        return "commons/comment";
    }
}
