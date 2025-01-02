package com.ibn.OT_Management_System.service.Impl;

import com.ibn.OT_Management_System.DTO.RoomDto;
import com.ibn.OT_Management_System.common.ResourceNotFoundException;
import com.ibn.OT_Management_System.entity.Room;
import com.ibn.OT_Management_System.repository.RoomRepo;
import com.ibn.OT_Management_System.service.RoomService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomRepo roomRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public RoomDto createRoom(RoomDto roomDto) {
        Room room = this.modelMapper.map(roomDto,Room.class);
//        Room room = new Room();
//        room.setRoomId(roomDto.getRoomId());
//        room.setRoomName(roomDto.getRoomName());
//        room.setStatus(roomDto.getStatus());
        Room roomSave = this.roomRepo.save(room);
        return this.modelMapper.map(roomSave,RoomDto.class);
    }

    @Override
    public RoomDto updateRoom(RoomDto roomDto, Long id) {
        Room room = this.roomRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Room","Id",id));
        room.setRoomId(roomDto.getRoomId());
        room.setRoomName(roomDto.getRoomName());
        Room save = this.roomRepo.save(room);
        return this.modelMapper.map(save,RoomDto.class);
    }

    @Override
    public void deleteRoom(Long id) {
        Room room = this.roomRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Room","Id",id));
        this.roomRepo.delete(room);
    }

    @Override
    public List<RoomDto> getAllRoom() {
        List<Room> allRoom = this.roomRepo.findAll();
        List<RoomDto> allRoomDto = allRoom.stream().map((room)-> this.modelMapper.map(room,RoomDto.class)).collect(Collectors.toList());
        return allRoomDto;
    }

    @Override
    public RoomDto getSingleRoom(Long id) {
        Room room = this.roomRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Room","Id",id));
        RoomDto roomDto = this.modelMapper.map(room,RoomDto.class);
        return roomDto;
    }

}
