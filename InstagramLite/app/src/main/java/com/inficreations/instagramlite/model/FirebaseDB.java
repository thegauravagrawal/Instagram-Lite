package com.inficreations.instagramlite.model;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class FirebaseDB {
    public static void addUserToFirebase() {

        /*UserModel Model = new UserModel(
                user.getUsername(),
                user.getFull_name(),
                user.getProfile_pic_url(),
                user.getPk());*/
        UserModel model = new UserModel();
        model.setPkId(AppConstants.USER_PK_ID);
        model.setProfilePic(AppConstants.USER_PROFILE_PIC);
        model.setUsername(AppConstants.USER_USERNAME);
        model.setFullName(AppConstants.USER_FULL_NAME);


/*
        Map<String ,Object> data=new HashMap<>();
        data.put("username",Model.getFullName());
        data.put("fullname",Model.getFullName());
*/
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference collectionReference = db.collection("userTable");
        DocumentReference documentReference =
                collectionReference.document(String.valueOf(model.getPkId()));

        documentReference.set(model)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        System.out.println("addUserToFirebase success");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        e.printStackTrace();
                        System.out.println("addUserToFirebase failed " + e.getMessage());
                    }
                });

/*
        documentReference.get().addOnCompleteListener(task -> {

            if (task.isSuccessful()) {
                DocumentSnapshot document = task.getResult();
                if (document != null) {
                    UserModel model1 = (UserModel) document.getData();
                }
            }
        });
*/
    }
}
