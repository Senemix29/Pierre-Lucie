package br.com.fisica;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;


public class PierreCalc extends Activity {
	Button btP, btPlinha, btCalcP, btCalcPlinha, btVolta1, btVolta2;
	EditText Val_F_P, Val_F_Plinha, Val_P, Val_Plinha;
	TextView txP, txPlinha;
    String viewAtual="";
    int cont=1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		chamaMenu();
	}

    public void onBackPressed() {
        if (viewAtual.equals("Menu")) {
            cont++;
            if (cont==2){
               MensagemToast("Pressione novamente para sair");
            }
            else{
                super.onBackPressed();
            }
        }
        else{
            chamaMenu();
        }
    }

	void inicializaObjetos(){
			btP = (Button) findViewById(R.id.btP);
			btPlinha = (Button) findViewById(R.id.btPlinha);
			btCalcP = (Button) findViewById(R.id.btCalcP);
			btCalcPlinha = (Button) findViewById(R.id.btCalcPlinha);
			btVolta1 = (Button) findViewById(R.id.btVolta1);
			btVolta2 = (Button) findViewById(R.id.btVolta2);
			Val_F_P = (EditText) findViewById(R.id.Val_F_P);
			Val_F_Plinha = (EditText) findViewById(R.id.Val_F_Plinha);
			Val_P = (EditText) findViewById(R.id.Val_P);
			Val_Plinha = (EditText) findViewById(R.id.Val_Plinha);
			txP = (TextView) findViewById(R.id.txP);
			txPlinha = (TextView) findViewById(R.id.txPlinha);
	}
		
	public void chamaMenu(){
		setContentView(R.layout.activity_pierre_calc);
		inicializaObjetos();
		menuListeners();
        viewAtual="Menu";
	}

	public void chamaP(){
		try{
            viewAtual="P"; cont=1;
			setContentView(R.layout.pega_dado_p);
			inicializaObjetos();
			PListeners();
			btCalcP.setEnabled(false);
			CampoPListeners();

			
		}catch(Exception e){
			Mensagem("Erro","Erro:"+e);			
		}
	}

	public void chamaPlinha(){

		try{
            viewAtual="Plinha"; cont=1;
			setContentView(R.layout.pega_dado_pelinha);
			inicializaObjetos();
			PlinhaListeners();
			btCalcPlinha.setEnabled(false);
			CampoPLinhaListeners();

		}catch(Exception e){
			Mensagem("Erro","Erro:"+e);			
		}
	}
	
	public void menuListeners(){
			
			btP.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					chamaP();
				}
			});
			
			btPlinha.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					chamaPlinha();
				}
			});
	}
	
	public void PListeners(){
		
			btCalcP.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					MinimizaTeclado();

					String a = Val_Plinha.getText().toString();
					double plinha = Double.parseDouble(a);
					String b = Val_F_Plinha.getText().toString();
					double fplinha = Double.parseDouble(b);

                    Espelho espelho = new Espelho(plinha,fplinha);
                    espelho.calculaP();
                    Resultado(txP, "P", espelho.getPontoObjeto(),
                            espelho.getTamanho(), espelho.getDirecao(), espelho.getTipo());

				}
			});
			btVolta1.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					chamaMenu();
					
				}
			});
	}
	
	public void PlinhaListeners(){
			btCalcPlinha.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
                    MinimizaTeclado();

					String a = Val_P.getText().toString();
					double p = Double.parseDouble(a);
					String b = Val_F_P.getText().toString();
					double f = Double.parseDouble(b);

                    Espelho espelho = new Espelho(p,f,"burlando o Construtor!");
                    espelho.calculaPlinha();

					Resultado(txPlinha,"P'",espelho.getPontoImagem(),
                            espelho.getTamanho(), espelho.getDirecao(), espelho.getTipo());
				}
			});
			btVolta2.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					chamaMenu();
					
				}
			});
		
	}

	public void CampoPListeners(){
		Val_F_Plinha.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
                ValidaTexto(btCalcP,Val_F_Plinha,Val_Plinha,s);
			}
		});
		Val_Plinha.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
                ValidaTexto(btCalcP,Val_Plinha,Val_F_Plinha,s);
			}
		});
	}
	
	public void CampoPLinhaListeners(){
		Val_F_P.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
                ValidaTexto(btCalcPlinha,Val_F_P,Val_P, s);
			}
		});
		Val_P.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {

			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void afterTextChanged(Editable s) {
                ValidaTexto(btCalcPlinha,Val_P,Val_F_P, s);
			}
		});
	}

    protected void ValidaTexto(Button bt, EditText campo1, EditText campo2, Editable s2){
        if (!(s2.length()==0) && !(campo2.getText().length()==0))
            bt.setEnabled(true);
        else
            bt.setEnabled(false);
        if(campo1.getText().toString().equals("0")){
            AvisoDivZero();
            s2.clear();
        }
        String str = campo1.getText().toString();
        if(str.equals(".") | str.equals(",")){
            Mensagem("Entrada inválida","Favor, digite apenas números inteiros ou decimais separados por ponto.");
            s2.clear();
        }
    }

    protected void MinimizaTeclado(){
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        //noinspection ConstantConditions
        inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

	protected void AvisoDivZero(){
        Mensagem("Risco de divisão por 0","Favor, inserir valores maiores ou menores que zero");
    }

	public void Mensagem(String titulo, String msg){
		AlertDialog.Builder mensagem = new AlertDialog.Builder(PierreCalc.this);
		mensagem.setTitle(titulo);
		mensagem.setMessage(msg);
		mensagem.setNeutralButton("OK", null);
		mensagem.show();
	}

    public void MensagemToast(String msg){
        Crouton.showText(this, msg, Style.INFO);
    }

    public void Resultado(TextView Tx,String P_ou_Plinha,double PimagemPvirtual,String tam, String direcao, String espelho ){
        //Com os dados previamente gerados pelo objeto espelho, pega os mesmos e mostra num Textview
        Tx.setText("Valor de " + P_ou_Plinha + " = " + String.valueOf(PimagemPvirtual) + "\nImagem " + tam +
                direcao + "\n" + espelho);
    }

}
