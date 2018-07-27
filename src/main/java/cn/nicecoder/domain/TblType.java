package cn.nicecoder.domain;

/**
 * @Author: lt
 * @Description: 类别表
 *
 * @Date: 下午10:37 2018/6/7
 */
public class TblType {
    private Integer id;

    private String typename;

    private Integer count;

    private String creattime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    public String getCreattime() {
        return creattime;
    }

    public void setCreattime(String creattime) {
        this.creattime = creattime;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}