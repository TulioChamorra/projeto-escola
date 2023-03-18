package io.github.tuliochamorra.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 150)
    private String name;

    @Column(nullable = false, length = 11)
    private String dateBirth;

    @Column(nullable = false, length = 11)
    private String gender;

    @Column(nullable = false, length = 9)
    private String rg;

    @Column(nullable = false, length = 11)
    private String cpf;

    @Column(nullable = false, length = 150)
    private String fatherName;

    @Column(nullable = false, length = 150)
    private String motherName;

    @Column(nullable = false, length = 11)
    private String phone;

    @Column(nullable = false, length = 50)
    private String email;



}
