package com.example.sasitha.bocmobileapp;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class OwnAccountTransactionSuccessFragment extends Fragment {

    Fragment fragment = null;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    TextView id, fromAcc, toAcc, amount, date, txtTime, description, saving;
    TextView view1, view2, view3, view4,view5, view6, view7;
    Button home, save;

    String path;

    String signature_pdf_;

    Button btn, btnScroll;
    LinearLayout llPdf;
    Bitmap bitmap;

    String name = "pdffromlayout";

    ProgressBar progressBar;

    ImageView backImage;
    ProgressBar progressBarTrans;

    private int progressStatus = 0, progressStatus2 = 0, progressStatus3 = 0;
    private Handler handler = new Handler();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.own_account_transfer_success, null);

    }


    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle bundle2 = getArguments();

        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

//        TextView view1 = (TextView) view.findViewById(R.id.textView23);
//        TextView view2 = (TextView) view.findViewById(R.id.textView9);
//        TextView view3 = (TextView) view.findViewById(R.id.textView11);
//        TextView view4 = (TextView) view.findViewById(R.id.textView14);
//        TextView view5 = (TextView) view.findViewById(R.id.textView15);
//        TextView view6 = (TextView) view.findViewById(R.id.textView16);
//        TextView view7 = (TextView) view.findViewById(R.id.textView17);
//        view1.setVisibility(View.VISIBLE);
//        view2.setVisibility(View.VISIBLE);
//        view3.setVisibility(View.VISIBLE);
//        view4.setVisibility(View.VISIBLE);
//        view5.setVisibility(View.VISIBLE);
//        view6.setVisibility(View.VISIBLE);
//        view7.setVisibility(View.VISIBLE);
//        id.setVisibility(View.VISIBLE);
//        fromAcc.setVisibility(View.VISIBLE);
//        toAcc.setVisibility(View.VISIBLE);
//        id.setVisibility(View.VISIBLE);
//        id.setVisibility(View.VISIBLE);
//        id.setVisibility(View.VISIBLE);


        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        saving = (TextView) view.findViewById(R.id.textViewSaving);
        progressBar.setVisibility(View.INVISIBLE);
        saving.setVisibility(View.INVISIBLE);

        id = (TextView) view.findViewById(R.id.textViewID);
        fromAcc = (TextView) view.findViewById(R.id.textViewFromACC);
        toAcc = (TextView) view.findViewById(R.id.textViewToACC);
        amount = (TextView) view.findViewById(R.id.textAmount);
        date = (TextView) view.findViewById(R.id.textViewDateTime);
        description = (TextView) view.findViewById(R.id.textViewDesc);

        home = (Button) view.findViewById(R.id.btnTransSuccHome);
        save = (Button) view.findViewById(R.id.buttonSavePDF);

        llPdf = (LinearLayout) view.findViewById(R.id.constraintLayoutPrint);

        final AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
        final View customLayout = getLayoutInflater().inflate(R.layout.alert_dialog_layout, null);
        alert.setView(customLayout);
        TextView amountAlert = (TextView) customLayout.findViewById(R.id.alertAmount);
        String amnt = bundle2.getString("amount");

        String text = "Amount : Rs ";
        amountAlert.setText(text+amnt);

        final AlertDialog alertDialog = alert.create();
        Button ok = (Button) customLayout.findViewById(R.id.alertOkBtn);
        alertDialog.show();
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                alertDialog.dismiss();
            }
        });

        if(bundle2 != null){

            id.setText(bundle2.getString("id"));
            fromAcc.setText(bundle2.getString("fromAcc"));
            toAcc.setText(bundle2.getString("toAcc"));
            amount.setText("Rs "+bundle2.getString("amount"));
            date.setText(bundle2.getString("date"));
            description.setText(bundle2.getString("description"));

        }



        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragmentD = new DashboardFragment();
                fragmentTransaction.replace(R.id.screen_area, fragmentD);
                fragmentTransaction.commit();
                getActivity().setTitle("Home");
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                name = id.getText().toString();
                Log.d("size"," "+llPdf.getWidth() +"  "+llPdf.getWidth());
                bitmap = loadBitmapFromView(llPdf, llPdf.getWidth(), llPdf.getHeight());


                progressBar.setVisibility(View.VISIBLE);
                saving.setVisibility(View.VISIBLE);

                if (progressStatus == 100) {
                    progressStatus = 0;
                }

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (progressStatus < 100) {
                            // Update the progress status
                            progressStatus += 1;

                            // Try to sleep the thread for 20 milliseconds
                            try {
                                Thread.sleep(20);  //3 seconds
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                            // Update the progress bar
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    progressBar.setProgress(progressStatus);
                                    // Show the progress on TextView
                                    //tv.setText(progressStatus + "/100");
                                    if(progressBar.getProgress() == 100){
                                        Toast.makeText(getContext(), "PDF is created!!!", Toast.LENGTH_SHORT).show();
                                        progressBar.setVisibility(View.GONE);
                                        saving.setVisibility(View.GONE);
                                        if(createPdf()){
                                            openGeneratedPDF();
                                        }
                                    }


                                }
                            });
                        }
                    }
                }).start(); // Start the operation

            }
        });


    }

    public static Bitmap loadBitmapFromView(View v, int width, int height) {
        Bitmap b = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(b);
        v.draw(c);

        return b;
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private boolean createPdf(){

        WindowManager wm = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
        //  Display display = wm.getDefaultDisplay();
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        float hight = displaymetrics.heightPixels ;
        float width = displaymetrics.widthPixels ;

        int convertHighet = (int) hight, convertWidth = (int) width;

//        Resources mResources = getResources();
//        Bitmap bitmap = BitmapFactory.decodeResource(mResources, R.drawable.screenshot);

        PdfDocument document = new PdfDocument();
        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(convertWidth, convertHighet, 1).create();
        PdfDocument.Page page = document.startPage(pageInfo);

        Canvas canvas = page.getCanvas();

        Paint paint = new Paint();
        canvas.drawPaint(paint);

        bitmap = Bitmap.createScaledBitmap(bitmap, convertWidth, convertHighet, true);

        paint.setColor(Color.BLUE);
        canvas.drawBitmap(bitmap, 0, 0 , null);
        document.finishPage(page);

        // write the document content
        String targetPdf = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Transaction_"+id.getText().toString()+".pdf";
        File filePath;
        filePath = new File(targetPdf);
        try {
            document.writeTo(new FileOutputStream(filePath));

        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(getContext(), "Something wrong: " + e.toString(), Toast.LENGTH_LONG).show();
        }

        // close the document
        document.close();

        return  true;
    }



    private void openGeneratedPDF(){

        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/Transaction_"+id.getText().toString()+".pdf");
        if (file.exists())
        {


            try
            {
                //Intent intent=new Intent(Intent.ACTION_VIEW);
                //Uri uri = Uri.fromFile(file);
                //intent.setDataAndType(uri, "application/pdf");
                //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                //startActivity(intent);


                Uri uri;
                if (Build.VERSION.SDK_INT < 24) {
                    uri = Uri.fromFile(file);
                } else {
                    uri = Uri.parse(file.getPath());
                }
                Intent viewFile = new Intent(Intent.ACTION_VIEW);

                viewFile.setDataAndType(uri, "application/pdf");
                startActivity(viewFile);
            }
            catch(ActivityNotFoundException e)
            {
                Toast.makeText(getContext(), "No Application available to view pdf", Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(getContext(), "No File!", Toast.LENGTH_LONG).show();
        }
    }




}
