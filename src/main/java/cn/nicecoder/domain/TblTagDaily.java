package cn.nicecoder.domain;

/**
 * @Author: lt
 * @Description: 中间表
 *
 * @Date: 下午10:37 2018/6/7
 */
public class TblTagDaily {
    private Integer id;

    private Integer tagid;

    private Integer dailyid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTagid() {
        return tagid;
    }

    public void setTagid(Integer tagid) {
        this.tagid = tagid;
    }

    public Integer getDailyid() {
        return dailyid;
    }

    public void setDailyid(Integer dailyid) {
        this.dailyid = dailyid;
    }
}