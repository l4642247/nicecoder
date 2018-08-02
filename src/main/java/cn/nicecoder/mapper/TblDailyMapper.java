package cn.nicecoder.mapper;

import cn.nicecoder.domain.TblDaily;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface TblDailyMapper {
    List<TblDaily> findAll();
    List<TblDaily> findAllByCondition(HashMap querymap);
    int insert(TblDaily tblDaily);
    TblDaily findByPrimaryKey(int id);
    List<TblDaily> selectHotEight();
    List<TblDaily> findNearById(int id);
    int getCount(@Param("display") String display);
}
