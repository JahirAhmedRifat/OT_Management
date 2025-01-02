package com.ibn.OT_Management_System.entity;

import com.ibn.OT_Management_System.common.BaseEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "rooms")
public class Room extends BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id",nullable = false,unique = true,updatable = false)
    private Long id;

    @Column(name = "room_id",unique = true)
    private String roomId;

    @Column(name = "room_name")
    private String roomName;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }


}
