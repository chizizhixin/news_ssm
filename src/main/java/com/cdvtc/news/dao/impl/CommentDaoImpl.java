package com.cdvtc.news.dao.impl;

import com.cdvtc.news.dao.CommentDao;
import com.cdvtc.news.model.Comment;
import com.cdvtc.news.model.User;
import com.cdvtc.news.util.CommentUtil;
import com.cdvtc.news.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CommentDaoImpl implements CommentDao {
    @Override
    public void addComment(Comment comment) {
        try {
            //获取连接
            Connection con = DBConnection.getConnection();
            //执行查询并返回结果集
            PreparedStatement pst = con.prepareStatement("insert into comment(content, pubDate, ipAddress, news_id, creator, likeNum, dislikeNum, reply_for_id, hidden) values(?, ?, ?, ?, ?, ?, ?, ?, ?)");
            pst.setString(1, comment.getContent());
            pst.setTimestamp(2, new Timestamp(new java.util.Date().getTime())); //当前时间作为发布时间
            pst.setString(3, comment.getIpAddress());
            pst.setInt(4, comment.getNews().getId());
            pst.setInt(5, comment.getCreator().getId());
            pst.setInt(6, 0); // 默认为0
            pst.setInt(7, 0); // 默认为0

            if (comment.getReplyFor() != null) {
                pst.setLong(8, comment.getReplyFor().getId());
            } else {
                pst.setNull(8, Types.BIGINT); // 被回复的评论为空(即新评论)
            }
            pst.setBoolean(9, false); //默认为不隐藏

            pst.executeUpdate();

            //释放连接资源
            pst.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Comment> getCommentsByNewsId(int newsId) {
        List<Comment> comments = new ArrayList<>();
        try {
            //获取连接
            Connection con = DBConnection.getConnection();

            //执行查询并返回结果集
            PreparedStatement  pst = con.prepareStatement("select c.*, r.id as rid, u.nickname, u.photo from `comment` as c left join `comment` as r on r.reply_for_id = c.id left join `user` as u on c.creator=u.id  where c.news_id=? and c.hidden=?");
            pst.setInt(1, newsId);
            pst.setBoolean(2, false);
            ResultSet rs = pst.executeQuery();

            //封装结果为列表数据
            List<Comment> rows = new ArrayList<>(); //获取所有行数据，注意行中的Comment对象可能重复
            while (rs.next()) {
                Comment comment = new Comment();
                comment.setId(rs.getLong("id"));
                comment.setContent(rs.getString("content"));
                comment.setLikeNum(rs.getInt("likeNum"));
                comment.setDislikeNum(rs.getInt("dislikeNum"));
                comment.setIpAddress(rs.getString("ipaddress"));

                Comment replyFor = new Comment();
                replyFor.setId(rs.getLong("reply_for_id"));
                comment.setReplyFor(replyFor);

                User creator = new User();
                creator.setId(rs.getInt("creator"));
                creator.setNickname(rs.getString("nickname"));
                creator.setPhoto(rs.getString("photo"));
                comment.setCreator(creator);

                comment.setReplyId(rs.getLong("rid"));
                comment.setPubDate(rs.getTimestamp("pubdate"));

                rows.add(comment);
            }

            //处理数据
            Map<Long, Comment> commentMap = new ConcurrentHashMap<>(); //保存数据，方便查询，去重 ，使用ConcurrentHashMap是为了解决线程安全问题，防止引发ConcurrentModificationException
            for(Comment comment: rows) {
                commentMap.put(comment.getId(), comment);
            }

            //处于所有关联
            for(Comment comment: rows) {
                if(comment.getReplyId() > 0) {
                    List<Comment> replies = commentMap.get(comment.getId()).getReplies();  //注意:这里必须要以Map中数据为准（List中同一Comment对象可能存在多个）
                    if(replies == null) {
                        replies = new ArrayList<>();
                        commentMap.get(comment.getId()).setReplies(replies); //注意：存入Map中的唯一对象
                    }
                    replies.add(commentMap.get(comment.getReplyId())); // 加入到回复列表中
                }
            }

            //移除回复的评论（只保留只评价）
            for (Long id: commentMap.keySet()) {
                Comment comment = commentMap.get(id);
                if(comment.getReplyFor().getId() > 0) {
                    commentMap.remove(comment.getId());
                }
            }

            //生成最终结果数据
            for(Long id: commentMap.keySet()) {
                comments.add(commentMap.get(id));
            }

            //释放连接资源
            rs.close();
            pst.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return comments;
    }

    @Override
    public int updateLikeNum(int commentId, boolean like) {
        // 获取点赞数
        try {
            //获取连接
            Connection con = DBConnection.getConnection();

            //执行查询并返回结果集
            PreparedStatement  pst = con.prepareStatement("select likenum from comment where id=?");
            pst.setInt(1, commentId);
            ResultSet rs = pst.executeQuery();
            if(rs.next()) {
                int likenum = rs.getInt("likenum");
                if(like) {
                    likenum++;
                } else if(!like && likenum>0) {
                    likenum--;
                }
                // 更新点赞数
                pst = con.prepareStatement("update comment set likenum=? where id=?");
                pst.setInt(1, likenum);
                pst.setInt(2, commentId);
                pst.executeUpdate();

                //释放连接资源
                rs.close();
                pst.close();
                con.close();

                return likenum; //返回最新的点赞数
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; //出错时返回
    }

    @Override
    public int updateDislikeNum(int commentId, boolean dislike) {
        // 获取点赞数
        try {
            //获取连接
            Connection con = DBConnection.getConnection();

            //执行查询并返回结果集
            PreparedStatement  pst = con.prepareStatement("select dislikenum from comment where id=?");
            pst.setInt(1, commentId);
            ResultSet rs = pst.executeQuery();
            if(rs.next()) {
                int dislikeNum = rs.getInt("dislikenum");
                if(dislike) {
                    dislikeNum++;
                } else if(!dislike && dislikeNum>0) {
                    dislikeNum--;
                }
                // 更新点赞数
                pst = con.prepareStatement("update comment set dislikeNum=? where id=?");
                pst.setInt(1, dislikeNum);
                pst.setInt(2, commentId);
                pst.executeUpdate();

                //释放连接资源
                rs.close();
                pst.close();
                con.close();

                return dislikeNum; //返回最新的点踩数
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; //出错时返回
    }

    public static void main(String[] args) {
        CommentDao commentDao = new CommentDaoImpl();
//        System.out.println(commentDao.getCommentsByNewsId(3));
        System.out.println(CommentUtil.toMap(commentDao.getCommentsByNewsId(3)));
    }
}
