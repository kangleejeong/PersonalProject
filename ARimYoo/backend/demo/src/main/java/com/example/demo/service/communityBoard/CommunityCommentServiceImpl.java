package com.example.demo.service.communityBoard;

import com.example.demo.entity.communityBoard.CommunityBoard;
import com.example.demo.entity.communityBoard.CommunityCommentBox;
import com.example.demo.repository.CommunityBoard.CommunityBoardRepository;
import com.example.demo.repository.CommunityBoard.CommunityCommentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class CommunityCommentServiceImpl implements CommunityCommentService {

    @Autowired
    CommunityBoardRepository boardRepository;

    @Autowired
    CommunityCommentRepository repository;

   @Override
    public void register (Long boardNo, CommunityCommentBox communityComment){
       Optional<CommunityBoard> boardItem = boardRepository.findById(Long.valueOf(boardNo));
       communityComment.setCommunityBoard(boardItem.get());
       repository.save(communityComment);
    }

    @Override
    public List<CommunityCommentBox> list (Long boardNo) {
       CommunityBoard communityBoard = boardRepository.findById(boardNo).get();
        return repository.findCommunityCommentBoxByCommunityBoard(communityBoard);
    }

    @Override
    public void delete (Long commentId) {
        repository.deleteById(Long.valueOf(commentId));
    }
}
