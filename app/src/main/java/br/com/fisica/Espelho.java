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
    public Espelho(double pontoObjeto, double pontoFocal, String hack) {
        this.pontoObjeto = pontoObjeto;
        this.pontoFocal = pontoFocal;
    }
    public Espelho(double pontoFocal) {
        this.pontoFocal = pontoFocal;
    }

    public void CalculaP(){
        pontoObjeto = (pontoFocal*pontoFocal)/(pontoImagem - pontoFocal) + pontoFocal;
        defineImagem();
    }

    public void CalculaPlinha(){
        pontoImagem = (pontoFocal*pontoFocal)/(pontoObjeto - pontoFocal) + pontoFocal;
        defineImagem();
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

    private  void defineImagem(){

        if (((-pontoImagem)/pontoObjeto)>0){
            direcao=" Direita";
        }
        if (((-pontoImagem)/pontoObjeto)<0){
            direcao=" Invertida";
        }

        if ((-pontoImagem)<pontoFocal){
            tamanho=" Menor ";
        }
        else if((-pontoImagem)==pontoFocal){
            tamanho=" Igual ";
        }
        else{
            tamanho=" Maior ";
        }
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
