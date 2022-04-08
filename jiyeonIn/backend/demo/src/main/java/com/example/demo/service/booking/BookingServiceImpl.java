package com.example.demo.service.booking;


import com.example.demo.controller.bookingController.request.BookingRequest;
import com.example.demo.controller.uploadFileController.request.UploadRequest;
import com.example.demo.entity.booking.BookingInfo;
import com.example.demo.entity.uploadCake.UploadCake;
import com.example.demo.repository.booking.BookingRepository;
import com.example.demo.repository.upload.UploadRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Slf4j
@Service
public class BookingServiceImpl implements BookingService{
    @Autowired
    BookingRepository repository;
    @Autowired
    UploadRepository uploadRepository;

    @Transactional
    @Override
    public void register(BookingRequest info, String originalFilename) {
        String putLink = info.getDate()+"."+info.getId()+"."+originalFilename;

        if(info.getCakeArrNo().equals("0")) {
            BookingInfo bookingInfo2 = new BookingInfo(info.getId(), info.getDate(), info.getTime(), "예약중", info.getContents(), putLink);
            repository.save(bookingInfo2);

        }else {
            Optional<UploadCake> uploadCake = uploadRepository.findById(Long.valueOf(info.getCakeArrNo()));
            UploadCake checkCake = uploadCake.get();

            BookingInfo bookingInfo1 =
                    new BookingInfo(info.getId(), info.getDate(), info.getTime(), "예약중", info.getContents(), putLink,
                            checkCake.getDesign(),checkCake.getSize(),checkCake.getPrice());
            repository.save(bookingInfo1);
        }

    }

    @Override
    public void exceptFilesBooking(BookingRequest info) {

        if(info.getCakeArrNo().equals("0")) {
            BookingInfo bookingInfo2 = new BookingInfo(info.getId(), info.getDate(), info.getTime(), "예약중", info.getContents());
            repository.save(bookingInfo2);

        }else {
            Optional<UploadCake> uploadCake = uploadRepository.findById(Long.valueOf(info.getCakeArrNo()));
            UploadCake checkCake = uploadCake.get();

            BookingInfo bookingInfo1 =
                    new BookingInfo(info.getId(), info.getDate(), info.getTime(), "예약중", info.getContents(), checkCake.getLinkInfo(),
                            checkCake.getDesign(),checkCake.getSize(),checkCake.getPrice());
            repository.save(bookingInfo1);

        }
    }

}
