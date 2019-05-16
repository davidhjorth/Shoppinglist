package com.spaden.shoppinglista;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DialogShowItem extends DialogFragment {



    private Item mItem;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        View dialogView = inflater. inflate(R.layout.dialog_show_item, null);

        TextView textViewQty = (TextView) dialogView.findViewById(R.id.textViewQty);

        TextView textViewItem = (TextView) dialogView.findViewById(R.id.textViewItem);

        textViewItem.setText(mItem.getToBuy());
        textViewQty.setText(mItem.getQty());



        Button btnOK = (Button) dialogView.findViewById(R.id.button_show_ok);


        builder.setView(dialogView).setMessage("Your Item");

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });


        return builder.create();

    }
    public void sendItemSelected(Item itemSelected){


        mItem = itemSelected;

    }
}
