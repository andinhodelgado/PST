package br.com.usinasantafe.pst.util;

import android.content.Context;
import android.util.Log;

import br.com.usinasantafe.pst.control.AbordagemCTR;
import br.com.usinasantafe.pst.util.conHttp.PostMultipartGenerico;
import br.com.usinasantafe.pst.util.conHttp.UrlsConexaoHttp;

public class EnvioDadosServ {

    private static EnvioDadosServ instance = null;
    private int statusEnvio; //1 - Enviando; 2 - Existe Dados para Enviar; 3 - Todos os Dados Enviados
    private boolean enviando = false;

    public static EnvioDadosServ getInstance() {
        if (instance == null) {
            instance = new EnvioDadosServ();
        }
        return instance;
    }

    //////////////////////// ENVIAR DADOS ////////////////////////////////////////////

    public void dadosEnvio() {

        UrlsConexaoHttp urlsConexaoHttp = new UrlsConexaoHttp();
        AbordagemCTR abordagemCTR = new AbordagemCTR();

        String[] dados = new String[7];

        String cabec = abordagemCTR.dadosCabecFechEnvio();
        String item = abordagemCTR.dadosItemFechEnvio();

        Log.i("PST", "CABECALHO = " + cabec);
        Log.i("PST", "ITEM = " + item);
        Log.i("PST", "FOTO 1 = " + abordagemCTR.dadosFotoFechEnvio(1));
        Log.i("PST", "FOTO 2 = " + abordagemCTR.dadosFotoFechEnvio(2));
        Log.i("PST", "FOTO 3 = " + abordagemCTR.dadosFotoFechEnvio(3));
        Log.i("PST", "FOTO 4 = " + abordagemCTR.dadosFotoFechEnvio(4));

        dados[0] = urlsConexaoHttp.getsInserirDados();
        dados[1] = cabec;
        dados[2] = item;
        dados[3] = abordagemCTR.dadosFotoFechEnvio(1);
        dados[4] = abordagemCTR.dadosFotoFechEnvio(2);
        dados[5] = abordagemCTR.dadosFotoFechEnvio(3);
        dados[6] = abordagemCTR.dadosFotoFechEnvio(4);

        PostMultipartGenerico postMultipartGenerico = new PostMultipartGenerico();
        postMultipartGenerico.execute(dados);

    }

    //////////////////////VERIFICAÇÃO DE DADOS///////////////////////////

    public Boolean verifEnvioDados() {
        AbordagemCTR abordagemCTR = new AbordagemCTR();
        return abordagemCTR.verEnvioDados();
    }

    /////////////////////////MECANISMO DE ENVIO//////////////////////////////////

    public void envioDados(Context context) {
        ConexaoWeb conexaoWeb = new ConexaoWeb();
        if (conexaoWeb.verificaConexao(context)) {
            enviando = true;
            envioDados();
        }
        else{
            enviando = false;
        }

    }

    public void envioDados() {
        if(verifEnvioDados()){
            enviando = true;
            dadosEnvio();
        }
        else{
            enviando = false;
        }
    }

    public boolean verifDadosEnvio() {
        if (!verifEnvioDados()){
            enviando = false;
            return false;
        } else {
            return true;
        }
    }

    public int getStatusEnvio() {
        if (enviando) {
            statusEnvio = 1;
        } else {
            if (!verifDadosEnvio()) {
                statusEnvio = 3;
            } else {
                statusEnvio = 2;
            }
        }
        return statusEnvio;
    }

    public boolean isEnviando() {
        return enviando;
    }

    public void setEnviando(boolean enviando) {
        this.enviando = enviando;
    }
}