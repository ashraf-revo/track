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
public class Call {
    @Id
    private String id;
    private String trackerId;
    private Date date=new Date();
    private CallType callType;
    private String number;
    private int duration;
}
