package carlos.dara.kaua.raynan.reciclamais.repository;

import android.content.Context;

import carlos.dara.kaua.raynan.reciclamais.util.Config;
import carlos.dara.kaua.raynan.reciclamais.util.HttpRequest;

public class PontosColetaRepository {
    Context context;
    public PontosColetaRepository(Context context){
        this.context = context;
    }

    /**
     * Método que cria uma requisição HTTP para registrar um novo usuário junto ao servidor web.
     * @param newLogin o login do novo usuário
     * @param newPassword a senha do novo usuário
     * @return true se o usuário foi cadastrado e false caso contrário
     */

    public boolean registrarPontoColeta(String newLogin, String newPassword){
        //TODO: Alterar endereço da URL

        // Cria uma requisição HTTP a adiona o parâmetros que devem ser enviados ao servidor
        HttpRequest httpRequest = new HttpRequest(Config.CONECTDB_APP_URL + "registrar.php", "POST", "UTF-8");
        httpRequest.addParam("novo_login", newLogin);
        httpRequest.addParam("nova_senha", newPassword);

        return false;
    }
}
