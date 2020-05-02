package com.example.materialdesigndemo.fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.PopupMenu;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;

import com.example.materialdesigndemo.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.text.DateFormat;
import java.util.Calendar;

public class DialogsFragment extends Fragment implements View.OnClickListener {
    private Button btn_dialog_1, btn_dialog_2, btn_dialog_3, btn_dialog_4, btn_dialog_5,
            btn_dialog_6, btn_dialog_7, btn_dialog_8, btn_dialog_9, btn_dialog_10, btn_dialog_11;
    private Calendar calendar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        NestedScrollView nestedScrollView = (NestedScrollView) inflater.inflate(R.layout.fragment_dialogs, container, false);

        btn_dialog_1 = nestedScrollView.findViewById(R.id.btn_dialog_1);
        btn_dialog_2 = nestedScrollView.findViewById(R.id.btn_dialog_2);
        btn_dialog_3 = nestedScrollView.findViewById(R.id.btn_dialog_3);
        btn_dialog_4 = nestedScrollView.findViewById(R.id.btn_dialog_4);
        btn_dialog_5 = nestedScrollView.findViewById(R.id.btn_dialog_5);
        btn_dialog_6 = nestedScrollView.findViewById(R.id.btn_dialog_6);
        btn_dialog_7 = nestedScrollView.findViewById(R.id.btn_dialog_7);
        btn_dialog_8 = nestedScrollView.findViewById(R.id.btn_dialog_8);
        btn_dialog_9 = nestedScrollView.findViewById(R.id.btn_dialog_9);
        btn_dialog_10 = nestedScrollView.findViewById(R.id.btn_dialog_10);
        btn_dialog_11 = nestedScrollView.findViewById(R.id.btn_dialog_11);

        return nestedScrollView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        calendar = Calendar.getInstance();

        btn_dialog_1.setOnClickListener(this);
        btn_dialog_2.setOnClickListener(this);
        btn_dialog_3.setOnClickListener(this);
        btn_dialog_4.setOnClickListener(this);
        btn_dialog_5.setOnClickListener(this);
        btn_dialog_6.setOnClickListener(this);
        btn_dialog_7.setOnClickListener(this);
        btn_dialog_8.setOnClickListener(this);
        btn_dialog_9.setOnClickListener(this);
        btn_dialog_10.setOnClickListener(this);
        btn_dialog_11.setOnClickListener(this);
    }

    // on click listener
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_dialog_1:
                //simple dialog with positive button
                new AlertDialog.Builder(getContext())
                        .setMessage(getString(R.string.main_dialog_simple_title))
                        .setPositiveButton(getString(R.string.dialog_ok), null)
                        .show();
                break;

            case R.id.btn_dialog_2:
                // simple dialog with positive, negative, neutral button
                new AlertDialog.Builder(getContext())
                        .setTitle(getString(R.string.main_dialog_simple_title))
                        .setMessage(getString(R.string.main_dialog_simple_message))
                        .setPositiveButton(getString(R.string.dialog_ok), null)
                        .setNegativeButton(getString(R.string.dialog_cancel), null)
                        .setNeutralButton(getString(R.string.dialog_neutral), null)
                        .show();
                break;

            case R.id.btn_dialog_3:
                // dialog with radio button
                String[] singleChoiceItems = getResources().getStringArray(R.array.dialog_choice_array);
                int itemSelected = 0;
                new AlertDialog.Builder(getContext())
                        .setTitle(getString(R.string.main_dialog_single_choice))
                        .setSingleChoiceItems(singleChoiceItems, itemSelected, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        })
                        .setNegativeButton(getString(R.string.dialog_cancel), null)
                        .show();
                break;

            case R.id.btn_dialog_4:
                // dialog with checkbox
                String[] multiChoiceItems = getResources().getStringArray(R.array.dialog_choice_array);
                boolean[] checkedItems = {true, false, false, false, false};
                new AlertDialog.Builder(getContext())
                        .setTitle(getString(R.string.main_dialog_multi_choice))
                        .setMultiChoiceItems(multiChoiceItems, checkedItems, null)
                        .setPositiveButton(getString(R.string.dialog_ok), null)
                        .setNegativeButton(getString(R.string.dialog_cancel), null)
                        .show();
                break;

            case R.id.btn_dialog_5:
                // simple progress dialog
                ProgressDialog progressDialog = new ProgressDialog(getContext());
                progressDialog.setMessage(getString(R.string.main_dialog_progress_title));
                progressDialog.show();
                break;

            case R.id.btn_dialog_6:
                // horizontal progress dialog
                final ProgressDialog horizontalProgressDialog = new ProgressDialog(getContext());
                horizontalProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                horizontalProgressDialog.setMessage(getString(R.string.main_dialog_progress_title));
                horizontalProgressDialog.setCancelable(false);
                horizontalProgressDialog.setMax(100);
                horizontalProgressDialog.show();

                new Thread(new Runnable() {
                    int progress = 0;

                    @Override
                    public void run() {
                        while (progress <= 100) {
                            horizontalProgressDialog.setProgress(progress);
                            if (progress == 100) {
                                horizontalProgressDialog.dismiss();
                            }
                            try {
                                Thread.sleep(35);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            progress++;
                        }
                    }
                }).start();
                break;

            case R.id.btn_dialog_7:
                // date picker dialog
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        calendar.set(Calendar.YEAR, year);
                        calendar.set(Calendar.MONTH, monthOfYear);
                        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        String date = DateFormat.getDateInstance(DateFormat.MEDIUM).format(calendar.getTime());
                        btn_dialog_7.setText(date);
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
                break;

            case R.id.btn_dialog_8:
                // time picker dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        calendar.set(Calendar.HOUR_OF_DAY, i);
                        calendar.set(Calendar.MINUTE, i1);
                        String time = DateFormat.getTimeInstance(DateFormat.SHORT).format(calendar.getTime());
                        btn_dialog_8.setText(time);
                    }
                }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);
                timePickerDialog.show();
                break;

            case R.id.btn_dialog_9:
                //bottom sheet dialog
                final BottomSheetDialog mBottomSheetDialog = new BottomSheetDialog(getContext());
                View dialogView = getActivity().getLayoutInflater().inflate(R.layout.dialog_bottom_sheet, null);
                Button btn_dialog_bottom_sheet_ok = dialogView.findViewById(R.id.btn_dialog_bottom_sheet_ok);
                Button btn_dialog_bottom_sheet_cancel = dialogView.findViewById(R.id.btn_dialog_bottom_sheet_cancel);
                mBottomSheetDialog.setContentView(dialogView);

                btn_dialog_bottom_sheet_ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mBottomSheetDialog.dismiss();
                    }
                });
                btn_dialog_bottom_sheet_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mBottomSheetDialog.dismiss();
                    }
                });
                mBottomSheetDialog.show();
                break;

            case R.id.btn_dialog_10:
                // full screen dialog
                final Dialog fullscreenDialog = new Dialog(getContext(), R.style.DialogFullscreen);
                fullscreenDialog.setContentView(R.layout.dialog_fullscreen);
                ImageView img_dialog_fullscreen_close = fullscreenDialog.findViewById(R.id.img_dialog_fullscreen_close);
                img_dialog_fullscreen_close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        fullscreenDialog.dismiss();
                    }
                });
                fullscreenDialog.show();
                break;

            case R.id.btn_dialog_11:
                // pop up menu
                PopupMenu popupMenu = new PopupMenu(getContext(), btn_dialog_11);
                popupMenu.getMenuInflater().inflate(R.menu.popup_menu_main, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        return false;
                    }
                });
                popupMenu.show();
                break;
        }
    }
}
