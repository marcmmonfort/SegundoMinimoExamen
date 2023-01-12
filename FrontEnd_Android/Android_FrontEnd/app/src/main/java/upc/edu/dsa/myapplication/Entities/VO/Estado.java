package upc.edu.dsa.myapplication.Entities.VO;

public class Estado {

// ATRIBUTOS

    private double dineroPou;
    private Integer nivelHambrePou;
    private Integer nivelSaludPou;
    private Integer nivelDiversionPou;
    private Integer nivelSuenoPou;

    // CONSTRUCTORES

    public Estado() {}

    public Estado(double dineroPou, Integer nivelHambrePou, Integer nivelSaludPou, Integer nivelDiversionPou, Integer nivelSuenoPou) {
        this.dineroPou = dineroPou;
        this.nivelHambrePou = nivelHambrePou;
        this.nivelSaludPou = nivelSaludPou;
        this.nivelDiversionPou = nivelDiversionPou;
        this.nivelSuenoPou = nivelSuenoPou;
    }

    // GETTERS Y SETTERS

    public double getDineroPou() {
        return dineroPou;
    }

    public void setDineroPou(double dineroPou) {
        this.dineroPou = dineroPou;
    }

    public Integer getNivelHambrePou() {
        return nivelHambrePou;
    }

    public void setNivelHambrePou(Integer nivelHambrePou) {
        this.nivelHambrePou = nivelHambrePou;
    }

    public Integer getNivelSaludPou() {
        return nivelSaludPou;
    }

    public void setNivelSaludPou(Integer nivelSaludPou) {
        this.nivelSaludPou = nivelSaludPou;
    }

    public Integer getNivelDiversionPou() {
        return nivelDiversionPou;
    }

    public void setNivelDiversionPou(Integer nivelDiversionPou) {
        this.nivelDiversionPou = nivelDiversionPou;
    }

    public Integer getNivelSuenoPou() {
        return nivelSuenoPou;
    }

    public void setNivelSuenoPou(Integer nivelSuenoPou) {
        this.nivelSuenoPou = nivelSuenoPou;
    }

}
