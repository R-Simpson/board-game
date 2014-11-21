package scratch;

import java.awt.Polygon;
import java.io.*;

public class scratchPad {

	public static void main (String args[])  throws IOException, ClassNotFoundException
	{
        int[][] testCases = new int[][]{{437, 174}, {427, 112}, {452, 199}, {474, 160}, {397, 162}, {474, 114}, {999, 999}};

        Polygon p = new Polygon(
            new int[]{410,419,431,452,471,486,477,462,464,481,478,460,451,444,442,431,406,401,390,386,397,404,406},
            new int[]{118,102,95,91,105,117,128,121,134,140,185,228,237,237,222,211,187,170,170,154,144,137,129},
            23);

        for (int[] pt : testCases) System.out.println(pt[0] + " " + pt[1] + ": " + p.contains(pt[0], pt[1]));
        System.out.println("-----");

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(p);
        oos.close();
        byte[] bytes = baos.toByteArray();

        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(bais);
        p = (Polygon)ois.readObject();
        ois.close();

        for (int[] pt : testCases) System.out.println(pt[0] + " " + pt[1] + ": " + p.contains(pt[0], pt[1]));
    }
}
