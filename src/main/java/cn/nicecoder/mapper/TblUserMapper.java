package cn.nicecoder.mapper;

import cn.nicecoder.domain.TblUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TblUserMapper {
    TblUser selectByPrimaryKey(int id);
    TblUser selectByEmail(String email);
    int insert(TblUser tblUser);
}
