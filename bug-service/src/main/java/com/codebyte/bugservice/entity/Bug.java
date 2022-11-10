package com.codebyte.bugservice.entity;

import com.codebyte.bugservice.converter.ULIDConverter;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "bugs")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Bug{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    private String name;

    private String description;

    private int severity;

    private int status;

    @Convert(converter = ULIDConverter.class)
    private String productUlid;

    @Convert(converter = ULIDConverter.class)
    private String assignByUlid;

    @Convert(converter = ULIDConverter.class)
    private String assignToUlid;

    private LocalDate createdDate;

    private LocalDate updatedDate;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "bugId")
    private List<Comment> comments;


}
