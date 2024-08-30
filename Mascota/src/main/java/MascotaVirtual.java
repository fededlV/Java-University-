public class MascotaVirtual {

    private int energia;
    private int humor;
    private int ingestasConsecutivas;
    private int actividades;
    private boolean vivo;
    private boolean durmiendo;
    private boolean muerto;

    public MascotaVirtual(int energiaInicial, int humorInicial) {
        this.energia = energiaInicial;
        this.humor = humorInicial;
        this.ingestasConsecutivas = 0;
        this.durmiendo = false;
        this.muerto = false;
        this.vivo = true;
        this.actividades = 0;
    }

    public boolean comer() {
        if(isMuerto()) return false;
        if(vivo && !durmiendo && !muerto) {
            ingestasConsecutivas++;
            actividades = 0;
            energia += (int) (0.10 * energia);
            if(ingestasConsecutivas >= 5) {
                muerto = true;
                vivo = false;
                return false;
            }
            if(ingestasConsecutivas >= 3) {
                humor--;
            } else {
                humor++;
            }
            if(energia >= 100) energia = 100;
            if(humor >= 5) humor = 5;
            vivo = true;
            muerto = false; 
            durmiendo = false; 
            return true;
        }
        return false;

    }

    public boolean beber() {
        if(vivo && !durmiendo && !muerto) {
            ingestasConsecutivas++;
            actividades = 0;
            if(humor >= 5) humor = 5;
            if(ingestasConsecutivas >= 5) {
                muerto = true;
                vivo = false;
                return false;
            }
            if(ingestasConsecutivas >= 3) {
                humor--;
            } else {
                humor++;
            }
            energia += (int) (0.05 * energia);
            if(energia >= 100) energia = 100;
            vivo = true;
            return true;
        }
        return false;
    }

    public boolean correr() {
        if(vivo && !durmiendo && !muerto) {
            actividades++;
            ingestasConsecutivas = 0;
            energia -= (int) (0.35 * energia);
            humor -= 2;
            if(energia <= 0) {
                muerto = true;
                vivo = false;
                return false;
            }
            if(actividades >= 3) {
                durmiendo = true;
                return false;
            }
            vivo = true;
            return true;
        }
        return false;
        
    }

    public boolean saltar() {
        if(vivo && !durmiendo && !muerto) {
            actividades++;
            ingestasConsecutivas = 0;
            energia -= (int) (0.15 * energia);
            humor -= 2;
            if(energia <= 0) {
                muerto = true;
                vivo = false;
                return false;
            }
            if(actividades >= 3) {
                durmiendo = true;
                return false;
            }
            vivo = true;
            return true;
        }
        return false;
    }

    public boolean dormir() {
        if(vivo && !durmiendo && !muerto){
            if(energia <= 100) {
                energia += 25;
            } else if (energia >= 100) {
                energia = 100;
            }
            if(humor <= 5) {
                humor += 2;
            } 
            if (humor >= 5) {
                humor = 5;
            }
            durmiendo = true;
            return true;
        }
        return false;
    }

    public boolean despertar() {
        if(vivo && durmiendo && !muerto){
            humor--;
            durmiendo = false;
            return true;
        }
        return false;
    }

    public boolean estaVivo(){
        if(ingestasConsecutivas >= 5) {
            muerto = true;
            vivo = false;
            return false;
        }
        if(energia <= 0) {
            muerto = true;
            vivo = false;
            return false;
        }
        return true;
    }

    public int getEnergia() {
        return energia;
    }

    public int getHumor() {
        return humor;
    }

    public int getActividades() {
        return actividades;
    }

    public int getIngestasConsecutivas() {
        return ingestasConsecutivas;
    }

    public boolean isVivo() {
        return vivo;
    }

    public boolean isDurmiendo() {
        return durmiendo;
    }

    public boolean isMuerto() {
        return muerto;
    }

    public boolean estaDormido(){
        if((durmiendo || actividades == 3) && vivo) {
            return true;
        }
        return false;
    }

}
