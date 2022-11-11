package com.cvtc.news.model;

import com.cvtc.news.util.TimeFormat;

import java.util.Date;
import java.util.Set;

/**
 * 新闻
 */
public class News {
    /**
     * 编号
     */
    private Integer id;

    /**
     * 标题
     */
    private String title;

    /**
     * 图片地址（保存在服务器"/img"目录中）
     */
    private String img;

    /**
     * 内容
     */
    private String content;

    /**
     * 发布时间
     */
    private Date pubDate;

    /**
     * 是否置顶
     */
    private Boolean stick;

    /**
     * 分类
     */
    private Category category;

    private Integer category_id;
    /**
     *
     *
     * 编辑
     */
    private Admin editor;

    /**
     * (本新闻的)标签集合
     */
    private Set<Tag> tags;

    /**
     * 点击（阅读）计数
     */
    private int clickCount;

    /**
     * 评论数量（通过统计获取）
     */
    private Integer commentNum;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
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

    public Boolean getStick() {
        return stick;
    }

    public void setStick(Boolean stick) {
        this.stick = stick;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Admin getEditor() {
        return editor;
    }

    public void setEditor(Admin editor) {
        this.editor = editor;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public int getClickCount() {
        return clickCount;
    }

    public void setClickCount(int clickCount) {
        this.clickCount = clickCount;
    }
    
    public Integer getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(Integer commentNum) {
        this.commentNum = commentNum;
    }

    public Integer getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Integer category_id) {
        this.category_id = category_id;
    }

    /**
     * 发布时间（间隔时间）文字描述
     * @return
     */
    public String getPubDateInterval() {
        return TimeFormat.getInterval(pubDate);
    }

    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", img='" + img + '\'' +
                ", content='" + content + '\'' +
                ", pubDate=" + pubDate +
                ", stick=" + stick +
                ", category=" + category +
                ", CategoryId=" + category_id +
                ", editor=" + editor +
                ", tags=" + tags +
                ", clickCount=" + clickCount +
                ", commentNum=" + commentNum +
                '}';
    }
}
