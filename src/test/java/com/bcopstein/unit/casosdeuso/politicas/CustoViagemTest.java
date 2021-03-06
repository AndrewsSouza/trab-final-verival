package com.bcopstein.unit.casosdeuso.politicas;

import com.bcopstein.casosdeuso.politicas.CalculoCustoViagem;
import com.bcopstein.casosdeuso.politicas.CalculoCustoViagemBasico;
import com.bcopstein.casosdeuso.politicas.CustoViagem;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CustoViagemTest {

    @Test
    void custoViagemTest() {
        CalculoCustoViagem ccv = mock(CalculoCustoViagemBasico.class);
        when(ccv.custoViagem()).thenReturn(35.0);
        double rEsp = 35.0;
        CustoViagem cv = new CustoViagem(ccv);
        double rObs = cv.custoViagem(null, null);
        assertEquals(rEsp, rObs, 0.0001);
    }
}
