package carlos.dara.kaua.raynan.reciclamais.fragments;

import static android.app.Activity.RESULT_OK;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import android.os.Environment;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import carlos.dara.kaua.raynan.reciclamais.R;
import carlos.dara.kaua.raynan.reciclamais.activity.LoginActivity;
import carlos.dara.kaua.raynan.reciclamais.activity.MainActivity;
import carlos.dara.kaua.raynan.reciclamais.entities.TipoMaterial;
import carlos.dara.kaua.raynan.reciclamais.util.Config;
import carlos.dara.kaua.raynan.reciclamais.util.Util;
import carlos.dara.kaua.raynan.reciclamais.viewModel.MainViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AdicionarPontoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AdicionarPontoFragment extends Fragment {

    static int RESULT_TAKE_PICTURE = 1;
    private MainViewModel mViewModel;
    private View view;
    private ArrayList<String> materiaisSelecionados = new ArrayList<>();

    EditText etCidade, etBairro, etNumero, etLogradouro, etCep, etEstado, etTipoLogradouro;

    public AdicionarPontoFragment() {
        // Required empty public constructor
    }

    public static AdicionarPontoFragment newInstance() {
        return new AdicionarPontoFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_adicionar_ponto, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MainViewModel mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        if(Config.getLogin(requireActivity()).isEmpty()){
            Intent i = new Intent(requireActivity(), LoginActivity.class);
            i.putExtra("fragment", "adicionarPonto");
            startActivity(i);
            requireActivity().finish();
        }

        ImageView imvFotoPonto = view.findViewById(R.id.imv_foto_ponto_adicionar_ponto);
        Button botaoAdicionarFoto = view.findViewById(R.id.btn_adicionar_foto_ponto_adicionar_ponto);

        botaoAdicionarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dispatchGalleryOrCameraIntent();
            }
        });

        String currentPhotoPath = mainViewModel.getCurrentPhotoPath();
        if(!currentPhotoPath.isEmpty()){
            Bitmap bitmap = Util.getBitmap(currentPhotoPath, imvFotoPonto.getWidth(), imvFotoPonto.getHeight());
            imvFotoPonto.setImageBitmap(bitmap);
        }

        EditText etNome = view.findViewById(R.id.editText_nome_adicionar_ponto);

        etCep = view.findViewById(R.id.editText_CEP_adicionar_ponto);
        etTipoLogradouro = view.findViewById(R.id.editText_tpLogradouro_adicionar_ponto);
        etLogradouro = view.findViewById(R.id.editText_logradouro_adicionar_ponto);
        etNumero = view.findViewById(R.id.editText_numero_adicionar_ponto);
        etEstado = view.findViewById(R.id.editText_estado_adicionar_ponto);
        etCidade = view.findViewById(R.id.editText_cidade_adicionar_ponto);
        etBairro = view.findViewById(R.id.editText_bairro_adicionar_ponto);

        etCep.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Nada a fazer aqui
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Nada a fazer aqui
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String cep = editable.toString();
                if (cep.length() == 8) {
                    new ConsultaCEPTask().execute(cep);
                }
            }
        });

        Button botaoVidro = view.findViewById(R.id.btn_vidro_adicionar_ponto);
        botaoVidro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(materiaisSelecionados.contains(botaoVidro.getText())){
                    materiaisSelecionados.remove(botaoVidro.getText());
                }else{
                    materiaisSelecionados.add(botaoVidro.getText().toString());
                }
            }
        });

        Button botaoPlastico = view.findViewById(R.id.btn_plastico_adicionar_ponto);
        botaoPlastico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(materiaisSelecionados.contains(botaoPlastico.getText())){
                    materiaisSelecionados.remove(botaoPlastico.getText());
                }else{
                    materiaisSelecionados.add(botaoPlastico.getText().toString());
                }
            }
        });

        Button botaoMadeira = view.findViewById(R.id.btn_madeira_adicionar_ponto);
        botaoMadeira.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(materiaisSelecionados.contains(botaoMadeira.getText())){
                    materiaisSelecionados.remove(botaoMadeira.getText());
                }else{
                    materiaisSelecionados.add(botaoMadeira.getText().toString());
                }
            }
        });

        Button botaoMetal = view.findViewById(R.id.btn_metal_adicionar_ponto);
        botaoMetal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(materiaisSelecionados.contains(botaoMetal.getText())){
                    materiaisSelecionados.remove(botaoMetal.getText());
                }else{
                    materiaisSelecionados.add(botaoMetal.getText().toString());
                }
            }
        });

        Button botaoHospitalar = view.findViewById(R.id.btn_hospitalar_adicionar_ponto);
        botaoHospitalar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(materiaisSelecionados.contains(botaoHospitalar.getText())){
                    materiaisSelecionados.remove(botaoHospitalar.getText());
                }else{
                    materiaisSelecionados.add(botaoHospitalar.getText().toString());
                }
            }
        });

        Button botaoOrganico = view.findViewById(R.id.btn_organico_adicionar_ponto);
        botaoOrganico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(materiaisSelecionados.contains(botaoOrganico.getText())){
                    materiaisSelecionados.remove(botaoOrganico.getText());
                }else{
                    materiaisSelecionados.add(botaoOrganico.getText().toString());
                }
            }
        });

        Button botaoPapel = view.findViewById(R.id.btn_papel_adicionar_ponto);
        botaoPapel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(materiaisSelecionados.contains(botaoPapel.getText())){
                    materiaisSelecionados.remove(botaoPapel.getText());
                }else{
                    materiaisSelecionados.add(botaoPapel.getText().toString());
                }
            }
        });

        Button botaoEletronico = view.findViewById(R.id.btn_eletronicos_adicionar_ponto);
        botaoEletronico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(materiaisSelecionados.contains(botaoEletronico.getText())){
                    materiaisSelecionados.remove(botaoEletronico.getText());
                }else{
                    materiaisSelecionados.add(botaoEletronico.getText().toString());
                }
            }
        });


        Button botaoAdicionarPontoColeta = view.findViewById(R.id.btn_adicionar_ponto_de_coleta_adicionar_ponto);

        botaoAdicionarPontoColeta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nome = etNome.getText().toString();
                String cep = etCep.getText().toString();
                String tipoLogradouro = etTipoLogradouro.getText().toString();
                String logradouro = etLogradouro.getText().toString();
                String numero = etNumero.getText().toString();
                String estado = etEstado.getText().toString();
                String cidade = etCidade.getText().toString();
                String bairro = etBairro.getText().toString();

                System.out.println(materiaisSelecionados);
                if(materiaisSelecionados.isEmpty()){
                    Toast.makeText(getContext(), "Por favor, selecione um material", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(nome)){
                    Toast.makeText(getContext(), "Por favor, preencha o campo nome", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(cep)){
                    Toast.makeText(getContext(), "Por favor, preencha o campo CEP", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(tipoLogradouro)){
                    Toast.makeText(getContext(), "Por favor, selecione o tipo de logradouro", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(logradouro)){
                    Toast.makeText(getContext(), "Por favor, preencha o campo logradouro", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(numero)){
                    Toast.makeText(getContext(), "Por favor, preencha o campo numero", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(estado)){
                    Toast.makeText(getContext(), "Por favor, selecione o estado", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(cidade)){
                    Toast.makeText(getContext(), "Por favor, preencha o campo cidade", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(bairro)){
                    Toast.makeText(getContext(), "Por favor, preencha o campo bairro", Toast.LENGTH_SHORT).show();
                    return;
                }

                String currentPhotoPath = mainViewModel.getCurrentPhotoPath();
                if(currentPhotoPath.isEmpty()) {
                    Toast.makeText(getContext(), "O campo foto não foi preenchido", Toast.LENGTH_LONG).show();
                    view.setEnabled(true);
                    return;
                }

                // Neste ponto, já verificamos que todos os campos foram preenchidos corretamente.
                // Antes enviar esses dados ao servidor, nós fazemos uma escala na imagem escolhida
                // para o produto. Fazemos isso porque a câmera do celular produz imagens muito grandes,
                // com resolução muito mais alta do que aquela que realmente precisamos. Logo, na
                // prática, o que fazemos aqui é diminuir o tamanho da imagem antes de enviá-la ao
                // servidor. Isso garante que será usado menos recurso de rede e de banco de dados
                // no servidor.
                //
                // A imagem é escalada de forma que sua altura fique em 300dp (tamanho do ImageView
                // que exibe os detalhes de um produto. A largura vai possuir
                // um tamanho proporcional ao tamamnho original.
                try {
                    int h = (int) getResources().getDimension(R.dimen.img_height);
                    Util.scaleImage(currentPhotoPath, -1, 2*h);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    return;
                }

                LiveData<Boolean> resultLD = mainViewModel.
            }
        });
    }

    private class ConsultaCEPTask extends AsyncTask<String, Void, JSONObject> {

        @Override
        protected JSONObject doInBackground(String... strings) {
            String cep = strings[0];

            try {
                URL url = new URL("https://brasilapi.com.br/api/cep/v2/" + cep);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    StringBuilder response = new StringBuilder();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }

                    reader.close();

                    return new JSONObject(response.toString());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(JSONObject dados) {
            if (dados != null) {
                preencherCampos(dados);
            }
        }
    }

    private void preencherCampos(JSONObject dados) {
        try {
            String street = dados.getString("street");

            // Dividir a string em duas partes no primeiro espaço encontrado
            int primeiroEspacoIndex = street.indexOf(" ");
            String tpLogradouro = (primeiroEspacoIndex != -1) ? street.substring(0, primeiroEspacoIndex) : street;
            String logradouro = (primeiroEspacoIndex != -1) ? street.substring(primeiroEspacoIndex + 1) : "";

            etCidade.setText(dados.getString("city"));
            etLogradouro.setText(logradouro);
            etTipoLogradouro.setText(tpLogradouro);
            etBairro.setText(dados.getString("neighborhood"));
            etEstado.setText(dados.getString("state"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Esse método exibe um pequeno menu de opções que permite que o usuário escolha de onde virá
     * a imagem do produto: câmera ou galeria.
     */
    public void dispatchGalleryOrCameraIntent() {

        // Primeiro, criamos o arquivo que irá guardar a imagem.
        File f = null;
        try {
            f = createImageFile();
        } catch (IOException e) {
            Toast.makeText(getActivity(), "Não foi possível criar o arquivo", Toast.LENGTH_LONG).show();
            return;
        }

        // Se o arquivo foi criado com sucesso...
        if(f != null) {

            // setamos o endereço do arquivo criado dentro do ViewModel
            MainViewModel mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
            mainViewModel.setCurrentPhotoPath(f.getAbsolutePath());

            // Criamos e configuramos o INTENT que dispara a câmera
            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            Uri fUri = FileProvider.getUriForFile(getActivity(), "carlos.dara.kaua.raynan.reciclamais.fileprovider", f);
            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, fUri);

            // Criamos e configuramos o INTENT que dispara a escolha de imagem via galeria
            Intent galleryIntent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            galleryIntent.setType("image/*");

            // Criamos o INTENT que gera o menu de escolha. Esse INTENT contém os dois INTENTS
            // anteriores e permite que o usuário esolha entre câmera e galeria de fotos.
            Intent chooserIntent = Intent.createChooser(galleryIntent, "Pegar imagem de...");
            chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[] { cameraIntent });
            startActivityForResult(chooserIntent, RESULT_TAKE_PICTURE);
        }
        else {
            Toast.makeText(getActivity(), "Não foi possível criar o arquivo", Toast.LENGTH_LONG).show();
            return;
        }


    }

    /**
     * Método que cria um arquivo vazio, onde será guardada a imagem escolhida. O arquivo é
     * criado dentro do espaço interno da app, no diretório PICTURES. O nome do arquivo usa a
     * data e hora do momento da criação do arquivo. Isso garante que sempre que esse método for
     * chamado, não haverá risco de sobrescrever o arquivo anterior.
     */
    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp;
        File storageDir = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File f = File.createTempFile(imageFileName, ".jpg", storageDir);
        return f;
    }

    /**
     * Esse método é chamado depois que o usuário escolhe a foto
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == RESULT_TAKE_PICTURE) {

            // Pegamos o endereço do arquivo vazio que foi criado para guardar a foto escolhida
            MainViewModel mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
            String currentPhotoPath = mainViewModel.getCurrentPhotoPath();

            // Se a foto foi efetivamente escolhida pelo usuário...
            if(resultCode == Activity.RESULT_OK) {
                ImageView imvPhoto = view.findViewById(R.id.imv_foto_ponto_adicionar_ponto);

                // se o usuário escolheu a câmera, então quando esse método é chamado, a foto tirada
                // já está salva dentro do arquivo currentPhotoPath. Entretanto, se o usuário
                // escolheu uma foto da galeria, temos que obter o URI da foto escolhida:

                if(data != null) {
                    Uri selectedPhoto = data.getData();
                    try {
                        // carregamos a foto escolhida em um bitmap
                        Bitmap bitmap = Util.getBitmap(requireActivity(), selectedPhoto);
                        // salvamos o bitmao dentro do arquivo currentPhotoPath
                        Util.saveImage(bitmap, currentPhotoPath);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                        return;
                    }
                }

                // Carregamos a foto salva em currentPhotoPath com a escala correta e setamos no ImageView
                Bitmap bitmap = Util.getBitmap(currentPhotoPath, imvPhoto.getWidth(), imvPhoto.getHeight());
                imvPhoto.setImageBitmap(bitmap);
            }
            else {
                // Se a imagem não foi escolhida, deletamos o arquivo que foi criado para guardá-la
                File f = new File(currentPhotoPath);
                f.delete();
                mainViewModel.setCurrentPhotoPath("");
            }
        }
    }
}
