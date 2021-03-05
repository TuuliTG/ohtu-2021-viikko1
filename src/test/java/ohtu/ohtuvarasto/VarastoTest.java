package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    Varasto varasto2;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
        varasto2 = new Varasto(10, 2);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void tilavuus0josvirheellinentilavuus() {
        Varasto varasto0 = new Varasto(-10);
        assertEquals(0, varasto0.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    
    @Test
    public void toinenkonstruktoriLuoOikeanTilavuuden() {
        assertEquals(10, varasto2.getTilavuus(),vertailuTarkkuus);
     
    }
    
    @Test
    public void toinenKonstruktoriLuoOikeanSaldon() {
        assertEquals(2, varasto2.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void saldoPienempiKuinNolla() {
        Varasto v = new Varasto(10, -1);
        assertEquals(0, v.getSaldo(),vertailuTarkkuus);
    }
    
    @Test
    public void tilavuusPienempiKuinNolla() {
        Varasto v = new Varasto(-1, 0);
        assertEquals(0, v.getTilavuus(),vertailuTarkkuus);
    }
    
    @Test
    public void alkusaldoSuurempiKuinTilavuus() {
        Varasto v = new Varasto(10, 12);
        assertEquals(10, v.getSaldo(), vertailuTarkkuus);
        assertEquals(10, v.getTilavuus(),vertailuTarkkuus);
    }
    
    
    @Test
    public void negatLisaaminenEiLisaa() {
        varasto.lisaaVarastoon(-1);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void lisaaEnemmanKuinMahtuu() {
        varasto.lisaaVarastoon(15);
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void otaNegatiivinen() {
        varasto.lisaaVarastoon(2);
        varasto.otaVarastosta(-2);
        assertEquals(2, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void otaEnemmanKuinOn() {
        varasto.lisaaVarastoon(5);
        double maara = varasto.otaVarastosta(6);
        assertEquals(5, maara, vertailuTarkkuus);
        assertEquals(0, varasto.getSaldo(),vertailuTarkkuus);
        
    }
    
    @Test
    public void toStringTest() {
        varasto.lisaaVarastoon(2);
        String s  = varasto.toString();
        String oikea = "saldo = 2.0, vielä tilaa 8.0";
        assertEquals(oikea, s);
    }
}