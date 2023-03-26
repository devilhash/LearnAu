package com.app.learnau;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.app.learnau.CSE.Fragments.FileInfo;
import com.app.learnau.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.google.firebase.storage.internal.StorageReferenceUri;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.util.HashMap;


public class UploadActivity extends AppCompatActivity {
    ImageView filelogo,browseimg,cancel,uploading;
    EditText filename;
    Uri filepath;


     StorageReference storageReference;
    DatabaseReference databaseReference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        databaseReference = FirebaseDatabase.getInstance().getReference("My_Documents").child("Ai syllabus");




        storageReference = FirebaseStorage.getInstance().getReference();
        filelogo = findViewById(R.id.filelogo);
        browseimg = findViewById(R.id.imagebrowse);
        cancel = findViewById(R.id.cancelfile);
        uploading = findViewById(R.id.imageupload);
        filename = findViewById(R.id.filetitle);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                filelogo.setVisibility(View.INVISIBLE);
                cancel.setVisibility(View.INVISIBLE);
                browseimg.setVisibility(View.VISIBLE);
            }
        });

        browseimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dexter.withContext(getApplicationContext()).withPermission(Manifest.permission.READ_EXTERNAL_STORAGE).withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                        Intent intent = new Intent();
                        intent.setType("application/pdf");
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        startActivityForResult(Intent.createChooser(intent,"select pdf"),101);

                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                        permissionToken.continuePermissionRequest();
                    }
                }).check();
            }
        });
        uploading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                processUpload(filepath);
            }
        });
    }

    private void processUpload(Uri filepath) {
        ProgressDialog pd = new ProgressDialog(this);
        pd.setTitle("File uploading");
        pd.show();
          StorageReference reference = storageReference.child("uploads"+System.currentTimeMillis()+".pdf");
          reference.putFile(filepath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
              @Override
              public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                  reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                      @Override
                      public void onSuccess(Uri uri) {
                          FileInfo ob = new FileInfo(filename.getText().toString(),uri.toString(),0,0);
                          databaseReference.child(databaseReference.push().getKey()).setValue(ob);
                          filelogo.setVisibility(View.INVISIBLE);
                          cancel.setVisibility(View.INVISIBLE);
                          browseimg.setVisibility(View.VISIBLE);filename.setText("");
                          pd.dismiss();
                      }
                  });
              }
          }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
              @Override
              public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                  float percet = (100*  snapshot.getBytesTransferred()/snapshot.getTotalByteCount());
                  pd.setMessage("uploading : "+(int)percet+"%");
              }
          });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==101&&resultCode==RESULT_OK){
            filepath = data.getData();
            filelogo.setVisibility(View.VISIBLE);
            browseimg.setVisibility(View.INVISIBLE);
            cancel.setVisibility(View.VISIBLE);
        }
    }
     }