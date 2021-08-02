package productmanager.products;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    Product product;

    @BeforeEach
    void init(){
        product = new Product("28503","Montessori Torony","20 cm-es montessori torony",ProductType.WOODEN_TOY,1257,2990);
    }

    @Test
    void testUpdateArticle(){
        product.update(new UpdateProductCommand("28502","",null,null,null,null,null));

        assertEquals("28502",product.getArticleNumber());
        assertEquals("Montessori Torony",product.getName());
    }

    @Test
    void testUpdateName(){
        product.update(new UpdateProductCommand("","Puzzle",null,null,null,null,null));

        assertEquals("28503",product.getArticleNumber());
        assertEquals("Puzzle",product.getName());
    }

    @Test
    void testUpdateType(){
        product.update(new UpdateProductCommand(null,null,"",ProductType.BOOK,null,null,null));

        assertEquals(ProductType.BOOK,product.getType());
        assertEquals("20 cm-es montessori torony",product.getDescription());
    }

    @Test
    void testUpdateDescription(){
        product.update(new UpdateProductCommand(null,null,"Puzzle fából",null,null,null,null));

        assertEquals(ProductType.WOODEN_TOY,product.getType());
        assertEquals("Puzzle fából",product.getDescription());
    }

    @Test
    void testUpdateNePrice(){
        product.update(new UpdateProductCommand(null,null,null,null,1550L,null,null));

        assertEquals(1550,product.getNetPrice());
        assertEquals(2990,product.getGrossPrice());
    }

    @Test
    void testUpdateGrossPrice(){
        product.update(new UpdateProductCommand(null,null,null,null,null,3150L,null));

        assertEquals(1257,product.getNetPrice());
        assertEquals(3150,product.getGrossPrice());
    }

}