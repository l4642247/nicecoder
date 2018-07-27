package cn.nicecoder.mapper;

import cn.nicecoder.domain.TblDaily;
import cn.nicecoder.domain.TblTag;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TblTagMapper {
    TblTag selectByTagname(String tagName);
    int insert(TblTag tblTag);
}
