import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class MascotaVirtualTest {

    private static MascotaVirtual getDefault(){
        return new MascotaVirtual(20, 5);
    }

    private static MascotaVirtual getMuerto(){
        return new MascotaVirtual(0, 0);
    }
    @Test
    public void comerIncrementaEnergiaOK(){
        var mv = new MascotaVirtual(20, 1);

        assertTrue(mv.comer());
        assertEquals(22, mv.getEnergia());
        assertEquals(2, mv.getHumor());
    }

    @Test
    public void comerNoSuperaLimiteOK(){
        var mv = new MascotaVirtual(100, 5);
        assertTrue(mv.comer());
        assertEquals(mv.getEnergia(), 100);
        assertEquals(mv.getHumor(), 5);
    }

    @Test
    public void comerMuerto(){
        var mv = getMuerto();
        assertFalse(mv.comer());
    }

    @Test
    public void actividadesMuerto(){
        var mv = getMuerto();
        assertFalse(mv.estaVivo());
        assertFalse(mv.comer());
        assertFalse(mv.beber());
        assertFalse(mv.correr());
        assertFalse(mv.saltar());
        assertFalse(mv.dormir());
        assertFalse(mv.despertar());
    }

    @Test
    public void dormirYDespertar(){
        var mv = getDefault();
        assertTrue(mv.dormir());
        assertTrue(mv.despertar());
    }

    @Test
    public void dormirYaDormido(){
        var mv = getDefault();
        assertTrue(mv.dormir());
        assertFalse(mv.dormir());
    }

    @Test
    public void actividadesDormido(){
        var mv = getDefault();
        assertTrue(mv.dormir());
        assertTrue(mv.estaVivo());

        assertEquals(mv.getEnergia(), 45);
        assertEquals(mv.getHumor(), 5);
        assertFalse(mv.comer());
        assertFalse(mv.beber());
        assertFalse(mv.correr());
        assertFalse(mv.saltar());
        assertFalse(mv.dormir());

        assertTrue(mv.despertar());
    }

    @Test
    public void ingestasConsecutivas(){
        var mv = new MascotaVirtual(20, 3);

        for(int i=0;i<3;i++){
            assertTrue(mv.comer());
        }

        assertEquals(4, mv.getHumor());

    }

    @Test
    public void ingestasConsecutivasMuere(){
        var mv = getDefault();

        for(int i=0;i<5;i++){
            mv.comer();
        }
        assertFalse(mv.estaVivo());
    }

    @Test
    public void actividadesConsecutivasEmpaca(){
        var mv = getDefault();

        for(int i=0;i<3;i++){
            mv.correr();
        }
        assertTrue(mv.estaDormido());

    }

}
