package productmanager.products;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import productmanager.suppliers.Supplier;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "article_number")
    private String articleNumber;

    private String name;

    private String description;

    private ProductType type;

    @Column(name = "net_price")
    private long netPrice;

    @Column(name = "gross_price")
    private long grossPrice;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    public Product(String articleNumber, String name, String description, ProductType type, long netPrice, long grossPrice) {
        this.articleNumber = articleNumber;
        this.name = name;
        this.description = description;
        this.type = type;
        this.netPrice = netPrice;
        this.grossPrice = grossPrice;
    }

    public void update(UpdateProductCommand command) {
        String article = command.getArticleNumber();
        String nameUpdate = command.getName();
        String descriptionUpdate = command.getDescription();

        if (notEmpty(article)) {
            setArticleNumber(article);
        }

        if (notEmpty(nameUpdate)) {
            setName(nameUpdate);
        }

        if (notEmpty(descriptionUpdate)) {
            setDescription(descriptionUpdate);
        }

        if (command.getType() != null){
            setType(command.getType());
        }

        if(command.getNetPrice() != null){
            setNetPrice(command.getNetPrice());
        }

        if(command.getGrossPrice() != null){
            setNetPrice(command.getGrossPrice());
        }
    }

    private boolean notEmpty(String s) {
        return s != null && !s.isBlank();
    }
}
