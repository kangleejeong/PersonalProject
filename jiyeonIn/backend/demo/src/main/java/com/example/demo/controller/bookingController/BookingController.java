package com.example.demo.controller.bookingController;

import com.example.demo.controller.bookingController.request.BookingReadRequest;
import com.example.demo.controller.bookingController.request.BookingRequest;
import com.example.demo.entity.booking.BookingInfo;
import com.example.demo.service.booking.BookingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/booking")
@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*")
public class BookingController {

    @Autowired
    BookingService service;

    @ResponseBody
    @PostMapping(value = "/register", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public String uploadBookingContents ( @RequestPart(value = "info", required = false) BookingRequest info,
                                          @RequestPart(value = "fileList", required = false) List<MultipartFile> fileList) {

        log.info("uploadBookingContents()" + info);
        log.info("uploadImg()" + fileList);

        if (fileList != null) {
            try{
                for(MultipartFile multipartFile : fileList) {
                    log.info("requestUploadFile() - Make file: " +
                            multipartFile.getOriginalFilename());

                    FileOutputStream writer = new FileOutputStream(
                            "../../vue_frontend/book_cake/src/assets/bookingImg/"+info.getDate()+"."+info.getId()+"."+multipartFile.getOriginalFilename());
                    log.info("디렉토리에 파일 배치 성공!");

                    writer.write(multipartFile.getBytes());
                    writer.close();
                    service.register(info, multipartFile.getOriginalFilename());
                }
            } catch (Exception e) {
                return "Upload Fail!!";
            }
        } else if (fileList == null) {
            service.exceptFilesBooking(info);
        }


        log.info("requestUpload(): Success!!!");
        return "Upload Success!!";
    }

    @GetMapping("/list")
    public List<BookingInfo> bookingList() {
        log.info("bookingList()");


        return service.list();
    }

    @GetMapping("/list/{checkId}")
    public List<BookingInfo> bookingList(@PathVariable("checkId") String checkId) {
        log.info("bookingList()");


        return service.checkIdlist(checkId);
    }

    @RequestMapping(value = "/read",  method = RequestMethod.GET)
    public BookingInfo bookingReadBoard (@RequestParam(value = "checkBookingNo", required = false) Integer checkBookingNo,
                                         @RequestParam(value = "checkId", required = false) String checkId) {


        log.info("checkNo:" +checkBookingNo + "id:" +checkId);

        return service.read(checkBookingNo,checkId);
    }

    @PutMapping("/{bookingNo}")
    public BookingInfo modifyBookingBoard (
            @PathVariable("bookingNo") Integer bookingNo,
            @RequestBody BookingInfo bookingInfo) {

        log.info("modifyBookingNo() :" + bookingNo);
        log.info("modifyBookingBoard() :" + bookingInfo);

        bookingInfo.setBookingNo(Long.valueOf(bookingNo));
        service.modify(bookingInfo);

        return bookingInfo;
    }

}
