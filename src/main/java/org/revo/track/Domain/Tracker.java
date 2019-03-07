package org.revo.track.Domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Tracker {
    @Id
    private String id;
    private String account[];
    private Date createdDate;
    private Date lastUpdateCall;
    private Date lastUpdateLocation;
    
    public Tracker(String id) {
        this.id = id;
    }
}
