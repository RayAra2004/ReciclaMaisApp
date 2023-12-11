package carlos.dara.kaua.raynan.reciclamais.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.paging.PagingDataAdapter;
import androidx.recyclerview.widget.DiffUtil;

import carlos.dara.kaua.raynan.reciclamais.R;
import carlos.dara.kaua.raynan.reciclamais.activity.CadastroActivity;
import carlos.dara.kaua.raynan.reciclamais.activity.SeusMateriaisActivity;
import carlos.dara.kaua.raynan.reciclamais.entities.Comentario;
import carlos.dara.kaua.raynan.reciclamais.entities.Material;
import carlos.dara.kaua.raynan.reciclamais.util.ImageCache;
import carlos.dara.kaua.raynan.reciclamais.viewModel.SeusMateriaisViewModel;

public class MyAdapterMaterial extends PagingDataAdapter<Material, MyViewHolder> {
    SeusMateriaisActivity seusMateriaisActivity;
    SeusMateriaisViewModel seusMateriaisViewModel;
    private AlertDialog dialog;

    public MyAdapterMaterial(SeusMateriaisActivity seusMateriaisActivity, @NonNull DiffUtil.ItemCallback<Material> diffCallback){
        super(diffCallback);
        this.seusMateriaisActivity = seusMateriaisActivity;
    }

    /**
     * Cria os elementos de UI referente a um item da lista
     */
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.component_material, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(v);
        return viewHolder;
    }

    /**
     * Preenche um item da lista
     */
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Material material = this.getItem(position);

        // preenche o campo de nome
        TextView tvEstadoList = holder.itemView.findViewById(R.id.tv_estado_componete_material);
        tvEstadoList.setText(material.estado);

        // preenche o campo de foto
        int w = (int) seusMateriaisActivity.getResources().getDimension(R.dimen.thumb_width);
        int h = (int) seusMateriaisActivity.getResources().getDimension(R.dimen.thumb_height);
        ImageView imvMaterialThumb = holder.itemView.findViewById(R.id.imv_foto_material_material);
        // somente agora o a imagem é obtida do servidor. Caso a imagem já esteja salva no cache da app,
        // não baixamos ela de novo
        ImageCache.loadImageUrlToImageView(seusMateriaisActivity, material.imageURL, imvMaterialThumb, w, h);

        /*Button btnExcluirMaterial = holder.itemView.findViewById(R.id.btn_excluir_postagem_material);
        btnExcluirMaterial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarDialogExclusao(holder.itemView.getContext(), material.id);
            }
        });*/
    }

    private void mostrarDialogExclusao(Context context, Integer id){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Confirmação");
        builder.setMessage("Você tem certeza que deseja excluir?");

        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                realizarExclusao(id);
            }
        });

        builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Ação a ser realizada se o usuário clicar em "Não" ou fechar o diálogo
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void realizarExclusao(Integer id){
        // O ViewModel possui o método register, que envia as informações para o servidor web.
        // O servidor web recebe as infos e cadastra um novo usuário. Se o usuário foi cadastrado
        // com sucesso, a app recebe o valor true. Se não o servidor retorna o valor false.
        //
        // O método de register retorna um LiveData, que na prática é um container que avisa
        // quando o resultado do servidor chegou.
        LiveData<Boolean> resultLD = seusMateriaisViewModel.deleteMaterial(id);

        // Aqui nós observamos o LiveData. Quando o servidor responder, o resultado indicando
        // se o cadastro deu certo ou não será guardado dentro do LiveData. Neste momento o
        // LiveData avisa que o resultado chegou chamando o método onChanged abaixo.
        resultLD.observe(seusMateriaisActivity, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                // aBoolean contém o resultado do cadastro. Se aBoolean for true, significa
                // que o cadastro do usuário foi feito corretamente. Indicamos isso ao usuário
                // através de uma mensagem do tipo toast e finalizamos a Activity. Quando
                // finalizamos a Activity, voltamos para a tela de login.
                if(aBoolean) {
                    Toast.makeText(seusMateriaisActivity, "Material deletado com sucesso", Toast.LENGTH_LONG).show();
                    dialog.dismiss();
                }
                else {
                    Toast.makeText(seusMateriaisActivity, "Erro ao deletar material", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
