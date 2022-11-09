package com.cdvtc.news.util;

import com.cdvtc.news.model.Comment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommentUtil {
    /**
     * 将评论列表转为Map（解决jsp:param不能传递对象的问题）
     * @param comments
     * @return
     */
    public static Map<Long, Comment> toMap(List<Comment> comments) {
        Map<Long, Comment> commentMap = new HashMap<>();
        for (Comment comment: comments) {
            commentMap.put(comment.getId(), comment);

            List<Comment> replies =comment.getReplies();
            if(replies != null && replies.size()>0){
                commentMap.putAll(toMap(replies)); //递归调用
            }
        }

        return commentMap;
    }
}
