package carlos.dara.kaua.raynan.reciclamais.repository;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import carlos.dara.kaua.raynan.reciclamais.entities.Comentario;
import carlos.dara.kaua.raynan.reciclamais.entities.Endereco;
import carlos.dara.kaua.raynan.reciclamais.entities.PontoColeta;
import carlos.dara.kaua.raynan.reciclamais.entities.TipoMaterial;
import carlos.dara.kaua.raynan.reciclamais.util.Config;
import carlos.dara.kaua.raynan.reciclamais.util.HttpRequest;
import carlos.dara.kaua.raynan.reciclamais.util.Util;

public class PontoColetaRepository {
    Context context;
    public PontoColetaRepository(Context context){
        this.context = context;
    }

    /**
     * Método que cria uma requisição HTTP para cadastrar um novo produto junto ao servidor web.
     * @param nome nome do ponto de coleta
     * @param materiaisReciclados materiais que o ponto recicla
     * @param endereco endereco do ponto
     * @param imgLocation endereço do arquivo que contém a imagem do produto
     * @return true se o produto foi cadastrado junto ao servidor, false caso contrário
     */
    public boolean addPontoColeta(String nome, ArrayList<String> materiaisReciclados,
                                  @NonNull Endereco endereco, String imgLocation) {
        // Para cadastrar um produto, é preciso estar logado. Então primeiro otemos o login e senha
        // salvos na app.
        String login = Config.getLogin(context);
        String password = Config.getPassword(context);

        StringBuilder stringBuilder = new StringBuilder("[");
        for (int i = 0; i < materiaisReciclados.size(); i++) {
            stringBuilder.append("'").append(materiaisReciclados.get(i)).append("'");
            if (i < materiaisReciclados.size() - 1) {
                stringBuilder.append(", ");
            }
        }
        stringBuilder.append("]");

        // Convertendo o StringBuilder para String
        String stringMateriaisReciclados = stringBuilder.toString();

        System.out.println(nome);
        System.out.println(stringMateriaisReciclados);
        System.out.println(String.valueOf(endereco.getCep()));
        System.out.println(endereco.getTp_logradouro());
        System.out.println(endereco.getLogradouro());
        System.out.println(String.valueOf(endereco.getNumero()));
        System.out.println(endereco.getEstado());
        System.out.println(endereco.getCidade());
        System.out.println(endereco.getBairro());
        System.out.println(new File(imgLocation));


        // Cria uma requisição HTTP a adiona o parâmetros que devem ser enviados ao servidor
        HttpRequest httpRequest = new HttpRequest(Config.CONECTDB_APP_URL + "postPontoColeta.php", "POST", "UTF-8");
        httpRequest.addParam("nome", nome);
        httpRequest.addParam("materiais", stringMateriaisReciclados);
        httpRequest.addParam("cep", String.valueOf(endereco.getCep()));
        httpRequest.addParam("tp_logradouro", endereco.getTp_logradouro().toLowerCase());
        httpRequest.addParam("logradouro", endereco.getLogradouro());
        httpRequest.addParam("numero", String.valueOf(endereco.getNumero()));
        httpRequest.addParam("estado", endereco.getEstado());
        httpRequest.addParam("cidade", endereco.getCidade());
        httpRequest.addParam("bairro", endereco.getBairro());
        httpRequest.addFile("img", new File(imgLocation));

        // Para esta ação, é preciso estar logado. Então na requisição HTTP setamos o login e senha do
        // usuário. Ao executar a requisição, o login e senha do usuário serão enviados ao servidor web,
        // o qual verificará se o login e senha batem com aquilo que está no BD. Somente depois dessa
        // verificação de autenticação é que o servidor web irá realizar esta ação.
        httpRequest.setBasicAuth(login, password);



        String result = "";
        try {
            // Executa a requisição HTTP. É neste momento que o servidor web é contactado. Ao executar
            // a requisição é aberto um fluxo de dados entre o servidor e a app (InputStream is).
            InputStream is = httpRequest.execute();

            // Obtém a resposta fornecida pelo servidor. O InputStream é convertido em uma String. Essa
            // String é a resposta do servidor web em formato JSON.
            //
            // Em caso de sucesso, será retornada uma String JSON no formato:
            //
            // {"sucesso":1}
            //
            // Em caso de falha, será retornada uma String JSON no formato:
            //
            // {"sucesso":0,"erro":"Erro ao criar produto"}
            result = Util.inputStream2String(is, "UTF-8");

            // Fecha a conexão com o servidor web.
            httpRequest.finish();

            Log.i("HTTP ADD PONTO DE COLETA RESULT", result);

            // A classe JSONObject recebe como parâmetro do construtor uma String no formato JSON e
            // monta internamente uma estrutura de dados similar ao dicionário em python.
            JSONObject jsonObject = new JSONObject(result);

            // obtem o valor da chave sucesso para verificar se a ação ocorreu da forma esperada ou não.
            int success = jsonObject.getInt("status");

            // Se sucesso igual a 1, significa que o produto foi adicionado com sucesso.
            if(success == 1) {
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("HTTP RESULT", result);
        }
        return false;
    }

    /**
     * Método que cria uma requisição HTTP para obter uma página/bloco de produtos junto ao servidor web.
     * @param limit a quantidade de produtos a serem obtidos
     * @param offSet a posição a partir da qual a página de produtos deve começar
     * @return lista de produtos
     */
    public List<PontoColeta> loadPontosColeta(Integer limit, Integer offSet, Double lat, Double lon) {

        // cria a lista de produtos incicialmente vazia, que será retornada como resultado
        List<PontoColeta> pontosColetaList = new ArrayList<>();
        ArrayList<TipoMaterial> tipoMateriais = new ArrayList<>();


        // Cria uma requisição HTTP a adiona o parâmetros que devem ser enviados ao servidor
        HttpRequest httpRequest = new HttpRequest(Config.CONECTDB_APP_URL +"getPontosColeta.php", "GET", "UTF-8");
        httpRequest.addParam("limit", limit.toString());
        httpRequest.addParam("offset", offSet.toString());

        // Para esta ação, é preciso estar logado. Então na requisição HTTP setamos o login e senha do
        // usuário. Ao executar a requisição, o login e senha do usuário serão enviados ao servidor web,
        // o qual verificará se o login e senha batem com aquilo que está no BD. Somente depois dessa
        // verificação de autenticação é que o servidor web irá realizar esta ação.

        //httpRequest.setBasicAuth(login, password);

        String result = "";
        try {
            // Executa a requisição HTTP. É neste momento que o servidor web é contactado. Ao executar
            // a requisição é aberto um fluxo de dados entre o servidor e a app (InputStream is).
            InputStream is = httpRequest.execute();

            // Obtém a resposta fornecida pelo servidor. O InputStream é convertido em uma String. Essa
            // String é a resposta do servidor web em formato JSON.
            //
            // Em caso de sucesso, será retornada uma String JSON no formato:
            //
            // {"sucesso":1,
            //  "pontos":[
            //          {"id":"7", "nome":"zé recicla", "nota":"10.00", "img":"www.imgur.com/img1.jpg",
            //          "distancia": "1.8",  "materiais": [{"id" :"2", "nome": "vidro"}, {"id" :"3", "nome": "plastico"}]},
            //
            //       ]
            // }
            //
            // Em caso de falha, será retornada uma String JSON no formato:
            //
            // {"sucesso":0,"erro":"Erro ao obter produtos"}
            result = Util.inputStream2String(is, "UTF-8");

            // Fecha a conexão com o servidor web.
            httpRequest.finish();

            Log.i("HTTP PONTOS DE COLETA RESULT", result);

            // A classe JSONObject recebe como parâmetro do construtor uma String no formato JSON e
            // monta internamente uma estrutura de dados similar ao dicionário em python.
            JSONObject jsonObject = new JSONObject(result);

            // obtem o valor da chave sucesso para verificar se a ação ocorreu da forma esperada ou não.
            int success = jsonObject.getInt("status");

            // Se sucesso igual a 1, os pontos de coleta são obtidos da String JSON e adicionados à lista de
            // produtos a ser retornada como resultado.
            if(success == 1) {

                // A chave produtos é um array de objetos do tipo json (JSONArray), onde cada um desses representa
                // um ponto de coleta
                JSONArray jsonArray = jsonObject.getJSONArray("pontos");

                // Cada elemento do JSONArray é um JSONObject que guarda os dados de um ponto de coleta
                for(int i = 0; i < jsonArray.length(); i++) {

                    // Obtemos o JSONObject referente a um ponto de coleta
                    JSONObject jPonto = jsonArray.getJSONObject(i);

                    // Obtemos os dados de um ponto de coleta via JSONObject
                    int pid = Integer.parseInt(jPonto.getString("id"));
                    String nome = jPonto.getString("nome");
                    Double distancia = Double.parseDouble(jPonto.getString("distancia"));
                    String img = jPonto.getString("imagem");

                    JSONArray jsonArrayMateriais = jPonto.getJSONArray("materiais_reciclados");

                    for(int j = 0; j < jsonArrayMateriais.length(); j++){

                        JSONObject jMaterial = jsonArrayMateriais.getJSONObject(j);

                        int mid = Integer.parseInt(jMaterial.getString("id"));
                        String nomeMaterial = jPonto.getString("nome");

                        TipoMaterial tipoMaterial = new TipoMaterial(mid, nomeMaterial);
                        tipoMateriais.add(tipoMaterial);
                    }

                    // Criamo um objeto do tipo PontoColeta para guardar esses dados
                    PontoColeta pontoColeta = new PontoColeta(img, tipoMateriais, distancia, pid);


                    // Adicionamos o objeto product na lista de pontos
                    pontosColetaList.add(pontoColeta);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("HTTP RESULT", result);
        }

        return pontosColetaList;
    }

    /**
     * Método que cria uma requisição HTTP para obter os detalhes de um produto junto ao servidor web.
     * @param id id do produto que se deseja obter os detalhes
     * @return objeto do tipo product contendo os detalhes do produto
     */
    public PontoColeta loadPontoColetaDetail(String id) {

        // Cria uma requisição HTTP a adiona o parâmetros que devem ser enviados ao servidor
        HttpRequest httpRequest = new HttpRequest(Config.CONECTDB_APP_URL + "pegar_detalhes_produto.php", "GET", "UTF-8");
        httpRequest.addParam("id", id);

        ArrayList<Comentario> comentarios = new ArrayList<>();

        String result = "";
        try {
            // Executa a requisição HTTP. É neste momento que o servidor web é contactado. Ao executar
            // a requisição é aberto um fluxo de dados entre o servidor e a app (InputStream is).
            InputStream is = httpRequest.execute();

            // Obtém a resposta fornecida pelo servidor. O InputStream é convertido em uma String. Essa
            // String é a resposta do servidor web em formato JSON.
            //
            // Em caso de sucesso, será retornada uma String JSON no formato:
            //
            // {"sucesso":1,"nome":"produto 1","preco":"10.00", "img":"www.imgur.com/img1.jpg", "descricao":"produto 1","criado_em":"2022-10-03 19:43:31.42905","criado_por":"daniel"}
            //
            // Em caso de falha, será retornada uma String JSON no formato:
            //
            // {"sucesso":0,"erro":"Erro ao obter detalhes do produto"}
            result = Util.inputStream2String(is, "UTF-8");

            // Fecha a conexão com o servidor web.
            httpRequest.finish();

            Log.i("HTTP DETAILS RESULT", result);

            // A classe JSONObject recebe como parâmetro do construtor uma String no formato JSON e
            // monta internamente uma estrutura de dados similar ao dicionário em python.
            JSONObject jsonObject = new JSONObject(result);

            // obtem o valor da chave sucesso para verificar se a ação ocorreu da forma esperada ou não.
            int success = jsonObject.getInt("sucesso");

            // Se sucesso igual a 1, os detalhes do produto são obtidos da String JSON e um objeto
            // do tipo Product é criado para guardar esses dados
            if(success == 1) {

                Double notaPonto = Double.parseDouble(jsonObject.getString("notaPonto"));
                String img = jsonObject.getString("img");
                String nome = jsonObject.getString("nome");

                BigInteger cep = new BigInteger(jsonObject.getString("cep"));

                String tp_logadouro = jsonObject.getString("tp_logadouro");
                String logradouro = jsonObject.getString("logradouro");
                Integer numero = Integer.parseInt(jsonObject.getString("numero"));
                String estado = jsonObject.getString("estado");
                String cidade = jsonObject.getString("cidade");
                String bairro = jsonObject.getString("bairro");
                Double latitude = Double.parseDouble(jsonObject.getString("latitude"));
                Double longitude = Double.parseDouble(jsonObject.getString("longitude"));

                BigInteger telefone = new BigInteger(jsonObject.getString("telefone"));


                JSONArray jsonArray = jsonObject.getJSONArray("comentarios");
                for(int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jComentario = jsonArray.getJSONObject(i);

                    int cid = Integer.parseInt(jComentario.getString("id"));
                    String nomeUsuario = jComentario.getString("nomeUsuario");
                    Integer nota = Integer.parseInt(jComentario.getString("nota"));
                    String descricao = jComentario.getString("descricao");

                    Comentario comentario = new Comentario(cid, nomeUsuario, nota, descricao);

                    comentarios.add(comentario);
                }
                // Cria um objeto PontoColeta e guarda os detalhes do produto dentro dele.

                Endereco enderecoPonto = new Endereco(cep, tp_logadouro, logradouro, numero, estado, cidade, bairro, latitude, longitude);
                PontoColeta pontoColeta = new PontoColeta(notaPonto, img, nome, enderecoPonto, telefone, comentarios);

                return pontoColeta;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
