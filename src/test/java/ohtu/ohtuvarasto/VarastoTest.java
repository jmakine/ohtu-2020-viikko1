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
    Varasto newVarasto;
    Varasto nollaVarasto;
    Varasto newNollaVarasto;
    Varasto miinusVarasto;
    Varasto yliVarasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
        newVarasto = new Varasto(10,0);
        nollaVarasto = new Varasto(0);
        newNollaVarasto = new Varasto(0, 0);
        miinusVarasto = new Varasto(1, -1);
        yliVarasto = new Varasto(2, 3);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
        assertEquals(0, nollaVarasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void konstruktoriArvoillaToimii() {
        assertEquals(0, newVarasto.getSaldo(), vertailuTarkkuus);
        assertEquals(10, newVarasto.getTilavuus(), vertailuTarkkuus);
        assertEquals(0, newNollaVarasto.getSaldo(), vertailuTarkkuus);
        assertEquals(0, newNollaVarasto.getTilavuus(), vertailuTarkkuus);       
    }

    @Test
    public void konstruktoriYlitaydellaToimii() {
        assertEquals(2, yliVarasto.getSaldo(), vertailuTarkkuus);
        assertEquals(2, yliVarasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void konstruktoriNegatiivisellaAlkusaldollaToimii() {
        assertEquals(0, miinusVarasto.getSaldo(), vertailuTarkkuus);
        assertEquals(1, miinusVarasto.getTilavuus(), vertailuTarkkuus);
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
    public void negatiivinenLisaysEiMuutaSaldoa(){
        newVarasto.lisaaVarastoon(-1);
        assertEquals(0, newVarasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void negatiivinenPoistoEiMuutaSaldoa(){
        newVarasto.otaVarastosta(-1);
        assertEquals(0, newVarasto.getSaldo(), vertailuTarkkuus);
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
    public void varastoonEiVoiLaittaaLiikaa() {
        varasto.lisaaVarastoon(11);

        //Varastoon mahtuu vain 10. Jos lisätään 11, tulee saldon olla silti 10.
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void varastostaEiVoiOttaaLiikaa() {
        varasto.otaVarastosta(1);

        //Varastossa on 0. Jos yritetään ottaa 1, tulee saldon olla silti 0.
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void tulosteToimii(){
        assertEquals("saldo = 0.0, vielä tilaa 10.0", newVarasto.toString());
    }

}