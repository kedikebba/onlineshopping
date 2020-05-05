package edu.miu.pm.onlineshopping.product.model;

import edu.miu.pm.onlineshopping.admin.model.Vendor;
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
//    @ManyToOne()
//    @JoinColumn(name = "vendorId", referencedColumnName="id",  nullable = false)
//    private Vendor vendorId; //vendor of the product.

    public ProductDetail(@NotNull(message = "*Product detail name is required") String detailName, String description, @NotNull int quantity, Product product) {
        this.detailName = detailName;
        this.description = description;
        this.quantity = quantity;
        this.product = product;

    }
}
