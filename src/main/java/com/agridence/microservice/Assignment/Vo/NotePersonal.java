package com.agridence.microservice.Assignment.Vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class NotePersonal implements Serializable {
    private Integer Id;
    private String title;
}
