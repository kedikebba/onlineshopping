package edu.miu.pm.onlineshopping.product.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Table(name = "product_Detail")
@Data
@Getter
@Setter
@NoArgsConstructor
@Entity
public class ProductDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "detail_id")
    private long detailId;
    @NotNull(message = "*Product detail name is required")
    @Column(name = "detail_name")
    private String detailName;
    @Column(name = "description")
    private  String description;
    @NotNull
    private int quantity;
    @OneToOne
    @JoinColumn(name = "productId", nullable = false)
    private  Product product;


    public ProductDetail(@NotNull @NotEmpty String detailName, String description, int quantity, Product product) {
        this.detailName = detailName;
        this.description = description;
        this.quantity = quantity;
        this.product = product;

    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
