package com.ibn.OT_Management_System.controller;

import com.ibn.OT_Management_System.DTO.ApiResponse;
import com.ibn.OT_Management_System.DTO.RoomDto;
import com.ibn.OT_Management_System.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/room")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createRoom(@RequestBody RoomDto roomDto){
        try {
            RoomDto room = this.roomService.createRoom(roomDto);
            return new ResponseEntity<ApiResponse>(new ApiResponse("Room create successful!",room,true), HttpStatus.CREATED);
        }catch (Exception ex){
            return new ResponseEntity<ApiResponse>(new ApiResponse("Room create failed!",false), HttpStatus.OK);
        }

    }

    @GetMapping("/getAll")
    public ResponseEntity<List<RoomDto>> getAllRoom(){
        List<RoomDto> allRoom = this.roomService.getAllRoom();
        return new ResponseEntity<List<RoomDto>>(allRoom,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomDto> getSingleRoom(@PathVariable Long id){
        RoomDto singleRoom = this.roomService.getSingleRoom(id);
        return new ResponseEntity<RoomDto>(singleRoom,HttpStatus.OK);
    }

    @PutMapping("/updated/{id}")
    public ResponseEntity<RoomDto> updateRoom(@RequestBody RoomDto roomDto,@PathVariable Long id){
        RoomDto room = this.roomService.updateRoom(roomDto,id);
        return new ResponseEntity<RoomDto>(room, HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<ApiResponse> deleteRoom(@PathVariable Long id){
        this.roomService.deleteRoom(id);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Room is deleted successfully !!",true),HttpStatus.OK);
    }

}
