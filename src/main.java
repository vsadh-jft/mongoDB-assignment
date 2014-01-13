import com.mongodb.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.prefs.Preferences;

public class main {
    public static void main(String[] args) throws IOException {
        MongoClient mongoClient = new MongoClient( "localhost" );
        DB db = mongoClient.getDB( "students" );
        DBCollection coll = db.getCollection("students");
        int yn= 'Y';

        System.out.println("Student Database");
        do {       System.out.println("Enter 1 for insert. 2 for delete. 3 for update. 4 to list all students ");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
int option = Integer.parseInt(br.readLine());
        int choice;



        switch(option)
        {
            case 1:
do{
    System.out.println("Enter the ID");
    String id = br.readLine();
                System.out.println("Enter the name");
    String name = br.readLine();
                System.out.println("Enter the Enrollment Number");
    String enroll = br.readLine();
    System.out.println("Enter the Course");
    String course = br.readLine();
    BasicDBObject doc = new BasicDBObject("ID",id).append("name", name).
            append("enrollment" ,enroll).append("course",course);


    coll.insert(doc);
                System.out.println("Enter 99 to enter more values");
    choice= Integer.parseInt(br.readLine());


}while(choice==99);
                break;

            case 2:
                System.out.println("Enter the ID of the document you want to delete");
               String delid = br.readLine();
                BasicDBObject query = new BasicDBObject();
                query.put("ID", delid);
                coll.remove(query);
break;

            case 3:
                System.out.println("Enter the ID OF student whose name you want to update");
                String updateid = br.readLine();
              DBObject obj = coll.findOne(new BasicDBObject("ID",updateid));


                System.out.println("Enter new name");
                String name=br.readLine();
                System.out.println("Enter new course");
                String course=br.readLine();


                BasicDBObject newobj = new BasicDBObject().append("$set",
                        new BasicDBObject().append("name", name).append("course", course));
                coll.update(obj, newobj);
break;

            case 4:
                DBCursor cursor = coll.find();
                try {
                    while(cursor.hasNext()) {
                        System.out.println(cursor.next());
                    }
                } finally {
                    cursor.close();
                }



        }


    System.out.println("Enter Y to continue");
    yn = br.read();

}while(yn=='Y');
}


}