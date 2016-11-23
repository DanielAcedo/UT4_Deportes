package com.danielacedo.deportes;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * Created by Daniel on 23/11/16.
 */


public class FilterDialog extends DialogFragment {

    public interface FilterDialogCallback{
        void dismiss(String s);
    }

    private EditText edt_filterCharacter;
    private FilterDialogCallback callback;

    public void onDismissal(final FilterDialogCallback callback) {
        this.callback = callback;

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.filtersport_dialog_layout, null);
        edt_filterCharacter = (EditText)v.findViewById(R.id.edt_filterDialog_character);

        builder.setView(v)
                .setPositiveButton(getResources().getString(R.string.filterDialog_positiveButton), new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id){
                        dialog.dismiss();
                        if(edt_filterCharacter!=null){
                                callback.dismiss(edt_filterCharacter.getText().toString());
                        }else{
                            Log.e("errorsito", "Esto es nulo macho");
                        }
                    }
                });


        return builder.create();
    }


}
