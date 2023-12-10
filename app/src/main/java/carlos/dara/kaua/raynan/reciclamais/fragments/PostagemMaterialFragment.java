package carlos.dara.kaua.raynan.reciclamais.fragments;

import static android.app.Activity.RESULT_OK;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import carlos.dara.kaua.raynan.reciclamais.R;
import carlos.dara.kaua.raynan.reciclamais.activity.LoginActivity;
import carlos.dara.kaua.raynan.reciclamais.util.Config;
import carlos.dara.kaua.raynan.reciclamais.util.Util;
import carlos.dara.kaua.raynan.reciclamais.viewModel.MainViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PostagemMaterialFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PostagemMaterialFragment extends Fragment {

    private MainViewModel mViewModel;
    static int RESULT_TAKE_PICTURE = 1;
    private View view;
    private ArrayList<String> materiaisSelecionados = new ArrayList<>();

    public PostagemMaterialFragment() {
        // Required empty public constructor
    }

    public static PostagemMaterialFragment newInstance() {
        return  new PostagemMaterialFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        view = inflater.inflate(R.layout.fragment_postagem_material, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MainViewModel mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        if(Config.getLogin(requireActivity()).isEmpty()){
            Intent i = new Intent(requireActivity(), LoginActivity.class);
            startActivity(i);
        }

        ImageView imvFotoMaterial = view.findViewById(R.id.imv_foto_material_postagem_material);
        Button botaoAdicionarFotoMaterial = view.findViewById(R.id.btn_adicionar_foto_material_postagem_material);

        botaoAdicionarFotoMaterial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dispatchGalleryOrCameraIntent();
            }
        });

        String currentPhotoPath = mainViewModel.getCurrentPhotoPath();
        if(!currentPhotoPath.isEmpty()){
            Bitmap bitmap = Util.getBitmap(currentPhotoPath, imvFotoMaterial.getWidth(), imvFotoMaterial.getHeight());
            imvFotoMaterial.setImageBitmap(bitmap);
        }

        Button botaoVidro = view.findViewById(R.id.btn_vidro_postar_material);

        // Defina a cor de fundo original do botão
        final int originalColor = botaoVidro.getSolidColor();

        // Defina um ouvinte de clique para o botão
        botaoVidro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Verifique se o botão já está selecionado
                if (materiaisSelecionados.contains("Vidro")) {
                    // Se o botão já estiver selecionado, volte à cor original
                    botaoVidro.setBackgroundColor(originalColor);
                    materiaisSelecionados.remove("Vidro");
                } else {
                    // Caso contrário, defina a cor de fundo para verde
                    botaoVidro.setBackgroundColor(Color.GREEN);
                    materiaisSelecionados.add("Vidro");
                }
            }
        });

        Button botaoPlastico = view.findViewById(R.id.btn_plastico_postar_material);
        botaoPlastico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (materiaisSelecionados.contains("Plástico")) {
                    botaoPlastico.setBackgroundColor(originalColor);
                    materiaisSelecionados.remove("Plástico");
                } else {
                    botaoPlastico.setBackgroundColor(Color.RED);
                    materiaisSelecionados.add("Plástico");
                }
            }
        });

        Button botaoMadeira = view.findViewById(R.id.btn_madeira_postar_material);
        botaoMadeira.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(materiaisSelecionados.contains("Madeira")){
                    botaoMadeira.setBackgroundColor(originalColor);
                    materiaisSelecionados.remove("Madeira");
                }else{
                    botaoMadeira.setBackgroundColor(Color.BLACK);
                    materiaisSelecionados.add("Madeira");
                }
            }
        });

        Button botaoMetal = view.findViewById(R.id.btn_metal_postar_material);
        botaoMetal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(materiaisSelecionados.contains("Metal")){
                    botaoMetal.setBackgroundColor(originalColor);
                    materiaisSelecionados.remove("Metal");
                }else{
                    botaoMetal.setBackgroundColor(Color.YELLOW);
                    materiaisSelecionados.add("Metal");
                }
            }
        });

        Button botaoHospitalar = view.findViewById(R.id.btn_hospitalar_postar_material);

        final int cor_hospitalar = getResources().getColor(R.color.Cor_Hospitalar);
        botaoHospitalar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(materiaisSelecionados.contains("Hospitalar")){
                    botaoHospitalar.setBackgroundColor(originalColor);
                    materiaisSelecionados.remove("Hospitalar");
                }else{
                    botaoHospitalar.setBackgroundColor(cor_hospitalar);
                    materiaisSelecionados.add("Hospitalar");
                }
            }
        });

        Button botaoOrganico = view.findViewById(R.id.btn_organico_postar_material);
        final int cor_organico = getResources().getColor(R.color.Cor_Organico);
        botaoOrganico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(materiaisSelecionados.contains("Orgânico")){
                    botaoOrganico.setBackgroundColor(originalColor);
                    materiaisSelecionados.remove("Orgânico");
                }else{
                    botaoOrganico.setBackgroundColor(cor_organico);
                    materiaisSelecionados.add("Orgânico");
                }
            }
        });

        Button botaoPapel = view.findViewById(R.id.btn_papel_postar_material);
        botaoPapel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(materiaisSelecionados.contains("Papel")){
                    botaoPapel.setBackgroundColor(originalColor);
                    materiaisSelecionados.remove("Papel");
                }else{
                    botaoPapel.setBackgroundColor(Color.BLUE);
                    materiaisSelecionados.add("Papel");
                }
            }
        });

        Button botaoEletronico = view.findViewById(R.id.btn_eletronicos_postar_material);
        final int cor_eletronicos = getResources().getColor(R.color.Cor_eletronicos);
        botaoEletronico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(materiaisSelecionados.contains("Eletrônicos")){
                    botaoEletronico.setBackgroundColor(originalColor);
                    materiaisSelecionados.remove("Eletrônicos");
                }else{
                    botaoEletronico.setBackgroundColor(cor_eletronicos);
                    materiaisSelecionados.add("Eletrônicos");
                }
            }
        });

        EditText etTitulo = view.findViewById(R.id.editText_titulo_anuncio_postagem_material);
        EditText etPeso = view.findViewById(R.id.editText_peso_material_postagem_material);
        EditText etDescricaoMaterial = view.findViewById(R.id.editText_descricao_postagem_material);
        Button botaoSolicitarColeta = view.findViewById(R.id.btn_solicitar_coleta_postagem_material);

        botaoSolicitarColeta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String titulo = etTitulo.getText().toString();
                String peso = etPeso.getText().toString();
                String descricaoMaterial = etDescricaoMaterial.getText().toString();

                if (TextUtils.isEmpty(titulo)){
                    Toast.makeText(getContext(),"Por favor, preencha o campo de título", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(peso)){
                    Toast.makeText(getContext(),"Por favor, preencha o campo de peso", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(descricaoMaterial)){
                    Toast.makeText(getContext(),"Por favor, preencha o campo de descricao do material", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(materiaisSelecionados.isEmpty()){
                    Toast.makeText(getContext(), "Por favor, selecione um material", Toast.LENGTH_SHORT).show();
                    return;
                }

                String currentPhotoPath = mainViewModel.getCurrentPhotoPath();
                if(currentPhotoPath.isEmpty()) {
                    Toast.makeText(getContext(), "O campo foto não foi preenchido", Toast.LENGTH_LONG).show();
                    view.setEnabled(true);
                    return;
                }
                view.setEnabled(false);

                try {
                    int h = (int) getResources().getDimension(R.dimen.img_height);
                    Util.scaleImage(currentPhotoPath, -1, 2*h);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    return;
                }

                LiveData<Boolean> resultLD = mainViewModel.postarMaterial(currentPhotoPath, titulo, Double.valueOf(peso), descricaoMaterial, materiaisSelecionados);
                resultLD.observe(getActivity(), new Observer<Boolean>() {
                    @Override
                    public void onChanged(Boolean aBoolean) {
                        // aBoolean contém o resultado do cadastro do produto. Se aBoolean for true, significa
                        // que o cadastro do produto foi feito corretamente. Indicamos isso ao usuário
                        // através de uma mensagem do tipo toast e finalizamos a Activity. Quando
                        // finalizamos a Activity, voltamos para a tela home, que mostra a lista de
                        // produtos.
                        if(aBoolean == true) {
                            view.setEnabled(true);
                            imvFotoMaterial.setImageDrawable(null);
                            etTitulo.setText("");
                            etPeso.setText("");
                            etDescricaoMaterial.setText("");
                            materiaisSelecionados.clear();
                            botaoEletronico.setBackgroundColor(originalColor);
                            botaoMadeira.setBackgroundColor(originalColor);
                            botaoHospitalar.setBackgroundColor(originalColor);
                            botaoOrganico.setBackgroundColor(originalColor);
                            botaoMetal.setBackgroundColor(originalColor);
                            botaoPapel.setBackgroundColor(originalColor);
                            botaoPlastico.setBackgroundColor(originalColor);
                            botaoVidro.setBackgroundColor(originalColor);
                            Toast.makeText(getActivity(), "Material adicionado com sucesso", Toast.LENGTH_LONG).show();
                            // indica que a Activity terminou com resultado positivo e a finaliza
                            getActivity().setResult(RESULT_OK);

                        }
                        else {
                            // Se o cadastro não deu certo, apenas continuamos na tela de cadastro e
                            // indicamos com uma mensagem ao usuário que o cadastro não deu certo.
                            // Reabilitamos também o botão de adicionar, para permitir que o usuário
                            // tente realizar uma nova adição de produto.
                            view.setEnabled(true);
                            Toast.makeText(getActivity(), "Ocorreu um erro ao adicionar o material", Toast.LENGTH_LONG).show();

                        }
                    }
                });

            }
        });
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
                ImageView imvPhoto = view.findViewById(R.id.imv_foto_material_postagem_material);

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