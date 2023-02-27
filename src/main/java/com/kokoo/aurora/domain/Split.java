package com.kokoo.aurora.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Builder
@Entity
@Table(name = "split")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Split {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
}
