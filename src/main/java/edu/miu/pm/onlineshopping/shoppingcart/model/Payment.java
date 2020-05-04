package edu.miu.pm.onlineshopping.shoppingcart.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String method;
    private String currency;
    private boolean isCompleted;
    private boolean isErrorHappened;
    private TypeOfError typeOfError;

    private String intent = "purchasing on ONline shopping app";
    private String description = "Used to purchase items on online shopping application";
}
