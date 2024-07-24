package com.nutapos.crud.Utils;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.nutapos.crud.Model.Player;
import com.nutapos.crud.R;

public class UpdatePlayerDialog extends AppCompatDialogFragment {

    private EditText mName;
    private EditText mAge;
    private EditText mPosition;
    private Button mSaveBtn;
    private UpdatePlayerDialog.UpdatePlayerListener mListener;
    private Player player;

    public void setPlayer(Player player) {
        this.player = player;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog,null);

        builder.setView(view);
        builder.setCancelable(true);
        builder.setTitle("Update Barang");

        mName = view.findViewById(R.id.et_name);
        mAge = view.findViewById(R.id.et_age);
        mPosition = view.findViewById(R.id.et_position);
        mSaveBtn = view.findViewById(R.id.btn_save);

        mName.setText(player.getName());
        mAge.setText(player.getAge());
        mPosition.setText(player.getPosition());

        mSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = mName.getText().toString();
                String age = mAge.getText().toString();
                String position = mPosition.getText().toString();

                if(name.isEmpty()||age.isEmpty()||position.isEmpty()) {
                    return;
                }
                else {
                    Player currentPlayer = new Player(name,position,age);
                    currentPlayer.setId(player.getId());
                    mListener.updateNewPlayer(currentPlayer);
                    dismiss();
                }
            }
        });

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (UpdatePlayerDialog.UpdatePlayerListener)context;
    }

    public interface UpdatePlayerListener{
        void updateNewPlayer(Player player);
    }
}
