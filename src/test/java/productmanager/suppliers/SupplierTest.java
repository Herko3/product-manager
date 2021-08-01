package productmanager.suppliers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SupplierTest {

    Supplier supplier;

    @BeforeEach
    void init(){
        supplier = new Supplier("Goki","Germany","Random street 15", Currency.EUR);
    }

    @Test
    void updateName(){
        supplier.update(new UpdateSupplierCommand("Legler",null,null,null));

        assertEquals("Legler", supplier.getName());
        assertEquals("Germany", supplier.getCountry());
    }

    @Test
    void updateCountry(){
        supplier.update(new UpdateSupplierCommand(null,"Netherland",null,null));

        assertEquals("Netherland", supplier.getCountry());
        assertEquals(Currency.EUR, supplier.getCurrency());
    }

    @Test
    void updateAddress(){
        supplier.update(new UpdateSupplierCommand("",null,"Bp 1155",null));

        assertEquals("Goki", supplier.getName());
        assertEquals("Bp 1155", supplier.getAddress());
    }

    @Test
    void updateCurrency(){
        supplier.update(new UpdateSupplierCommand(null,null,null,Currency.HUF));

        assertEquals("Random street 15", supplier.getAddress());
        assertEquals(Currency.HUF, supplier.getCurrency());
    }

}