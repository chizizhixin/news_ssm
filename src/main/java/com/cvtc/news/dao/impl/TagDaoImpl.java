package com.cvtc.news.dao.impl;

import com.cvtc.news.dao.TagDao;
import com.cvtc.news.model.Tag;
import com.cvtc.news.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TagDaoImpl implements TagDao {
    @Override
    public List<Tag> getAllTags() {
        try {
            //获取连接
            Connection con = DBConnection.getConnection();

            //执行查询并返回结果集
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from tag");

            //封装结果为列表数据
            List<Tag> tags = new ArrayList<>();
            while (rs.next()) {
                Tag tag = new Tag();

                int id = rs.getInt("id");
                String name = rs.getString("name");
                tag.setId(id);
                tag.setName(name);

                tags.add(tag);
            }

            //释放连接资源
            rs.close();
            st.close();
            con.close();

            //返回结果
            return tags;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Set<Tag> getTagsByNewsId(int newsId) {
        try {
            //获取连接
            Connection con = DBConnection.getConnection();

            //执行查询并返回结果集
            PreparedStatement  pst = con.prepareStatement("select * from tag, news_tag where tag.id=news_tag.tag_id and news_tag.news_id=?");
            pst.setInt(1, newsId);
            ResultSet rs = pst.executeQuery();

            //封装结果为列表数据
            Set<Tag> tags = new HashSet<>();
            while (rs.next()) {
                Tag tag = new Tag();

                int id = rs.getInt("tag_id");
                String name = rs.getString("name");
                tag.setId(id);
                tag.setName(name);

                tags.add(tag);
            }

            //释放连接资源
            rs.close();
            pst.close();
            con.close();

            //返回结果
            return tags;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    private boolean isNewsTagExisted(int newsId, int tagId){
        try {
            //获取连接
            Connection con = DBConnection.getConnection();

            //执行查询并返回结果集
            PreparedStatement  pst = con.prepareStatement("select * from news_tag where tag_id=? and news_id=?");
            pst.setInt(1, tagId);
            pst.setInt(2, newsId);
            ResultSet rs = pst.executeQuery();

            boolean result = rs.next();

            //释放连接资源
            rs.close();
            pst.close();
            con.close();

            //返回结果
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public void addTagsForNews(int newsId, Set<Integer> tagIds) {
        try {
            //获取连接
            Connection con = DBConnection.getConnection();
            PreparedStatement pst =con.prepareStatement("insert into news_tag(news_id, tag_id) values(?, ?)");
            for(Integer tagId: tagIds){
                if(!isNewsTagExisted(newsId,tagId)){ //不存在该组合才存库
                    pst.setInt(1, newsId);
                    pst.setInt(2, tagId);

                    pst.executeUpdate();
                }
            }

            //释放连接资源
            pst.close();
            pst.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void deleteTagsForNews(int newsId) {
        try {
            //获取连接
            Connection con = DBConnection.getConnection();

            //执行查询并返回结果集
            PreparedStatement pst =con.prepareStatement("delete from news_tag where news_id=?");
            pst.setInt(1, newsId);

            pst.executeUpdate();

            //释放连接资源
            pst.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateTagsForNews(int newsId, Set<Integer> tagIds) {
        //删除新闻的所有标签
        deleteTagsForNews(newsId);

        //增加新闻标签
        this.addTagsForNews(newsId, tagIds);
    }

    public static void main(String[] args) {
        TagDao dao = new TagDaoImpl();

        Set<Integer> tagIds = new HashSet<>();
        tagIds.add(2);
        tagIds.add(3);
        dao.addTagsForNews(1, tagIds);
        System.out.println(dao.getTagsByNewsId(1));
    }
}
