package com.cvtc.news.dao.impl;

import com.cvtc.news.dao.AdminDao;
import com.cvtc.news.dao.NewsDao;
import com.cvtc.news.dao.Page;
import com.cvtc.news.model.Admin;
import com.cvtc.news.model.News;
import com.cvtc.news.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NewsDaoImpl implements NewsDao {
    @Override
    public List<News> getNewsByCategory(Integer categoryId) {
        try {
            //获取连接
            Connection con = DBConnection.getConnection();

            //执行查询并返回结果集
            PreparedStatement pst = con.prepareStatement("select * from news where category_id = ?");
            pst.setInt(1, categoryId); //填充参数，注意参数编号从1开始
            ResultSet rs = pst.executeQuery();

            //封装结果为列表数据
            List<News> newsList = new ArrayList<>();
            while (rs.next()) {
                News news = new News();

                news.setId(rs.getInt("id"));
                news.setTitle(rs.getString("title"));
                news.setImg(rs.getString("img"));
                news.setContent(rs.getString("content"));
                news.setPubDate(rs.getTimestamp("pubdate"));
                news.setStick(rs.getBoolean("stick"));
                news.setClickCount(rs.getInt("click_count"));

                /**
                 * 获取编辑信息（先获取编号，然后根据编号获取全部信息）
                 */
                int editorId = rs.getInt("editor");
                AdminDao adminDao = new AdminDaoImpl();
                Admin editor = adminDao.getAdminById(editorId);
                news.setEditor(editor);

                news.setCommentNum(this.getCommentNumByNewsId(news.getId())); // 关联查询

                newsList.add(news);
            }

            //释放连接资源
            rs.close();
            pst.close();
            con.close();

            //返回结果
            return newsList;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<News> getNewsByTag(Integer tagId) {
        try {
            //获取连接
            Connection con = DBConnection.getConnection();

            //执行查询并返回结果集
            PreparedStatement pst = con.prepareStatement("select * from news inner join news_tag on news.id=news_tag.news_id where tag_id = ?");
            pst.setInt(1, tagId);
            ResultSet rs = pst.executeQuery();

            //封装结果为列表数据
            List<News> newsList = new ArrayList<>();
            while (rs.next()) {
                News news = new News();

                news.setId(rs.getInt("id"));
                news.setTitle(rs.getString("title"));
                news.setImg(rs.getString("img"));
                news.setContent(rs.getString("content"));
                news.setPubDate(rs.getTimestamp("pubdate"));
                news.setStick(rs.getBoolean("stick"));
                news.setClickCount(rs.getInt("click_count"));

                /**
                 * 获取编辑信息（先获取编号，然后根据编号获取全部信息）
                 */
                int editorId = rs.getInt("editor");
                AdminDao adminDao = new AdminDaoImpl();
                Admin editor = adminDao.getAdminById(editorId);
                news.setEditor(editor);

                news.setCommentNum(this.getCommentNumByNewsId(news.getId())); // 关联查询

                newsList.add(news);
            }

            //释放连接资源
            rs.close();
            pst.close();
            con.close();

            //返回结果
            return newsList;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 获取新闻评论数
     *
     * @param newsId
     * @return
     */
    private Integer getCommentNumByNewsId(int newsId) {
        try {
            //获取连接
            Connection con = DBConnection.getConnection();

            //执行查询并返回结果集
            PreparedStatement pst = con.prepareStatement("select count(*) from comment WHERE news_id =?");
            pst.setInt(1, newsId);

            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                int num = rs.getInt(1);

                //释放连接资源
                rs.close();
                pst.close();
                con.close();

                return num;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public List<News> getStickNews(int limit) {
        try {
            //获取连接
            Connection con = DBConnection.getConnection();

            //执行查询并返回结果集
            PreparedStatement pst = con.prepareStatement("select * from news where stick = ? limit ?");
            pst.setBoolean(1, true);
            pst.setInt(2, limit);
            ResultSet rs = pst.executeQuery();

            //封装结果为列表数据
            List<News> newsList = new ArrayList<>();
            while (rs.next()) {
                News news = new News();

                news.setId(rs.getInt("id"));
                news.setTitle(rs.getString("title"));
                news.setImg(rs.getString("img"));
                news.setContent(rs.getString("content"));
                news.setPubDate(rs.getTimestamp("pubdate"));
                news.setStick(rs.getBoolean("stick"));
                news.setClickCount(rs.getInt("click_count"));

                /**
                 * 获取编辑信息（先获取编号，然后根据编号获取全部信息）
                 */
                int editorId = rs.getInt("editor");
                AdminDao adminDao = new AdminDaoImpl();
                Admin editor = adminDao.getAdminById(editorId);
                news.setEditor(editor);

                news.setCommentNum(this.getCommentNumByNewsId(news.getId())); // 关联查询

                newsList.add(news);
            }

            //释放连接资源
            rs.close();
            pst.close();
            con.close();

            //返回结果
            return newsList;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<News> getAllNews() {
        try {
            //获取连接
            Connection con = DBConnection.getConnection();

            //执行查询并返回结果集
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from news");
            //封装结果为列表数据
            List<News> newsList = new ArrayList<>();
            while (rs.next()) {
                News news = new News();

                news.setId(rs.getInt("id"));
                news.setTitle(rs.getString("title"));
                news.setImg(rs.getString("img"));
                news.setContent(rs.getString("content"));
                news.setPubDate(rs.getTimestamp("pubdate"));
                news.setStick(rs.getBoolean("stick"));
                news.setClickCount(rs.getInt("click_count"));

                /**
                 * 获取编辑信息（先获取编号，然后根据编号获取全部信息）
                 */
                int editorId = rs.getInt("editor");
                AdminDao adminDao = new AdminDaoImpl();
                Admin editor = adminDao.getAdminById(editorId);
                news.setEditor(editor);

                news.setCommentNum(this.getCommentNumByNewsId(news.getId())); // 关联查询

                newsList.add(news);
            }

            //释放连接资源
            rs.close();
            st.close();
            con.close();

            //返回结果
            return newsList;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Integer addNews(News news) {
        try {
            //获取连接
            Connection con = DBConnection.getConnection();

            //执行查询并返回结果集
            PreparedStatement pst = con.prepareStatement("insert into news(title, content, pubdate, category_id, img, editor, stick, click_count) values(?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, news.getTitle());
            pst.setString(2, news.getContent());
            pst.setTimestamp(3, new Timestamp(new java.util.Date().getTime())); //当前时间作为发布时间
            pst.setInt(4, news.getCategory().getId());
            pst.setString(5, news.getImg());
            pst.setInt(6, news.getEditor().getId());
            pst.setBoolean(7, news.getStick());
            pst.setInt(8, 0);

            pst.executeUpdate();
            ResultSet rs = pst.getGeneratedKeys();
            rs.next();
            Integer id = rs.getInt(1);

            //释放连接资源
            rs.close();
            pst.close();
            con.close();

            return id;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1; //新增失败
    }

    @Override
    public void updateNews(News news) {
        try {
            //获取连接
            Connection con = DBConnection.getConnection();

            //执行查询并返回结果集
            PreparedStatement pst = con.prepareStatement("update news set title=?, content=?, category_id =?, editor=?, stick=?"
                    + (news.getImg() != null ? ", img=? " : "")
                    + " where id=?");
            pst.setString(1, news.getTitle());
            pst.setString(2, news.getContent());
            pst.setInt(3, news.getCategory().getId());
            pst.setInt(4, news.getEditor().getId());
            pst.setBoolean(5, news.getStick());
            if (news.getImg() != null) {
                pst.setString(6, news.getImg());
                pst.setInt(7, news.getId());
            } else {
                pst.setInt(6, news.getId());
            }

            pst.executeUpdate();

            //释放连接资源
            pst.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public News getNewsById(int newsId) {
        try {
            //获取连接
            Connection con = DBConnection.getConnection();

            //执行查询并返回结果集
            PreparedStatement pst = con.prepareStatement("select * from news where id = ?");
            pst.setInt(1, newsId); //填充参数，注意参数编号从1开始
            ResultSet rs = pst.executeQuery();

            News news = new News();
            if (rs.next()) {
                news.setId(rs.getInt("id"));
                news.setTitle(rs.getString("title"));
                news.setContent(rs.getString("content"));
                news.setPubDate(rs.getTimestamp("pubdate"));
                news.setStick(rs.getBoolean("stick"));
                news.setClickCount(rs.getInt("click_count"));

                /**
                 * 获取编辑信息（先获取编号，然后根据编号获取全部信息）
                 */
                int editorId = rs.getInt("editor");
                AdminDao adminDao = new AdminDaoImpl();
                Admin editor = adminDao.getAdminById(editorId);
                news.setEditor(editor);

                news.setCommentNum(this.getCommentNumByNewsId(news.getId())); // 关联查询
            }

            //释放连接资源
            rs.close();
            pst.close();
            con.close();

            //返回结果
            return news;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<News> getHotNews() {
        try {
            //获取连接
            Connection con = DBConnection.getConnection();

            //执行查询并返回结果集
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from news order by pubdate desc limit 6");
            //封装结果为列表数据
            List<News> newsList = new ArrayList<>();
            while (rs.next()) {
                News news = new News();

                news.setId(rs.getInt("id"));
                news.setTitle(rs.getString("title"));
                news.setImg(rs.getString("img"));
                news.setContent(rs.getString("content"));
                news.setPubDate(rs.getTimestamp("pubdate"));
                news.setStick(rs.getBoolean("stick"));
                news.setClickCount(rs.getInt("click_count"));

                /**
                 * 获取编辑信息（先获取编号，然后根据编号获取全部信息）
                 */
                int editorId = rs.getInt("editor");
                AdminDao adminDao = new AdminDaoImpl();
                Admin editor = adminDao.getAdminById(editorId);
                news.setEditor(editor);

                news.setCommentNum(this.getCommentNumByNewsId(news.getId())); // 关联查询

                newsList.add(news);
            }

            //释放连接资源
            rs.close();
            st.close();
            con.close();

            //返回结果
            return newsList;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<News> getRecommendedNews(int newsId) {
        try {
            //获取连接
            Connection con = DBConnection.getConnection();

            //执行查询并返回结果集
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from news order by rand() limit 5");
            //封装结果为列表数据
            List<News> newsList = new ArrayList<>();
            while (rs.next()) {
                News news = new News();

                news.setId(rs.getInt("id"));
                news.setTitle(rs.getString("title"));
                news.setImg(rs.getString("img"));
                news.setContent(rs.getString("content"));
                news.setPubDate(rs.getTimestamp("pubdate"));
                news.setStick(rs.getBoolean("stick"));
                news.setClickCount(rs.getInt("click_count"));

                /**
                 * 获取编辑信息（先获取编号，然后根据编号获取全部信息）
                 */
                int editorId = rs.getInt("editor");
                AdminDao adminDao = new AdminDaoImpl();
                Admin editor = adminDao.getAdminById(editorId);
                news.setEditor(editor);

                news.setCommentNum(this.getCommentNumByNewsId(news.getId())); // 关联查询

                newsList.add(news);
            }

            //释放连接资源
            rs.close();
            st.close();
            con.close();

            //返回结果
            return newsList;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void updateClickCount(int newsId) {
        try {
            //获取连接
            Connection con = DBConnection.getConnection();
            PreparedStatement pst = con.prepareStatement("update news set click_count=click_count+1 where id=?");
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
    public List<Map<String, Object>> statNewsCountByCategory() {
        //封装结果为列表数据
        List<Map<String, Object>> statsList = new ArrayList<>();
        try {
            //获取连接
            Connection con = DBConnection.getConnection();

            //执行查询并返回结果集
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select id, name, IFNULL(count, 0) as count from category left JOIN (SELECT category_id, count(*) as count from news GROUP BY category_id) as news on news.category_id=category.id");

            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();
                row.put("id", rs.getInt("id"));
                row.put("name", rs.getString("name"));
                row.put("count", rs.getInt("count"));

                statsList.add(row);
            }

            //释放连接资源
            rs.close();
            st.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return statsList;
    }

    @Override
    public List<Map<String, Object>> statNewsCountByTag() {
        //封装结果为列表数据
        List<Map<String, Object>> statsList = new ArrayList<>();
        try {
            //获取连接
            Connection con = DBConnection.getConnection();

            //执行查询并返回结果集
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select tag.id as id, name, count(*) as count from news left join news_tag on news.id=news_tag.news_id left join tag on news_tag.tag_id=tag.id group by name");

            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();
                row.put("id", rs.getInt("id"));
                row.put("name", rs.getString("name"));
                row.put("count", rs.getInt("count"));

                statsList.add(row);
            }

            //释放连接资源
            rs.close();
            st.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //返回结果
        return statsList;
    }

    @Override
    public List<Map<String, Object>> statNewsCommentCountByDate() {
        //封装结果为列表数据
        List<Map<String, Object>> statsList = new ArrayList<>();
        try {
            //获取连接
            Connection con = DBConnection.getConnection();

            //执行查询并返回结果集
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select date_format(pubdate, '%Y-%m-%d') as date, count(*) as count from `comment` group by date order by pubdate");

            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();
                row.put("date", rs.getString("date"));
                row.put("count", rs.getInt("count"));

                statsList.add(row);
            }

            //释放连接资源
            rs.close();
            st.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return statsList;
    }

    @Override
    public List<Map<String, Object>> statNewsCommentCountByUser() {
        //封装结果为列表数据
        List<Map<String, Object>> statsList = new ArrayList<>();

        try {
            //获取连接
            Connection con = DBConnection.getConnection();

            //执行查询并返回结果集
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select nickname, count(`comment`.id) as count from user left join `comment`  on `comment`.creator=user.id group by nickname order by count desc");

            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();
                row.put("nickname", rs.getString("nickname"));
                row.put("count", rs.getInt("count"));

                statsList.add(row);
            }

            //释放连接资源
            rs.close();
            st.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return statsList;
    }

    @Override
    public Page<News> getPagedNews(int pageNumer, int pageSize) {
        // 校正参数
        if (pageNumer < 1) {
            pageNumer = 1;
        }
        if (pageSize < 1) {
            pageSize = 1;
        }

        // 初始化分页对象
        PageImpl<News> page = new PageImpl<>();
        page.setPageNumber(pageNumer);
        page.setPageSize(pageSize);


        try {
            //获取连接
            Connection con = DBConnection.getConnection();

            // 查询总行数
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select  count(*) from news");
            if (rs.next()) {
                page.setTotalRows(rs.getInt(1)); //获取并设置总记录行数

                //执行分页查询
                PreparedStatement pst = con.prepareStatement("select * from news limit ?, ?");
                pst.setInt(1, (pageNumer - 1) * pageSize); // 设置limit的offset（起始行偏移值，0表达第1行）参数
                pst.setInt(2, pageSize); // 设置limit的count（最大返回行数）参数

                rs = pst.executeQuery();

                //封装结果为列表数据
                List<News> pagedData = new ArrayList<>();
                while (rs.next()) {
                    News news = new News();

                    news.setId(rs.getInt("id"));
                    news.setTitle(rs.getString("title"));
                    news.setImg(rs.getString("img"));
                    news.setContent(rs.getString("content"));
                    news.setPubDate(rs.getTimestamp("pubdate"));
                    news.setStick(rs.getBoolean("stick"));
                    news.setClickCount(rs.getInt("click_count"));

                    /**
                     * 获取编辑信息（先获取编号，然后根据编号获取全部信息）
                     */
                    int editorId = rs.getInt("editor");
                    AdminDao adminDao = new AdminDaoImpl();
                    Admin editor = adminDao.getAdminById(editorId);
                    news.setEditor(editor);

                    news.setCommentNum(this.getCommentNumByNewsId(news.getId())); // 关联查询

                    pagedData.add(news);
                }
                page.setPagedData(pagedData); //设置分页查询结果数据

                //释放连接资源
                pst.close();
                rs.close();
                st.close();
                con.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return page;
    }

    public static void main(String[] args) {
        NewsDao newsDao = new NewsDaoImpl();
//        System.out.println(newsDao.getNewsByCategory(1));
//        System.out.println(newsDao.getStickNews(5));
//        newsDao.updateClickCount(1);

        System.out.println(newsDao.getPagedNews(1,2));
    }
}
