package com.example.sahibinden.model.entity;

import com.example.sahibinden.model.Motor;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
@Table(name = "Motor")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MotorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double guc;
    private Double hacim;
    private Double silindirsayisi;
    private Double tork;
    private String name;
    private String yil;
    @Column(unique = true)
    private String shortName;


    @ManyToOne
    @JsonIgnore
    private KasaEntity kasa;

    public static MotorEntity fromModel(Motor motor) {
        if (motor==null){
            return null;
        }
     return MotorEntity.builder()
             .id(motor.getId())
             .guc(motor.getGuc())
             .hacim(motor.getHacim())
             .silindirsayisi(motor.getSilindirsayisi())
             .tork(motor.getTork())
             .name(motor.getName())
             .yil(motor.getYil())
             .shortName(motor.getShortName())
             .kasa(KasaEntity.fromModel(motor.getKasa()))
             .build();

    }


}
