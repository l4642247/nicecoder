package cn.nicecoder.mapper;

import cn.nicecoder.domain.TblTagDaily;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface TblTagDailyMapper {
    int insert(TblTagDaily tblTagDaily);
    TblTagDaily selectByTagidAndDailyId(Map<String, Integer> queryMap);
}
