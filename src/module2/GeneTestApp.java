/*
* Third assignment of week 2 from the coursera "Java Programming: Solving Problems with Software" course.
* */

package module2;

import edu.duke.FileResource;
import edu.duke.StorageResource;

public class GeneTestApp
{
    public static void
    main(String argv[])
    {
        FileResource file = new FileResource();
        Gene test = new Gene(file.asString(), new StorageResource());

        test.FindAllGenes();
        test.Print(60, 0.35f);
        System.out.println("Longest gene has " + test.LongestGene() + " characters");

        int num = test.CodonCount(file.asString(), "CTG");
        System.out.println("The codon \"CTG\" appers " + num + " times in the gene");

        test.PrintAll();
    }
}
