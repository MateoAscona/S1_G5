package com.Sprint1.Sprint1.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data

@Entity
@Table(name = "status_code")
public class StatusCodeObject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private Integer code;
    @Column
    private String mensaje;

    public StatusCodeObject(int i, String ok) {
    }
}
