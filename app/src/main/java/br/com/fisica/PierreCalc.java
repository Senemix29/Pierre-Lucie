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
import android.widget.Toast;


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

					String direcao, tam;
					
					direcao=""; tam="";
					
					String a = Val_Plinha.getText().toString();
					double plinha = Double.parseDouble(a);
					String b = Val_F_Plinha.getText().toString();
					double fplinha = Double.parseDouble(b);
					
					double p = CalculaP(plinha, fplinha);

					if (((-plinha)/p)>0){
						direcao=" Direita";
					}
					if (((-plinha)/p)<0){
						direcao=" Invertida";
					}
					
					if ((-plinha)<p){
						tam=" Menor ";
					}
					else if((-plinha)==p){
						tam=" Igual ";
					}
					else{
						tam=" Maior ";
					}

                    Resultado(txP,"P",fplinha,p,tam,direcao);

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

					String direcao,tam;
					direcao=""; tam="";
					String a = Val_P.getText().toString();
					double p = Double.parseDouble(a);
					String b = Val_F_P.getText().toString();
					double f = Double.parseDouble(b);
					
					double plinha = CalculaPlinha(p, f);

					if (((-plinha)/p)>0){
						direcao=" Direita";
					}
					if (((-plinha)/p)<0){
						direcao=" Invertida";
					}
					
					if ((-plinha)<p){
						tam=" Menor ";
					}
					else if((-plinha)==p){
						tam=" Igual ";
					}
					else{
						tam=" Maior ";
					}
					
					Resultado(txPlinha,"P'",f,plinha,tam,direcao);
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
				if (!(s.length()==0) && !(Val_Plinha.getText().length()==0))
					btCalcP.setEnabled(true);
				else
					btCalcP.setEnabled(false);
				if(Val_F_Plinha.getText().toString().equals("0")){
					AvisoDivZero();
					s.clear();
				}
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
				if (!(s.length()==0) && !(Val_F_Plinha.getText().length()==0))
					btCalcP.setEnabled(true);
				else
					btCalcP.setEnabled(false);
				if(Val_Plinha.getText().toString().equals("0")){
					AvisoDivZero();
					s.clear();
				}
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
				if (!(s.length()==0) && !(Val_P.getText().length()==0))
					btCalcPlinha.setEnabled(true);
				else
					btCalcPlinha.setEnabled(false);
				if(Val_F_P.getText().toString().equals("0")){
					AvisoDivZero();
					s.clear();
				}
                String str = Val_F_P.getText().toString();
                if(str.equals(".") | str.equals(",")){
                    Mensagem("Entrada inválida","Favor, digite apenas números inteiros ou decimais separados por ponto.");
                    s.clear();
                }
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
				if (!(s.length()==0) && !(Val_F_P.getText().length()==0))
					btCalcPlinha.setEnabled(true);
				else
					btCalcPlinha.setEnabled(false);
				if(Val_P.getText().toString().equals("0")){
					AvisoDivZero();
					s.clear();
				}
                String str = Val_P.getText().toString();
                if(str.equals(".") | str.equals(",")){
                    Mensagem("Entrada inválida","Favor, digite apenas números inteiros ou decimais separados por ponto.");
                    s.clear();
                }
			}
		});
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
        Toast toast = Toast.makeText(getBaseContext(), msg, Toast.LENGTH_SHORT);
        toast.show();
    }
	
	public double CalculaP(double p_linha, double f){
		double p;
		p = (f*f)/(p_linha - f) + f;
		return p;
	}
	public double CalculaPlinha(double p, double f){
		double p_linha;
		p_linha = (f*f)/(p - f) + f;
		return p_linha;
	}
    public void Resultado(TextView Tx,String P_ou_Plinha, double f,double PimagemPvirtual,String tam, String direcao ){
        String espelho="";
        if(f>0){
            espelho= "Espelho concavo ";
        }
        if(f<0){
            espelho= "Espelho convexo ";
        }

        Tx.setText("Valor de " + P_ou_Plinha + " = " + String.valueOf(PimagemPvirtual) + "\nImagem " + tam +
                direcao + "\n" + espelho);
    }

}
