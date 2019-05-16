package com.spaden.shoppinglista;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class DialogNewItem extends DialogFragment {


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        View dialogView = inflater.inflate(R.layout.dialog_new_item, null);

        final TextView textTitle = (TextView) dialogView.findViewById(R.id.textViewTitle);
        final EditText editQty = (EditText) dialogView.findViewById(R.id.editTextQty);
        final EditText editToBuy = (EditText) dialogView.findViewById(R.id.editTextToBuy);
        Button btnCancel = (Button) dialogView.findViewById(R.id.buttonCancel);
        Button btnOK = (Button) dialogView.findViewById(R.id.buttonOK);

        builder.setView(dialogView).setMessage("Lägg till en artikel i listan");
        btnCancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                dismiss();
            }
        });


        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Item newItem = new Item();
                newItem.setToBuy(editToBuy.getText().toString());
                newItem.setQty(editQty.getText().toString());


                MainActivity callingActivity = (MainActivity) getActivity();

                callingActivity.createNewItem(newItem);

                dismiss();
            }
        });
        return builder.create();
    }
}

