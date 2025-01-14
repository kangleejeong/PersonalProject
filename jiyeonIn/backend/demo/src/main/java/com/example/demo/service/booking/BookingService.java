package com.example.demo.service.booking;

import com.example.demo.controller.bookingController.request.BookingReadRequest;
import com.example.demo.controller.bookingController.request.BookingRequest;
import com.example.demo.entity.booking.BookingInfo;

import java.util.List;

public interface BookingService {
    public void register(BookingRequest info, String originalFilename);

    public void exceptFilesBooking(BookingRequest info);

    public List<BookingInfo> list();


    public void modify(BookingInfo bookingInfo);

    public BookingInfo read(Integer checkBookingNo, String checkId);

    public List<BookingInfo> checkIdlist(String checkId);
}
