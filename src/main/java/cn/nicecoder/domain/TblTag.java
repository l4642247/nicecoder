package cn.nicecoder.domain;

/**
 * @Author: lt
 * @Description: 标签类
 *
 * @Date: 下午10:36 2018/6/7
 */
public class TblTag {
    private Integer id;

    private String tagname;

    private String createtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTagname() {
        return tagname;
    }

    public void setTagname(String tagname) {
        this.tagname = tagname;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }
}