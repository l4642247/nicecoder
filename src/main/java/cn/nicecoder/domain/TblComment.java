package cn.nicecoder.domain;

import java.util.List;

public class TblComment{
    private Integer id;

    private String type;

    private String discussid;

    private int userid;

    private String pudate;

    private String agree;

    private String status;

    private String ob1;

    private String ob2;

    private int touserid;

    private byte[] content;

    //添加
    List<TblComment> tblCommentList;
    private String image;
    private String name;
    private String toimage;
    private String toname;

    public List<TblComment> getTblCommentList() {
        return tblCommentList;
    }

    public void setTblCommentList(List<TblComment> tblCommentList) {
        this.tblCommentList = tblCommentList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDiscussid() {
        return discussid;
    }

    public void setDiscussid(String discussid) {
        this.discussid = discussid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getPudate() {
        return pudate;
    }

    public void setPudate(String pudate) {
        this.pudate = pudate;
    }

    public String getAgree() {
        return agree;
    }

    public void setAgree(String agree) {
        this.agree = agree;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOb1() {
        return ob1;
    }

    public void setOb1(String ob1) {
        this.ob1 = ob1;
    }

    public String getOb2() {
        return ob2;
    }

    public void setOb2(String ob2) {
        this.ob2 = ob2;
    }

    public int getTouserid() {
        return touserid;
    }

    public void setTouserid(int touserid) {
        this.touserid = touserid;
    }

    public String getToimage() {
        return toimage;
    }

    public void setToimage(String toimage) {
        this.toimage = toimage;
    }

    public String getToname() {
        return toname;
    }

    public void setToname(String toname) {
        this.toname = toname;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
}