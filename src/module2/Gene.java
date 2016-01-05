/*
* Third assignment of week 2 from the coursera "Java Programming: Solving Problems with Software" course.
* */

package module2;

import edu.duke.StorageResource;

public class Gene
{
    public static final int BEGIN         = 0;
    public static final int END           = -1;
    public static final int INVALID_CODON = -2;
    public static final int LENGTH        = 3;

    public Gene(String dna, StorageResource genes_pool)
    {
        dna_        = dna.toLowerCase();
        genes_pool_ = genes_pool;
    }

    public void FindAllGenes()
    {
        int start = BEGIN;
        while(true)
        {
            int location = dna_.indexOf("atg", start);
            if(location == END) break;

            int end = FindStopCodon(location);
            if(end == INVALID_CODON) start += LENGTH;
            else
            {
                start = end + LENGTH;

                String temp = dna_.substring(location, end + LENGTH);
                genes_pool_.add(temp);
            }
        }

    }

    public int FindStopCodon(int index)
    {
        int stop1 = FindStopCodonImpl("tga", index);
        int stop2 = FindStopCodonImpl("taa", index);
        int stop3 = FindStopCodonImpl("tag", index);

        if(stop1 == dna_.length() &&
           stop2 == dna_.length() &&
           stop3 == dna_.length())

            return  INVALID_CODON;

        return Math.min(stop1, Math.min(stop2, stop3));
    }

    public void PrintAll() { System.out.println("\nNumber of genes: " + genes_pool_.size()); }

    public void Print(int num_of_characters)
    {
        int count = 0;
        System.out.println("Genes with more characters than " + num_of_characters);
        for(String gene : genes_pool_.data())
        {
            if(gene.length() > num_of_characters)
            {
                System.out.println(gene);
                ++count;
            }
        }
        System.out.println("\nNumber of genes with length greater than " + num_of_characters + ": " + count);
    }

    public void Print(int num_of_characters, float cg_ratio)
    {
        int count = 0;

        Print(num_of_characters);
        System.out.println("\n\nGenes with more CG Ratio than " + cg_ratio);
        for(String gene : genes_pool_.data())
        {
            if(CGRatio(gene) > cg_ratio)
            {
                System.out.println(gene);
                ++count;
            }
        }
        System.out.println("\nNumber of genes with CG Ration greater than " +cg_ratio + ": " + count);
    }

    int LongestGene()
    {
        int longest = 0;
        for(String gene : genes_pool_.data())
        {
            if(longest < gene.length()) longest = gene.length();
        }

        return longest;
    }

    public float CGRatio(String gene)
    {
        int count = 0;
        int length = gene.length();

        for(char ch : gene.toCharArray())
        {
            if(ch == 'c' || ch == 'g') ++count;
        }


        return (float) count / (float) length;
    }

    public int CodonCount(String gene, String codon)
    {
        int count = 0;
        int start = BEGIN;
        int location;
        while(true)
        {
            location = gene.indexOf(codon, start);

            if(location == END) break;
            else
            {
                start = location + LENGTH;
                ++count;
            }
        }
        return count;
    }

    private int FindStopCodonImpl(String stop_codon, int index)
    {
        int number = dna_.indexOf(stop_codon, index + LENGTH);

        if(number == END || ((number - index) % 3 != 0)) return dna_.length();
        else                                             return number;
    }

    private String dna_;
    private StorageResource genes_pool_;
}
