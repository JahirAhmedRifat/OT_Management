package com.ibn.OT_Management_System.common;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@MappedSuperclass
@Data
public abstract class BaseEntity <ID>{

    @CreationTimestamp
    @Column(name = "created_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createdDate;

    @CreationTimestamp
    @Column(name = "last_modified_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date lastModifiedDate;

//    @Column(name = "modified_by", nullable = true)
//    private Long modified_by;

//    public abstract ID getId();

//    @PrePersist
//    public void prePersist() {
//        this.createdDate = new Date();
//        this.lastModifiedDate = new Date();
//        if(SecurityContextHolder.getContext().getAuthentication() !=null ){
//            if(!(SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof String)) {
//                AuthUser user = (AuthUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//                this.modified_by = user.getId();
//            }
//        }
//    }

    @PreUpdate
    public void preUpdate() {
        this.lastModifiedDate = new Date();
    }

}
