package org.revo.track.Domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
@Getter
@Setter
public class Location {
    @Id
    private String id;
    private String trackerId;
    private Date date;
    private Double lat;
    private Double lng;
}
