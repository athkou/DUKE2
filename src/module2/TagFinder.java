/*
* First assignment of week 2 from the coursera "Java Programming: Solving Problems with Software" course.
* */

package module2;

public class TagFinder
{
    public static final String START_CODON = "atg";

    public TagFinder(String dna) { dna_ = dna.toLowerCase(); }

    public String ProteinFinder(String start_codon, String stop_codon)
    {
        if(start_codon.isEmpty() || stop_codon.isEmpty()) return "";

        int start = dna_.indexOf(start_codon);
        int stop  = dna_.indexOf(stop_codon, start + 3);

        if(start == -1 || stop == -1) return "";

       if((stop - start) % 3 == 0) return dna_.substring(start, stop + 3);
       else                        return "";
    }

    public String StopCodon(String dna)
    {
        if(dna.contains("tag")) return StopCodonImpl(dna, "tag");
        if(dna.contains("tga")) return StopCodonImpl(dna, "tga");
        if(dna.contains("taa")) return StopCodonImpl(dna, "taa");

        return "";
    }

    public String DNA() { return dna_;          }
    public int Size()   { return dna_.length(); }

    public void SetDNA(String dna)
    {
        if(!dna.isEmpty()) dna_ = dna.toLowerCase();
    }

    private String StopCodonImpl(String dna, String tag)
    {
        int begin = dna.indexOf(tag);
        return dna.substring(begin, begin + 3);
    }

    private String dna_;
}
