package cn.nicecoder.service;

import cn.nicecoder.domain.TblComment;
import cn.nicecoder.util.DateUtil;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Override
    public void commentListDeal(TblComment tblComment) {
            tblComment.setOb1(new String(tblComment.getContent()));
            String pudate = tblComment.getPudate();
            tblComment.setPudate(DateUtil.getDateTime(tblComment.getPudate()));
    }
}
