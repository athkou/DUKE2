/*
 * First assignment of week 2 from the coursera "Java Programming: Solving Problems with Software" course.
  */

package module2;

public class TagFinderTestApp
{
    public static void
    main(String argv[])
    {
        TagFinder tmp = new TagFinder("AAATGCCCTAACTAGATTGAAACC");
        System.out.println("DNA: " + tmp.DNA());

        String stop_codon = tmp.StopCodon(tmp.DNA());
        System.out.println("Stop codon: " + stop_codon);

        String result = tmp.ProteinFinder(TagFinder.START_CODON, stop_codon);
        int begin;
        int end  = tmp.DNA().indexOf(stop_codon);

        while(end != tmp.Size() - 1)
        {
            begin = end + 1;
            stop_codon = tmp.StopCodon(tmp.DNA().substring(begin));

            result = tmp.ProteinFinder(TagFinder.START_CODON, stop_codon);
            if(!result.isEmpty()) break;

            end = tmp.DNA().indexOf(stop_codon, begin);
        }

        System.out.println("Result: " + result);
    }
}
