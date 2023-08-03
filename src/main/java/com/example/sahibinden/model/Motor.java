
package com.example.sahibinden.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Motor {
    @Id
    private Long id;
    private Double motorgucu;
    private Double motorhacmi;
    private Double silindirhacmi;
    private Double silindirsayisi;
    private Double tork;

}
