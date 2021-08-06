package productmanager.tender;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import productmanager.products.Product;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Entity
@Table(name = "tenders")
public class Tender {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany
    @JoinTable(name = "products_to_tenders",
    joinColumns = @JoinColumn(name = "tender_id"),
    inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products;

    @Column(name = "quotation_date")
    private LocalDate quotationDate;

    public Tender(String name, LocalDate quotationDate) {
        this.name = name;
        this.quotationDate = quotationDate;
    }

    public void update(String updateName, LocalDate updatedDate) {
        if(updateName !=null && !updateName.isBlank()){
            setName(updateName);
        }
        if(updatedDate != null){
            setQuotationDate(updatedDate);
        }
    }
}
