package cn.nicecoder.mapper;

import cn.nicecoder.domain.TblComment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface TblCommentMapper {
    int insert(TblComment tblComment);
    List<TblComment> findAll(Map queryMap);
    List<TblComment> findAllSub(Map queryMap);
}
