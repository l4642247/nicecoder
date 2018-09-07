package cn.nicecoder.service;

import cn.nicecoder.domain.TblComment;
import org.springframework.stereotype.Service;

@Service
public interface CommentService {
   void commentListDeal(TblComment tblComment);
}
