package edu.miu.pm.onlineshopping.product.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;

@Table(name="category")
@Getter@Setter
@NoArgsConstructor
@Entity
public class Category implements  Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long categoryId;
    @Column(name = "category_name")
    private String categoryName;
    @Column(name = "description")
    private String description;
    private int status;
    @Column(name = "modified_date")
    private LocalDate modifiedDate;

    public Category( String categoryName, String description, int status, LocalDate modifiedDate) {
        this.categoryName = categoryName;
        this.description = description;
        this.status = status;
        this.modifiedDate = modifiedDate;
    }


}
