package productmanager.tender;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class TenderTest {

    Tender tender = new Tender("Tender 505", LocalDate.of(2020,10,10));

    @Test
    void updateName(){
        tender.update("Tender 402",null);

        assertEquals("Tender 402", tender.getName());
        assertEquals(LocalDate.of(2020,10,10), tender.getQuotationDate());
    }

    @Test
    void updateDate(){
        tender.update("",LocalDate.of(2021,4,4));

        assertEquals("Tender 505", tender.getName());
        assertEquals(LocalDate.of(2021,4,4), tender.getQuotationDate());
    }

}