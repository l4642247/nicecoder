package cn.nicecoder.mapper;

import cn.nicecoder.domain.TblTag;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface TblTagMapper {
    TblTag selectByTagName(String tagName);
    ArrayList<TblTag> selectByDailyId(Integer dailyid);
    int insert(TblTag tblTag);
    ArrayList<TblTag> findAll();
}
