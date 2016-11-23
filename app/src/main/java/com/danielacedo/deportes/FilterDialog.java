package com.danielacedo.deportes;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

/**
 * Created by Daniel on 23/11/16.
 */

/**
 * NO VA
 */
public class FilterDialog extends DialogFragment {

    public interface FilterDialogCallback{
        void dismiss(char c);
    }

    private EditText edt_filterCharacter;
    private FilterDialogCallback callback;

    public void onDismiss(final FilterDialogCallback callback) {

        this.callback = callback;

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.filtersport_dialog_layout, null);
        edt_filterCharacter = (EditText)v.findViewById(R.id.edt_filterDialog_character);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setView(inflater.inflate(R.layout.filtersport_dialog_layout, null))
                .setPositiveButton(getResources().getString(R.string.filterDialog_positiveButton), new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id){
                        if(edt_filterCharacter!=null){
                            callback.dismiss(edt_filterCharacter.getText().toString().charAt(0));
                        }

                        dialog.dismiss();
                    }
                });

        return builder.create();
    }


}
