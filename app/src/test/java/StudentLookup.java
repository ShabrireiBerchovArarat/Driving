import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.paz.razabi.driving.Globals;
import com.paz.razabi.driving.Student;

public abstract class StudentLookup {
    public static void findStudent(Student s, String path){
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child(path).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot childSnapshot : snapshot.getChildren()) {
                    Student student = childSnapshot.getValue(Student.class);

                    if (student.getTeacher().equals(Globals.currTeacher)){
                        if(student.getName().equals(s.getName()))
                            student = s;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
