package com.ibn.OT_Management_System.repository;

import com.ibn.OT_Management_System.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepo extends JpaRepository<Room,Long> {

}
