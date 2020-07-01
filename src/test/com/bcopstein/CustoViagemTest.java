package com.bcopstein;

import com.bcopstein.casosDeUso.Politicas.CalculoCustoViagem;
import com.bcopstein.casosDeUso.Politicas.CalculoCustoViagemBasico;
import com.bcopstein.casosDeUso.Politicas.CustoViagem;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CustoViagemTest {

    @Test
    public void custoViagemTTest() {
        CalculoCustoViagem ccv = mock(CalculoCustoViagemBasico.class);
        when(ccv.custoViagem()).thenReturn(35.0);
        double rEsp = 35.0;
        CustoViagem cv = new CustoViagem(ccv);
        double rObs = cv.custoViagem(null, null);
        assertEquals(rEsp, rObs, 0.0001);
    }
}
