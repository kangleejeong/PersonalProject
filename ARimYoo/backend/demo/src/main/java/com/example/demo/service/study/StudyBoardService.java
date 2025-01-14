package com.example.demo.service.study;

import com.example.demo.entity.communityBoard.CommunityBoard;
import com.example.demo.entity.study.Study;
import com.example.demo.entity.study.StudyBoard;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface StudyBoardService {

    public void register (StudyBoard study, Study studyGroup, @RequestParam(required = false) MultipartFile file) throws Exception;
    public List<StudyBoard> list ();
    public StudyBoard read (Long studyNo);
    public void modify (StudyBoard studyBoard) throws Exception;
    public void remove (Integer studyNo) throws Exception ;
}
