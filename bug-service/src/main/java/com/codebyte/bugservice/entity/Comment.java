package com.codebyte.bugservice.entity;

import com.codebyte.bugservice.converter.ULIDConverter;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "comments")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Convert(converter = ULIDConverter.class)
    private String commentUlid;

    private Long bugId;

    private String description;

    private LocalDate createdDate;

    @Convert(converter = ULIDConverter.class)
    private String createdBy;

    @Convert(converter = ULIDConverter.class)
    private String updatedBy;

    private LocalDate updatedDate;

}
