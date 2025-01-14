package com.example.demo.controller.board.videoBoard;

import com.example.demo.entitiy.board.videoBoard.VideoBoard;
import com.example.demo.service.board.videoBoard.VideoBoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.time.LocalDate;
import java.util.List;


@Slf4j
@RestController
@RequestMapping("/videoBoard")
@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*")
public class VideoBoardController {

    @Autowired
    private VideoBoardService service;

    //등록
    @PostMapping("/register")
    public void VideoBoardRegister (@Validated @RequestBody VideoBoard videoBoard) {
        log.info("VideoBoardRegister()");
        LocalDate now = LocalDate.now();
        String fileName = now + videoBoard.getFileName();
        videoBoard.setFileName(fileName);
        service.register(videoBoard);
    }
    //목록
    @GetMapping("/list")
    public List<VideoBoard> VideoBoardList () {
        log.info("VideoBoardList()");

        return service.list();
    }
    //읽기
    @GetMapping("/{boardNo}")
    public VideoBoard videoBoardRead (
            @PathVariable("boardNo") Integer boardNo) {
        log.info("videoBoardRead()");

        return service.read(boardNo);
    }
    //파일 업로드
    @ResponseBody
    @PostMapping("/uploadImg")
    public String requestUploadFile (
            @RequestParam("fileList") List<MultipartFile> fileList) {

        log.info("requestUploadFile(): " + fileList);

        try {
            for (MultipartFile multipartFile : fileList) {
                log.info("requestUploadFile() - Make file: " +
                        multipartFile.getOriginalFilename());

                //일단 저장된 위치에 이름 중복 될까봐 앞에 날짜 붙였는데 더좋은 방법 없나
                //혹시 하루에 똑같은 이름 될 수도 있으니 시간까지 할려다가 너무 길어져서 일단 보류 함함
               LocalDate now = LocalDate.now();
                //LocalTime now = LocalTime.now();


                FileOutputStream writer = new FileOutputStream(
                        "../../frontend/src/assets/uploadVideo/"+ now + multipartFile.getOriginalFilename());


                log.info("디렉토리에 파일 배치 성공!");

                writer.write(multipartFile.getBytes());
                writer.close();
            }
        } catch (Exception e) {
            return "Upload Fail!!!";
        }

        log.info("requestUploadFile(): Success!!!");

        return "Upload Success!!!";
    }
    //수정
    @PutMapping("/{boardNo}")
    public VideoBoard videoBoardModify (
            @PathVariable("boardNo") Integer boardNo,
            @RequestBody VideoBoard videoBoard) {
        log.info("videoBoardModify(): " + videoBoard);

        videoBoard.setBoardNo(Long.valueOf(boardNo));
        //수정 할 때 파일은 첨부하지 않고 넣었을 경우가 있어 db에서 파일명 가져올 수 있도록 하기
        String checkFileName = videoBoard.getFileName();

            if(checkFileName != null){
            LocalDate now = LocalDate.now();
            String fileName = now + videoBoard.getFileName();
                videoBoard.setFileName(fileName);

            }

            service.modify(videoBoard,boardNo);

        return videoBoard;
    }

    @DeleteMapping("/{boardNo}")
    public void videoBoardRemove (
            @PathVariable("boardNo") Integer boardNo) {
        log.info("videoBoardRemove()");

        service.remove(boardNo);
    }

}
