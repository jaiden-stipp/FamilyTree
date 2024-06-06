import java.io.*;

public class FamilyTreeSerializer {
    public static void save(FamilyTree familyTree, String filePath) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filePath))) {
            out.writeObject(familyTree);
        }
    }

    public static FamilyTree load(String filePath) throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filePath))) {
            return (FamilyTree) in.readObject();
        }
    }
}

