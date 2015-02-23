package br.com.fisica;

/**
 * Created by Natan on 22/02/2015.
 */
public class Espelho {
    private double pontoImagem;
    private double pontoObjeto;
    private double pontoFocal;
    private String direcao;
    private String tamanho;
    private String tipo;

    public Espelho(double pontoImagem, double pontoFocal) {
        this.pontoImagem = pontoImagem;
        this.pontoFocal = pontoFocal;
    }
    public Espelho(double pontoImagem, double pontoFocal, String hack) {
        this.pontoImagem = pontoImagem;
        this.pontoFocal = pontoFocal;
    }
    public Espelho(double pontoFocal) {
        this.pontoFocal = pontoFocal;
    }
    public double CalculaP(){
        pontoObjeto = (pontoFocal*pontoFocal)/(pontoImagem - pontoFocal) + pontoFocal;
        return pontoObjeto;
    }
    public double CalculaPlinha(){
        pontoImagem = (pontoFocal*pontoFocal)/(pontoObjeto - pontoFocal) + pontoFocal;
        return pontoImagem;
    }
    public String TipoEspelho(){
        if(pontoFocal>0){
            tipo= "Espelho concavo ";
        }
        if(pontoFocal<0){
            tipo= "Espelho convexo ";
        }
        return tipo;
    }

    public double getPontoImagem() {
        return pontoImagem;
    }

    public double getPontoObjeto() {
        return pontoObjeto;
    }

    public double getPontoFocal() {
        return pontoFocal;
    }

    public String getDirecao() {
        return direcao;
    }

    public void setDirecao(String direcao) {
        this.direcao = direcao;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }
}
