package com.cdvtc.news.model;

import java.util.Date;
import java.util.List;

/**
 * 评论
 */
public class Comment {
    /**
     * 编号
     */
    private Long id;

    /**
     * 内容
     */
    private String content;

    /**
     * 发表时间
     */
    private Date pubDate;

    /**
     * IP地址
     */
    private String ipAddress;

    /**
     * (被评论的)新闻
     */
    private News news;

    /**
     * 评论人
     */
    private User creator;

    /**
     * 点赞数
     */
    private Integer likeNum;

    /**
     * 点踩数
     */
    private Integer dislikeNum;

    /**
     * 回复（评论）
     */
    private Comment replyFor;

    /**
     * 是否隐藏
     */
    private Boolean hidden;

    /**
     * 临时存储回复本条记录的ID（本字段不存库，用于叛逆查询）
     */
    private Long replyId;

    /**
     * （针对本评论的）回复列表
     */
    private List<Comment> replies;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getPubDate() {
        return pubDate;
    }

    public void setPubDate(Date pubDate) {
        this.pubDate = pubDate;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public News getNews() {
        return news;
    }

    public void setNews(News news) {
        this.news = news;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public Integer getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(Integer likeNum) {
        this.likeNum = likeNum;
    }

    public Integer getDislikeNum() {
        return dislikeNum;
    }

    public void setDislikeNum(Integer dislikeNum) {
        this.dislikeNum = dislikeNum;
    }

    public Comment getReplyFor() {
        return replyFor;
    }

    public void setReplyFor(Comment replyFor) {
        this.replyFor = replyFor;
    }

    public Boolean getHidden() {
        return hidden;
    }

    public void setHidden(Boolean hidden) {
        this.hidden = hidden;
    }

    public List<Comment> getReplies() {
        return replies;
    }

    public void setReplies(List<Comment> replies) {
        this.replies = replies;
    }

    public Long getReplyId() {
        return replyId;
    }

    public void setReplyId(Long replyId) {
        this.replyId = replyId;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", pubDate=" + pubDate +
                ", ipAddress='" + ipAddress + '\'' +
                ", news=" + news +
                ", creator=" + creator +
                ", likeNum=" + likeNum +
                ", dislikeNum=" + dislikeNum +
                ", replyFor=" + replyFor +
                ", hidden=" + hidden +
                ", replies=" + replies +
                '}';
    }
}
