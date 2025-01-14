package com.example.demo.repository.board.photoBoard;

import com.example.demo.entitiy.board.photoBoard.PhotoBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PhotoBoardRepository extends JpaRepository<PhotoBoard, Long> {

    @Query("select m.fileName from PhotoBoard m where m.boardNo = :boardNo")
    Optional<PhotoBoard> findFileName(Long boardNo);
}
