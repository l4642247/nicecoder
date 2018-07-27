package cn.nicecoder.mapper;

import cn.nicecoder.domain.TblDaily;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TblDailyMapper {
    List<TblDaily> findAll();
    int insert(TblDaily tblDaily);
    TblDaily findByPrimaryKey(int id);
}
