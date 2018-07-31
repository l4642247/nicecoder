package cn.nicecoder.mapper;

import cn.nicecoder.domain.TblType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TblTypeMapper {
    List<TblType> findAll();
    void increase(Integer id);
    List<TblType> selectFirstFive();
}
